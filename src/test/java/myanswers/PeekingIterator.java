package myanswers;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PeekingIterator implements Iterator {
    Integer peekval;
    Iterator<Integer> iterator;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    public Integer peek() {
        if (peekval == null && !iterator.hasNext()) {
            throw new NoSuchElementException();
        }

        if (peekval != null) {
            return peekval;
        }

        peekval = iterator.next();
        return peekval;

    }

    @Override
    public Integer next() {
        if (peekval != null) {
            int val = peekval;
            peekval = null;
            return val;
        }
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext() || peekval != null;
    }
}
