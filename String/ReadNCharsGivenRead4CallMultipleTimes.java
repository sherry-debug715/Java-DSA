package String;
// lintcode 660 
// time: O(N)
// Space: O(1)
public class ReadNCharsGivenRead4CallMultipleTimes {
    private char[] buffer = new char[4];
    int head = 0, tail = 0; // head: buffer pointer, tail: number of chars returned by read4 
    public int read(char[] buf, int n) {
        int idx = 0; // idx is pointer of buf 
        while (idx < n) {
            if (head == tail) {
                head = 0;
                tail = read4(buffer);
                if (tail == 0) {
                    break;
                }
            }

            // populate buf 
            while (idx < n && head < tail) {
                buf[idx++] = buffer[head++];
            }
        }
        // end while 
        return idx;
    }
}
