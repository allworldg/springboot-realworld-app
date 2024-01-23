package realworld.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user")
public class User {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String email;
    private String username;
    private String password;
    private String bio;
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User(Long id, String email, String username, String password, String bio, String image) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.image = image;
    }

    public User(String email, String username, String password, String bio, String image) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.image = image;
    }

    public User() {
    }
}
