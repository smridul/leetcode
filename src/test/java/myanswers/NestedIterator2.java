package myanswers;

import leetcode_classes.NestedInteger;
import org.junit.Test;

import java.util.*;

public class NestedIterator2 implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> stack;
    Integer peeked = null;

    public NestedIterator2(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        Iterator<NestedInteger> iterator = nestedList.iterator();
        stack.push(iterator);
        setTheState();
    }

    public void setTheState() {
        if (!stack.isEmpty()) {
            Iterator<NestedInteger> stackTop = stack.peek();
            if (!stackTop.hasNext()) {
                //pop it
                stack.pop();
                setTheState();
            } else {
                NestedInteger item = stackTop.next();
                if (!item.isInteger()) {
                    Iterator<NestedInteger> iterator = item.getList().iterator();
                    stack.push(iterator);
                    setTheState();
                }else{
                    peeked = item.getInteger();
                }
            }
        }
    }


    @Override
    public boolean hasNext() {
        return peeked!=null;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            Integer returnvalue = peeked;
            peeked = null;
            setTheState();
            return returnvalue;
        }
        return null;
    }
}
