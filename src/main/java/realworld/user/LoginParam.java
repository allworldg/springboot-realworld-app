package realworld.user;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonTypeName("user")
public class LoginParam {
    @NotBlank(message = "can't be blank")
    @Email(message = "need to be formatted correctly")
    private String email;
    @NotBlank(message = "can't be blank")
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
