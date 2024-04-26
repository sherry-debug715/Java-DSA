package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//Definition for Directed graph.
class DirectedGraphNode {
    int label;
    ArrayList<DirectedGraphNode> neighbors;
    DirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<DirectedGraphNode>();
    }
}
 
public class TopologicalSorting {
    // 拓扑排序可以这样理解：
    // graph = {0,1,2,3#1,4#2,4,5#3,4,5#4#5}， graph是有向图
    // 0的children nodes有1,2,3，如果把所有nodes都看成是课程的话，要想学习1，2，3课程就必须先
    // 学习0课程，而要想学习课程4和5又必须先学习课程1，2，3.
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> output = new ArrayList<>();
        // a hash map that hash key of child node, and value of number of parents nodes.  
        Map<DirectedGraphNode, Integer> levels = formLevels(graph);
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        // insert the root node to queue 
        for (Map.Entry<DirectedGraphNode, Integer> entry : levels.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }
        // bfs tranverse 
        while (!queue.isEmpty()) {
            DirectedGraphNode curNode = queue.poll();
            output.add(curNode);
            for (DirectedGraphNode node : curNode.neighbors) {
                levels.put(node, levels.get(node) - 1);
                if (levels.get(node) == 0) {
                    queue.offer(node);   
                }
            }
        }
        return output;
    }

    private Map<DirectedGraphNode, Integer> formLevels(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Integer> levels = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            levels.putIfAbsent(node, 0);
            for (DirectedGraphNode childNode : node.neighbors) {
                    levels.putIfAbsent(childNode, 0);
                    levels.put(childNode, levels.get(childNode) + 1);
            }
        }

        return levels;
    }
}
