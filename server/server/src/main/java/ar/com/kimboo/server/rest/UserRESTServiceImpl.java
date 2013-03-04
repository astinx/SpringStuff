
package ar.com.kimboo.server.rest;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.kimboo.model.User;
import ar.com.kimboo.server.exceptions.MailExistException;
import ar.com.kimboo.server.exceptions.UserDoesntExistException;
import ar.com.kimboo.server.services.UserServiceImpl;

@Component
@Path("/user")
public class UserRESTServiceImpl {
    @Autowired UserServiceImpl userService;

    private static final Logger logger = LoggerFactory.getLogger(UserRESTServiceImpl.class);

    /**
     * @return json.
     * @uri http://localhost:8080/server/rest/user/
     */
	@GET @Path("/") @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody List<User> getAllUsers() {
        return userService.getAll();
    }

    /**
     * @param newUser: The new user to add to the database.
     * @return 
     * @throws NoSuchAlgorithmException 
     * @throws MailExistException if the email already exists
     * @uri http://localhost:8080/server/rest/user/
     */
	@POST @Path("/") @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(@RequestBody User newUser) {
		try {
			userService.saveUser(newUser);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("NoSuchAlgorithmException").build();
		} catch (MailExistException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("MailExistException").build();
		}
		return Response.status(Response.Status.OK).entity("User has been persisted").build();
    }

    /**
     * @param email: The user thats gonna to be deleted email.
     * @uri http://localhost:8080/server/rest/user/
     */
	@DELETE @Path("/") @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUserByEmail(String email) {
        try {
        	userService.deleteUserByEmail(email);
        } catch (UserDoesntExistException e) {
        	e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("UserDoesntExistException").build();
        }
        return Response.status(Response.Status.OK).entity("User has been deleted").build();
    }
    
    /**
     * @param user: The user thats gonna to be updated.
     * @uri http://localhost:8080/server/rest/user/
     */
	@PUT @Path("/") @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        try {
        	userService.saveUser(user);
        } catch (Exception e) {
        	return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.OK).entity("User has been updated").build();
    }



}
