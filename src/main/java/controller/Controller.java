package controller;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javafx.scene.layout.*;
import model.contact;
import model.contactDatabaseInterface;
import model.person;
import model.personDatabaseInterface;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Controller implements Serializable {
	//ArrayList<Contact> contactsArray = new ArrayList<Contact>();
	//ArrayList<closeContact> closeContactsArray = new ArrayList<closeContact>();
	personDatabaseInterface personTable = new personDatabaseInterface();
	contactDatabaseInterface contactTable = new contactDatabaseInterface();
	
	
	//Creating an contact and adding it to the contactsArray arraylist. Receiving the controls instead of just the text is necessary so that the text boxes can be cleared after the contact is created
	 public void addPerson(String firstName, String middleName, String lastName, String phoneNum, String email, TableView<person> contacts) {
		 person newPerson = new person(firstName, middleName, lastName, phoneNum, email);
		 personTable.add(newPerson);
		 listPeople(contacts);
	 }
	 
	 //Gets the index that's selected by the user and removes that item
	 public void removePerson(TableView<person> contacts) {
		 person personToDelete = contacts.getSelectionModel().getSelectedItem();
		 personTable.remove(personToDelete.getPersonId());
		 contactTable.removeAllWithId(personToDelete.getPersonId());
		 listPeople(contacts);
	}

	 public void listPeople(TableView<person> peopleTable) {
		 peopleTable.getItems().clear();
		 ArrayList<person> queryResult = personTable.queryAll();
		 for(int i = 0;i < queryResult.size(); i++) {
			 peopleTable.getItems().add(queryResult.get(i));
		 }
	 }
	 
//		Comparing the input contact with this contact
		public boolean comparePeople(person contactInput1, person contactInput2) {
			if(contactInput1.getName().getFirstName() == contactInput2.getName().getFirstName() && contactInput1.getName().getMiddleName() == contactInput2.getName().getMiddleName() && contactInput1.getName().getLastName() == contactInput2.getName().getLastName() && contactInput1.getPersonId() == contactInput2.getPersonId() && contactInput1.getPhone() == contactInput2.getPhone()) {
				return true;
			}
			else {
				return false;
			}
		}
	 
	 public void search(TableView<person> peopleTable, String option, String input) {
		 if (option == "Name" || option == "ID") {
			 person queryResult;
			 peopleTable.getItems().clear();
			 if (option == "Name") {
				 queryResult = personTable.getPersonByName(input);
			 }
			 else {
				 queryResult = personTable.getPersonByID(Integer.parseInt(input));
			 }
			 peopleTable.getItems().add(queryResult);
		 }
	 }
	 
//	 //Loads all of the contacts form the file
//	 public void loadPeople(TableView<person> contacts) {
//		 //clearing the current arraylist
//		 contactsList.clear();
//		 
//		 ArrayList<Contact> tempContactsArray = new ArrayList<Contact>();
//		 
//		 try{
//			 ObjectInputStream is = new ObjectInputStream(new FileInputStream("contacts.txt"));  
//			 tempContactsArray = (ArrayList<Contact>) is.readObject();
//		     is.close();       
//		 }catch (Exception e) {e.printStackTrace();}
//		 
//		 contactsList.setContactList(tempContactsArray);
//		 //updating the view
//		 listContacts(contacts);
//	 }
	 
	 //Saves all of the contacts in the tableview to a file
	 public void savePeople() {
		 ArrayList<person> tempContactsArray = personTable.queryAll();
		 
		 try{
			 ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("contacts.txt"));
			 out.writeObject(tempContactsArray);
			 out.close();
		 } catch(Exception e) {e.printStackTrace();}
	 }
	 
	 //Checking to see if the current contacts are saved, if they are then exit, but if they aren't ask the user if they want to save the contacts
	 public void exitProgram(){
		System.exit(0);
	 }
	 
//	 public void saveAndExit() {
//		 saveContacts();
//		 System.exit(0);
//	 }
	 
	 public void fillComboBox(ComboBox<String> user1Combo) {
		 user1Combo.getItems().clear();
		 ArrayList<person> queryResult = personTable.queryAll();
		 for(int i = 0;i < queryResult.size(); i++) {
			 String temp = queryResult.get(i).getPersonId() + ": " + queryResult.get(i).getName().getFirstName() + " " + queryResult.get(i).getName().getMiddleName() + " " + queryResult.get(i).getName().getLastName();
			 user1Combo.getItems().addAll(temp);
		 }
	 }
	 
	 public void listContacts(TableView<contact> closeContacts) {
		 closeContacts.getItems().clear();
		 ArrayList<contact> queryResult = contactTable.queryAll();
		 for(int i = 0;i < queryResult.size(); i++) {
			 closeContacts.getItems().add(queryResult.get(i));
		 }
	 }
	 
	 public void listContactsSinceDate(TableView<contact> closeContacts, LocalDate date) {
		 closeContacts.getItems().clear();
		 ArrayList<contact> queryResult = contactTable.queryGetSinceDate(date);
		 for(int i = 0;i < queryResult.size(); i++) {
			 closeContacts.getItems().add(queryResult.get(i));
		 }
	 }
	 
//	 public void listSortedContacts(TableView<contact> closeContacts, String option) {
//		 if(option == "Name") {
//			 closeContactsList.sortByName();
//		 }
//		 else if (option == "Date") {
//			 closeContactsList.sortByDate();
//		 }
//		 closeContacts.getItems().clear();
//		 for(int i = 0;i < closeContactsList.size(); i++) {
//			 closeContacts.getItems().add(closeContactsList.get(i));
//		 }
//	 }
	 
//	 public void listContactsByName(TableView<contact> closeContacts, String nameInput) {
//		 closeContacts.getItems().clear();
//		 ArrayList<contact> queryResult = contactTable.queryAll();
//		 for(int i = 0;i < queryResult.size(); i++) {
//			 if (closeContactsList.get(i).getContact1Name().contentEquals(nameInput) || closeContactsList.get(i).getContact2Name().equals(nameInput)) {
//				 closeContacts.getItems().add(closeContactsList.get(i));
//			 }
//		 }
//	 }
	 
	 public void addContact(String contact1Name, String contact2Name, LocalDate date) {
		 String id1[] = contact1Name.split(":");
		 String id2[] = contact2Name.split(":");
		 contact newContact = new contact(Integer.parseInt(id1[0]), Integer.parseInt(id2[0]), date);
		 contactTable.addContact(newContact);
	 }
	 
	 public void memoryException(){
		 ArrayList<person> collection = new ArrayList<person>();
		 while(true) {
			 person newPerson = new person("Dummy", "Dummy", "Dummy", "Dummy", "Dummy");
			 collection.add(newPerson);
		 }
	 }
}