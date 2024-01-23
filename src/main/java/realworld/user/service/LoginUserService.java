package realworld.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import realworld.exception.InvalidEmailOrPasswordException;
import realworld.user.LoginUser;
import realworld.user.User;
import realworld.user.repository.UserRepository;

@Service
public class LoginUserService implements UserDetailsService {
    private UserRepository userRepository;

    public LoginUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws InvalidEmailOrPasswordException {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new InvalidEmailOrPasswordException());
        return createUserDetails(user);
    }

    public UserDetails createUserDetails(User user) {
        return new LoginUser(user.getEmail(), user.getPassword(), user.getUsername(),
                "123", user.getBio(),
                user.getImage());
    }
}
