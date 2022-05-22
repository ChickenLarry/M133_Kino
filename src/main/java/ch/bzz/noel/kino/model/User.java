package ch.bzz.noel.kino.model;

public class User {

    private String userUUID;

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
