package hu.szakadth;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by bogrea on 2018.11.13..
 */
public class SortedMapTest {

    public static void main( String[] args ) {
        new SortedMapTest().test();
    }

    private void test() {
        Map<Key, String> map = new HashMap<>();
        map.put(new Key("alma", 1), "alma1");
        map.put(new Key("dio", 3), "dio3");
        map.put(new Key("banán", 2), "banán2");
        map.put(new Key("dio", 4), "dio4");
        map.put(new Key("banán", 1), "banán1");
        map.put(new Key("alma", 3), "alma3");
        map.put(new Key("dio", 2), "dio2");
        map.put(new Key("citrom", 2), "citrom2");
        map.put(new Key("banán", 4), "banán4");
        map.put(new Key("dio", 1), "dio1");
        map.put(new Key("citrom", 1), "citrom1");
        map.put(new Key("banán", 3), "banán3");
        map.put(new Key("alma", 2), "alma2");

        List<Map.Entry<Key, String>> sortedMapEntries =
            map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toList());

        for (Map.Entry<Key, String> entry : sortedMapEntries) {
            Key key = entry.getKey();
            String value = entry.getValue();
            System.out.println(value);
        }
    }

    private static Comparator<Key> KEY_COMPARATOR = getComparator();

    private static Comparator<Key> getComparator(){
        Comparator<Key> result =
            Comparator.comparing(Key::getStrKey)
                .thenComparing(Key::getIntKey);
            ;
        return result;
    }


    private class Key implements Comparable<Key> {



        private String strKey;
        private Integer intKey;

        public Key( String strKey, Integer intKey ) {
            this.strKey = strKey;
            this.intKey = intKey;
        }

        public String getStrKey() {
            return strKey;
        }

        public void setStrKey( String strKey ) {
            this.strKey = strKey;
        }

        public Integer getIntKey() {
            return intKey;
        }

        public void setIntKey( Integer intKey ) {
            this.intKey = intKey;
        }

        @Override public boolean equals( Object o ) {
            if ( this == o )
                return true;
            if ( o == null || getClass() != o.getClass() )
                return false;
            Key key = (Key) o;
            return Objects.equals(strKey, key.strKey) && Objects.equals(intKey, key.intKey);
        }

        @Override public int hashCode() {
            return Objects.hash(strKey, intKey);
        }

        @Override
        public int compareTo( Key that ) {
            return KEY_COMPARATOR.compare(this, that);
        }

    }
}
