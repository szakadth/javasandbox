package hu.szakadth;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Created by bogrea on 2018.11.13..
 */
public class LambdaTest {

    private Set<DataHolder> data = new HashSet<DataHolder>() {{
        add(new DataHolder(3, "Test3"));
        add(new DataHolder(1, "Test1"));
        add(new DataHolder(2, "Test2"));
    }};

    private Map<DataHolder, Integer> testMap = new TreeMap<DataHolder, Integer>(){{
        put(new DataHolder(3, "Test3"), 1);
        put(new DataHolder(2, "Test2"), 0);
        put(new DataHolder(3, "Test3"), 1);
        put(new DataHolder(1, "Test1"), 0);
        put(new DataHolder(1, "Test2"), 1);
        put(new DataHolder(1, "Test3"), 3);
        put(new DataHolder(3, "Test4"), 3);
        put(new DataHolder(3, "Test5"), 1);
        put(new DataHolder(3, "Test4"), 2);

    }};

    public static void main( String... args ) {
        new LambdaTest().test();
    }

    public void test() {
        // test1();
        // test2();
        test3();
    }

    private void test3() {
        List<Integer> intValues = data.stream()
            .map(DataHolder::getIntValue)
            .distinct()
            .sorted(Integer::compare)
            .collect(Collectors.toList());
        intValues.forEach(System.out::println);

    }
    private void test1() {
        for ( DataHolder dh : data ) {
            if ((dh.getIntValue() + 1) == 3) {
                System.out.println(dh.getStringValue());
            }
        }
    }

    private void test2() {
        System.out.println(data.stream()
            .filter(p -> (p.getIntValue() + 1) == 3)
            .map(DataHolder::getStringValue)
            .findFirst().orElse("N/A"));
    }



    private class DataHolder {
        private Integer intValue = null;
        private String stringValue = null;
        private Double doubleValue = null;

        public DataHolder( Integer intValue, String stringValue ) {
            this.intValue = intValue;
            this.doubleValue = new Double(intValue);
            this.stringValue = stringValue;
        }

        public Integer getIntValue() {
            return intValue;
        }

        public void setIntValue( Integer intValue ) {
            this.intValue = intValue;
        }

        public String getStringValue() {
            return stringValue;
        }

        public void setStringValue( String stringValue ) {
            this.stringValue = stringValue;
        }

        public Double getDoubleValue() {
            return doubleValue;
        }

        public void setDoubleValue( Double doubleValue ) {
            this.doubleValue = doubleValue;
        }
    }
}
