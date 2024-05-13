package Array.NestedListWeightSum;

public class PartTwo {
    int depth = 0;
    public int depthSumInverse(List<NestedInteger> nestedList) {
        // count depth of list 
        countDepth(nestedList);
        // iterate over nestedList again to count output 
        Queue<NestedInteger> queue = new LinkedList<>(nestedList);
        int output = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger cur = queue.poll();
                if (!cur.isInteger()) {
                    queue.addAll(cur.getList());
                    continue;
                }
                int weight = cur.getInteger() * depth;
                output += weight;
            }
            depth -= 1;
        }
        return output;
    }

    private void countDepth(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>(nestedList);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger cur = queue.poll();
                if (!cur.isInteger()) {
                    queue.addAll(cur.getList());
                }
            }
            depth += 1;
        }
    }
}
