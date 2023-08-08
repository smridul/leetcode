package myanswers;

import java.util.*;

class NameNodeGraph {
    String name;
    List<NameNodeGraph> neighbours;
    int freq;

    public NameNodeGraph(String name, int freq) {
        this.name = name;
        neighbours = new ArrayList<>();
        this.freq = freq;
    }
}

class Synonym {
    public String name1;
    public String name2;

    Synonym(String name1, String name2) {
        this.name1 = name1;
        this.name2 = name2;
    }
}

class NameFreq {
    public String name;
    public int freq;

    NameFreq(String name, int freq) {
        this.name = name;
        this.freq = freq;
    }
}


public class BabyName2 {
    public ArrayList<NameFreq> processUsingGraphApproach(ArrayList<Synonym> synonyms, ArrayList<NameFreq> nameFreqs) {

        HashMap<String, NameNodeGraph> map = new HashMap<>();
        for (NameFreq nameFreq : nameFreqs) {
            map.put(nameFreq.name, new NameNodeGraph(nameFreq.name, nameFreq.freq));
        }

        for (Synonym synonym : synonyms) {
            NameNodeGraph node1 = map.get(synonym.name1);
            NameNodeGraph node2 = map.get(synonym.name2);

            node1.neighbours.add(node2);
            node2.neighbours.add(node1);
        }

        Set<String> visited = new HashSet<>();
        ArrayList<NameFreq> result = new ArrayList<>();

        // traverse
        for(Map.Entry<String, NameNodeGraph> node: map.entrySet()){
            if(!visited.contains(node.getKey())){
                int count = dfs(node.getKey(), map, visited);
                result.add(new NameFreq(node.getKey(), count));
            }
        }
        return result;

    }


    int dfs(String name, Map<String, NameNodeGraph> map, Set<String> visited){
        NameNodeGraph current  = map.get(name);
        visited.add(current.name);
        int sum = current.freq;
        for(NameNodeGraph neighbour: current.neighbours){
            if(!visited.contains(neighbour.name)){
                sum+= dfs(neighbour.name, map, visited);
            }
        }
        return sum;
    }

}

