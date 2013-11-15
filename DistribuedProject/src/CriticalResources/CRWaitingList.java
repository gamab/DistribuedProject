package CriticalResources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;


public class CRWaitingList extends SortedList<CRWaitingListCell> {

	public CRWaitingList() {
		super();
	}
	
    public Integer getPosition(CRWaitingListCell e) {
    	ListIterator<CRWaitingListCell> it = this.listIterator();
    	Integer result = null;
    	int i = 0;
    	while (it.hasNext() && result==null){
    		CRWaitingListCell current = it.next();
    		if (current.equals(e)) {
    			System.out.println("In CRWaitingList : " + current + " == " + e);
    			result = i;
    		} else {
    			System.out.println("In CRWaitingList : " + current + " != " + e);
    			i++;
    		}
    	}
		return result;
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

		if (obj.contains(":::")) {
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
		} else {
			result = false;
		}
		return result;		
	}



}
