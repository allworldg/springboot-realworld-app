package realworld.user.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import realworld.exception.EmailAlreadyExistException;
import realworld.exception.InvalidEmailOrPasswordException;
import realworld.exception.UnAuthorizedException;
import realworld.exception.UserNameAlreadyExistException;
import realworld.user.LoginUser;
import realworld.user.User;
import realworld.user.UserRegister;
import realworld.user.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;


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
                             .orElseThrow(UnAuthorizedException::new);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                             .orElseThrow(InvalidEmailOrPasswordException::new);
    }


    public void updateUser(LoginUser param, LoginUser loginUser) {
        User user = new User();
        String email = param.getEmail();
        if (!StringUtils.isBlank(email) && !StringUtils.equals(email, loginUser.getEmail())) {
            userRepository.findUserByEmail(email).ifPresent(u -> {
                throw new EmailAlreadyExistException();
            });
            user.setEmail(email);
        }
        String username = param.getUsername();
        if (!StringUtils.isBlank(username) &&
                !StringUtils.equals(username, loginUser.getUsername())) {
            userRepository.findUserByUserName(username).ifPresent(u -> {
                throw new UserNameAlreadyExistException();
            });
            user.setUsername(username);
        }
        if (StringUtils.isBlank(param.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(param.getPassword());
        }
        user.setImage(param.getImage());
        user.setId(loginUser.getId());
        user.setBio(param.getBio());
        userRepository.updateUser(user);

    }
}
