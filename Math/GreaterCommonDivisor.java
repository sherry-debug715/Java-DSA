package Math;

public class GreaterCommonDivisor {
    public int gcd(int a, int b) {
        int greater = a, smaller = b;
        if (b > a) {
            greater = b; smaller = a;
        }

        return findGcd(greater, smaller);
    }

    private int findGcd(int greater, int smaller) {
        if (smaller == 0) {
            return greater;
        }

        return findGcd(smaller, greater % smaller);
    }
}
