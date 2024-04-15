# Sort Array
```java
// Use Arrays.sort()
int[] numbers = {3, 1, 4, 1, 5};
Arrays.sort(numbers);
// numbers now contains {1, 1, 3, 4, 5}

String[] strings = {"banana", "apple", "orange"};
Arrays.sort(strings, Collections.reverseOrder());
// strings now contains {"orange", "banana", "apple"}
```

# Sort List
Use Collections.sort()
- Natural Ordering: The elements of the list must implement the Comparable interface.
- Custom Ordering: You can specify a Comparator to define a custom order for sorting.
```java
List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5));
Collections.sort(list);
// list now contains [1, 1, 3, 4, 5]
```
```java
List<String> strings = new ArrayList<>(Arrays.asList("banana", "apple", "orange"));
Collections.sort(strings, Collections.reverseOrder());
// strings now contains ["orange", "banana", "apple"]
```