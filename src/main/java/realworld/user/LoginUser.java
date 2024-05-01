package realworld.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonTypeName("user")
public class LoginUser implements UserDetails {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    private Long id;
    private String email;
    private String token;
    private String username;
    private String bio;
    private String image;
    private String password;


    public LoginUser() {
    }


    public LoginUser(Long id, String email, String password, String username, String token,
                     String bio,
                     String image) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.token = token;
        this.username = username;
        this.bio = bio;
        this.image = image;
    }

    public LoginUser(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.bio = user.getBio();
        this.image = user.getImage();
    }

    public void setByUser(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.bio = user.getBio();
        this.image = user.getImage();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    public void setEmail(String email) {
        this.email = email;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
