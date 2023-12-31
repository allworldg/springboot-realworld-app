package realworld;

import realworld.api.exception.ResourceNotFoundException;
import realworld.infrastructure.mybatis.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        throw new ResourceNotFoundException();
    }
}
