package realworld;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import realworld.user.LoginParam;
import realworld.user.repository.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.invoke.VarHandle;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DemoApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Value("${server.port}")
    private String image;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;


    @Test
    void contextLoads() {
        LoginParam loginParam = new LoginParam();
        this.restTemplate.postForObject("http://localhost:" + port + "/api/login", loginParam, loginParam.getClass());
    }
}
