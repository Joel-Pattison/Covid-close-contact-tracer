package model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Name implements Serializable {
	private static final long serialVersionUID = 1L;
	//Attributes
	@Column(name = "firstName", nullable = false)
	private String firstName;
	@Column(name = "middleName", nullable = false)
	private String middleName;
	@Column(name = "lastName", nullable = false)
	private String lastName;
	
	//Constructor
	public Name(String firstNameInput, String middleNameInput, String lastNameInput) {
		this.firstName = firstNameInput;
		this.middleName = middleNameInput;
		this.lastName = lastNameInput;
	}
	
	public Name() {
		
	}
	
	//Getters
	public String getFirstName() {
		return this.firstName;
	}
		
	public String getMiddleName() {
		return this.middleName;
	}
		
	public String getLastName() {
		return this.lastName;
	}
	
	//Setters
	public void setFirstName(String firstNameInput) {
		this.firstName = firstNameInput;
	}
		
	public void setMiddleName(String middleNameInput) {
		this.middleName = middleNameInput;
	}
	
	public void setLastName(String firstLastInput) {
		this.firstName = firstLastInput;
	}
}
