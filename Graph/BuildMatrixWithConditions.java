package Graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// leetcode 2392
// fast solution 
public class BuildMatrixWithConditions {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        List<Integer>[] rowGraph = new ArrayList[k + 1]; 
        for(int i = 1 ; i < rowGraph.length; i ++) {
            rowGraph[i] = new ArrayList();
        }
        for(int [] rowCondition : rowConditions){ 
            rowGraph[rowCondition[0]].add(rowCondition[1]); 
        }

        List<Integer>[] colGraph = new ArrayList[k + 1]; 
        for(int i = 1 ; i < colGraph.length; i ++) {
            colGraph[i] = new ArrayList();
        }
        for(int [] colCondition : colConditions){
            colGraph[colCondition[0]].add(colCondition[1]); 
        }

        int[] visited = new int[k + 1];
        Deque<Integer> queue = new LinkedList<>(); 
        for(int i = 1; i < rowGraph.length; i++){ 
            if(!topSort(rowGraph, i, visited, queue)){
                return new int[0][0];
            }
        }

        
        int[] rowOrder = new int[k];
        int[] rowIndexMap = new int[k + 1]; 
        for(int i = 0; i < k; i++){ 
            int node = queue.pollLast(); 
            rowOrder[i] = node; //
            rowIndexMap[node] = i;
        }

        visited = new int[k + 1];
        queue = new LinkedList();
        for(int i = 1; i < colGraph.length; i++){
            if(!topSort(colGraph, i, visited, queue)){
                return new int[0][0];
            }
        }

        int[] colOrder = new int[k];
        int[] colIndexMap = new int[k+1];
        for(int i = 0; i < k; i++){
            int node = queue.pollLast();
            colOrder[i] = node;
            colIndexMap[node] = i;
        }

        int[][] result = new int[k][k];
        
        for(int i = 1; i <= k; i++){
            result[rowIndexMap[i]][colIndexMap[i]] = i;
        }

        return result;

    }

    public boolean topSort(List<Integer>[] graph, int node, int[] visited, Deque<Integer> queue){
        if(visited[node] == 2) {
            return false;
        }
        if(visited[node] == 0){
            visited[node] = 2;
            for(int child : graph[node]){
                if(!topSort(graph, child, visited, queue)){
                    return false;
                }
            }
            visited[node] = 1;
            queue.add(node);
        }
        return true;
    }
}
// my solution 
class Solution {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[][] res = new int[k][k];
        List<Integer>[] rowGraph = buildGraph(rowConditions, k);
        List<Integer>[] colGraph = buildGraph(colConditions, k);
        int[] rowOrder = topologicalOrder(rowGraph, k);
        int[] colOrder = topologicalOrder(colGraph, k);
        if (rowOrder == null || colOrder == null) {
            return new int[0][0];
        }

        int[] rowIdx = new int[k + 1];
        int[] colIdx = new int[k + 1];
        for (int i = 0; i < k; i++) {
            rowIdx[rowOrder[i]] = i;
            colIdx[colOrder[i]] = i;
        }

        for (int num = 1; num <= k; num ++) {
            res[rowIdx[num]][colIdx[num]] = num;
        }
        return res;
    }

    private List<Integer>[] buildGraph(int[][] positions, int size) {
         List<Integer>[] graph = new List[size + 1];
         for (int i = 1; i <= size; i++) {
            graph[i] = new ArrayList<Integer>();
         }

         for (int[] pos : positions) {
            graph[pos[0]].add(pos[1]);
         }

         return graph;
    }

    private int[] topologicalOrder(List<Integer>[] graph, int size) {
        int[] degree = new int[size + 1];
        for (int i = 1; i <= size; i++) {
            for (int neighbor : graph[i]) {
                degree[neighbor] += 1;
            }
        }

        // find order 
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= size; i++) {
            if (degree[i] == 0) {
                queue.addLast(i);
            }
        } 
        int[] order = new int[size]; 
        int idx = 0;
        while (!queue.isEmpty()) {
            int cur = queue.removeFirst();
            order[idx++] = cur;
            for (int next : graph[cur]) {
                degree[next] -= 1;
                if (degree[next] == 0) {
                    queue.addLast(next);
                }
            }
        }

        if (idx != size) {
            return null;
        }

        return order;
    }
}

