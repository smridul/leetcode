package myanswers;

import org.junit.Test;

public class NextGreatest3 {
    public int nextGreaterElement(int n) {


        char[] arr = String.valueOf(n).toCharArray();


        boolean found = false;

        for(int i = arr.length-1 ; i >=0 ; i--){

            if(i!=arr.length-1 && arr[i] < arr[i+1]){
                int k = arr.length-1;
                while(arr[k] <= arr[i]){
                    k--;
                }
                swap(arr, k, i);
                reverse(arr, i+1, arr.length-1);
                found =true;
                break;
            }
        }


        if(found){

            double d = Double.parseDouble(new String(arr));
            if(d > Integer.MAX_VALUE){
                return -1;
            }
            return  Integer.parseInt(new String(arr));

        }

        return -1;
    }

    void swap(char[] arr, int i , int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    void reverse(char[] arr, int i , int j){
        while(i < j){
            swap(arr, i, j);
            i++;
            j--;
        }
    }

    @Test
    public void test(){
        System.out.println(nextGreaterElement(230241));

    }
}
