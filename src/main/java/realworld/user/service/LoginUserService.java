package realworld.user.service;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import realworld.common.HttpCommon;
import realworld.exception.InvalidEmailOrPasswordException;
import realworld.user.LoginUser;
import realworld.user.User;
import realworld.user.repository.UserRepository;
import realworld.utils.TokenService;

@Service
public class LoginUserService implements UserDetailsService {
    private UserRepository userRepository;
    private TokenService tokenService;

    public LoginUserService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws InvalidEmailOrPasswordException {
        User user = userRepository.findUserByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(HttpCommon.IS_INVALID));
        UserDetails userDetails = createUserDetails(user);
        return userDetails;
    }

    public UserDetails createUserDetails(User user) {
        String token = tokenService.createTokenByUserId(user.getId());
        return new LoginUser(user.getEmail(), user.getPassword(),
                user.getUsername(),
                token, user.getBio(),
                user.getImage());
    }
}
