package realworld.user;

import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@JsonRootName("user")
public class LoginParam {
    @NotBlank(message = "email can't be blank")
    @Email(message = "should be an email")
    private String email;
    @NotBlank(message = "password can't be blank")
    @Length(max = 26, message = "maximum length is 26")
    private String password;

    public LoginParam(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginParam() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


}
