package ch.bzz.noel.kino.model;

import jakarta.validation.constraints.Pattern;
import jakarta.ws.rs.FormParam;

/**
 * @author noel
 * @version 1.0
 */
public class User {

    /**
     * @see User
     */

    @FormParam("userUUID")
    @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
    private String userUUID;

    /**
     * empty Constructor
     */
    public User() {
    }

    /**
     * Constructor
     *
     * @param userUUID
     */
    public User(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }
}
