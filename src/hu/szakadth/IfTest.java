package hu.szakadth;

/**
 * Created by bogrea on 2018.10.16..
 */
public class IfTest {
    public static void main( String[] args ) {
        int a = 2;
        boolean b = a > 0 && then() || otherwise();
    }

    private static boolean then() {
        System.out.println("Then");
        return true;
    }
    private static boolean otherwise() {
        System.out.println("otherwise");
        return false;
    }
}
