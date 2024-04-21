package realworld.user.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import realworld.user.Profile;
import realworld.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public Optional<User> findUserByEmail(String email) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        User user = userMapper.selectOne(wrapper);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<Profile> getProfileByUserName(String username, Long userId) {
        return Optional.ofNullable(userMapper.getProfileByUserName(username, userId));
    }
}
