package hu.szakadth;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by bogrea on 2018.04.03..
 */
public class Fibonacci {

    public static void main (String... args) {
        new Fibonacci().stream(10);
    }

    public void stream(long n) {
        Stream.iterate(new BigDecimal[]{ BigDecimal.ONE, BigDecimal.ONE }, p->new BigDecimal[]{ p[1], p[0].add(p[1])})
                .limit(n).forEach(p -> System.out.print(String.format("%d, ", p[0].intValue())));
        System.out.println(Stream.iterate(new Integer[]{ 1, 1 }, p -> new Integer[]{ p[1], p[0]+ p[1]})
            .limit(n).map(p -> p[0].toString()).collect(Collectors.joining(",")));
    }

 }
