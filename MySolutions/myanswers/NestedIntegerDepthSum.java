package myanswers;

import leetcode_classes.NestedInteger;

import java.util.List;

public class NestedIntegerDepthSum {

    public int depthSum(List<NestedInteger> nestedList) {
        return depthSum(nestedList, 1);
    }

    public int depthSum(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                sum += nestedInteger.getInteger() * depth;
            } else {
                sum += depthSum(nestedInteger.getList(), depth + 1);
            }
        }
        return sum;
    }


}
