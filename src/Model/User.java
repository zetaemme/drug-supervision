package Model;

public class User {
    private String username;
    private String password;
    private boolean type;

    public User(String username, String password, boolean type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getType() {
        return type;
    }
}
