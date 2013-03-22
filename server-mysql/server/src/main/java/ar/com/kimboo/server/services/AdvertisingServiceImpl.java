package ar.com.kimboo.server.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
	

	public List<Advertising> getAllForDeviceAndAppAndTag(String deviceId,
			String idApp, String tagId) {
		return advertisingDAO.getAllForDeviceAndAppAndTag(deviceId,idApp,tagId);
	}
	
	/**
	 * Saves uploadedInputStream in uploadedFileLocation.
	 * @param uploadedInputStream: The file.
	 * @param uploadedFileLocation: The location.
	 */
	public void writeToFile(InputStream uploadedInputStream,
		String uploadedFileLocation) {
		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
 
			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
 
	}
    
}
