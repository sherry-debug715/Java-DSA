package Array;

public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        // edge case
        if (num1.equals("0") || num2.equals("0")) return "0";
        // the output size will not exceed newSize 
        int newSize = num1.length() + num2.length();
        int[] res = new int[newSize];

        // 模拟乘法竖式计算 
        for (int i = num1.length() - 1; i > -1; i--) {
            for (int j = num2.length() - 1; j > -1; j--) {
                res[i + j + 1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        } 

        // handling 进位
        for (int i = res.length - 1; i > 0; i--) {
            res[i - 1] += res[i] / 10;
            res[i] = res[i] % 10;
        }

        // convert array of integer to string 
        String ans = "";
        for (int i = 0; i < newSize; i++) {
            if (ans.length() == 0 && res[i] == 0) continue; 
            ans += (char)(res[i] + '0');
        }

        return ans;

    }
}
