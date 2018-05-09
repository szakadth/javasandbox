package hu.szakadth;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * Created by bogrea on 2018.02.27..
 */
public class Test {


    public static void main(String[] args) {
        Integer int1 = new Integer(1234567);
        Integer int2 = new Integer(1234568);
        if (int1 <= int2) {
            System.out.println("<=");
        } else {
            System.out.println(">");
        }
        System.out.println(int1.compareTo(int2));

        int[] ints = IntStream.of(0,0,0,0).toArray();
        int i = 0;
        ints[++i] = ++i;
        AtomicInteger count = new AtomicInteger(-1);
        IntStream.of(ints).boxed().forEach(v -> {
            System.out.println(String.format("%4d -> %4d", count.incrementAndGet(), v));
        });


        System.out.println(getInt(10));

    }


    private static Integer getInt(int i) {
        return i > 5 ? null : new Integer(i);

    }


}
