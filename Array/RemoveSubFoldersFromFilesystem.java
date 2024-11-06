package Array;

import java.util.List;
import java.util.Map;

// leetcode 1233
public class RemoveSubFoldersFromFilesystem {
    class TrieNode {
        Map<String, TrieNode> children;
        boolean endOfWord;
        public TrieNode() {
             children = new HashMap<>();
             endOfWord = false;
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

        public void insert(String path) {
            TrieNode curr = root;
            String[] pathArr = path.split("/");
            for (String sub : pathArr) {
                if (sub.isEmpty()) {
                    return;
                }
                if (!curr.children.containsKey(sub)) {
                    curr.children.put(sub, new TrieNode());
                }
                curr = curr.children.get(sub);
            }
            curr.endOfWord = true;
        } 
    }
// Time: O(n * m)
// Space: O(nodes + res.size()) nodes == total number of file names from folder 
    public List<String> removeSubfolders(String[] folder) {
        Trie trie = new Trie();
        for (String path : folder) { // O(n * m) n == folder.length, m == folder[i].length
            trie.insert(path.substring(1));
        }    
        List<String> res = new ArrayList<>();

        for (String path : folder) { // O(n)
            TrieNode curr = trie.getRoot();
            String[] pathArr = path.substring(1).split("/"); // O(m)
            int i = 0;
            while (i < pathArr.length) { // O(m)
                if (i < pathArr.length && curr.endOfWord) {
                    break;
                }
                curr = curr.children.get(pathArr[i++]);
            }
            if (i == pathArr.length) {
                res.add(path);
            }
        }
        return res;
    }
}
