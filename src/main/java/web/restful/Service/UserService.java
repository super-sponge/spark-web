package web.restful.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.restful.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sponge on 2017/6/28.
 */
public class UserService {
    private static Logger LOG = LoggerFactory.getLogger(UserService.class);

    private List<User> users = new ArrayList<User>(10);

    // returns a list of all users
    public List<User> getAllUsers() {
        return users;
    }

    // returns a single user by id
    public User getUser(String id) {
        for(User user : users) {
            if (user.getId().equals(id)) {
                LOG.info("find " + user);
                return user;
            }
        }

        LOG.warn("No user with id " + id +" found");
        return  null;
    }

    // creates a new user
    public User createUser(String id, String name, String email) {
        User addUser = new User(id, name, email);
        LOG.info("Add user " + addUser);
        users.add(addUser);
        return addUser;
    }

    // updates an existing user
    public User updateUser(String id, String name, String email) {
        for(User user: users) {
            if(user.getId().equals(id)) {
                user.setEmail(email);
                user.setName(name);
                LOG.info("Update User " + user);
                return  user;
            }
        }

        return  createUser(id, name, email);
    }

}
