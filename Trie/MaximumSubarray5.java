package Trie;
// lintcode 722 
// Time: O(n)
// Space: O(n)
public class MaximumSubarray5 {
    public int maxXorSubarray(int[] nums) {
        int prefixOr = 0; // prefix XOR sum 
        int maxXor = 0; // res 
        Trie trie = new Trie();
        trie.insert(prefixOr); // it's important to insert 0 into trie

        for (int n : nums) { // O(n) n is the length of nums
            prefixOr ^= n;
            trie.insert(prefixOr); // O(1)
            // look for the largest prefix xor of current prefixOr 
            int temp = trie.getNearestNum(prefixOr); // O(1)
            maxXor = Math.max(maxXor, temp ^ prefixOr);
        }
        return maxXor;
    }

    class TrieNode {
        TrieNode[] children;
        boolean isNum;
        int num;
        public TrieNode() {
            children = new TrieNode[2];
            isNum = false;
            num = 0;
        }
    }

    class Trie {
        TrieNode root;
        public Trie() {
            root = new TrieNode();
        }

        public void insert(int num) {
            TrieNode node = root;
            for (int i = 30; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.children[bit] == null) {
                    node.children[bit] = new TrieNode();
                }
                node = node.children[bit];
            }

            node.isNum = true;
            node.num = num;
        }

        public int getNearestNum(int num) {
            TrieNode node = root;
            for (int i = 30; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.children[1- bit] != null) {
                    node = node.children[1 - bit];
                    
                } else {
                    node = node.children[bit];
                }
            }
            return node.num;
        }
    }
}
