package realworld.core.user;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserByUserId(long id);

    void addUser(User user);

    void addFollowRelation(long userId, long followUserId);

    void removeFollowRelation(long userId, long followUserId);

}
