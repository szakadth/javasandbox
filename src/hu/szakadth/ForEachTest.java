package hu.szakadth;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by bogrea on 2018.07.12..
 */
public class ForEachTest {
    private Set<Test> a1 = new TreeSet<Test>() {{
        add(new Test("a", 1d));
        add(new Test("b", 2d));
        add(new Test("c", 3d));
        add(new Test("d", 5d));
        add(new Test("e", 8d));
        add(new Test("g", 13d));
        add(new Test("h", 21d));
        add(new Test("i", 34d));
        add(new Test("j", 55d));
    }};

    private Set<Test> a2 = new TreeSet<Test>() {{
        add(new Test("a", 10d));
        add(new Test("b", 20d));
        add(new Test("c", 30d));
        add(new Test("d", 40d));
        add(new Test("e", 50d));
        add(new Test("g", 60d));
        add(new Test("h", 70d));
        add(new Test("i", 80d));
        add(new Test("j", 90d));
    }};

    public static void main( String... args ) {
        new ForEachTest().process();

    }

    private void process() {
        Map<String, Test> m2 = a2.stream().collect(Collectors.toMap(Test::getId, Function.identity()));
        System.out.println(a1.stream().map(p -> substract(p)).reduce(0d, Double::sum));
        System.out.println(a1.stream().map(p -> p.getValue() - m2.get(p.getId()).getValue()).reduce(0d, Double::sum));


    }
    private Double substract(Test test) {
        Double val = a2.stream().filter(p -> p.getId().equals(test.getId())).map(Test::getValue).findFirst().orElse(0d);
        return test.getValue() - val;
    }


    private class Test {
        private String id;
        private Double value;

        public Test( String id, Double value ) {
            this.id = id;
            this.value = value;
        }

        public String getId() {
            return id;
        }

        public Double getValue() {
            return value;
        }
    }
}
