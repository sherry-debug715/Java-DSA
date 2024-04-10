// Lintcode problem 1781
package String;

public class reverseAsciiEncodedStr {
    public String reverseAsciiEncodedString(String encodeString) {
        if (encodeString == null || encodeString.length() == 0) return "";

        StringBuilder decoded = new StringBuilder();
        for (int i = encodeString.length() - 2; i >= 0; i -= 2) {
            // create a substring and convert it to integer

            // method one
            // String curAscii = encodeString.substring(i, i + 2);
            // int num = Integer.valueOf(curAscii);
            
            // method two:
            int num =(encodeString.charAt(i) - '0') * 10 + (encodeString.charAt(i + 1) - '0');
            decoded.append((char) num); 
        }

        return decoded.toString();
    }
}
