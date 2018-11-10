package hu.szakadth;

import java.util.stream.IntStream;

/**
 * Created by bogrea on 2018.03.28..
 */
public class JavaTest1 {
    public static void main (String... arg) {


        StringBuilder sb = IntStream.iterate(0, x->(x+1)%32)
                .mapToObj(x -> new StringBuilder("" + (char)(x)))
//                .parallel()
                .limit(32)
                .sequential()
                .collect(() -> new StringBuilder(), (x,y) -> x.append(y), (x,y) -> {
                    System.out.println("qlq");
                });
        System.out.println(sb);


    }
}
