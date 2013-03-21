package ar.com.kimboo.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ar.com.kimboo.model.Advertising;
import ar.com.kimboo.server.db.AdvertisingDAO;

@Service
public class AdvertisingServiceImpl {
    @Autowired AdvertisingDAO advertisingDAO;

	public void delete(Advertising targetAdvertising) {
		advertisingDAO.delete(targetAdvertising);
	}

	public void saveAdvertising(Advertising newAdvertising) {
		advertisingDAO.save(newAdvertising);
	}

	public List<Advertising> getAll() {
		return advertisingDAO.getAll();
	}

	public List<Advertising> getAllForDevice(String deviceId) {
		return advertisingDAO.getAllForDevice(deviceId);
	}

	public List<Advertising> getAllForDeviceAndApp(String deviceId, String appId) {
		return advertisingDAO.getAllForDeviceAndApp(deviceId,appId);
	}
    
}
