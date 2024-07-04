package Trie;
Trie trie;

// lintcode 473
public class AddSearchWordDSDesign {
    class TrieNode {
        TrieNode[] children;
        boolean isWord;

        public TrieNode() {
            children = new TrieNode[26];
            isWord = false;
        }
    }

    class Trie {
        TrieNode root;
        public Trie() {
            root = new TrieNode();
        }

        public TrieNode getRoot() {
            return root;
        }

        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }
                node = node.children[idx];
            }
            node.isWord = true;
        }
    }

    public WordDictionary() {
        trie = new Trie();
    }

    public void addWord(String word) { // Time: O(wordLen)
        trie.insert(word);
    }

    /*
     * @param word: A word could contain the dot character '.' to represent any one letter.
     * @return: if the word is in the data structure.
     */
    public boolean search(String word) { // Time: every char is '.', O(wordLen * 26)
        if (word == null) {
            return false;
        }
        return searchWord(word, 0, trie.getRoot());
    }

    private boolean searchWord(String word, int idx, TrieNode root) {
        if (root == null) {
            return false;
        }
        if (idx == word.length()) {
            return root.isWord;
        }

        char c = word.charAt(idx);
        if (c != '.') {
            return searchWord(word, idx + 1, root.children[c - 'a']);
        }
        for (int i = 0; i < 26; i++) {
            TrieNode child = root.children[i];
            if (child != null && searchWord(word, idx + 1, child)) {
                return true;
            }
        }
        return false;
    } 
}
