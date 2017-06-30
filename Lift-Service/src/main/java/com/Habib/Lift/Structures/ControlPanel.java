/**
 * 
 */
package com.Habib.Lift.Structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.PriorityQueue;

/**
 * This class will be used to model the control panel of a lift.
 * 
 * @author Muhammed Hasan
 *
 */
public class ControlPanel extends Observable {
	private Lift liftControlling;
	private PriorityQueue<Floor> serviceQueue;
	private ArrayList<Floor> floors;
	private Floor currentFloor;

	public ControlPanel(Lift liftControlling, Floor currentFloor) {
		this.floors = new ArrayList<Floor>();
		this.liftControlling = liftControlling;
		this.currentFloor = currentFloor;

		this.serviceQueue = new PriorityQueue<Floor>(new PriorityComparator<Floor>());
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

	// TODO: add select floor methods i.e. methods to use when selecting a floor
	// to go to.

	public boolean selectFloor(Floor floor) {
		if (floors.contains(floor) && floor.isSelected() == false) {
			// set the floor as being selected
			floor.setSelected(true);
			this.addFloorToService(floor);
			return true;
		}
		return false;
	}

	/**
	 * Moves the lift up one level
	 */
	public void moveUp() {
		int currentIndex = floors.indexOf(currentFloor);

		if (currentIndex < floors.size()) {
			// set the current floor as the one above
			Floor currentFloor = floors.get(currentIndex + 1);

			// a pause for a realistic affect
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// update the current floor
			setCurrentFloor(currentFloor);
			liftControlling.setCurrentFloor(currentFloor);

			System.out.println("--------------------------------------------------");
			System.out.println("Movement update up: " + liftControlling.getId());
			System.out.println("Current floor priority: " + liftControlling.getCurrentFloor().getCurrentPriority());
			System.out.println("Current level: " + liftControlling.getCurrentFloor().getFloorNumber());
			System.out.println("Door is open: " + liftControlling.isDoorOpen());
			System.out.println("--------------------------------------------------");

		}
	}

	/**
	 * Moves the lift down one level
	 */
	public void moveDown() {
		int currentIndex = floors.indexOf(currentFloor);

		if (currentIndex > 0) {
			// set the current floor to be the one lower down
			Floor currentFloor = floors.get(currentIndex - 1);

			// add a little pause for to make it seem more realistic
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// update the current floor
			setCurrentFloor(currentFloor);
			liftControlling.setCurrentFloor(currentFloor);

			System.out.println("--------------------------------------------------");
			System.out.println("Movement update down: " + liftControlling.getId());
			System.out.println("Current floor priority: " + liftControlling.getCurrentFloor().getCurrentPriority());
			System.out.println("Current level: " + liftControlling.getCurrentFloor().getFloorNumber());
			System.out.println("Door is open: " + liftControlling.isDoorOpen());
			System.out.println("--------------------------------------------------");

		}
	}

	/**
	 * Adds a floor to the service queue provided that it isn't already part of
	 * the queue.
	 * 
	 * @param floorToAdd
	 *            The floor to add
	 */
	private void addFloorToService(Floor floorToAdd) {
		// we will only add the floor if it hasn't already been added
		if (!serviceQueue.contains(floorToAdd)) {
			int currentFloorLevel = this.currentFloor.getFloorNumber();
			int liftToAddLevel = floorToAdd.getFloorNumber();

			int priority = Math.abs(currentFloorLevel - liftToAddLevel);

			floorToAdd.setCurrentPriority(priority);

			serviceQueue.add(floorToAdd);

			System.out.println("Floor " + floorToAdd + " added to service queue");
			setChanged();
			notifyObservers();
		}
	}

	/**
	 * Output the service queue as it is now.
	 */
	public void outputServiceQueue() {
		Iterator<Floor> queueIter = serviceQueue.iterator();

		while (queueIter.hasNext()) {
			Floor current = queueIter.next();
			System.out.println(current.toString());
		}
	}

	/**
	 * This method is called to have the lift start.
	 */
	public void run() {
		// check if the lift has been requested
		if (serviceQueue.isEmpty()) {
			System.out.println("No jobs");
			return;
		}

		// get the target floor and find the index of the floor in the array
		Floor targetFloor = serviceQueue.poll();
		System.out.println("Target: " + targetFloor);
		int indexOfTarget = floors.indexOf(targetFloor);

		// If the target is above, we will go up, otherwise we will go down.
		if (indexOfTarget > currentFloor.getFloorNumber()) {
			while (indexOfTarget > currentFloor.getFloorNumber()) {
				moveUp();
			}
			liftControlling.openDoor();
		} else if (indexOfTarget < currentFloor.getFloorNumber()) {
			while (indexOfTarget < currentFloor.getFloorNumber()) {
				moveDown();
			}
			liftControlling.openDoor();
		} else {
			System.out.println("Same level");
			liftControlling.openDoor();;
		}
		
		setChanged();
		notifyObservers();

	}

	public void closeDoor() {
		liftControlling.closeDoor();
	}

	public void openDoor() {
		liftControlling.openDoor();
	}

	public Iterator<Floor> getFloors() {
		return this.floors.iterator();
	}
}
