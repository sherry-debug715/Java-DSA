package BinarySearch;
// Time: O(nlogx)
// Space: O(1)
public class RootOfNumber {
    static double root(double x, int n) {
        if (x == 0) {
          return 0;
        }
    
        double left = 0, right = x;
        double mid = left + (right - left) / 2;
        while (mid - left >= 0.001) {
          if (power(mid, n) > x) {
            right = mid;
          } else if (power(mid, n) < x) {
            left = mid;
          } else {
            return mid;
          }
          mid = left + (right - left) / 2;
        }
        return mid;
      }
    
      static double power(double mid, int n) {
        double res = 1;
        for (int i = 0; i < n; i++) {
          res *= mid;
        }
        return res;
      }
}
