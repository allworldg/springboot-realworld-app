package realworld.infrastructure.repository;

import realworld.core.user.User;
import realworld.core.user.UserRepository;
import realworld.infrastructure.mybatis.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MybatisUserRepository implements UserRepository {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Optional<User> findUserByUserId(long id) {
        return Optional.empty();
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void addFollowRelation(long userId, long followUserId) {

    }

    @Override
    public void removeFollowRelation(long userId, long followUserId) {

    }
}
