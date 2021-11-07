package myanswers;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PeekingIterator2 implements Iterator {
    Integer peekval;
    Iterator<Integer> iterator;

    public PeekingIterator2(Iterator<Integer> iterator) {
        if (iterator.hasNext()) {
            peekval = iterator.next();
        }
        this.iterator = iterator;
    }

    public Integer peek() {
        if (peekval == null) {
            throw new NoSuchElementException();
        }
        return peekval;
    }

    @Override
    public Integer next() {
        if (peekval == null && !iterator.hasNext()) {
            throw new NoSuchElementException();
        }

        int toReturn = peekval;
        if (!iterator.hasNext()) {
            peekval = null;
        } else {
            peekval = iterator.next();
        }
        return toReturn;
    }

    @Override
    public boolean hasNext() {
        return peekval == null;
    }
}
