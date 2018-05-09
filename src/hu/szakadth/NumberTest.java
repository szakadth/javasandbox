package hu.szakadth;

/**
 * Created by bogrea on 2018.03.13..
 */
public class NumberTest {
    public static void main(String[] args) {
        Double d1 = new Double(12.0d);
        Double d2 = new Double(12);
        Double d3 = d1 - d2;
        System.out.println(String.format("Result: %10.3f", d3));
        if (d3 == 0.0d) {
            System.out.println("ZERO");
        }
    }
}
