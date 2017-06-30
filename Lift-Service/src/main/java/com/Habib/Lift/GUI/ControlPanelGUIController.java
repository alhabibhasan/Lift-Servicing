/**
 * 
 */
package com.Habib.Lift.GUI;

import java.net.URL;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import com.Habib.Lift.Structures.ControlPanel;
import com.Habib.Lift.Structures.Floor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

/**
 * The controller for the Control Panel GUI
 * 
 * @author Muhammed Hasan
 *
 */
public class ControlPanelGUIController implements Initializable, Observer {
	private ControlPanel controlPanel;
	@FXML
	private GridPane buttonsGrid;
	@FXML
	private Button closeDoor, openDoor;

	public ControlPanelGUIController(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
	}

	/**
	 * This method is part of the Initializable interface. It is executed when
	 * the GUI first opened up.
	 */
	public void initialize(URL location, ResourceBundle resources) {

		setFloorButtons();

		setCurrentFloorLabel();

		closeDoor.setOnAction(e -> {
			controlPanel.closeDoor();
		});

		openDoor.setOnAction(e -> {
			controlPanel.openDoor();
		});
	}

	/**
	 * 
	 */
	private void setCurrentFloorLabel() {
		buttonsGrid.getChildren().removeIf(e -> e instanceof Label);
		Label currentFloor = new Label(
				"Current floor: " + String.valueOf(controlPanel.getCurrentFloor().getFloorNumber()));

		System.out.println("setting floor label");
		
		GridPane.setConstraints(currentFloor, 0, controlPanel.getNoOfFloors() + 1);

		buttonsGrid.getChildren().add(currentFloor);
	}

	/**
	 * 
	 */
	private void setFloorButtons() {
		buttonsGrid.getChildren().removeIf(e -> e instanceof ToggleButton);
		Iterator<Floor> floors = controlPanel.getFloors();
		
		System.out.println("setting floor buttons");
		
		while (floors.hasNext()) {
			Floor f = floors.next();

			ToggleButton floorSelectButton = new ToggleButton(String.valueOf(f.getFloorNumber()));
			GridPane.setConstraints(floorSelectButton, 0, f.getFloorNumber());

			floorSelectButton.setUserData(f);
			floorSelectButton.setSelected(f.isSelected());
			floorSelectButton.setOnAction(e -> {
				if (!floorSelectButton.isSelected()) {
					floorSelectButton.setSelected(true);
				}
				controlPanel.selectFloor((Floor) floorSelectButton.getUserData());
			});

			buttonsGrid.setVgap(10.0);
			buttonsGrid.getChildren().addAll(floorSelectButton);
		}

	}

	public void setControlPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("notified");
		setCurrentFloorLabel();
		setFloorButtons();
	}

}
