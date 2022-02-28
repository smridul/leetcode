package myanswers;

import org.junit.Test;

import java.util.*;

public class ReconstructItenary {
    int edges=0;
    public List<String> findItinerary(List<List<String>> tickets) {


        // all possible dfs from jfk till all edges are covered
        // if all edges are covered then it is our answer case

        edges = tickets.size();


        Map<String, List<String>> map = new HashMap<>();
        Map<String, Set<String>> visited = new HashMap<>();


        for(List<String> ticket : tickets){
            String s1 = ticket.get(0);
            String s2 = ticket.get(1);

            if(!map.containsKey(s1)){
                map.put(s1, new ArrayList<>());
                visited.put(s1, new HashSet<>());
            }
            map.get(s1).add(s2);
        }


        for(String key :  map.keySet()){

            Collections.sort(map.get(key));
        }


        List<String> ans = new ArrayList<>();
        ans.add("JFK");
        dfs("JFK", map,visited, ans);
        return ans;
    }



    boolean dfs(String source, Map<String, List<String>> map,  Map<String, Set<String>> visited, List<String> ans){

        if(ans.size() == edges+1){
            // No more backtracking after this point
            return true;
        }




        if(!map.containsKey(source)){
            return false;
        }


        for(String dest :  map.get(source)){

            if(! (visited.get(source).contains(dest))){
                ans.add(dest);
                visited.get(source).add(dest);
                if(dfs(dest, map,visited,  ans)){
                    return true;
                }
                ans.remove(ans.size()-1);
                visited.get(source).remove(dest);
            }



        }

        return false;
    }


    @Test
    public  void test(){
        String[][] arr = new String[][]
                {{"EZE","AXA"},{"TIA","ANU"},{"ANU","JFK"},{"JFK","ANU"},{"ANU","EZE"},{"TIA","ANU"},{"AXA","TIA"},{"TIA","JFK"},{"ANU","TIA"},{"JFK","TIA"}
                };

        List<List<String>> tickets = new ArrayList<>();
        for(String[] ticket: arr){

            List<String> l = new ArrayList<>();
            l.add(ticket[0]);
            l.add(ticket[1]);
            tickets.add(l);
        }

        System.out.println(findItinerary(tickets));
    }
}
