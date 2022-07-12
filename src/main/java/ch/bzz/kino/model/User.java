package ch.bzz.kino.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import javax.ws.rs.FormParam;

/**
 *
 * User Class
 * @Author: Noel
 *
 * @Since 1.0.0-SNAPSHOT
 *
 */
@Getter
@Setter
public class User {

    @FormParam("userUUID")
    @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
    private String userUUID;

    @FormParam("username")
    @NotEmpty(message = "Username can't be empty")
    @Size(min = 5, max = 30)
    private String userName;

    @FormParam("password")
    @NotEmpty(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!-_%*?&]{"+8+","+16+"}$", message = "Passwort needs: one lowercase letter, one uppercase letter, one digit. It must be between "+8+" & "+16+" Letters long")
    private String password;

    @FormParam("userRole")
    @NotEmpty(message = "userRole can't be empty")
    @Size(min = 5, max = 5)
    private String userRole;

    public User(){
        setUserRole("guest");
    }
}