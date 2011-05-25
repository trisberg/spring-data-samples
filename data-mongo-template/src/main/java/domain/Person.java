package domain;

import java.util.Map;


public class Person {
	
	private String id;

	private String name;
	
	private Map<String, Object> personalInfo;
	
	private Address address;

	// need default constructor for now
	public Person() {
	}

	public Person(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Object> getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(Map<String, Object> personalInfo) {
		this.personalInfo = personalInfo;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String toString() {
		return "[" + id + "] " + name + " " + address + " : " + this.getPersonalInfo();
	}

}
