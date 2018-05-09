package hu.szakadth;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by bogrea on 2018.03.06..
 */
public class ReduceTest {
    public static void main(String[] args) {
        Random random = new Random();
        Supplier<Integer> supplier = () -> new Integer(random.nextInt(200));
        long start = new Date().getTime();
        Integer sum = Stream.generate(supplier).limit(10000000).parallel().reduce(0, Integer::sum);
        long stop = new Date().getTime();
        System.out.println(stop-start);
        System.out.println(sum);

        int[] randomintArray = new Random().ints(10000000, 0, 200).toArray();
        Double avg  = IntStream.of(randomintArray).average().getAsDouble();
        System.out.println(avg);


        ArrayList<Integer> intsWithNull = new ArrayList<Integer> () {{
            add(2);
            add(null);
            add(10);
            add(8);
        }};
        sum = intsWithNull.stream().filter(Objects::nonNull).reduce(0,Integer::sum);
        System.out.println(sum);

    }
}
