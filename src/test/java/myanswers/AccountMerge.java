package myanswers;

import org.junit.Test;

import java.util.*;

public class
AccountMerge {


    public List<List<String>> accountsMerge(List<List<String>> accounts) {


        Map<String, String> nameNode = new HashMap<>();

        int counter=0;
        for(List<String> list :  accounts){
            String name = list.get(0) + ":"+ counter;
            nameNode.put(name, name);
            counter++;
        }
        counter=0;
        Map<String, String> map = new HashMap<>();
        for (List<String> list :  accounts){
            String group = list.get(0) + ":" + counter;

            for(int i=1; i< list.size(); i++){

                String email = list.get(i);
                if (map.containsKey(email)){
                    String name = map.get(email);
                    union(name, group, nameNode);

                }else{
                    map.put(email, group);
                }
            }
            counter++;
        }


        Map<String, List<String>> output = new HashMap<>();
        for(Map.Entry<String, String> entry : map.entrySet()){

            String email = entry.getKey();
            String groupName = entry.getValue();
            groupName = findParent(nameNode, groupName);
            if(output.containsKey(groupName)){
                List<String> list = output.get(groupName);
                list.add(email);
            }else{
                List<String> list = new ArrayList<>();
                list.add(email);
                output.put(groupName, list);
            }
        }

        List<List<String>> answer = new ArrayList<>();

        for(Map.Entry<String, List<String>> entry : output.entrySet()){

            String group = entry.getKey();
            group=  group.substring(0, group.indexOf(":"));
            List<String> list = entry.getValue();
            Collections.sort(list);

            List<String> outputList = new ArrayList<>();
            outputList.add(group);
            outputList.addAll(list);
            answer.add(outputList);
        }
        return answer;
    }


    void union(String str1, String str2, Map<String, String> nameNode){

        String parent1 = findParent(nameNode, str1);
        String parent2 = findParent(nameNode, str2);
        if(!parent1.equals(parent2)){
            nameNode.put(parent1, parent2);
        }

    }

//    public String findParent( Map<String, String> nameNode, String n) {
//        while (nameNode.get(n) != n) {
//            n= nameNode.get(n);
//        }
//        return nameNode.get(n);
//    }

    public String findParent( Map<String, String> nameNode, String n) {
        if(!nameNode.get(n).equals(n)){
            nameNode.put(n, findParent(nameNode, nameNode.get(n)));
        }
        return nameNode.get(n);
    }















































    Map<String, EmailNode> emailToNode = new HashMap<>();

    public List<List<String>> accountsMerge2(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();

        for (List<String> list : accounts) {
            String name = "";
            for (int i = 0; i < list.size(); i++) {
                String email = list.get(i);
                if (name == "") {
                    name = email;
                    continue;
                }

                if (!emailToName.containsKey(email)) {
                    emailToName.put(email, name);
                    makeSet(email);
                }

                if (i >= 2) {
                    union(email, list.get(i - 1));
                }
            }
        }

        // nOw let generate the set
        Map<String, List<String>> ans = new HashMap();
        for (String email: emailToName.keySet()) {
            String leader= findSet(email);
            List<String> list = ans.getOrDefault(leader, new ArrayList<>());
            list.add(email);
            ans.put(leader, list);
        }

        for (List<String> component: ans.values()) {
            Collections.sort(component);
            component.add(0, emailToName.get(component.get(0)));
        }

        return new ArrayList(ans.values());
    }

    @Test
    public void test() {

        // [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]

        List<List<String>> accounts = new ArrayList<>();

        List<String> list1 = Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com");
        accounts.add(list1);

        List<String> list2 = Arrays.asList("John","johnsmith@mail.com","john00@mail.com");
        accounts.add(list2);

        List<String> list3 = Arrays.asList("Mary","mary@mail.com");
        accounts.add(list3);

        List<String> list4 = Arrays.asList("John","johnnybravo@mail.com");
        accounts.add(list4);


        List<List<String>> res = accountsMerge(accounts);
        for(List<String> list: res){
            for(String s: list){
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }


    public void union(String node1, String node2) {

        String l1 = findSet(node1);
        String l2 = findSet(node2);
        EmailNode leader1 = emailToNode.get(l1);
        EmailNode leader2 = emailToNode.get(l2);

        if (leader1.rank > leader2.rank) {
            leader2.parent = leader1;
        } else {
            leader1.parent = leader2;
        }
        if(leader1.rank == leader2.rank){
            // assume leader2 as parent in case of tie
            leader2.rank++;
        }
    }

    public String findSet(String n) {
        EmailNode node = emailToNode.get(n);
        while (node.parent != node) {
            node = node.parent;
        }
        return node.email;
    }

    public void makeSet(String email) {
       EmailNode emailNode = new EmailNode(email, 0);
       emailToNode.put(email, emailNode);
    }
}

class EmailNode {
    String email;
    int rank;
    EmailNode parent;

    public EmailNode(String email, int rank) {
        this.email = email;
        this.rank = rank;
        this.parent = this;
    }
}
