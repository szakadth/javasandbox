package hu.szakadth;

import java.util.Date;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by bogrea on 2018.07.20..
 */
public class StreamTest {
    public static void main( String... args ) {
        new StreamTest().process();
    }

    public void process() {
        Random random = new Random();
        Supplier<Integer> supplier = () -> new Integer(random.nextInt(200));
        Supplier<Stream<Integer>> stream = () -> Stream.generate(supplier).limit(100000);
//            .filter(p -> p > 50);
        stream.get().filter(p -> p > 50);
        stream.get().filter(p -> p < 150);

        Integer sum = stream.get()
            .reduce(0, Integer::sum);

        System.out.printf("Sum = %d", sum);
    }

}
