package Array;

public class MajorityEle2 {
    // theory: the most element that can appear for more than n / 3 times can only be two.
    // if 2 elements appeared more than 2n / 3 times, there is < 1/3 elements left in the array.  
    // Time: O(N);
    // Space: O(1)
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> output = new ArrayList<>();
        // find out elemnts that appeared the most from nums 
        int count1 = 0, count2 = 0;
        Integer ele1 = null, ele2 = null; 
        
        for (int n : nums) {
            if (ele1 != null && n == ele1) {
                count1 += 1;
            } else if (ele2 != null && n == ele2) {
                count2 += 1;
            } else if (count1 == 0) {
                ele1 = n;
                count1 += 1;
            } else if (count2 == 0) {
                ele2 = n;
                count2 += 1;
            } else { // find a 3rd number that differs from ele1 and ele2
                count1 -= 1;
                count2 -= 1;
            }
        }
        
        // check for valid number 
        count1 = 0; count2 = 0;
        int target = nums.length / 3;
        for (int n : nums) {
            if (ele1 != null && n == ele1) {
                count1 += 1;
            } 
            if (ele2 != null && n == ele2) {
                count2 += 1;
            }
        }
        
        if (count1 > target) {
            output.add(ele1);
        }
        if (count2 > target) {
            output.add(ele2);
        }
        return output;
    }
}
