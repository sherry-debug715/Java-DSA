package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
// Time: O(N)
// Space: O(N)
public class SearchGraphNodes {
    public UndirectedGraphNode searchNode(ArrayList<UndirectedGraphNode> graph,
                                        Map<UndirectedGraphNode, Integer> values,
                                        UndirectedGraphNode node,
                                        int target) {
    if (node == null || values == null) {
        return null;
    }

    Queue<UndirectedGraphNode> queue = new LinkedList<>();
    queue.offer(node);

    while (!queue.isEmpty()) {
        UndirectedGraphNode curNode = queue.poll();
        // check if curNode is the target 
        if (values.get(curNode) == target) {
            return curNode;
        }
        // go one level deeper 
        for (UndirectedGraphNode nbor : curNode.neighbors) {
            queue.offer(nbor);
        }
    }

    return null;
}
}
