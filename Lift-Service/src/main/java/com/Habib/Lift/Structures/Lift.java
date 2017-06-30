/**
 * 
 */
package com.Habib.Lift.Structures;

/**
 * This class will be used to model a single lift.
 * 
 * @author Muhammed Hasan
 *
 */
public class Lift {
	private String id;
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
		this.setCurrentFloor(controlPanel.getCurrentFloor());
	}

	/**
	 * @return the doorOpen
	 */
	public boolean isDoorOpen() {
		return doorOpen;
	}

	/**
	 * closes the lift door
	 */
	public void closeDoor() {
		System.out.println("Doors closing");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.doorOpen = false;

		// the lift will only run once the doors have closed.
		controlPanel.run();
	}

	/**
	 * closes the lift door
	 */
	public void openDoor() {
		System.out.println("Doors opening");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.doorOpen = true;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		closeDoor();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Lift [id=" + id + ", currentFloor=" + currentFloor + ", capacity=" + capacity + ", currentLoad="
				+ currentLoad + ", doorOpen=" + doorOpen + "]";
	}

}
