
package ar.com.aleatoria.server.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.aleatoria.server.db.UserDAO;
import ar.com.aleatoria.server.model.User;

import com.google.gson.Gson;

@Controller
@RequestMapping("user")
public class UserServiceImpl {
    @Autowired
    UserDAO userDAO;
    
    private static final Logger logger = 
	        LoggerFactory.getLogger(UserServiceImpl.class);
    
    /**
     * @return json.
     * @uri http://localhost:8080/server/user/
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    User[] getAllUsers() {
    	logger.info("I hitted the http://localhost:8080/server/user/ resource by GET");
        return userDAO.getAll().toArray(new User[userDAO.getAll().size()]);
    }

    /**
     * @param newUser: The new user to add to the database.
     * @uri http://localhost:8080/server/user/
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void addUser(@RequestBody
    User newUser) {
        userDAO.saveUser(newUser);
    }

    /**
     * @param username: The username of the user.
     * @uri http://localhost:8080/server/user/{id}
     */
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public @ResponseBody
    String getUserById(@PathVariable
    String username) {
        return new Gson().toJson(userDAO.getUserByUsername(username));
    }

    // /** DUPLICATED RESOURCE!!!
    // * @param username: The username/password of the user.
    // * @return
    // * @uri http://localhost:8080/server/user/{id}
    // */
    // @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    // public @ResponseBody
    // Credential existUser(@PathVariable
    // User user) {
    // if (userDAO.existUser(user)) {
    // List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();
    // AUTHORITIES.add(new GrantedAuthorityImpl("ROLE_USER"));
    // Md5PasswordEncoder md5 = new Md5PasswordEncoder();
    // return new Credential(user.getUsername(),
    // md5.encodePassword(user.getPassword(),
    // user.getName()), AUTHORITIES);
    // } else {
    // throw new UserDoesntExistException();
    // }
    // }

    /**
     * @param newUser: The newUser to update.
     * @uri http://localhost:8080/server/user/{newUser}
     */
    @RequestMapping(value = "/{newUser}", method = RequestMethod.PUT)
    public void updateUser(@RequestBody
    User newUser) {
        userDAO.saveUser(newUser);
    }

    /**
     * @param targetUser: The targetUser to delete.
     * @uri http://localhost:8080/server/user/{targetUser}
     */
    @RequestMapping(value = "/{targetUser}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable
    User targetUser) {
        userDAO.delete(targetUser);
    }
    
    ///////////////////////SINGLE BROWSER RESOURCES////////////////////////
    /**
     * @return json.
     * @uri http://localhost:8080/server/user/browser
     */
    @RequestMapping(value = "/browser", method = RequestMethod.GET)
    public @ResponseBody
    String getAllUsersBrowser() {
        String mess = "";
        Gson gson = new Gson();
        for (User user : userDAO.getAll()) {
            mess += gson.toJson(user);
        }
        return mess;
    }

}
