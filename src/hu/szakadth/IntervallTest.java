package hu.szakadth;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by bogrea on 2018.09.26..
 */
public class IntervallTest {
    public static void main( String... args ) {
        new IntervallTest().test();
    }

    public void test() {
        List<Integer> list = Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80);
/*
        AtomicInteger prev = new AtomicInteger(0);
            .peek(p -> prev.set(p)).map(p -> prev.get()).forEach(p -> System.out.printf("intervall: %d - %d", prev.get(), p));
*/    }
}
