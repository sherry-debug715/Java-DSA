package DFS;
// Lintcode 1862
/*
Constrains:
2≤n≤10^5  only O(n) time could pass, opt out recursion
0≤father[i]<n,father[0]=−1
1≤times[i]≤1000,time[0]=−1
*/ 

import Sort.SortTheJumbledNumbers.java.ArrayList;

// recursion
public class TimeToFlowerTree {
        /**
     * @param father: the father of every node
     * @param time: the time from father[i] to node i
     * @return: time to flower tree
     */
    public int timeToFlowerTree(int[] father, int[] time) {
        int n = father.length; // total of n nodes 
        List<Integer>[] graph = new List[n]; // adjacency list 
        
        // Initialize graph adjacency list
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) { // i is the child node, father[i] is parent node 
            int parent = father[i];
            graph[parent].add(i);
        }

        int maxTime = 0;
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(0, 0));
        

        while (!stack.isEmpty()) {
            Node curNode = stack.pop();
            int node = curNode.val;
            int dist = curNode.dist;

            maxTime = Math.max(maxTime, dist);
            for (int childNode : graph[node]) {
                stack.push(new Node(childNode, dist + time[childNode]));
            }
        }

        return maxTime;
    }

    class Node {
        int val;
        int dist;
        public Node(int val, int dist) {
            this.val = val;
            this.dist = dist;
        }
    }
}
