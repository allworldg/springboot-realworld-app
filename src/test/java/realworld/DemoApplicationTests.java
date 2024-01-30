package realworld;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import realworld.utils.TokenService;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DemoApplicationTests {

    @Value("${secret_key}")
    String secretKey;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    TokenService tokenService;

    @Test
    void test() {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        String s = Jwts.builder().issuedAt(new Date()).subject("1").signWith(key, Jwts.SIG.HS256).compact();
        System.out.println(s);
    }

    @Test
    void testTokenService() {
        tokenService.createTokenByUserId(1L);
    }


}
