package myanswers;

import org.junit.Test;

import java.util.*;

public class MinStickers {
    public int minStickers(String[] stickers, String target) {

        Map<String, Integer> dp =new HashMap<>();
        dp.put("", 0);


        int[][] stickerCount = new int[stickers.length][26];

        for(int i=0; i < stickers.length; i++){
            for(char c : stickers[i].toCharArray()){
                stickerCount[i][c-'a']++;
            }

        }


        return dfs(target, 0, stickers, dp, stickerCount);
    }



    int dfs(String target, int startIndex, String[] stickers, Map<String, Integer> dp,  int[][] stickerCount){
        if(dp.containsKey(target)){
            return dp.get(target);
        }

        if(target.isEmpty()){
            return 0;
        }


        int ans = Integer.MAX_VALUE;

        int[] targetCount = new int[26];
        for(char c : target.toCharArray()){
            targetCount[c-'a']++;
        }


        for(int i=0; i < stickers.length; i++){

            if (stickerCount[i][target.charAt(0)-'a'] == 0) continue;
            //select this ith sticker if possible


            boolean choosen=false;
            StringBuilder sb= new StringBuilder();
            for(int k =0; k < 26; k++){
                if(targetCount[k] > 0){

                    if(stickerCount[i][k] > 0){
                        choosen = true;
                    }
                    int remainingCountInTarget = targetCount[k] - stickerCount[i][k];
                    while(remainingCountInTarget-- > 0){
                        sb.append( (char) (k+'a'));
                    }

                }
            }
            if(!choosen){
                // then sticker was of no use
                continue;
            }

            //choose again
            int min = dfs(sb.toString(), i, stickers, dp, stickerCount);

            if(min!= -1){
                ans = Math.min(ans, 1+ min);
            }

            // dont't choose this
            //automatically next i will simulate that part.


        }

        //
        if(ans!=Integer.MAX_VALUE){
            dp.put(target, ans);
            return ans;
        }else{
            return -1;
        }

    }






















    @Test
    public void test(){


        String [] stickers = new String[]{"heart","seven","consider","just","less","back","an","four","cost","kill","skin","happen","depend","broad","caught","fast","fig","way","under","print","white","war","sent","locate","be","noise","door","get","burn","quite","eight","press","eye","wave","bread","wont","short","cow","plain","who","well","drive","fact","chief","store","night","operate","page","south","once"};
        String target = "simpleexample";
        System.out.println(minStickers(stickers, target));
    }
}


