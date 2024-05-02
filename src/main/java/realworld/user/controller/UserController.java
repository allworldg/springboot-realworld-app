package realworld.user.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import realworld.user.LoginParam;
import realworld.user.LoginUser;
import realworld.user.User;
import realworld.user.UserRegister;
import realworld.user.service.UserService;
import realworld.utils.TokenService;

@RestController
public class UserController {
    private final UserService userService;

    private final AuthenticationManager manager;

    private final TokenService tokenService;

    public UserController(UserService userService, AuthenticationManager manager,
                          TokenService tokenService) {
        this.userService = userService;
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping("/users/login")
    public LoginUser login(@Valid @RequestBody LoginParam loginParam) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginParam.getEmail(),
                        loginParam.getPassword());

        Authentication authenticate = manager.authenticate(token);
        return (LoginUser) authenticate.getPrincipal();
    }

    @PostMapping("/users")
    public ResponseEntity<LoginUser> register(@Valid @RequestBody UserRegister userRegister) {
        User user = userService.addUser(userRegister);
        user = userService.findUserByUserId(user.getId());
        LoginUser loginUser = new LoginUser(user.getId(), user.getEmail(), "", user.getUsername(),
                tokenService.createTokenByUserId(user.getId())
                , user.getBio(), user.getImage());
        return ResponseEntity.status(HttpStatus.CREATED).body(loginUser);
    }

    @GetMapping("/user")
    public LoginUser getCurrentUser(@AuthenticationPrincipal LoginUser loginUser) {
        return loginUser;
    }

    @PutMapping("/user")
    public LoginUser updateUser(@RequestBody LoginUser param,
                                @AuthenticationPrincipal LoginUser loginUser) {

        userService.updateUser(param, loginUser);
        User user = userService.findUserByUserId(loginUser.getId());
        loginUser.setByUser(user);
        return loginUser;
    }
}
