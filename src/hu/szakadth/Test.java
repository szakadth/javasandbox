package hu.szakadth;

import java.util.stream.IntStream;

/**
 * Created by bogrea on 2018.02.27..
 */
public class Test {


    public static void main(String[] args) {

        new Test().test();
    }

    public void test() {
        int x = 0;
        int y = 5;

        x = ++y + ++y;
        System.out.printf("\n(++y) + (++y) = %d\n------------------", x);



        int j = 0;
        j = j++ + j;
        System.out.println("j : " + j);

        j = 0;
        System.out.println(j++ + ++j);

        j = 0;
        System.out.println(++j + j++);

        Integer int1 = new Integer(1234567);
        Integer int2 = new Integer(1234568);
        if (int1 <= int2) {
            System.out.println("<=");
        } else {
            System.out.println(">");
        }
        System.out.println(int1.compareTo(int2));

        int[] ints = {0,0,0,0}; //IntStream.of(0,0,0,0).toArray();
        int i = 0;
        ints[++i] = i++ + ++i;
//        AtomicInteger count = new AtomicInteger(-1);
        Counter counter = new Counter(0);
        IntStream.of(ints).boxed().forEach(v -> {
            System.out.printf("%4d -> %4d\n", counter.inc().getValue(), v);
        });
        System.out.printf("i = %4d\n\n", i);

        System.out.println(getInt(10));

    }


    private static Integer getInt(int i) {
        return i > 5 ? null : new Integer(i);

    }

    private class Counter {
        int c = 0;
        int startValue = 0;
        public Counter(int startValue) {
            c = startValue;
            this.startValue = startValue;
        }
        public int getValue() {
            return c;
        }
        public Counter inc(int incValue) {
            c += incValue;
            return this;
        }

        public Counter inc() {
            return inc(1);
        }

        public Counter set (int value) {
            c = value;
            return this;
        }

        public Counter reset () {
            return set(startValue);
        }
    }


}
