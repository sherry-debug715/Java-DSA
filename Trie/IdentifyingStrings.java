package Trie;

import java.util.Map;

// Lintcode 333 
public class IdentifyingStrings {
    class TrieNode {
        Map<Character, TrieNode> children;
        boolean isWord;
        String word;
        int prefixCount;
        public TrieNode() {
            children = new HashMap<>();
            isWord = false;
            word = null;
            prefixCount = 0;
        }
    }

    class Trie {
        private TrieNode root;
        public Trie() {
            root = new TrieNode();
        }

        public TrieNode getRoot() {
            return root;
        }

        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieNode());
                }
                node = node.children.get(c);
                node.prefixCount += 1;
            }
            node.isWord = true;
            node.word = word;
        }
    }


    public String[] shortPerfix(String[] stringArray) {
        // Step 1: construct a trie and populate it 
        Trie trie = new Trie();
        int n = stringArray.length;
        for (String word : stringArray) {
            trie.insert(word);
        }

        // Step 2: iterate over stringArray, look for the shortest prefix of each word 
        String[] output = new String[n];
        int idx = 0;
        for (String word : stringArray) {
            output[idx] = exploreTrie(trie.getRoot(), word);
            idx += 1;
        }
        return output;
    }

    private String exploreTrie(TrieNode root, String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (node.prefixCount == 1) {
                return word.substring(0, i);
            }
            node = node.children.get(c);
        }
        return word;
    }
}
