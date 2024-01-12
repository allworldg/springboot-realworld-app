package realworld.application.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import realworld.api.exception.UnAuthorizedException;
import realworld.core.user.User;
import realworld.core.user.UserRepository;
import realworld.infrastructure.mybatis.UserMapper;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class UserService  {
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

    public User findUserByUserId(Long id) throws Exception {
        return userRepository.findUserByUserId(id)
                             .orElseThrow(() -> new UnAuthorizedException());
    }

//    public UserWithToken getUserWithTokenById()

}
