package myanswers;

import org.junit.Test;


/**
 * Created by smridul on 2/5/19.
 */
public class HamiltonCycle {

    @Test
    public void test(){
        int graph1[][] = {
                {0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 1},
                {0, 1, 1, 1, 0},
        };
        int[] path = new int[graph1.length];
        for(int i=0; i< graph1.length; i++){
            path[i] = i;
        }


        // always assuming previous vertex as 0
        // starting with index 1
        if(isHamiltonianCycle(graph1, path, 1, 0)){
            printPath(path);
        }else{
            System.out.println("Not a Hamiltonian cycle");

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

        // always assuming previous vertex as 0
        if(isHamiltonianCycle(graph2, path, 1, 0)){
            printPath(path);
        }else{
            System.out.println("Not a Hamiltonian cycle");
        }
    }


    public void printPath(int [] path){
        System.out.println("Hamiltonian cycle");
        for(int a: path){
            System.out.print(a + " ");
        }
        System.out.print(path[0] + " ");
        System.out.println();
    }

    // this return after finding anyone
    // but if u want all store it in results
    public boolean isHamiltonianCycle(int[][] graph, int[] path, int index, int previousVertex){

        if(index == graph.length){
            if(graph[path[index-1]][path[0]] == 1){
                return true;
            }
            return false;
        }


        for(int i = index; i < graph.length; i++){
            // try all possibilities on  this index
            // may be for 1st vertex we don't need to try all possibilities because
            // we can't find with first vertex, we cannot find with any other vertex also
            // in first position because it is a cycle
            // but for hamiltonian path we will have to try all vertex as initial position

            if((previousVertex == -1 || graph[previousVertex][path[i]] == 1) && previousVertex!=path[i]) {

                // swap path[index] and path[i]
                int temp = path[index];
                path[index] = path[i];
                path[i] = temp;

                if(isHamiltonianCycle(graph, path, index+1, path[index])){
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
