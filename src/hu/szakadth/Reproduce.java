package hu.szakadth;
import java.util.stream.IntStream;
import java.util.stream.Stream;
public class Reproduce {
    public static void main(String... args) {
        char q = 34;
        String[] l = {
            "package hu.szakadth;",
            "import java.util.stream.IntStream;",
            "import java.util.stream.Stream;",
            "public class Reproduce {",
            "    public static void main(String... args) {",
            "        char q = 34;",
            "        String[] l = {",
            "           ",
            "        };",
            "        IntStream.range(0, 7).mapToObj(i -> l[i]).forEach(System.out::println);",
            "        Stream.of(l).forEach(p -> System.out.println(l[7] + q + p + q + ','));",
            "        IntStream.range(8, l.length).mapToObj(i -> l[i]).forEach(System.out::println);",
            "    }",
            "}",
        };
        IntStream.range(0, 7).mapToObj(i -> l[i]).forEach(System.out::println);
        Stream.of(l).forEach(p -> System.out.println(l[7] + q + p + q + ','));
        IntStream.range(8, l.length).mapToObj(i -> l[i]).forEach(System.out::println);
    }
}