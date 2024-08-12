package Trie;
// Lintcode 1248 
public class MaximumXOROfTwoNumbersInArr {
    class TrieNode {
        TrieNode[] sons;
        boolean isNum;
        int num;
        public TrieNode() {
            sons = new TrieNode[2];
            isNum = false;
            num = 0;
        }
    }

    class Trie {
        TrieNode root;
        public Trie () {
            root = new TrieNode();
        }

        public void insert(int num) { // O(31) or O(1)
            TrieNode node = root;
            for (int i = 30; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.sons[bit] == null) {
                    node.sons[bit] = new TrieNode();
                }
                node = node.sons[bit];
            }
            node.isNum = true;
            node.num = num;
        }

        public int getNearestNum(int num) {  // O(31) or O(1)
            TrieNode node = root;
            for (int i = 30; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.sons[1 - bit] != null) {
                    node = node.sons[1 - bit];
                } else {
                    node = node.sons[bit];
                }
            }
            return node.num;
            
        }
    }


    public int findMaximumXOR(int[] nums) { // O(n * 31)
        Trie trie = new Trie();
        for (int n : nums) {
            trie.insert(n);
        }
        int maxNum = 0;
        for (int n : nums) {
            int nearNum = trie.getNearestNum(n);
            maxNum = Math.max(maxNum, n ^ nearNum);
        }
        return maxNum;
    }
}
