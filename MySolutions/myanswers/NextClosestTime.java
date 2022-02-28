package myanswers;

import java.util.*;

public class NextClosestTime {
    public String nextClosestTime1(String time) {



        Set<Integer> set= new HashSet<>();


        int i=0;
        while(i < time.length()){

            if(i!=2){
                set.add(Integer.parseInt(time.substring(i, i+1)));
            }
            i++;

        }



        String s = time;
        while(true){

            s = getNext(s);
            if(isValid(s, set)){
                return s;
            }

        }

        //return "";
    }

    String getNext(String time){
        int hours = Integer.parseInt(time.substring(0, 2));
        int minutes = Integer.parseInt(time.substring(3, 5));


        minutes = minutes+1;
        hours = hours + minutes/60;
        hours = hours%24;
        minutes = minutes % 60;
        StringBuilder sb = new StringBuilder();


        if(hours < 10){
            sb.append("0");
        }
        sb.append(hours);
        sb.append(":");

        if(minutes<10){
            sb.append("0");
        }
        sb.append(minutes);
        return sb.toString();
    }

    boolean isValid(String time, Set<Integer> set){
        int i=0;
        while(i < time.length()){
            if(i!=2 && !set.contains(Integer.parseInt(time.substring(i, i+1)))){
                return false;
            }
            i++;
        }
        return true;

    }

























    public String nextClosestTime(String time) {

        Set<Integer> set= new HashSet<>();


        int i=0;
        while(i < time.length()){

            if(i!=2){
                set.add(Integer.parseInt(time.substring(i, i+1)));
            }
            i++;

        }

        List<Integer> list = new ArrayList<>(set);

        Collections.sort(list);


        i=time.length()-1;
        char[] ans = time.toCharArray();

        while(i >= 0){

            if(i!=2){

                int d = nextValid(time.substring(i, i+1), i, list, time);
                if(d==-1){
                    // asssing minimum
                    ans[i] = (char) (list.get(0) + '0');
                }else{
                    ans[i] = (char)(d+'0');
                    break;
                }

            }
            i--;

        }

        return new String(ans);
    }

    int nextValid(String s, int pos, List<Integer> list, String time){

        int digit = s.charAt(0) - '0';
        for(int i=0; i < list.size(); i++){

            if(list.get(i) > digit && isValidForPos(list.get(i), pos, time)){
                return list.get(i);
            }

        }

        return -1;
    }

    boolean isValidForPos(int digit, int pos, String time){

        if(pos == 3){
            return digit <=5;
        }else if (pos == 0){
            return digit<=2;
        }else if (pos == 1 && time.charAt(0)=='2'){
            return digit<=3;
        }
        return true;

    }
}
