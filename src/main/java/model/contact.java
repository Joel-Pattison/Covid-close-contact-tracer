package model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class contact implements Serializable {
	private static final long serialVersionUID = 1L;
	//Attributes
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name = "contactID", unique = true)
	private int contactid;
	@Column(name = "person1", nullable = false)
	private int contact1id;
	@Column(name = "person2", nullable = false)
	private int contact2id;
	@Column(name = "dateContact", nullable = false)
	private LocalDate date;
	
	//Constructor
	public contact(int contact1idInput, int contact2idInput, LocalDate date) {
		this.contact1id = contact1idInput;
		this.contact2id = contact2idInput;
		this.date = date;
	}
	
	public contact() {
		
	}
	
	//Getters
	public int getContact1id() {
		return this.contact1id;
	}
		
	public int getContact2id() {
		return this.contact2id;
	}
		
	public LocalDate getDate() {
		return this.date;
	}
		
	//Setters
	public void setContact1id(int contact1idInput) {
		this.contact1id = contact1idInput;
	}
		
	public void setContact2id(int contact2idInput) {
		this.contact2id = contact2idInput;
	}
		
	public void setDate(LocalDate dateInput) {
		this.date = dateInput;
	}
	
	//Functionality
	public String toString() {
		return this.contact1id + "," + this.contact2id + "," + this.date;
	}
}
