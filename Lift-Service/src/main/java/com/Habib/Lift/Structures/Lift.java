/**
 * 
 */
package com.Habib.Lift.Structures;

import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * This class will be used to model a single lift.
 * 
 * @author Muhammed Hasan
 *
 */
public class Lift {
	private String id;
	private PriorityQueue<Floor> serviceQueue;
	private Floor currentFloor;
	private ControlPanel controlPanel;
	private double capacity, currentLoad;
	private boolean doorOpen;

	public Lift(String id, double capacity) {
		this.id = id;
		this.capacity = capacity;
		this.controlPanel = null;
		this.doorOpen = true;
		this.currentLoad = 0.0;
		this.serviceQueue = new PriorityQueue<Floor>(new PriorityComparator<Floor>());
	}

	public void addFloorToService(Floor floorToAdd) {
		if (!serviceQueue.contains(floorToAdd)) {
			int currentFloorLevel = this.currentFloor.getFloorNumber();
			int liftToAddLevel = floorToAdd.getFloorNumber();

			int priority = Math.abs(currentFloorLevel - liftToAddLevel);

			floorToAdd.setCurrentPriority(priority);

			serviceQueue.add(floorToAdd);
		}
	}
	
	
	/**
	 * 
	 */
	public void outputServiceQueue() {
		Iterator<Floor> queueIter = serviceQueue.iterator();

		while (queueIter.hasNext()) {
			Floor current = queueIter.next();
			System.out.println(current.toString());
		}
	}

	/**
	 * @return the currentFloor
	 */
	public Floor getCurrentFloor() {
		return currentFloor;
	}

	/**
	 * @param currentFloor
	 *            the currentFloor to set
	 */
	public void setCurrentFloor(Floor currentFloor) {
		this.currentFloor = currentFloor;
	}

	/**
	 * @return the controlPanel
	 */
	public ControlPanel getControlPanel() {
		return controlPanel;
	}

	/**
	 * @param controlPanel
	 *            the controlPanel to set
	 */
	public void setControlPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
	}

	/**
	 * @return the doorOpen
	 */
	public boolean isDoorOpen() {
		return doorOpen;
	}

	/**
	 * @param doorOpen
	 *            the doorOpen to set
	 */
	public void setDoorOpen(boolean doorOpen) {
		this.doorOpen = doorOpen;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the capacity
	 */
	public double getCapacity() {
		return capacity;
	}

	/**
	 * @return the currentLoad
	 */
	public double getCurrentLoad() {
		return currentLoad;
	}

}
