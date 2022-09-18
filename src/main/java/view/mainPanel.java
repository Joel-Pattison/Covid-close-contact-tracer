package view;

import java.io.IOException;

import controller.Controller;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.person;
import javafx.scene.layout.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class mainPanel extends Tab {
	
	private static Label peopleLabel, firstNameLabel, middleNameLabel, lastNameLabel, phoneLabel, emailLabel;
	private static TextField firstNameText, lastNameText, phoneText, emailText, middleNameText, searchText;
	private static Button addBtn, removeBtn, listBtn, saveBtn, exitBtn, searchBtn, memoryExceptionBtn;
	private Controller control;
	private TableView<person> personTable;
	private ComboBox<String> searchCombo;
	private TableColumn<person, String> firstnameCol, lastNameCol, uniqueIDCol, phoneCol, middleNameCol, emailCol;
	
	public mainPanel(Controller controlInput) {
		setText("Intro Panel");
		//Creating the primary scene and its elements
		control = controlInput;
				
		peopleLabel = new Label("----------------People----------------");
		firstNameLabel = new Label("Enter First Name");
		middleNameLabel = new Label("Enter Middle Name");
		lastNameLabel = new Label("Enter Last Name");
		phoneLabel = new Label("Enter Phone Number");
		emailLabel = new Label("Enter Email");
		
		searchCombo = new ComboBox<String>();
				
		firstNameText = new TextField();
		lastNameText = new TextField();
		middleNameText = new TextField();
		phoneText = new TextField();
		emailText = new TextField();
		searchText = new TextField();
		
		//Creating buttons and their actions
		addBtn = new Button ("ADD");
		addBtn.setOnAction((e) -> {
			control.addPerson(firstNameText.getText(), middleNameText.getText(), lastNameText.getText(), phoneText.getText(), emailText.getText(), personTable);
			firstNameText.clear();
			middleNameText.clear();
			lastNameText.clear();
			phoneText.clear();
			emailText.clear();
		});
	    removeBtn = new Button("Remove");
	    removeBtn.setOnAction(e -> control.removePerson(personTable));
	    listBtn = new Button("List");
	    listBtn.setOnAction(e -> control.listPeople(personTable));
	    saveBtn = new Button("Save to file");
	    saveBtn.setOnAction(e -> control.savePeople());
	    exitBtn = new Button("Exit");
	    exitBtn.setOnAction(e -> control.exitProgram());
	    searchBtn = new Button ("Search");
	    searchBtn.setOnAction((e) -> {
	    	control.search(personTable, searchCombo.getValue(), searchText.getText());
	    });
	    memoryExceptionBtn = new Button("Create Memory Exception");
	    memoryExceptionBtn.setOnAction(e -> {
			control.memoryException();
		});
			    
	    final Pane rightAlignSpacing = new Pane();
	    HBox.setHgrow(rightAlignSpacing, Priority.ALWAYS);
			    
	    personTable = new TableView<person>();
	    HBox.setHgrow(personTable, Priority.ALWAYS);
			    
	    firstnameCol = new TableColumn<>("First Name");
	    firstnameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName().getFirstName()));
	    
	    middleNameCol = new TableColumn<>("Middle Name");
	    middleNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName().getMiddleName()));
	    
	    lastNameCol = new TableColumn<>("Last Name");
	    lastNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName().getLastName()));
	    
	    uniqueIDCol = new TableColumn<>("ID");
	    uniqueIDCol.setCellValueFactory(new PropertyValueFactory<person, String>("personId"));
	    
	    phoneCol = new TableColumn<>("phone");
	    phoneCol.setCellValueFactory(new PropertyValueFactory<person, String>("phone"));
	    
	    emailCol = new TableColumn<>("email");
	    emailCol.setCellValueFactory(new PropertyValueFactory<person, String>("email"));
	    
	    personTable.getColumns().addAll(uniqueIDCol, firstnameCol, middleNameCol, lastNameCol, phoneCol, emailCol);
				
	    //Adding the elements to the scene
		HBox row1 = new HBox();
		row1.getChildren().addAll(peopleLabel);
		
		HBox row2 = new HBox(32);
		row2.getChildren().addAll(firstNameLabel, firstNameText);
		
		HBox row3 = new HBox(32);
		row3.getChildren().addAll(middleNameLabel, middleNameText);
				
		HBox row4 = new HBox(33);
		row4.getChildren().addAll(lastNameLabel, lastNameText);
				
		HBox row5 = new HBox(9);
		row5.getChildren().addAll(phoneLabel, phoneText);
		
		HBox row6 = new HBox(9);
		row6.getChildren().addAll(emailLabel, emailText);
				
		HBox row7 = new HBox(10);
		row7.getChildren().addAll(addBtn, removeBtn, listBtn);
				
		HBox row8 = new HBox();
		row8.getChildren().addAll(personTable);

		HBox row9 = new HBox(10);
		row9.getChildren().addAll(searchText, searchCombo, searchBtn);
				
		HBox row10 = new HBox(11);
		row10.getChildren().addAll(saveBtn, memoryExceptionBtn, rightAlignSpacing, exitBtn);
		
		VBox layout = new VBox(10);
	    layout.setPadding(new Insets(5, 5, 5, 5));
	    layout.getChildren().addAll(row1, row2, row3, row4, row5, row6, row7, row8, row9, row10);
			    
	    //showing the scene
	    setContent(layout);
	    control.listPeople(personTable);
	    
	    searchCombo.getItems().addAll("Name", "ID");
	}
}
