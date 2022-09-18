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
import model.contact;
import javafx.scene.layout.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
//import application.Main;
import controller.Controller;

public class closeContactListPanel extends Tab {
	private Controller control;
	private TableView<contact> closeContactsTable;
	private TableColumn<contact, String> contact1Col, contact2Col, dateCol, contactIdCol;
	private static Label closeContactsListLabel, contactsSinceLabel;
	private static Button listButton, contactsSinceBtn;
	DatePicker datePick;
	
	public closeContactListPanel(Controller controlInput) {
		setText("Close Contacts List");
		
		control = controlInput;

		datePick = new DatePicker();
		
		closeContactsListLabel = new Label("----------------Close Contacts List----------------");
		contactsSinceLabel = new Label("since");
		
		closeContactsTable = new TableView<contact>();
	    HBox.setHgrow(closeContactsTable, Priority.ALWAYS);
		
	    contactIdCol = new TableColumn<>("Contact ID");
	    contactIdCol.setCellValueFactory(new PropertyValueFactory<contact, String>("contactid"));
		contact1Col = new TableColumn<>("Contact 1 ID");
		contact1Col.setCellValueFactory(new PropertyValueFactory<contact, String>("contact1id"));
		contact2Col = new TableColumn<>("Contact 2 ID");
		contact2Col.setCellValueFactory(new PropertyValueFactory<contact, String>("contact2id"));
		dateCol = new TableColumn<>("Date");
		dateCol.setCellValueFactory(new PropertyValueFactory<contact, String>("date"));
//		timeCol = new TableColumn<>("Time");
//		timeCol.setCellValueFactory(new PropertyValueFactory<contact, String>("Time"));
		closeContactsTable.getColumns().addAll(contact1Col, contact2Col, dateCol);
		
		listButton = new Button("List");
		listButton.setOnAction(e -> control.listContacts(closeContactsTable));
		contactsSinceBtn = new Button("Filter");
		contactsSinceBtn.setOnAction((e) -> {
		    control.listContactsSinceDate(closeContactsTable, datePick.getValue());
		    datePick.getEditor().clear();
		});
		
	    //Adding the elements to the scene
		HBox row1 = new HBox();
		row1.getChildren().addAll(closeContactsListLabel);
		HBox row2 = new HBox(10);
		row2.getChildren().addAll(contactsSinceLabel, datePick, contactsSinceBtn);
		HBox row3 = new HBox();
		row3.getChildren().addAll(closeContactsTable);
		HBox row4 = new HBox();
		row4.getChildren().addAll(listButton);
		
		VBox layout = new VBox(10);
	    layout.setPadding(new Insets(5, 5, 5, 5));
	    layout.getChildren().addAll(row1, row2, row3, row4);
	    
	    //showing the scene
	    setContent(layout);
	    
	    control.listContacts(closeContactsTable);
	}

}
