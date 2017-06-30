/**
 * 
 */
package com.Habib.Lift.Run;

import com.Habib.Lift.GUI.ControlPanelGUIController;
import com.Habib.Lift.Structures.ControlPanel;
import com.Habib.Lift.Structures.Floor;
import com.Habib.Lift.Structures.Lift;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Muhammed Hasan
 *
 */
public class Driver extends Application{
	private static ControlPanel controlPanel;
	private static Stage stage;
	public static void main(String[] args) {
		Floor f0 = new Floor(0, false);
		Floor f1 = new Floor(1, false);
		Floor f2 = new Floor(2, false);
		Floor f3 = new Floor(3, false);
		Floor f4 = new Floor(4, false);
		Floor f5 = new Floor(5, false);
		
		Lift lift = new Lift("Lift 1", 120.00);

		controlPanel = new ControlPanel(lift, f3);

		lift.setControlPanel(controlPanel);

		controlPanel.addFloor(f0);
		controlPanel.addFloor(f1);
		controlPanel.addFloor(f2);
		controlPanel.addFloor(f3);
		controlPanel.addFloor(f4);
		controlPanel.addFloor(f5);
	
		launch(args);
	}

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		ControlPanelGUIController controlPanelGUIController = new ControlPanelGUIController(controlPanel);
		
		controlPanel.addObserver(controlPanelGUIController);
		
		controlPanelGUIController.setControlPanel(controlPanel);
		stage = primaryStage; // initialize value of stage.
		
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ControlPanelGUI.fxml"));
		
		loader.setController(controlPanelGUIController);
		
		AnchorPane anchorPane = loader.load();
		
		Scene scene = new Scene(anchorPane);
		scene.getStylesheets().add(getClass().getClassLoader().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
		stage.setResizable(false);
		
	}
	
	
}
