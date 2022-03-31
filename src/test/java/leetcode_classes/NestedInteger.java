package leetcode_classes;

import java.util.List;


public class NestedInteger {
    private Integer value;
    private List<NestedInteger> list;

    public NestedInteger(int v) {
        value = v;
    }

    public NestedInteger(List<NestedInteger> list) {
        this.list = list;
    }

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