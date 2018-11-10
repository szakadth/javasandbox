package hu.szakadth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by bogrea on 2018.09.05..
 */
public class MaxTest {

    Collection<Test> tests = new ArrayList<Test>() {{
        add(new Test(1d,1d));
        add(new Test(1d,2d));
        add(new Test(2d,2d));
        add(new Test(1d,3d));
        add(new Test(2d,3d));
    }};


    public static void main( String[] args ) {
        new MaxTest().test();
    }

    public void test() {

        Double maxAge = tests.stream().map(p -> p.getAge()).reduce(0d, Double::max);
        Double maxValue = tests.stream().map(p -> p.getValue()).reduce(0d, Double::max);
        System.out.println("Test1: " + getTest(1d, 2d));
        System.out.println("Test2: " + getTest(1d, maxValue));
        System.out.println("Test3: " + getTest(maxAge, maxValue));
        System.out.println("Test4: " + getTest(2d, 4d));

        System.out.printf("Age1: %d\n", Optional.ofNullable(getTest(1d, 2d)).map(p -> p.getAge()).orElse(0d).intValue());
        System.out.printf("Age2: %d\n", Optional.ofNullable(getTest(1d, maxValue)).map(p -> p.getAge()).orElse(0d).intValue());
        System.out.printf("Age3: %d\n", Optional.ofNullable(getTest(maxAge, maxValue)).map(p -> p.getAge()).orElse(0d).intValue());
        System.out.printf("Age4: %d\n", Optional.ofNullable(getTest(2d, 4d)).map(p -> p.getAge()).orElse(0d).intValue());



    }

    private Test getTest(Double age, Double value) {
        Test test = tests.stream().filter(testFilter(age, value)).findFirst().orElse(null);
        return test;
    }

    private Predicate<Test> testFilter(Double age, Double value) {
        return p -> p.getAge().equals(age) && p.getValue().equals(value);
    }

    private class Test {
        private Double age;
        private Double value;

        public Test(Double age, Double value) {
            this.age = age;
            this.value = value;
        }

        public Double getAge() {
            return age;
        }

        public Double getValue() {
            return value;
        }

        @Override public String toString() {
            final StringBuilder sb = new StringBuilder("Test{");
            sb.append("age=").append(age);
            sb.append(", value=").append(value);
            sb.append('}');
            return sb.toString();
        }
    }
}
