public class Atoi {

    public int stringToInteger(String str) {

        if (str == null || str.isEmpty()) {
            return 0;
        }

        boolean isNegative = str.charAt(0) == '-';
        int start = isNegative ? 1 : 0;

        int result = 0;
        for (int i = start; i < str.length(); ++i) {
            char ch = str.charAt(i);
            int digit = ch - '0';

            if (!isNegative) {
                result = result * 10 + digit;
            } else {
                result = result * 10 - digit;
            }
        }

        return result;
    }
}
