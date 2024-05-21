package TwoPointers;

public class SumOfTwoString {
    public String sumofTwoStrings(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1; // i: a, j: b 
        String res = "";
        while (i >= 0 || j >= 0) {
            int numA = i < 0 ? 0 : a.charAt(i) - '0';
            int numB = j < 0 ? 0 : b.charAt(j) - '0';
            int sum = numA + numB;
            res = String.valueOf(sum) + res;
            i -= 1;
            j -= 1;
        }

        return res;
    }
}
