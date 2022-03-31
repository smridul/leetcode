package myanswers;

import org.junit.Test;

public class RemoveAllAdjacents {

    public String removeDuplicates(String s) {
        if (s.length() <= 1) {
            return s;
        }

        int i = 0;
        int j = 1;
        char[] arr = s.toCharArray();
        while (j <= s.length() - 1) {
            if (i==-1 || arr[i] != arr[j]) {
                i++;
                arr[i] = arr[j];
                j++;
            } else {
                i--;
                j++;
            }
        }

        if (i == -1) {
            return "";
        } else {
            return new String(arr, 0, i+1);
        }
    }


    public String removeDuplicatesK(String s, int k) {
        if (s.length() <= k-1) {
            return s;
        }

        int i = 0;
        int j = 1;
        int[] count = new int[s.length()];
        count[0]=1;
        char[] arr = s.toCharArray();
        while (j <= s.length() - 1) {
            if (i<0 || arr[i] != arr[j]) {
                i++;
                arr[i] = arr[j];
                j++;
                count[i]=1;
            } else if(arr[i] == arr[j] && count[i] == k-1){
                i = i-(k-1);
                j++;
            }else{
                i++;
                arr[i] = arr[j];
                j++;
                count[i] = count[i-1]+1;
            }
        }

        if (i == -1) {
            return "";
        } else {
            return new String(arr, 0, i+1);
        }
    }

    @Test
    public void test() {
        System.out.println(removeDuplicatesK("aabb", 2));
    }

}
