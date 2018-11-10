package hu.szakadth;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by bogrea on 2018.08.29..
 */
public class Question {

    public static void main( String... args ) {

        int a = 50;
        System.out.printf("%d%d%d%d\n\n", ++a, a++, --a, a--);

        IntStream.range(0,5).forEach(System.out::print);
        IntStream.range(0,5).forEach(p -> System.out.println(p));
        IntStream.range(0,5).forEach(p -> {System.out.println(p);});
//        IntStream.range(0,5).forEach({System.out::println;});

        System.out.println("---------------------------");
        int i = 0;
        IntStream.range(0,5).forEach(p -> System.out.println(i + p));
        System.out.println("---------------------------");
        Stream.iterate(new BigDecimal(0), p -> p.add(BigDecimal.ONE)).limit(5).forEach(System.out::println);
        System.out.println("---------------------------");
        Stream.iterate(0, p -> p = p + 1).limit(5).forEach(System.out::println);

        System.out.println("---------------------------");
        IntStream.range(100,200)
            .forEach(x -> System.out.println(x % 3 == 0 && x % 5 == 0 ? "abcdef" : x % 3 == 0 ? "abc" : x % 5 == 0 ? "def" : "" + x));

        System.out.println("---------------------------");
        System.out.println(IntStream.iterate(100, x -> x + 1)
            .mapToObj(x -> x % 3 == 0 && x % 5 == 0 ? "abcdef" : x % 3 == 0 ? "abc" : x % 5 == 0 ? "def" : "" + x)
            .limit(100)
            .collect(Collectors.joining(",", "", "")));

        System.out.println("---------------------------");
        System.out.println(IntStream.range(100, 200)
            .mapToObj(x -> x % 3 == 0 && x % 5 == 0 ? "abcdef" : x % 3 == 0 ? "abc" : x % 5 == 0 ? "def" : "" + x)
            .collect(Collectors.joining(",", "", "")));

        System.out.println("---------------------------");
        System.out.println(IntStream.range(100, 200)
            .mapToObj(x -> "" + x)
            .collect(Collectors.joining(",", "", "")));

    }

}
