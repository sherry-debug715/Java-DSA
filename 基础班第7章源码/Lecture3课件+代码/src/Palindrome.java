public class Palindrome {

    public boolean isPalindrome(String s) {
        // write your code here

        // 考虑只有小写字母的情况
        /*
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        } */

        /*
        for (int i = 0; i < s.length() / 2; ++i) {
            if(s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true; */

        // aBcD
        s = s.toLowerCase();  // abcd

        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char ch1 = s.charAt(i);
            char ch2 = s.charAt(j);

            if (!Character.isLetterOrDigit(ch1)) {
                i++;
                continue;
            }

            if (!Character.isLetterOrDigit(ch2)) {
                j--;
                continue;
            }

            if (ch1 != ch2) {
                return false;
            }
            i++;
            j--;
        }
        return true;

    }

}
