package Trie;
// Leetcode 720
public class LongestWordInDictionary {
    class TrieNode {
        TrieNode[] children;
        boolean endOfWord;
        public TrieNode() {
            children = new TrieNode[26];
            endOfWord = false;
        }
    }
    
    class Trie {
        TrieNode root;
        public Trie() {
            root = new TrieNode();
        }
        
        public void insert(String x) {
            TrieNode node = root;
            for (char c : x.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }
                node = node.children[idx];
            }
            node.endOfWord = true;
        }
        
        public boolean valid(String x) {
            TrieNode node = root;
            for (char c : x.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null || !node.children[idx].endOfWord) {
                    return false;
                }
                node = node.children[idx];
            }
            return node != null && node.endOfWord;
        }
    }
    
    public String longestWord(String[] words) {
        // build trie 
        Trie trie = new Trie();
        for (String w : words) {
            trie.insert(w);
        }
        int maxLen = 0;
        String output = "";
        for (String w : words) {
            int n = w.length();
            if (!trie.valid(w)) {
                continue;
            }
            if (n > maxLen) {
                maxLen = n;
                output = w;
            } else if (n == maxLen) {
                if (w.compareTo(output) < 0) {
                    output = w;
                }
            }
            
        }
        
        return output;
    }
}
