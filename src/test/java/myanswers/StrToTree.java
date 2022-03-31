package myanswers;

import org.junit.Test;

public class StrToTree {

    public TreeNode str2tree1(String s) {

        if(s==null || s.length()==0){
            return null;
        }

        int sign=1;
        int index = 0;
        if(s.charAt(0)=='-'){
            sign=-1;
            index++;
        }
        int num =0;
        while (index < s.length() && Character.isDigit(s.charAt(index))){
            num = num*10+ s.charAt(index)-'0';
            index++;
        }
        num = num*sign;

        TreeNode root = new TreeNode(num);
        int j=index+1;
        if(index < s.length() && s.charAt(index) == '('){
            int count =1;
            while (true){
                if(s.charAt(j) == '(') count++;
                if(s.charAt(j) == ')') count--;
                if(count == 0){
                    break;
                }
                j++;
            }
            root.left = str2tree1(s.substring(index+1, j));
        }

        index = j+1;
        j=index+1;

        if(index < s.length() && s.charAt(index) == '('){
            int count =1;
            while (true){
                if(s.charAt(j) == '(') count++;
                if(s.charAt(j) == ')') count--;
                if(count == 0){
                    break;
                }
                j++;
            }
            root.right = str2tree1(s.substring(index+1, j));
        }


        return root;
    }



    public TreeNode str2tree(String s) {
        return str2treeRec(s);
    }
    int index=0;

    public TreeNode str2treeRec(String s){
        if(s==null || s.length()==0 || index >=s.length()){
            return null;
        }

        int sign=1;
        if(s.charAt(index)=='-'){
            sign=-1;
            index++;
        }
        int num =0;
        while (index < s.length() && Character.isDigit(s.charAt(index))){
            num = num*10+ s.charAt(index)-'0';
            index++;
        }
        num = num*sign;

        TreeNode root = new TreeNode(num);


        if(index < s.length() && s.charAt(index)=='('){
            index++;
            root.left = str2treeRec(s);
        }


        if(index < s.length() && s.charAt(index)=='('){
            index++;
            root.right = str2treeRec(s);
        }


        if(index < s.length() && s.charAt(index)==')'){
            index++;
        }
        return root;
    }


    @Test
    public void test() {

        //System.out.println(str2tree("4"));

        System.out.println(str2tree("4(2(3)(1))(6(5))"));
    }


}
