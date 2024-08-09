## Trie 
### When to Use:
- When you need to check if there is a word/string that contains a specific prefix.
- For problems involving finding words in a character matrix.

## Complexity

### Time Complexity:
- O(L)  for insertion, deletion, search, and update operations, where  L  is the length of the word.
### Space Complexity:
- O(N \times L) , where  N  is the number of words, and  L  is the length of the words.

## Pattern 
###  Java
```java
class TrieNode {
    // Child nodes
    public Map<Character, TrieNode> children;
    // Indicates whether the path from the root node to this node forms a word
    public boolean isWord;
    // The word represented from the root node to this node
    public String word;

    public TrieNode() {
        children = new HashMap<Character, TrieNode>();
        isWord = false;
        word = null;
    }

}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public TrieNode getRoot() {
        return root;
    }

    // Insert a word
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (!node.children.containsKey(letter)) {
                node.children.put(letter, new TrieNode());
            }
            node = node.children.get(letter);
        }
        node.isWord = true;
        node.word = word;
    }

    // Check if the word is in the Trie
    public boolean hasWord(String word) {
        int L = word.length();
        TrieNode node = root;
        for (int i = 0; i < L; i++) {
            char letter = word.charAt(i);
            if (!node.children.containsKey(letter)) {
                return false;
            }
            node = node.children.get(letter);
        }
        return node.isWord;
    }

    // Check if the prefix is in the Trie
    public boolean hasPrefix(String prefix) {
        int L = prefix.length();
        TrieNode node = root;
        for (int i = 0; i < L; i++) {
            char letter = prefix.charAt(i);
            if (!node.children.containsKey(letter)) {
                return false;
            }
            node = node.children.get(letter);
        }
        return true;
    }
```
### Python
```python
class TrieNode:
    def __init__(self):
        # Child nodes
        self.children = {}
        # Indicates whether the path from the root node to this node forms a word
        self.is_word = False
        # The word represented from the root node to this node
        self.word = None

class Trie:
    def __init__(self):
        self.root = TrieNode()

    def get_root(self):
        return self.root

    # Insert a word
    def insert(self, word):
        node = self.root
        for letter in word:
            if letter not in node.children:
                node.children[letter] = TrieNode()
            node = node.children[letter]
        node.is_word = True
        node.word = word

    # Check if the word is in the Trie
    def has_word(self, word):
        node = self.root
        for letter in word:
            if letter not in node.children:
                return False
            node = node.children[letter]
        return node.is_word

    # Check if the prefix is in the Trie
    def has_prefix(self, prefix):
        node = self.root
        for letter in prefix:
            if letter not in node.children:
                return False
            node = node.children[letter]
        return True
```
### Must practice 
[Concatenated Words](https://www.lintcode.com/course/90/learn/1221?chapterId=476&sectionId=3311&ac=false)<br>
[Max Distance](https://www.lintcode.com/course/90/learn/1624?chapterId=476&sectionId=3312&ac=false)<br>
[Map Sum Pairs](https://www.lintcode.com/course/90/learn/1090?chapterId=476&sectionId=3313&ac=false)
