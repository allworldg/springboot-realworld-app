package realworld.user.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import realworld.exception.InvalidEmailOrPasswordException;
import realworld.exception.UnAuthorizedException;
import realworld.user.UserRegister;
import realworld.user.User;
import realworld.user.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;


    @Value("${image_url}")
    private String image;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User addUser(UserRegister userRegister) {
        User user = new User(userRegister.getEmail(),
                userRegister.getUsername(),
                userRegister.getPassword()
                , "", image);
        userRepository.addUser(user);
        return user;
    }

    public User findUserByUserId(Long id) {
        return userRepository.findUserByUserId(id)
                             .orElseThrow(() -> new UnAuthorizedException());
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                             .orElseThrow(() -> new InvalidEmailOrPasswordException());
    }


}
