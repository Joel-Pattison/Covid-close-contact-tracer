package view;

import java.io.IOException;

import controller.Controller;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class closeContactPanel extends Tab {
	private Controller control;
	private static Label closeContactsLabel, user1Label, user2Label, dateLabel;
	private ComboBox<String> user1Combo;
	private ComboBox<String> user2Combo;
	private static Button addButton, updateButton;
	DatePicker datePick;
	
	public closeContactPanel(Controller controlInput) {
		setText("Close Contacts");
		
		control = controlInput;
		
		closeContactsLabel = new Label("----------------Close Contacts----------------");
		user1Label = new Label("First person");
		user2Label = new Label("Second Person");
		dateLabel = new Label("Date");
		
		datePick = new DatePicker();
		
		user1Combo = new ComboBox<String>();
		user2Combo = new ComboBox<String>();
		
		addButton = new Button("Add Close Contact");
		addButton.setOnAction((e) -> {
		    control.addContact(user1Combo.getValue(), user2Combo.getValue(), datePick.getValue());
		    datePick.getEditor().clear();
		    user1Combo.getSelectionModel().clearSelection();
		    user2Combo.getSelectionModel().clearSelection();
		});
		
		updateButton = new Button("Update Dropdown");
		updateButton.setOnAction((e) -> {
		    control.fillComboBox(user1Combo);
		    control.fillComboBox(user2Combo);
		});
		
		HBox row1 = new HBox();
		row1.getChildren().addAll(closeContactsLabel);
		HBox row2 = new HBox(10);
		row2.getChildren().addAll(user1Label, user1Combo, updateButton);
		HBox row3 = new HBox(10);
		row3.getChildren().addAll(user2Label, user2Combo);
		HBox row4 = new HBox(10);
		row4.getChildren().addAll(dateLabel, datePick);
		HBox row5 = new HBox(10);
		row5.getChildren().addAll(addButton);
		
		VBox layout = new VBox(10);
	    layout.setPadding(new Insets(5, 5, 5, 5));
	    layout.getChildren().addAll(row1, row2, row3, row4, row5);
	    
	    //showing the scene
	    setContent(layout);
	    //control.loadContacts(contactsTable);
	    control.fillComboBox(user1Combo);
	    control.fillComboBox(user2Combo);
	    //user1Combo.getItems().addAll("test");
	}
}
