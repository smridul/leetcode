package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by smridul on 6/9/18.
 */
public class WordTransformer {

    @Test
    public void test() {

        String[] validWords = {"maps", "tan", "tree", "apple", "cans", "help", "aped", "pree", "pret", "apes", "flat", "trap", "fret", "trip", "trie", "frat", "fril"};


        String origin = "tree";
        String dest = "flat";
        ArrayList<String> words = getTheWordsOfLength(origin.length(), validWords);

        ArrayList<String> steps = transform(origin, dest, words);
        for (int i = steps.size() - 1; i >= 0; i--) {
            System.out.print(steps.get(i) + " ");
        }

        System.out.println();

        steps = transformBfs(origin, dest, words);
        for (int i = steps.size() - 1; i >= 0; i--) {
            System.out.print(steps.get(i) + " ");
        }


    }

    public ArrayList<String> transform(String origin, String dest, ArrayList<String> words) {
        Graph graph = createGraph(words);
        Node start = graph.nodesLookup.get(origin);
        Node end = graph.nodesLookup.get(dest);
        ArrayList<String> steps = new ArrayList<>();
        search(start, end, steps);
        return steps;
    }

    public ArrayList<String> transformBfs(String origin, String dest, ArrayList<String> words) {
        Graph graph = createGraph(words);
        Node start = graph.nodesLookup.get(origin);
        Node end = graph.nodesLookup.get(dest);
        ArrayList<String> steps = new ArrayList<>();
        Queue<SearchNode> queue = new LinkedList<>();
        SearchNode searchNode = new SearchNode(start, null);
        queue.add(searchNode);
        searchBfs(end, steps, queue);
        return steps;
    }

    SearchNode searchBfs(Node toFind, ArrayList<String> steps, Queue<SearchNode> queue) {

        while (queue.size() != 0) {
            SearchNode searchNode = queue.remove();
            Node toProcess = searchNode.node;
            toProcess.visited = true;

            if (toProcess.name.equals(toFind.name)) {
                steps.add(toProcess.name);
                return searchNode;
            }

            for (Node n : toProcess.children) {
                if (!n.visited) {
                    queue.add(new SearchNode(n, toProcess));
                }
            }
            SearchNode ss = searchBfs(toFind, steps, queue);

            if (ss != null && ss.parentNode != null && ss.parentNode.name.equals(toProcess.name)) {
                steps.add(toProcess.name);
                return searchNode;
            } else {
                return ss;
            }

        }

        return new SearchNode(null, null);
    }

    boolean search(Node start, Node toFind, ArrayList<String> steps) {
        if (start == null) {
            return false;
        }
        if (start.name.equals(toFind.name)) {
            steps.add(start.name);
            return true;

        }
        start.visited = true;


        for (Node n : start.children) {
            boolean found = false;
            if (n != null && n.visited == false) {
                found = search(n, toFind, steps);
            }
            if (found) {
                steps.add(start.name);
                return true;
            }
        }
        return false;
    }

    public Graph createGraph(ArrayList<String> words) {

        Graph graph = new Graph();
        for (int i = 0; i < words.size(); i++) {
            for (int j = i + 1; j < words.size(); j++) {


                int distance = computeDistance(words.get(i), words.get(j));

                if (distance == 1) {

                    insertEdge(graph, words.get(i), words.get(j));
                }

            }
        }

        return graph;

    }

    private void insertEdge(Graph graph, String word1, String word2) {


        Node node1 = null;
        Node node2 = null;
        if (graph.nodesLookup.get(word1) != null) {
            node1 = graph.nodesLookup.get(word1);
        } else {

            node1 = new Node(word1);
            graph.nodesLookup.put(word1, node1);

        }


        if (graph.nodesLookup.get(word2) != null) {
            node2 = graph.nodesLookup.get(word2);
        } else {

            node2 = new Node(word2);
            graph.nodesLookup.put(word2, node2);

        }


        // add edge if not aready prsent
        if (!node1.children.contains(node2)) {
            node1.children.add(node2);
        }

        // add edge if not aready prsent
        if (!node2.children.contains(node1)) {
            node2.children.add(node1);
        }

    }

    private int computeDistance(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return -1;
        }
        int distance = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }


    public ArrayList<String> getTheWordsOfLength(int length, String[] words) {

        ArrayList<String> wordsOfLength = new ArrayList<>();
        for (String word : words) {
            if (word.length() == length) {
                wordsOfLength.add(word);
            }
        }
        return wordsOfLength;
    }

}


class SearchNode {
    Node node;
    Node parentNode;

    SearchNode(Node node, Node parentNode) {
        this.node = node;
        this.parentNode = parentNode;
    }
}


class Graph {
    public ArrayList<Node> nodes = new ArrayList<>();
    public HashMap<String, Node> nodesLookup = new HashMap<>();
}

class Node {
    public String name;
    public boolean visited;
    public ArrayList<Node> children = new ArrayList<>();

    public Node(String name) {
        this.name = name;
    }
}

class MaxSumPair {
    int sum;
    int startIndex;
    int rightIndex;
}




