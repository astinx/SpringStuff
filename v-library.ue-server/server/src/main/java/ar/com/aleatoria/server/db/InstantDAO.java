
package ar.com.aleatoria.server.db;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.aleatoria.server.model.Instant;

@Repository
@Transactional
public class InstantDAO {
    @Autowired
    SessionFactory sessionFactory;

    public void saveInstant(Instant instant) {
        sessionFactory.getCurrentSession().saveOrUpdate(instant);
    }

    public List<Instant> getAll() {
        List<Instant> results = sessionFactory.getCurrentSession().createQuery("FROM Instant i ")
                .list();
        return results;
    }

    public Instant getInstantById(String id) {
        return (Instant)sessionFactory.getCurrentSession()
                .createQuery("From Instant i where (i.id = " + id + ")").uniqueResult();
    }

    public void delete(Instant targetInstant) {
        sessionFactory.getCurrentSession().delete(targetInstant);
    }

}
