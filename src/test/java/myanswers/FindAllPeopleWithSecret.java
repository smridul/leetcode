package myanswers;

import org.junit.Test;

import java.util.*;

public class FindAllPeopleWithSecret {
    int [] parents;
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {

        Arrays.sort(meetings, new Comparator<int[]>(){

            public int compare(int []a, int[]b ){
                return Integer.compare(a[2], b[2]);
            }
        });

        parents = new int[n];

        for(int i=0; i < n; i++){
            parents[i] = i;
        }

        union(0, firstPerson);

        List<int[]> lastTimeMeetingSet = new ArrayList<>();
        for(int i=0; i < meetings.length; i++){
            if(i==0){
                lastTimeMeetingSet.add(meetings[i]);
            }else{

                if(meetings[i][2] == meetings[i-1][2]){
                    lastTimeMeetingSet.add(meetings[i]);
                }else{

                    processLastMeetingSet(lastTimeMeetingSet, n);

                    lastTimeMeetingSet = new ArrayList<>();
                    lastTimeMeetingSet.add(meetings[i]);
                }

            }
        }

        // in the end also process lastTimeMeetingSet set
        processLastMeetingSet(lastTimeMeetingSet, n);


        // iterate to find elements of groups
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i =0; i< n; i++){

            if(!map.containsKey(parents[i])){
                map.put(parents[i], new ArrayList<>());
            }

            map.get(parents[i]).add(i);
        }


        return map.get(0);

    }


    void processLastMeetingSet(List<int[]> lastTimeMeetingSet, int n){


        for(int i =0; i< lastTimeMeetingSet.size(); i++){
            union(lastTimeMeetingSet.get(i)[0], lastTimeMeetingSet.get(i)[1]);
        }

        //reset
        for(int i=0; i < n; i++){
            if(find(i)!=0){
                parents[i] = i;
            }
        }
    }


    int find(int node){
        if(parents[node]!=node){
            parents[node] = find(parents[node]);
        }

        return parents[node];
    }

    void union(int node1, int node2){
        int leader1 = find(node1);
        int leader2 = find(node2);

        if(leader1 == leader2){
            return ;
        }

        if(leader1 < leader2){
            parents[leader2] = leader1;
        }
        if(leader2 < leader1){
            parents[leader1] = leader2;
        }

    }


    @Test
    public void test()
    {

        int[][] meets = new int[][]{
                {1,4,3},
                {0,4,3}
        };
        System.out.println(findAllPeople(5, meets, 3));


    }




}
