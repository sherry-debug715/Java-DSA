package SegmentTree;

import Sort.SortTheJumbledNumbers.java.ArrayList;

// Lintcode 248 
public class CountOFSmallerNumber {
        class SegmentTreeNode {
        int start, end;
        int count;
        SegmentTreeNode left, right;
        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.count = 0;
            this.left = this.right = null;
        }
    }

    class SegmentTree {
        SegmentTreeNode root;
        private int size;
        public SegmentTree(int size) {
            this.size = size;
            this.root = constructTree(0, size - 1);
        }

        public SegmentTreeNode getRoot() {
            return root;
        }

        public SegmentTreeNode constructTree(int start, int end) {
            SegmentTreeNode node = new SegmentTreeNode(start, end);
            if (start == end) {
                return node;
            }

            int mid = start + (end - start) / 2;
            node.left = constructTree(start, mid);
            node.right = constructTree(mid + 1, end);
            
            return node;
        }

        public void modify(SegmentTreeNode root, int index, int val) {
            if (root.start == index && root.end == index) {
                root.count += val;
                return;
            }

            int mid = root.start + (root.end - root.start) / 2;
            if (root.start <= index && index <= mid) {
                modify(root.left, index, val);
            }
            if (index > mid && index <= root.end) {
                modify(root.right, index, val);
            }
            root.count = root.left.count + root.right.count;
        }

        public int query(int start, int end) {
            return query(root, start, end);
        }

        private int query(SegmentTreeNode root, int start, int end) {
            if (root.start == start && root.end == end) {
                return root.count;
            }
            int leftSum = 0, rightSum = 0;
            int mid = root.start + (root.end - root.start) / 2;
            if (start <= mid) {
                leftSum = query(root.left, start, Math.min(mid, end));
            }
            if (end > mid) {
                rightSum = query(root.right, Math.max(mid + 1, start), end);
            }
            return leftSum + rightSum;
        }
    }


    public List<Integer> countOfSmallerNumber(int[] a, int[] queries) {
        SegmentTree tree = new SegmentTree(10001);
        
        for (int n : a) { // O(Nlog(max(a)))
            tree.modify(tree.getRoot(), n, 1);
        }

        List<Integer> res = new ArrayList<>();
        for (int n : queries) { // log(queriesSize*log(max(queries)))
            if (n == 0) {
                res.add(0);
                continue;
            }
            int count = tree.query(tree.getRoot(), 0, n - 1);
            res.add(count);
        }
        return res;
    }
}
