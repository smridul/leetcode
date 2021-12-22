package myanswers;

import org.junit.Test;

import java.util.*;

public class AccountMerge {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();
        Map<String, EmailNode> emailToNode = new HashMap<>();

        for (List<String> list : accounts) {
            String name = "";
            for (int i = 0; i < list.size(); i++) {
                String email = list.get(i);
                if (name == "") {
                    name = email;
                    continue;
                }

                EmailNode emailNode;
                if (!emailToName.containsKey(email)) {
                    emailToName.put(email, name);
                    emailNode = new EmailNode(email, 0);
                    emailToNode.put(email, emailNode);
                } else {
                    emailNode = emailToNode.get(email);
                }

                if (i >= 2) {
                    union(emailNode, emailToNode.get(list.get(i - 1)));
                }
            }
        }

        // nOw let generate the set
        Map<String, List<String>> ans = new HashMap();
        for (String email: emailToName.keySet()) {
            EmailNode leader= findSet(emailToNode.get(email));
            List<String> list = ans.getOrDefault(leader.email, new ArrayList<>());
            list.add(email);
            ans.put(leader.email, list);
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


    public void union(EmailNode node1, EmailNode node2) {

        EmailNode leader1 = findSet(node1);
        EmailNode leader2 = findSet(node2);

        if (leader1.rank > leader2.rank) {
            leader2.parent = leader1;
        } else {
            //make leader2 as leader
            leader1.parent = leader2;
            leader2.rank++;
        }
    }

    public EmailNode findSet(EmailNode node) {
        while (node.parent != node) {
            node = node.parent;
        }
        return node;
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
