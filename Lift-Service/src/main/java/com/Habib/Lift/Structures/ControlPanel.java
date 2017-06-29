/**
 * 
 */
package com.Habib.Lift.Structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * @author Muhammed Hasan
 *
 */
public class ControlPanel {
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
		if (floors.contains(floor) && !floor.isSelected()) {
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
			Floor currentFloor = floors.get(currentIndex + 1);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setCurrentFloor(currentFloor);
			liftControlling.setCurrentFloor(currentFloor);
			
			System.out.println("Movement update: " + liftControlling.toString());
			
		}
	}
	
	/**
	 * Moves the lift down one level
	 */
	public void moveDown() {
		int currentIndex = floors.indexOf(currentFloor);
		
		if (currentIndex > 0) {
			Floor currentFloor = floors.get(currentIndex - 1);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setCurrentFloor(currentFloor);
			liftControlling.setCurrentFloor(currentFloor);
			
			System.out.println("Movement update: " + liftControlling.toString());
		}
	}
	

	/**
	 * Adds a floor to the service queue provided that it isn't already part of the queue.
	 * @param floorToAdd The floor to add
	 */
	private void addFloorToService(Floor floorToAdd) {
		if (!serviceQueue.contains(floorToAdd)) {
			int currentFloorLevel = this.currentFloor.getFloorNumber();
			int liftToAddLevel = floorToAdd.getFloorNumber();

			int priority = Math.abs(currentFloorLevel - liftToAddLevel);

			floorToAdd.setCurrentPriority(priority);

			serviceQueue.add(floorToAdd);
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

	public void run() {
		Floor targetFloor = serviceQueue.poll();
		
		int indexOfTarget = floors.indexOf(targetFloor);
		
		if (indexOfTarget > currentFloor.getFloorNumber()) {
			while (indexOfTarget > currentFloor.getFloorNumber()) {
				moveUp();
			} 
		} else {
			while (indexOfTarget < currentFloor.getFloorNumber()) {
				moveDown();
			}
		}
		
	}
}
