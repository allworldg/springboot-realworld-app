package realworld.user.repository;

import realworld.user.Profile;
import realworld.user.User;

import java.util.Optional;


public interface UserRepository {
    Optional<User> findUserByUserId(long id);

    Optional<User> findUserByEmail(String email);

    void addUser(User user);


    Optional<Profile> getProfileByUserName(String username, Long userId);
}
