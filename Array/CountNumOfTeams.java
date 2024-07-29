package Array;
// Leetcode 1395 
// Time: O(N^2)
// Space: O(1)
public class CountNumOfTeams {
    public int numTeams(int[] rating) {
        int n = rating.length;
        int output = 0;
        // for each soldier from index 1 to n - 1, count how many number is less than it on the 
        // left and greater on the right, and how many number is greater than it on the left less 
        // on the right 
        for (int i = 1; i < n - 1; i++) {
            int leftSmaller = 0, leftGreater = 0;
            int rightSmaller = 0, rightGreater = 0;
            // count left side
            for (int j = 0; j < i; j++) {
                if (rating[j] < rating[i]) {
                    leftSmaller += 1;
                } else {
                    leftGreater += 1;
                }
            }
            // count right side 
            for (int k = i + 1; k < n; k++) {
                if (rating[i] < rating[k]) {
                    rightGreater += 1;
                } else {
                    rightSmaller += 1;
                }
            }

            output += (leftSmaller * rightGreater) + (leftGreater * rightSmaller);
        }
        return output;
    }
}
