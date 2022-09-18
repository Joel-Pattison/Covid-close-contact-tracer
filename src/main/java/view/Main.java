package view;
	
import java.io.IOException;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.scene.layout.*;


public class Main extends Application {
	
	//Variables
	private Stage mainScreen;
	private Controller control;
	
	public static void main(String[] args) {
		launch(args);
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public void start(Stage primaryStage) throws Exception{
		
		control = new Controller();
		
		//Creating the primary scene and its elements
		mainScreen = primaryStage;
		mainScreen.setTitle("Covid Close Contacts");
		

		BorderPane mainPane = new BorderPane();
		Group root = new Group();
		
		TabPane tabContainer = new TabPane();
		tabContainer.getTabs().add(new mainPanel(control));
		tabContainer.getTabs().add(new closeContactPanel(control));
		tabContainer.getTabs().add(new closeContactListPanel(control));
		
		mainPane.setCenter(tabContainer);
		
	    Scene scene = new Scene(root, 480, 550);
		root.getChildren().add(mainPane);
		
		mainPane.prefHeightProperty().bind(scene.heightProperty());
		mainPane.prefWidthProperty().bind(scene.widthProperty());
		
	    //showing the scene
	    mainScreen.setScene(scene);
	    mainScreen.show();
	}
}
