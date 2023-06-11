package ma.emsi.applicationgestionpersonne.entities;

import java.util.Date;

public class Person {
	private int id;
	private String name;
    private int age;
    private String gender;
    private String address;
    private String email;
    private String phoneNumber;
    private Date dateNaiss;
    private String cin;
    
    
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}





	public Person(int id, String name, int age, String gender, String address, String email, String phoneNumber,
			Date dateNaiss, String cin) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dateNaiss = dateNaiss;
		this.cin = cin;
	}
	public Person(String name, int age, String gender, String address, String email, String phoneNumber,
				  Date dateNaiss, String cin) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dateNaiss = dateNaiss;
		this.cin = cin;
	}


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}





	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public Date getDateNaiss() {
		return dateNaiss;
	}


	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}


	public String getCin() {
		return cin;
	}


	public void setCin(String cin) {
		this.cin = cin;
	}


	@Override
	public String toString() {
		return id + "," + name + "," + age + "," + gender + "," + address + ","+ email + "," + phoneNumber + "," + dateNaiss + "," + cin;
	}
	
	
    
	
    
	

	
}
