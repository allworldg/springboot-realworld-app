package realworld.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import realworld.exception.ResourceNotFoundException;
import realworld.user.LoginUser;
import realworld.user.Profile;
import realworld.user.repository.UserRepository;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private UserRepository userRepository;


    public Profile getProfileByUserName(String username, LoginUser user) {
        Long userId = Optional.ofNullable(user).map(LoginUser::getId).orElseGet(() -> null);
        return userRepository.getProfileByUserName(username, userId)
                             .orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional
    public void addFollow(String username, LoginUser user) {
        Long userId = user.getId();
        userRepository.addFollow(username, userId);
    }

    public void removeFollow(String username, LoginUser user) {
        Long userId = user.getId();
        userRepository.removeFollow(username,userId);
    }
}
