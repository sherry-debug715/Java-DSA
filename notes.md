-- Sort a list
```java
List<int[]> trees = new ArrayList<>();
// populate trees with v, r, c 
for (int r = 0; r < forest.size(); r++) {
    for (int c = 0; c < forest.get(0).size(); c++) {
        int num = forest.get(r).get(c);
        if (num != 0 && num != 1) {
            trees.add(new int[]{num, r, c});
        }
    }
}
// sort tress by value 
Collections.sort(trees, (a, b) -> Integer.compare(a[0], b[0])); 
```

```java
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        // convert a string into array while single out anything other than english letters 
        String[] strArr = paragraph.split("\\W+");
        // convert an array into HashSet
        Set<String> banSet = new HashSet<>(Arrays.asList(banned));

        Map<String, Integer> strMap = new HashMap<>();
        int max = 0;
        String output = null;
        for (String str : strArr) {
            String word = str.toLowerCase();
            strMap.put(word, strMap.getOrDefault(word, 0) + 1);
            if (!banSet.contains(word)) {
                int count = strMap.get(word);
                if (count > max) {
                    max = count;
                    output = word;
                }
            }
        }
        return output;
    }
}
```

-- Return an empty char type 
```java
if (str == null || str.length() == 0) {
    return '\0';
}
```