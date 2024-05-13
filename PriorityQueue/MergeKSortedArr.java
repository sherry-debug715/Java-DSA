package PriorityQueue;

class IdxTracker {
    int row;
    int col;
    int val;
    public IdxTracker(int _row, int _col, int _val) {
        row = _row;
        col = _col;
        val = _val;
    }
}

public class MergeKSortedArr {
    private Comparator<IdxTracker> valComparator = new Comparator<IdxTracker>() {
        public int compare(IdxTracker left, IdxTracker right) {
            return left.val - right.val;
        }
    };

    public int[] mergekSortedArrays(int[][] arrays) {
        // edge case 
        if (arrays == null) {
            return new int[0];
        }

        Queue<IdxTracker> heap = new PriorityQueue<>(arrays.length, valComparator);

        // populate queue with the int of first column, calculate total number 
        // of arrays used to initialize output array.
        int totalSize = 0;
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length == 0) {
                continue;
            }
            heap.add(new IdxTracker(i, 0, arrays[i][0]));
            totalSize += arrays[i].length;
        }
        // if totalSize is 0 return 
        if (totalSize == 0) {
            return new int[0];
        }

        int[] output = new int[totalSize];
        int curIdx = 0;
        while (!heap.isEmpty()) {
            IdxTracker curNum = heap.remove();
            // populate output 
            output[curIdx] = curNum.val;
            curIdx += 1;
            // add the next number from curNum.row to heap 
            if (curNum.col + 1 < arrays[curNum.row].length) {
                int newRow = curNum.row, newCol = curNum.col + 1;
                heap.add(new IdxTracker(newRow, newCol , arrays[newRow][newCol]));
            }
        }
        return output;
    }
}
