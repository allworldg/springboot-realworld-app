package realworld.user.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import realworld.user.Profile;
import realworld.user.User;


@Mapper
public interface UserMapper extends BaseMapper<User> {
    Profile getProfileByUserName(@Param("username") String username, @Param("userId") Long userId);
}
