package myanswers;


import org.junit.Test;

public class HamiltonianPath {
    @Test
    public void test() {
        int graph1[][] = {
                {0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 1},
                {0, 1, 1, 1, 0},
        };
        int[] path = new int[graph1.length];
        for (int i = 0; i < graph1.length; i++) {
            path[i] = i;
        }

        if (isHamiltonianPath(graph1, 0, path, -1)) {
            printPath(path);
        } else {
            System.out.println("Not a Hamiltonian path");

        }


        int graph2[][] = {
                {0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 0},
                {0, 1, 1, 0, 0},
        };
        path = new int[graph2.length];
        for(int i=0; i< graph2.length; i++){
            path[i] = i;
        }

        if (isHamiltonianPath(graph2, 0, path, -1)) {
            printPath(path);
        } else {
            System.out.println("Not a Hamiltonian path");

        }
    }

    public void printPath(int[] path) {
        System.out.println("Hamiltonian path");
        for (int a : path) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    // this return after finding anyone
    // but if u want all store it in results
    public boolean isHamiltonianPath(int[][] graph, int index, int[] path, int previousVertex) {
        if (index == graph.length) {
            return true;
        }

        for (int i = index; i < graph.length; i++) {
            // try all possibilities on  this index

            if ((previousVertex == -1 || graph[previousVertex][path[i]] == 1) && previousVertex != path[i]) {

                // swap path[index] and path[i]
                int temp = path[index];
                path[index] = path[i];
                path[i] = temp;

                if (isHamiltonianPath(graph, index + 1, path, path[index])) {
                    return true;
                }

                // unswap
                temp = path[index];
                path[index] = path[i];
                path[i] = temp;
            }
        }
        return false;
    }
}
