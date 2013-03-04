package ar.com.kimboo.server.services;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ar.com.kimboo.model.User;
import ar.com.kimboo.server.db.UserDAO;
import ar.com.kimboo.server.exceptions.MailExistException;
import ar.com.kimboo.server.exceptions.UserDoesntExistException;

@Service
public class UserServiceImpl {
    @Autowired UserDAO userDAO;

	public void delete(User targetUser) {
		userDAO.delete(targetUser);
	}

	public void saveUser(User newUser) throws MailExistException, NoSuchAlgorithmException {
		if (!(userDAO.existUserEmail(newUser.getEmail()))) {
			userDAO.saveUser(newUser);
        } else {
            throw new MailExistException();
        }
	}

	public User getUserByMail(String email) {
		return getUserByMail(email);
	}

	public boolean existUserEmail(String email) {
		return userDAO.getUserByEmail(email) != null;
	}

	public List<User> getAll() {
		return userDAO.getAll();
	}

	public void deleteUserByEmail(String email) throws UserDoesntExistException {
		User user = userDAO.getUserByEmail(email);
		if (user != null) {
			userDAO.delete(user);
		} else {
			throw new UserDoesntExistException();
		}
	}
    
    
}
