package realworld.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class TokenService {
    @Value("${secret_key}")
    private String key;

    @Value("${expire_time}")
    private long expireTime;


    private StringRedisTemplate redisTemplate;


    public TokenService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String createTokenByUserId(Long userId) {
        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
        String token = Jwts.builder()
                           .issuedAt(new Date())
                           .subject(userId.toString())
                           .signWith(secretKey)
                           .compact();
        redisTemplate.opsForValue()
                     .set(token, Long.toString(userId), expireTime, TimeUnit.MINUTES);
        return token;
    }

    public Long getIdByToken(String token) {
        String id = redisTemplate.opsForValue().get(token);
        if (id == null) {
            return null;
        }
        return Long.valueOf(id);
    }

    public void updateExpiredTime(String token) {
        Long expire = redisTemplate.getExpire(token);
        if (expire > 0 && expire < TimeUnit.HOURS.toSeconds(24)) {
            redisTemplate.expire(token, expireTime, TimeUnit.MINUTES);
        }
    }

    public void deleteToken(String token) {
        redisTemplate.delete(token);
    }
}
