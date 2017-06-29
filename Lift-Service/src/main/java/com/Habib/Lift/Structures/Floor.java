/**
 * 
 */
package com.Habib.Lift.Structures;

/**
 * This class will be used to model a single floor.
 * @author Muhammed Hasan
 *
 */
public class Floor {
	private int floorNumber, currentPriority;
	private boolean locked, selected;
	/**
	 * @param floorNumber
	 * @param locked
	 * @param selected
	 */
	public Floor(int floorNumber, boolean locked) {
		this.floorNumber = floorNumber;
		this.currentPriority = -1;
		this.locked = locked;
		this.selected = false;
	}
	/**
	 * @return the locked
	 */
	public boolean isLocked() {
		return locked;
	}
	/**
	 * @param locked the locked to set
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}
	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	/**
	 * @return the floorNumber
	 */
	public int getFloorNumber() {
		return floorNumber;
	}
	/**
	 * @return the currentPriority
	 */
	public int getCurrentPriority() {
		return currentPriority;
	}
	/**
	 * @param currentPriority the currentPriority to set
	 */
	public void setCurrentPriority(int currentPriority) {
		this.currentPriority = currentPriority;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Floor [floorNumber=" + floorNumber + ", currentPriority=" + currentPriority + ", locked=" + locked
				+ ", selected=" + selected + "]";
	}
	
	
	
	
}
