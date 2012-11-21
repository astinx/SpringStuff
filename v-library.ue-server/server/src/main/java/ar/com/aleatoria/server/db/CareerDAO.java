
package ar.com.aleatoria.server.db;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.aleatoria.server.model.Career;

@Repository
@Transactional
public class CareerDAO {
    @Autowired
    SessionFactory sessionFactory;

    public void saveCareer(Career instant) {
        sessionFactory.getCurrentSession().saveOrUpdate(instant);
    }

    public List<Career> getAll() {
        List<Career> results = sessionFactory.getCurrentSession().createQuery("FROM Career i ")
                .list();
        return results;
    }

    public Career getCareerById(String id) {
        return (Career)sessionFactory.getCurrentSession()
                .createQuery("From Career i where (i.id = " + id + ")").uniqueResult();
    }

    public void delete(Career targetCareer) {
        sessionFactory.getCurrentSession().delete(targetCareer);
    }

}
