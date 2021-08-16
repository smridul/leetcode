package myanswers;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by smridul on 2/1/19.
 */
public class DepthFirstSearch {

    @Test
    public void test(){

        //  1-> 2, 5,
        // 2-> 3, 4
        // 5-> 6,7,8
        GraphVertex g3 = new GraphVertex(3);
        GraphVertex g4 = new GraphVertex(4);


        GraphVertex g2 = new GraphVertex(2);
        g2.edges.add(g3);
        g2.edges.add(g4);


        GraphVertex g6 = new GraphVertex(6);
        GraphVertex g7 = new GraphVertex(7);
        GraphVertex g8 = new GraphVertex(8);

        GraphVertex g5 = new GraphVertex(5);
        g5.edges.add(g6);
        g5.edges.add(g7);
        g5.edges.add(g8);


        GraphVertex g = new GraphVertex(1);
        g.edges.add(g2);
        g.edges.add(g5);

        // introduce cycle
        //g4.edges.add(g);
       // g8.edges.add(g);


        ArrayList<GraphVertex> list = new ArrayList<>();
        list.add(g);
        list.add(g2);
        list.add(g5);
        list.add(g3);
        list.add(g4);
        list.add(g6);
        list.add(g7);
        list.add(g8);


        for (GraphVertex v: list){
            dfs(v);
        }


        for (GraphVertex v: list){
            System.out.println(v.label  +  " min is:" + (v.min==Integer.MAX_VALUE ? "null": v.min));
        }

    }


    public int dfs( GraphVertex current){

        current.color = 1;
        current.min = Integer.MAX_VALUE;

        for(GraphVertex node: current.edges){
            int min = 0;
            if(node.color == 0){
                min = Math.min(current.min, Math.min(node.label, dfs(node)));
            } else {
                min = Math.min(current.min, Math.min(node.label, node.min));
            }
            current.min = Math.min(current.min, min);
        }
        current.color = 2;
        return current.min;
    }
}
