package myanswers;

import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class ReconstructMatrix {

    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {

        int[][] result = new int[rowSum.length][colSum.length];

        PriorityQueue<MatrixSumNode> pq = new PriorityQueue<>(new Comparator<MatrixSumNode>() {
            @Override
            public int compare(MatrixSumNode o1, MatrixSumNode o2) {
                return o1.value - o2.value;
            }
        });

        HashMap<Integer, MatrixSumNode> rowMap = new HashMap<>();
        HashMap<Integer, MatrixSumNode> colMap = new HashMap<>();

        for (int i = 0; i < rowSum.length; i++) {
            MatrixSumNode node = new MatrixSumNode(rowSum[i], true, i);
            rowMap.put(i, node);
            pq.offer(node);
        }

        for (int i = 0; i < colSum.length; i++) {
            MatrixSumNode node = new MatrixSumNode(colSum[i], false, i);
            colMap.put(i, node);
            pq.offer(node);
        }

        while (!pq.isEmpty()) {
            MatrixSumNode node = pq.poll();
            if (node.value == 0) {
                continue;
            }

            if (node.isRow) {
                for (int i = 0; i < colSum.length; i++) {
                    if (result[node.number][i] == 0 && colSum[i] >= node.value) {
                        result[node.number][i] = node.value;
                        colSum[i] = colSum[i] - node.value;
                        rowSum[node.number] = 0;
                        MatrixSumNode toUpdate = colMap.get(i);
                        toUpdate.value = colSum[i];
                        break;
                    }

                }
            } else { // is column
                for (int i = 0; i < rowSum.length; i++) {
                    if (result[i][node.number] == 0 && rowSum[i] >= node.value) {
                        result[i][node.number] = node.value;
                        rowSum[i] = rowSum[i] - node.value;
                        colSum[node.number] = 0;
                        MatrixSumNode toUpdate = rowMap.get(i);
                        toUpdate.value = rowSum[i];
                        break;
                    }
                }
            }
        }
        return result;
    }


    @Test
    public void test() {
        int[] rowS = new int[]{22,23,45,35,38,23,16,28,30};
        int[] colS = new int[]{3,21,184,26,13,12,1};

        int[][] matrix = restoreMatrix(rowS, colS);

        for(int i=0; i<matrix.length; i++){
            for (int j=0; j< matrix[0].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }


        System.out.println("==========");
        for(int i=0; i<matrix.length; i++){
            int sum = 0;
            for (int j=0; j< matrix[0].length; j++){
                sum+=matrix[i][j];
            }
            System.out.println(sum);
        }

        System.out.println("==========");
        for(int j=0; j<matrix[0].length; j++){
            int sum = 0;
            for (int i=0; i< matrix.length; i++){
                sum+=matrix[i][j];
            }
            System.out.println(sum +" ");
        }

    }
}

class MatrixSumNode {

    int value;
    boolean isRow;
    int number;

    public MatrixSumNode(int value, boolean isRow, int number) {
        this.isRow = isRow;
        this.number = number;
        this.value = value;
    }
}
