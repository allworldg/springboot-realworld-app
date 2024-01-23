package realworld.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import realworld.user.annotation.NotDuplicatedEmail;

public class UserRegister {
    @Email(message = "should be an email")
    @NotBlank(message = "can not be empty")
    @NotDuplicatedEmail()
    private String email;

    @NotBlank(message = "username cannot be empty")
    private String username;
    @NotBlank(message = "password cannot be empty")
    private String password;

    public UserRegister(String email, String username, String image) {
        this.email = email;
        this.username = username;
        this.password = image;
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
        this.username =  username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
