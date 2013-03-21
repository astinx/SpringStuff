package ar.com.kimboo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Advertising implements Serializable {

	public static final int TOP = 1;
	public static final int BOTTOM = 2;
	public static final int CENTER = 3;
	public static final int LEFT = 4;
	public static final int RIGHT = 5;
	
	public static final String ANDROID = "android";
	public static final String IOS = "ios";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private Date modification;
	private byte[] image;
	private String description;
	private String device;
	private int position;
	private int relPosition;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getModification() {
		return modification;
	}

	public void setModification(Date modification) {
		this.modification = modification;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	public Advertising(Integer id, Date modification, byte[] image,
			String description, String device, int position, int relPositon) {
		super();
		this.id = id;
		this.modification = modification;
		this.image = image;
		this.description = description;
		this.device = device;
		this.position = position;
		this.relPosition = relPositon;
	}

	public Advertising() {}

	public int getRelPositon() {
		return relPosition;
	}

	public void setRelPositon(int relPositon) {
		this.relPosition = relPositon;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String devices) {
		this.device = devices;
	}
	
}
