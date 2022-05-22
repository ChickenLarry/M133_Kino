package ch.bzz.noel.kino.model;

import ch.bzz.noel.kino.data.DataHandler;

/**
 * @author noel
 * @version 1.0
 */
public class User {

    /**
     * @see User
     */
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
