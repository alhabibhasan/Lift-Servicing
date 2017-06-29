/**
 * 
 */
package com.Habib.Lift.Run;

import com.Habib.Lift.Structures.ControlPanel;
import com.Habib.Lift.Structures.Floor;
import com.Habib.Lift.Structures.Lift;

/**
 * @author Muhammed Hasan
 *
 */
public class Driver {
	public static void main(String[] args) {
		Floor f0 = new Floor(0, false);
		Floor f1 = new Floor(1, false);
		Floor f2 = new Floor(2, false);
		Floor f3 = new Floor(3, false);
		Floor f4 = new Floor(4, false);
		Floor f5 = new Floor(5, false);

		Lift lift = new Lift("Lift 1", 120.00);

		ControlPanel controlPanel = new ControlPanel(lift, f3);

		lift.setControlPanel(controlPanel);

		controlPanel.addFloor(f0);
		controlPanel.addFloor(f1);
		controlPanel.addFloor(f2);
		controlPanel.addFloor(f3);
		controlPanel.addFloor(f4);
		controlPanel.addFloor(f5);

		controlPanel.selectFloor(f5);
		controlPanel.selectFloor(f2);
		controlPanel.selectFloor(f1);

		lift.closeDoor();
		
	}
}
