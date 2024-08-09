package LinkedList;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

// Lintcode problem 541: https://www.lintcode.com/problem/541/
class Triple {
    int val;
    int arrIdx;
    int valIdx;

    public Triple(int val, int arrIdx, int valIdx) {
        this.val = val;
        this.arrIdx = arrIdx;
        this.valIdx = valIdx;
    }
}

public class ZigzagIterator2 {
    private Queue<Triple> queue;
    private List<List<Integer>> vecs;
    /*
    * @param vecs: a list of 1d vectors
    */public ZigzagIterator2(List<List<Integer>> vecs) {
        this.vecs = vecs;
        this.queue = new LinkedList<>();

        for (int i = 0; i < vecs.size(); i++) {
            if (vecs.get(i).size() == 0) {
                continue;
            }
            queue.offer(new Triple(vecs.get(i).get(0), i, 0));
        }
    }

    /*
     * @return: An integer
     */
    public int next() {
        if (!queue.isEmpty()) {
            Triple nextNum = queue.poll();
            if (nextNum.valIdx + 1 < vecs.get(nextNum.arrIdx).size()) {
                queue.offer(new Triple(vecs.get(nextNum.arrIdx).get(nextNum.valIdx + 1), nextNum.arrIdx, nextNum.valIdx + 1));
            }
            return nextNum.val;
        }

        throw new NoSuchElementException("No more elements");
    }

    /*
     * @return: True if has next
     */
    public boolean hasNext() {
        return queue.size() > 0;
    }
}
