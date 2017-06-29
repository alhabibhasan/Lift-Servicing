/**
 * 
 */
package com.Habib.Lift.Structures;

import java.util.ArrayList;

/**
 * @author Muhammed Hasan
 *
 */
public class ControlPanel {
	private Lift liftControlling;
	private ArrayList<Floor> floors;
	private Floor currentFloor;

	public ControlPanel(Lift liftControlling, Floor currentFloor) {
		this.floors = new ArrayList<Floor>();
		this.liftControlling = liftControlling;
		this.currentFloor = currentFloor;
	}

	public boolean addFloor(Floor floorToAdd) {
		return this.floors.add(floorToAdd);
	}

	public boolean removeFloor(Floor floorToRemove) {
		return this.floors.remove(floorToRemove);
	}

	public int getNoOfFloors() {
		return this.floors.size();
	}

	/**
	 * @return the currentFloor
	 */
	public int getCurrentFloor() {
		return currentFloor.getFloorNumber();
	}

	/**
	 * @param currentFloor
	 *            the currentFloor to set
	 */
	public void setCurrentFloor(Floor currentFloor) {
		this.currentFloor = currentFloor;
	}

	// TODO: add select floor methods i.e. methods to use when selecting a floor
	// to go to.

	public boolean selectFloor(Floor floor) {
		if (floors.contains(floor)) {
			liftControlling.addFloorToService(floor);
			return true;
		}
		return false;
	}
}
