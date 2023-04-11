package csc335.ToDoList;

import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * JavaFX App
 */
public class ToDoListGUI extends Application {

	BorderPane pane;
	private ToDoList toDoList;
	private ObservableList<String> list;
	private ListView<String> listView;
	private Alert openAlert;
	private Alert closeAlert;
	

	public static void main(String[] args) {
        launch();
    }
	
    @Override
    public void start(Stage stage) {
    	
    	stage.setTitle("ToDo List");
        
    	initializeOpenAlert();

        var scene = new Scene(pane, 500, 150);
        stage.setScene(scene);
        stage.show();
        
        stage.setOnCloseRequest(new CloseHandler());

    }
    
    private void initializeOpenAlert() {
    	openAlert = new Alert(AlertType.CONFIRMATION);
    	openAlert.setHeaderText("Click cancel to start with zero todos");
    	openAlert.setContentText("Start with hard coded ToDo list?");
    	Optional<ButtonType> result = openAlert.showAndWait();
    	
    	if (result.get() == ButtonType.OK) {
    		initializeLoadedList();
    	}
    	else {
    		initializeNewList();
    	}
    }
    
    private void initializeCloseAlert() {
    	closeAlert = new Alert(AlertType.CONFIRMATION);
        closeAlert.setHeaderText("Click cancel to not save any changes");
        closeAlert.setContentText("Click OK to to save the current ToDo list");
        Optional<ButtonType> result = closeAlert.showAndWait();
        
        if (result.get() == ButtonType.OK) {
        	
        }
        else {
        	for (int i = 0; i < toDoList.getList().size(); i ++) {
        		toDoList.removeItem(toDoList.getList().get(i));
        	}
        }
        
        Platform.exit();
        System.exit(0);
        
    }
    
    private void initializeNewList() {
    	
    	toDoList = new ToDoList();
    	
    	for (int i = 0; i < toDoList.getList().size(); i ++) {
    		toDoList.removeItem(toDoList.getList().get(i));
    	}
    	
    	list = FXCollections.observableArrayList(toDoList.getList());
    	
    	listView = new ListView<String>();
    	listView.setItems(list);
    	
    	pane = new BorderPane();
    	pane.setCenter(listView);
    }
    
    private void initializeLoadedList() {
    	
    	toDoList = new ToDoList();
    	
    	list = FXCollections.observableArrayList(toDoList.getList());
    	
    	listView = new ListView<String>();
    	listView.setItems(list);
    	
    	pane = new BorderPane();
    	pane.setCenter(listView);
    }  
    
    private class CloseHandler implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent event) {
			initializeCloseAlert();
		}
    	
    }

}