
package ar.com.kimboo.server.db;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.kimboo.model.Advertising;

@Repository
@Transactional
public class AdvertisingDAO {
    @Autowired
    SessionFactory sessionFactory;


    @SuppressWarnings("unchecked")
    public List<Advertising> getAll() {
		List<Advertising> results = (List<Advertising>) sessionFactory.getCurrentSession().createQuery("from Advertising").list();
        return results;
    }
    
    public void save(Advertising targetAdvertising) {
    	sessionFactory.getCurrentSession().saveOrUpdate(targetAdvertising);
    }
    
    public void delete(Advertising targetAdvertising) {
        sessionFactory.getCurrentSession().delete(targetAdvertising);
    }

    public boolean existAdvertising(Advertising Advertising) throws NoSuchAlgorithmException {
    	return (sessionFactory.getCurrentSession()
                .createQuery("FROM Advertising a where (a.id = '" + Advertising.getId()).list().size() == 1);
    }

    public Advertising getAdvertisingById(int AdvertisingId) {
        return (Advertising)sessionFactory.getCurrentSession()
                .createQuery("From Advertising u where (u.id = '" + AdvertisingId + "')").uniqueResult();
    }

	public List<Advertising> getAllForDevice(String deviceId) {
		List<Advertising> results = (List<Advertising>) sessionFactory.getCurrentSession().createQuery("from Advertising a where (a.device = '"+deviceId+"')").list();
        return results;
	}

	public List<Advertising> getAllForDeviceAndApp(String deviceId, String appId) {
		List<Advertising> results = (List<Advertising>) sessionFactory.getCurrentSession().createQuery("from Advertising a where (a.device = '"+deviceId+"' and a.app = '"+appId+"')").list();
        return results;
	}

}
