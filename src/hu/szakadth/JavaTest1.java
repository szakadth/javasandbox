package hu.szakadth;

import java.util.stream.IntStream;

/**
 * Created by bogrea on 2018.03.28..
 */
public class JavaTest1 {
    public static void main (String... arg) {
        StringBuilder sb = IntStream.iterate(0, x->(x+1)%26)
                .mapToObj(x -> new StringBuilder("" + (char)(x+'A')))
//                .parallel()
                .limit(52)
                .collect(() -> new StringBuilder(), (x,y) -> y.append(x), (x,y) -> y.append(x));
        System.out.println(sb);


    }
}
