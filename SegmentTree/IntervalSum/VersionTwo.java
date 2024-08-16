package SegmentTree.IntervalSum;
// Lintcode 207
public class VersionTwo {
    SegmentTree st;
    public Solution(int[] A) {
        // do intialization if necessary
        st = new SegmentTree(A, 0, A.length - 1);
    }

    /*
     * @param start: An integer
     * @param end: An integer
     * @return: The sum from start to end
     */
    public long query(int start, int end) {
        return st.query(st.root, start, end);
    }

    /*
     * @param index: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void modify(int index, int value) {
        st.modify(st.root, index, value);
    }

    class SegmentTreeNode {
        int start, end;
        int segmentSum;
        SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.segmentSum = sum;
            this.left = this.right = null;
        }
    }

    class SegmentTree {
        SegmentTreeNode root;
        public SegmentTree(int[] A, int start, int end) {
            this.root = constructTree(A, start, end);
        }

        public SegmentTreeNode constructTree(int[] A, int start, int end) {
            // edge case 
            if (start > end) {
                return null;
            }
            // create a new tree node for current number 
            SegmentTreeNode node = new SegmentTreeNode(start, end, 0);
            if (start < end) {
                // mid is the mid of current segment 
                int mid = start + (end - start) / 2;
                node.left = constructTree(A, start, mid);
                node.right = constructTree(A, mid + 1, end);
                if (node.left != null) {
                    node.segmentSum += node.left.segmentSum;
                }
                if (node.right != null) {
                    node.segmentSum += node.right.segmentSum;
                }
            } else {
                // when start == end 
                node.segmentSum = A[start];
            }

            return node;
        }

        public void modify(SegmentTreeNode root, int index, int val) {
            // if index happend to be root start and root end or the exit case when 
            // at leaf nodes 
            if (root.start == index && root.end == index) {
                root.segmentSum = val;
                return;
            }

            // start to divide and search from root segment 
            int mid = root.start + (root.end - root.start) / 2;
             // check which side index is on 
             if (root.start <= index && index <= mid) {
                 modify(root.left, index, val);
             }
             if (mid < index && index <= root.end) {
                 modify(root.right, index, val);
             }
            
             root.segmentSum = root.left.segmentSum + root.right.segmentSum;
        }

        public long query(SegmentTreeNode root, int start, int end) { // [start: end]
            if (root.start == start && root.end == end) {
                return root.segmentSum;
            }
            long res = 0;
            int mid = root.start + (root.end - root.start) / 2;
            // if start and end belong to different side of mid 
            if (start <= mid && end >= mid + 1) {
                res += query(root.left, start, mid);
                res += query(root.right, mid + 1, end);
            }
            // if start and end lies in the left to mid 
            if (end <= mid) {
                res += query(root.left, start, end);
            }
            // if start and end lies in the right to end 
            if (start >= mid + 1) {
                res += query(root.right, start, end);
            }
            return res;
        }
    }
}
