
package ar.com.kimboo.server.db;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.kimboo.model.User;
import ar.com.kimboo.utils.MD5Encoder;

@Repository
@Transactional
public class UserDAO {
    @Autowired
    SessionFactory sessionFactory;

    public void saveUser(User user) throws NoSuchAlgorithmException {
    	user.setEmail(MD5Encoder.encode(user.getEmail()));
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @SuppressWarnings("unchecked")
    public List<User> getAll() {
		List<User> results = (List<User>) sessionFactory.getCurrentSession().createQuery("from User").list();
        return results;
    }

    public User getUserByUsername(String user) {
        return (User)sessionFactory.getCurrentSession()
                .createQuery("From User u where (u.username LIKE '" + user + "')").uniqueResult();
    }

    public User getUserByEmail(String mail) {
        return (User)sessionFactory.getCurrentSession()
                .createQuery("from User u where u.email = '" + mail + "')").uniqueResult();
    }

    public void delete(User targetUser) {
        sessionFactory.getCurrentSession().delete(targetUser);
    }

    public boolean existUser(User user) throws NoSuchAlgorithmException {
    	return (sessionFactory.getCurrentSession()
                .createQuery("FROM User u where (u.email = '" + user.getEmail() + "' and u.password = '"+MD5Encoder.encode(user.getPassword())+"') ").list().size() == 1);
    }

    public User getUserById(int userId) {
        return (User)sessionFactory.getCurrentSession()
                .createQuery("From User u where (u.id = '" + userId + "')").uniqueResult();
    }

	public boolean existUserEmail(String email) {
		return (sessionFactory.getCurrentSession()
                .createQuery("FROM User u where (u.email = '" + email + "') ").list().size() != 0);
	}

}
