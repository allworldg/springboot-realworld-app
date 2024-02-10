package realworld.user.controller;

import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import realworld.user.LoginParam;
import realworld.user.UserRegister;
import realworld.user.service.UserService;
import realworld.user.LoginUser;
import realworld.user.User;
import realworld.utils.TokenService;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    private AuthenticationManager manager;

    private TokenService tokenService;

    public UserController(UserService userService, AuthenticationManager manager,
                          TokenService tokenService) {
        this.userService = userService;
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public LoginUser login(@Valid @RequestBody LoginParam loginParam) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginParam.getEmail(),
                        loginParam.getPassword());

        Authentication authenticate = manager.authenticate(token);
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        return loginUser;
    }

    @PostMapping()
    public ResponseEntity<LoginUser> register(@Valid @RequestBody UserRegister userRegister)
            throws Exception {
        User user = userService.addUser(userRegister);
        user = userService.findUserByUserId(user.getId());
        LoginUser loginUser = new LoginUser(user.getEmail(), "", user.getUsername(),
                tokenService.createTokenByUserId(user.getId())
                , user.getBio(), user.getImage());
        return ResponseEntity.status(HttpStatus.CREATED).body(loginUser);
    }
}
