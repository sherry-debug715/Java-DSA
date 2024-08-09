## Binary Search 
### Usage Scenarios:

-	Sorted Array (30-40% involve binary search): Typically used when dealing with sorted arrays where the problem can be solved more efficiently than O(n) time complexity.
-	When the interviewer asks for an algorithm with a time complexity better than O(n) (99%): A strong indication that a logarithmic time complexity solution is expected.
-	Finding a partition point in an array such that the left half satisfies a certain condition, and the right half does not (100%): This often involves determining a threshold or boundary.
-	Finding a maximum/minimum value that satisfies a certain condition (90%): Often involves optimization problems where a specific criterion must be met.

### Complexity:

-	Time Complexity: O(logn): The operation is expected to be logarithmic in terms of time, commonly associated with binary search and related algorithms.
-	Space Complexity: O(1): The solution should be implemented with constant space complexity, meaning it should not require additional space that grows with the size of the input.

### Pattern 
#### Java
```java
public class Solution {
    public int binarySearch(int[] nums, int target) {
        // Handle corner cases
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = nums.length - 1;

        // Use start + 1 < end to avoid infinite loops
        while (start + 1 < end) {
            // Calculate mid to avoid overflow
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        // Check start and end to find the target
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }
}
```
#### Python 
```python
def binary_search(self, nums, target):
    # Handle corner cases
    # Equivalent to nums is None or len(nums) == 0
    if not nums:
        return -1
    start, end = 0, len(nums) - 1
    # Use start + 1 < end instead of start < end to avoid infinite loops
    # Infinite loops won't occur in the case of finding the first position of the target
    # However, they can occur when finding the last position of the target
    # Example: nums = [1, 1] target = 1
    # To unify the template, we use start + 1 < end to ensure no infinite loops
    while start + 1 < end:
        # Python does not have overflow issues, so we can directly use // 2
        # In Java and C++, it's better to write mid = start + (end - start) / 2
        # To prevent overflow when start = 2^31 - 1 and end = 2^31 - 1
        mid = (start + end) // 2
        # Write the logic for >, =, < separately, then see if the = case can be merged with other branches
        if nums[mid] < target:
            start = mid
        elif nums[mid] == target:
            end = mid
        else:
            end = mid
    # Since the above loop condition is start + 1 < end,
    # the relationship between start and end when the loop ends will be adjacent (like 1 and 2, or 3 and 4)
    # Therefore, we need to separately check start and end to find the answer
    # If looking for the first position of the target, check start first, otherwise check end first
    if nums[start] == target:
        return start
    if nums[end] == target:
        return end
    return -1
```
### Must Practice 
[First Position of Target](https://www.lintcode.com/course/90/learn/14/solution?chapterId=439&sectionId=3064&ac=true)<br>
[Last Position of Target](https://www.lintcode.com/problem/458)<br>
[Find K Closest Elements](https://www.lintcode.com/course/90/learn/460?chapterId=439&sectionId=3153&ac=true)<br>
[Copy Books](https://www.lintcode.com/course/90/learn/437?chapterId=439&sectionId=3154&ac=true)