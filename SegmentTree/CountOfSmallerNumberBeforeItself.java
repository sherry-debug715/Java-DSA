package SegmentTree;

import Sort.SortTheJumbledNumbers.java.ArrayList;

// lintcode 249 
public class CountOfSmallerNumberBeforeItself {
        class SegmentTreeNode {
        int start;
        int end;
        int count;
        SegmentTreeNode left;
        SegmentTreeNode right;
        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            count = 0;
            left = right = null;
        }
    }

    class SegmentTree {
        private SegmentTreeNode root;
        public SegmentTree() {
            root = buildTree(0, 10001);
        }

        public SegmentTreeNode getRoot() {
            return root;
        }

        public SegmentTreeNode buildTree(int start, int end) {
            SegmentTreeNode node = new SegmentTreeNode(start, end);
            if (start == end) {
                return node;
            }

            int mid = start + (end - start) / 2;
            node.left = buildTree(start, mid);
            node.right = buildTree(mid + 1, end);
            return node;
        }

        public void modify(SegmentTreeNode root, int index, int count) {
            if (root.start == index && root.end == index) {
                root.count += count;
                return;
            }
            int mid = root.start + (root.end - root.start) / 2;
            if (root.start <= index && index <= mid) {
                modify(root.left, index, count);
            } else {
                modify(root.right, index, count);
            }

            root.count = root.left.count + root.right.count;
        }

        public int query(SegmentTreeNode root, int start, int end) {
            if (root.start == start && root.end == end) {
                return root.count;
            }
            int mid = root.start + (root.end - root.start) / 2;
            int leftCount = 0, rightCount = 0;
            if (start <= mid) {
                leftCount = query(root.left, start, Math.min(end, mid));
            }
            if (end > mid) {
                rightCount = query(root.right, Math.max(start, mid + 1), end);
            }
            return leftCount + rightCount;
        }
    }

    public List<Integer> countOfSmallerNumberII(int[] a) {
        SegmentTree tree = new SegmentTree();
        List<Integer> res = new ArrayList<>();
        SegmentTreeNode root = tree.getRoot();

        for (int n : a) {
            if (n == 0) {
                res.add(0);
            } else {
                res.add(tree.query(root, 0, n - 1));
            }
            tree.modify(root, n, 1);
        }
        return res;
    }
}
