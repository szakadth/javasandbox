package hu.szakadth.math;

/**
 * Created by bogrea on 2018.08.22..
 */
public class GCD {
    public static void main( String[] args ) {
        System.out.println(gcd(33, 3));

        double x = 27.0d;
        System.out.println(sqrt(x, x/2d, 0.00000001));
        System.out.println(Math.sqrt(x));
    }

    public static int gcd(int m, int n) {
        if (m > n) return gcd (n, m);
        if ( n % m == 0 ) return m;
        return (gcd(n % m, m));
    }

    public static double sqrt (double a, double x, double e) {
        if (Math.abs(x * x - a) < e) return x;
        return sqrt(a, (x + a / x ) / 2, e);
    }
}
