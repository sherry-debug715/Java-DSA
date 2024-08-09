## Two Pointers

### Usage Conditions

#### Sliding Window (90%):
- The sliding window technique should be used for 90% of the problems.
- Time Complexity Requirement:
  - The required time complexity is O(n), with 80% of the problems utilizing the two-pointer technique.
####	In-Place Operations:
-	In-place operations are required; only swapping is allowed. No extra space can be used (applies to 80% of the problems).
###	Keywords:
-	The problem description may include keywords such as “subarray” or “substring” (50% of the problems).
-	The keyword “palindrome” may also appear (50% of the problems).

### Complexity

Time Complexity:

- The expected time complexity is O(n).
-	This complexity depends on the number of times the main body of the innermost loop is executed, rather than the number of nested loops.

Space Complexity:

-	The required space complexity is O(1).
-	Only extra memory for two pointers is needed.

### Pattern 
#### Opposite Direction Two-Pointer (Partition in Quicksort)
```python
def partition(self, A, start, end):
    if start >= end:
        return
    left, right = start, end
    # Key point 1: Pivot is the value, not the index
    pivot = A[(start + end) // 2]
    # Key point 2: Always compare left & right with "left <= right", not "left < right"
    while left <= right:
        while left <= right and A[left] < pivot:
            left += 1
        while left <= right and A[right] > pivot:
            right -= 1
        if left <= right:
            A[left], A[right] = A[right], A[left]
            left += 1
            right -= 1
```
#### Backward Two-Pointer
```python
left = position
right = position + 1
while left >= 0 and right < len(s):
    if left and right can stop:
        break
    left -= 1
    right += 1
```
#### Same Direction Two-Pointer
```python
j = 0
for i in range(n):
    # Loop until the condition between i and j is satisfied
    while j < n and the condition between i and j is not satisfied:
        j += 1
    if the condition between i and j is satisfied:
        process the range between i and j
```
#### Merging Two Pointers
```python
def merge(list1, list2):
    new_list = []
    i, j = 0, 0
    # During merging, only move i and j, do not use list1.pop(0) or similar operations
    # because pop(0) has O(n) time complexity
    while i < len(list1) and j < len(list2):
        if list1[i] < list2[j]:
            new_list.append(list1[i])
            i += 1
        else:
            new_list.append(list2[j])
            j += 1
    # Add the remaining elements to new_list
    # Do not use new_list.extend(list1[i:]) or similar methods
    # because list1[i:] can incur additional space costs
    while i < len(list1):
        new_list.append(list1[i])
        i += 1
    while j < len(list2):
        new_list.append(list2[j])
        j += 1
    return new_list
```
### Practice the following problems 
[Two Sum VII](https://www.lintcode.com/course/90/learn/1879?chapterId=467&sectionId=3277&ac=true)<br>
[Binary Subarrays With Sum](https://www.lintcode.com/course/90/learn/1712?chapterId=467&sectionId=3278&ac=true)<br>
[Longest Palindrome](https://www.lintcode.com/course/90/learn/627?chapterId=467&sectionId=3279&ac=true)<br>
[Merge Sorted Array (easy version)](https://www.lintcode.com/course/90/learn/64?chapterId=467&sectionId=3280&ac=true)