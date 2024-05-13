package Array;

public class FirstUniqueNumberInDataStream2 {
    List<Integer> numsList;
    Map<Integer, Integer> counterMap;
    Queue<Integer> queue;
    public FirstUniqueNumberInDataStream2(){
        numsList = new ArrayList<>();
        counterMap = new HashMap<>();
        queue = new LinkedList<>();
    }
    /**
     * @param num: next number in stream
     * @return: nothing
     */
    public void add(int num) {
        numsList.add(num);
        // num not in counterMap, add it to queue 
        if (!counterMap.containsKey(num)) {
            counterMap.put(num, 1);
            queue.offer(num);
        } else {
            // increment the num counter by 1, if num is not the first 
            // number in queue, do nothing, if it is, deque 
            counterMap.put(num, counterMap.get(num) + 1);
            if (!queue.isEmpty() && num == queue.peek()) {
                while (!queue.isEmpty() && counterMap.get(queue.peek()) > 1) {
                    queue.poll();
                }
            } 
        }
    }

    /**
     * @return: the first unique number in stream
     */
    public int firstUnique() {
        return queue.peek();
    }
}
