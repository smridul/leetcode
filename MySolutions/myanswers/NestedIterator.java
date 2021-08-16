package myanswers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class NestedIterator implements Iterator<Integer> {

    List<Integer> result = new ArrayList<>();
    int index = 0;

    public NestedIterator(List<NestedInteger> nestedList) {

        for (int i = 0; i < nestedList.size(); i++) {
            readMe(nestedList.get(i));
        }

    }

    public void readMe(NestedInteger nestedInteger) {
        if (nestedInteger.isInteger()) {
            result.add(nestedInteger.getInteger());
        } else {
            for (int i = 0; i < nestedInteger.getList().size(); i++) {
                readMe(nestedInteger.getList().get(i));
            }
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        return result.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < result.size();
    }
}

class NestedInteger {
    private Integer value;
    private List<NestedInteger> list;

    public boolean isInteger() {
        return value != null;
    }

    public Integer getInteger() {
        return value;
    }

    public List<NestedInteger> getList() {
        return list;
    }
}
