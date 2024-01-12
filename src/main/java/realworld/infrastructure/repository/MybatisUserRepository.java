package realworld.infrastructure.repository;

import org.springframework.transaction.annotation.Transactional;
import realworld.core.user.User;
import realworld.core.user.UserRepository;
import realworld.infrastructure.mybatis.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.rmi.server.ExportException;
import java.util.Optional;

@Repository
public class MybatisUserRepository implements UserRepository {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Optional<User> findUserByUserId(long id) {
        User user = userMapper.selectById(id);
        return Optional.ofNullable(user);
    }

    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public void addFollowRelation(long userId, long followUserId) {

    }

    @Override
    public void removeFollowRelation(long userId, long followUserId) {

    }
}
