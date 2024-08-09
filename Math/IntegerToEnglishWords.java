package Math;

import java.util.List;
// leetcode 273
// Time: O(n)
// Space: O(n)
public class IntegerToEnglishWords {
        public String numberToWords(int nums) {
        if (nums == 0) {
            return "Zero";
        }
        List<String> resList = new ArrayList<>();
        String num = Integer.toString(nums);
        int n = num.length();
        int right = n - 1, left = n - 1;
        while (left >= 0) {
        while (left >= 0 && right - left < 3) {
            left -= 1;
        }
        // convert the 3 digit into String 
        int size = left < 0 ? n : n - left - 1;
        String words = convert3Digits(num.substring(left + 1, right + 1), size); // num(left: right]
        if (words.length() != 0) {
            resList.add(0, words);
        }
        right = left;
        }

        return String.join(" ", resList);
    }

    private String convert3Digits(String num, int size) {
        String[] unitString = new String[]{"Hundred", "Thousand", "Million", "Billion"};
        String[] digitString = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        String[] teenString = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen","Eighteen", "Nineteen"};
        String[] tenString = new String[]{"","","Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        int digits = num.length();
        List<String> wordList = new ArrayList<>();

        for (int i = 0; i < num.length(); i++) {
            int curNum = num.charAt(i) - '0';
            if (curNum == 0) {
                digits -= 1;
                continue;
            }
            if (digits == 3) {
                wordList.add(digitString[curNum]);
                wordList.add(unitString[0]);
            }
            if (digits == 2) {
                int nextNum = num.charAt(i + 1) - '0';
                if (curNum == 1) {
                    wordList.add(teenString[nextNum]);
                    break;
                }
                wordList.add(tenString[curNum]);
                if (nextNum == 0) {
                    break;
                }
            } 
            if (digits == 1) {
                wordList.add(digitString[curNum]);
            }
            digits -= 1;
        }
        if (wordList.size() != 0) {
            if (size <= 6 && size >= 4) {
                wordList.add(unitString[1]);
            } else if (size <= 9 && size >= 7) {
                wordList.add(unitString[2]);
            } else if (size > 9) {
                wordList.add(unitString[3]);
            }
        }

        return String.join(" ", wordList);
    }
}
