package SweepLine.MergeIntervals;
// Lintcode problem 577: https://www.lintcode.com/problem/577/?fromId=161&_from=collection
public class MergeKSortedIntervalLists {
    class Interval {
            int start, end;
            Interval(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }
        
    class Data {
        int arrIdx;
        int intervalIdx;
        Interval interval;
        public Data(int _arrIdx, int _intervalIdx, Interval _interval) {
            arrIdx = _arrIdx;
            intervalIdx = _intervalIdx;
            interval = _interval;
        }
    }
    private Comparator<Data> intervalComparator = new Comparator<>() {
        public int compare(Data data1, Data data2) {
            return data1.interval.start - data2.interval.start;
        }
    };
    /**
     * @param intervals: the given k sorted interval lists
     * @return:  the new sorted interval list
     */
    public List<Interval> mergeKSortedIntervalLists(List<List<Interval>> intervals) {
        Queue<Data> minHeap = new PriorityQueue<>(intervals.size(), intervalComparator);
        Stack<Interval> stack = new Stack<>();
        // add the first column to minHeap 
        for (int i = 0; i < intervals.size(); i++) {
            if (intervals.get(i).size() == 0) {
                continue;
            }
            minHeap.add(new Data(i, 0, intervals.get(i).get(0)));
        }
        while (!minHeap.isEmpty()) {
            Data curInterval = minHeap.poll();
            int arrRow = curInterval.arrIdx;
            int col = curInterval.intervalIdx;
            // add to stack 
            mergeInterval(stack, curInterval.interval);
            if (col + 1 < intervals.get(arrRow).size()) {
                minHeap.add(new Data(arrRow, col + 1, intervals.get(arrRow).get(col + 1)));
            }
        }

        return new ArrayList<>(stack);
    }

    private void mergeInterval(Stack<Interval> stack, Interval interval) {
        if (stack.isEmpty()) {
            stack.push(interval);
        } else {
            Interval prevInterval = stack.peek();
            if (interval.start > prevInterval.end) {
                stack.push(interval);
            } else {
                prevInterval.end = Math.max(prevInterval.end, interval.end);
            }
        }
    }
}
