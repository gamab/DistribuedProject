package CriticalResources;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.PriorityQueue;


public class CRWaitingList extends AbstractList<CRWaitingListCell> {

	public CRWaitingList() {
		super();
	}


	// Note that add(E e) in AbstractList is calling this one
	@Override 
	public void add(int position, CRWaitingListCell e) {
		this.add(e);
		Collections.sort(this, new CRWaitingListCell());
	}

	@Override
	public CRWaitingListCell get(int i) {
		return this.get(i);
	}

	@Override
	public int size() {
		return this.size();
	}
	
	
	public String toString() {
		String result = new String();
		ListIterator<CRWaitingListCell> iter = this.listIterator();
		boolean first = true;

		while (iter.hasNext()) {
			if (first) {
				first = false;
				result = iter.next().toString();
			} else {
				result += ":::" + iter.next().toString();
			}			
		}

		return result;
	}

	public boolean fromString(String obj) {
		boolean result = true;
		CRWaitingListCell currentCell;

		//first empty the current list
		if (this.size() >= 1) {
			this.removeRange(0,this.size()-1);
		}

		//then split the string to have access to all cells
		String[] aux = obj.split(":::");

		//finally for all cells add them to the list
		for (int i = 0; i<aux.length && result; i++) {
			currentCell = new CRWaitingListCell();
			result = currentCell.fromString(aux[i]);
			if (result) {
				this.add(currentCell);
			}
		}
		return result;		
	}
}
