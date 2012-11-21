
package ar.com.aleatoria.server.db;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.aleatoria.server.model.User;

@Repository
@Transactional
public class UserDAO {
    @Autowired
    SessionFactory sessionFactory;

    public void saveUser(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    public List<User> getAll() {
        List<User> results = sessionFactory.getCurrentSession().createQuery("FROM User u ").list();
        return results;
    }

    public User getUserByUsername(String user) {
        return (User)sessionFactory.getCurrentSession()
                .createQuery("From User u where (u.username LIKE '" + user + "')").uniqueResult();
    }

    public void delete(User targetUser) {
        sessionFactory.getCurrentSession().delete(targetUser);
    }

    public boolean existUser(User user) {
        return (sessionFactory
                .getCurrentSession()
                .createQuery(
                        "FROM User u where (u.username LIKE '" + user.getUsername()
                                + "' AND u.password LIKE '" + user.getPassword() + "') ").list().size() == 1);
    }

}
