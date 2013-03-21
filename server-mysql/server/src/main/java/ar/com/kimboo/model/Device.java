package ar.com.kimboo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Device implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private String name;
	
	@OneToMany(targetEntity=Advertising.class)
	private List<Advertising> advertisings;

	public Device(Integer id, String name, List<Advertising> advertisings) {
		super();
		this.id = id;
		this.name = name;
		this.advertisings = advertisings;
	}
	
	public Device() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Advertising> getAdvertisings() {
		return advertisings;
	}

	public void setAdvertisings(List<Advertising> advertisings) {
		this.advertisings = advertisings;
	}
	
}
