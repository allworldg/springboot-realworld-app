package realworld.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import realworld.application.TokenService;
import realworld.application.user.UserRegister;
import realworld.application.user.UserService;
import realworld.application.user.UserWithToken;
import realworld.core.user.User;

@RestController
public class UserApi {
    private UserService userService;

    private TokenService tokenService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register")
    public ResponseEntity<UserWithToken> register(@Valid @RequestBody UserRegister userRegister) throws Exception {
        User user = userService.addUser(userRegister);
        user = userService.findUserByUserId(user.getId());

        UserWithToken userWithToken = new UserWithToken(user.getEmail(), "", user.getUsername(), "", user.getImage());
        return ResponseEntity.status(HttpStatus.CREATED).body(userWithToken);
    }
}
