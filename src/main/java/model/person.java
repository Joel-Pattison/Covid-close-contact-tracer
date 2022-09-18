package model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class person implements Serializable {
	private static final long serialVersionUID = 1L;
	//Attributes
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name = "personID", unique = true)
	private int personId;
	@Column(name = "phone", nullable = false)
	private String phone;
	@Column(name = "email", nullable = false)
	private String email;
	@Embedded
//	@AttributeOverrides({
//	  @AttributeOverride(name = "firstName", column = @Column(name = "firstName", nullable = false)),
//	  @AttributeOverride(name = "middleName", column = @Column(name = "middleName", nullable = false)),
//	  @AttributeOverride(name = "lastName", column = @Column(name = "lastName", nullable = false))
//	})
	private Name name;
	
	//Constructor
	public person(String firstNameInput, String middleNameInput, String lastNameInput, String phoneInput, String emailInput) {
		name = new Name(firstNameInput, middleNameInput, lastNameInput);
		phone = phoneInput;
		email = emailInput;
	}
	
	public person() {
		
	}
	
	//Getters
	public int getPersonId() {
		return this.personId;
	}
		
	public String getPhone() {
		return this.phone;
	}
		
	public String getEmail() {
		return this.email;
	}
	
	public Name getName() {
		return this.name;
	}
		
	//Setters
	public void setPersonId(int personIdInput) {
		this.personId = personIdInput;
	}
		
	public void setPhone(String phoneInput) {
		this.phone = phoneInput;
	}
		
	public void setEmail(String emailInput) {
		this.email = emailInput;
	}
	
	public void setName(Name nameInput) {
		this.name = nameInput;
	}
}
