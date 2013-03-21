package ar.com.kimboo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Advertising implements Serializable {

	public static final String ANDROID = "android";
	public static final String IOS = "ios";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	private String appId;
	private Date lastModification;
	private String description;
	private String device;
	private String path;
	private String tag;
	
	public Advertising(Integer id, String appId, Date lastModification,
			String description, String device, String path, String tag) {
		super();
		this.id = id;
		this.appId = appId;
		this.lastModification = lastModification;
		this.description = description;
		this.device = device;
		this.path = path;
		this.tag = tag;
	}
	
	public Advertising() {}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getAppId() {
		return appId;
	}


	public void setAppId(String appId) {
		this.appId = appId;
	}


	public Date getLastModification() {
		return lastModification;
	}


	public void setLastModification(Date lastModification) {
		this.lastModification = lastModification;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getDevice() {
		return device;
	}


	public void setDevice(String device) {
		this.device = device;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}
	

	
}
