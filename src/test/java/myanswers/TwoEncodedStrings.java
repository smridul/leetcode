package myanswers;

import org.junit.Test;

import java.util.Arrays;

public class TwoEncodedStrings {
    String s1;
    String s2;
    Boolean[][][] memo;


    public boolean possiblyEquals(String s1, String s2) {
        memo = new Boolean[s1.length()+1][s2.length()+1][2000];
        this.s1 = s1;
        this.s2 = s2;
        return dfs(0, 0, 0);
    }


    public boolean dfs(int i, int j, int diff) {

        if(memo[i][j][1000+diff] !=null){
            return memo[i][j][1000+diff] ;
        }

        boolean res=false;
        if(i>=s1.length() && j>=s2.length() && diff==0){
            res = true;
        }


        if(i < s1.length()){

            if(Character.isDigit(s1.charAt(i))){
                int count = 1;

                while(i+count-1 < s1.length() && count<=3){

                    if(!Character.isDigit(s1.charAt(i+count-1))){
                        break;
                    }

                    int num = Integer.parseInt(s1.substring(i, i+count));

                    if(dfs(i+count, j, diff+num)){
                        res=true;
                    }
                    count++;
                }




            }else{
                if(diff<0){

                    if(dfs(i+1, j, diff+1)){
                        res=true;
                    }

                }else if (diff==0 && j < s2.length() && s1.charAt(i) == s2.charAt(j)){

                    if(dfs(i+1, j+1, diff)){
                        res=true;
                    }
                }
            }

        }




        if(j < s2.length()){

            if(Character.isDigit(s2.charAt(j))){
                int count = 1;

                while(j+count-1 < s2.length() && count<=3){

                    if(!Character.isDigit(s2.charAt(j+count-1))){
                        break;
                    }

                    int num = Integer.parseInt(s2.substring(j, j+count));

                    if(dfs(i, j+count, diff-num)){
                        res=true;
                    }
                    count++;
                }




            }else{
                if(diff>0){

                    if(dfs(i, j+1, diff-1)){
                        res=true;
                    }

                }else if (diff==0 && i< s1.length() && s1.charAt(i) == s2.charAt(j)){

                    if(dfs(i+1, j+1, diff)){
                        res=true;
                    }
                }
            }
        }



        memo[i][j][diff+1000] = res;
        return res;
    }
    @Test
    public void test(){
        String s1 = "l123e";
        String s2 = "44";
        System.out.println(possiblyEquals(s1, s2));

    }
}
