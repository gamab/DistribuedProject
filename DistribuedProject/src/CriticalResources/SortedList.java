package CriticalResources;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class SortedList<E> extends AbstractList<E> {

    private ArrayList<E> internalList = new ArrayList<E>();

    // Note that add(CRWaitingListCell e) in AbstractList is calling this one
    @Override 
    public void add(int position, E e) {
        internalList.add(e);
        Collections.sort(internalList, null);
    }
    
    @Override 
    public E remove(int position) {
        return internalList.remove(position);
    }
    
    @Override 
    public boolean remove(Object o) {
        return internalList.remove(o);
    }
    
    @Override
    public E get(int i) {
        return internalList.get(i);
    }

    @Override
    public int size() {
        return internalList.size();
    }

}