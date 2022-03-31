package myanswers;

import leetcode_classes.NestedInteger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NestedIteratorTest {

    @Test
    public void test() {
        NestedInteger first1 = new NestedInteger(1);
        NestedInteger first2 = new NestedInteger(1);


        NestedInteger first = new NestedInteger(Arrays.asList(first1, first2));

        NestedInteger second = new NestedInteger(2);

        NestedInteger three1 = new NestedInteger(1);
        NestedInteger three2 = new NestedInteger(1);

        NestedInteger three = new NestedInteger(Arrays.asList(three1, three2));

        List<NestedInteger> finallist = new ArrayList<>();
        finallist.add(first);
        finallist.add(second);
        finallist.add(three);


        NestedIterator2 nestedIterator2 = new NestedIterator2(finallist);


        Integer ii = nestedIterator2.next();
        Integer ii2 = nestedIterator2.next();

        int a=0;

    }
}
