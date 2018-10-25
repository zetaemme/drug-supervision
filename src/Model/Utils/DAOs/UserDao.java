package Model.Utils.DAOs;

import Model.User;
import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User getUser(String username);
}
