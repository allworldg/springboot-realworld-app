package realworld.user.repository;

import realworld.user.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserByUserId(long id);

    Optional<User> findUserByEmail(String email);

    void addUser(User user);

    void addFollowRelation(long userId, long followUserId);

    void removeFollowRelation(long userId, long followUserId);

}
