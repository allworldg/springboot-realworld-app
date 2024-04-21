package realworld.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import realworld.exception.ResourceNotFoundException;
import realworld.user.LoginUser;
import realworld.user.Profile;
import realworld.user.repository.UserRepository;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private UserRepository userRepository;


    public Profile getProfile(String username, LoginUser user) {
        Long userId = Optional.ofNullable(user).map(LoginUser::getId).orElseGet(() -> null);
        return userRepository.getProfileByUserName(username, userId)
                             .orElseThrow(ResourceNotFoundException::new);
    }
}
