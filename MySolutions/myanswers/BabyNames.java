package myanswers;

import org.junit.Test;

import java.util.*;

/**
 * Created by smridul on 6/4/18.
 */
public class BabyNames {


    @Test
    public void babyNames() {

        ArrayList<NameFreq> names = new ArrayList<>();
        names.add(new NameFreq("john", 10));
        names.add(new NameFreq("jon", 3));

        names.add(new NameFreq("davis", 2));
        names.add(new NameFreq("kari", 3));
        names.add(new NameFreq("johny", 11));

        names.add(new NameFreq("carlton", 8));
        names.add(new NameFreq("carleton", 2));
        names.add(new NameFreq("jonathan", 9));
        names.add(new NameFreq("carrie", 5));



        ArrayList<Synonym> synonyms = new ArrayList<>();
        synonyms.add(new Synonym("jonathan", "john"));
        synonyms.add(new Synonym("jon", "johny"));
        synonyms.add(new Synonym("johny", "john"));


        synonyms.add(new Synonym("kari", "carrie"));
        synonyms.add(new Synonym("carleton", "carlton"));
        process(synonyms ,names);
    }


    public void process(ArrayList<Synonym> synonyms, ArrayList<NameFreq> nameFreqs) {

        HashMap<String, NameNode> map = new HashMap<>();
        for (NameFreq nameFreq : nameFreqs) {
            map.put(nameFreq.name, new NameNode(nameFreq.name, nameFreq.freq));
        }

        for (Synonym synonym : synonyms) {

            if(map.get(synonym.name1)==null || map.get(synonym.name2) ==null){
                continue;
            }

            NameNode leader1 = findSet(map.get(synonym.name1));
            NameNode leader2 = findSet(map.get(synonym.name2));


            if(leader1.rank > leader2.rank){
                leader2.parent = leader1;
                leader1.rank += leader2.rank;
            }else{
                leader1.parent = leader2;
                leader2.rank += leader1.rank;
            }
        }


        HashSet<NameNode>  set = new HashSet<>();

        for(Map.Entry<String, NameNode> entry: map.entrySet()){

            set.add(findSet(entry.getValue()));
        }
        for(NameNode node: set){
            System.out.print(node.name + " "+node.rank);
            System.out.println();
        }
    }


    public NameNode findSet(NameNode node) {
        while(node.parent != node){
            node = node.parent;
        }
        return node;
    }

    class NameFreq {
        public String name;
        public int freq;

        NameFreq(String name, int freq) {
            this.name = name;
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


    class NameNode {
        String name;
        int rank;
        NameNode parent;

        public NameNode(String name, int rank) {
            this.name = name;
            this.rank = rank;
            this.parent = this;
        }
    }
}

