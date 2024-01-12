package realworld;

import org.springframework.beans.factory.annotation.Value;
import realworld.api.exception.ResourceNotFoundException;
import realworld.core.user.User;
import realworld.infrastructure.mybatis.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Value("${server.port}")
    private String image;

    @Test
    void contextLoads() {
        System.out.println(image);
    }
}
