import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem1 {


    public List<Integer> mergeList(List<Integer> list1, List<Integer> list2, int maxLength) {

        if (maxLength <= 0) {
            return new ArrayList<>();
        }
        int i = 0;
        int j = 0;

        List<Integer> res = new ArrayList<>();

        while (i < list1.size() && j < list2.size()) {

            if (list1.get(i) < list2.get(j)) {
                res.add(list1.get(i));
                i++;
            } else {
                res.add(list2.get(j));
                j++;
            }

            if (res.size() == maxLength) {
                break;
            }
        }


        while (i < list1.size() && res.size() < maxLength) {
            res.add(list1.get(i));
            i++;
        }

        while (j < list2.size() && res.size() < maxLength) {
            res.add(list2.get(j));
            j++;
        }

        return res;
    }


    @Test
    public void test1() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = Arrays.asList(1, 2, 3);

        List<Integer> res = mergeList(list1, list2, 3);

        for (int i : res) {
            System.out.print(i + " ");
        }
    }

}
