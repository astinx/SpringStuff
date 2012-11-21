
package ar.com.aleatoria.server.db;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.aleatoria.server.model.Subject;

@Repository
@Transactional
public class SubjectDAO {
    @Autowired
    SessionFactory sessionFactory;

    public void saveSubject(Subject subject) {
        sessionFactory.getCurrentSession().saveOrUpdate(subject);
    }

    public List<Subject> getAll() {
        List<Subject> results = sessionFactory.getCurrentSession().createQuery("FROM Subject s ")
                .list();
        return results;
    }

    public Subject getSubjectById(String id) {
        return (Subject)sessionFactory.getCurrentSession()
                .createQuery("From Subject s where (s.id = " + id + ")").uniqueResult();
    }

    public void delete(Subject targetSubject) {
        sessionFactory.getCurrentSession().delete(targetSubject);
    }

    public int countSubjects() {
        return sessionFactory.getCurrentSession().createQuery("FROM Subject s ").list().size();
    }

}
