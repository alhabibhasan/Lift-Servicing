/**
 * 
 */
package com.Habib.Lift.Structures;

import java.util.Comparator;

/**
 * @author Muhammed Hasan
 *
 */
public class PriorityComparator<E> implements Comparator<Floor> {

	public int compare(Floor f1, Floor f2) {
		return Integer.compare(f1.getCurrentPriority(),f2.getCurrentPriority());
	}

}
