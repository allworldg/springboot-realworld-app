package realworld.user.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import realworld.user.Profile;
import realworld.user.User;

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

    @Override
    public void addFollow(String username, Long userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        Optional.ofNullable(user).map(User::getId).ifPresent(id -> {
            boolean hasFollowed = userMapper.isAlreadyFollowed(id, userId);
            if(!hasFollowed){
                userMapper.addFollow(id,userId);
            }
        });
    }

    @Override
    @Transactional
    public void removeFollow(String username, Long userId) {
        userMapper.removeFollow(username,userId);
    }
}
