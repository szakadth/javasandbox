package hu.szakadth;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by bogrea on 2018.03.02..
 */
public class Nvl {

    public class Root {
        private Trunk trunk;

        public Trunk getTrunk() {
            return trunk;
        }

        public void setTrunk(Trunk trunk) {
            this.trunk = trunk;
        }
    }

    public class Trunk {
        private Branch branch;

        public Branch getBranch() {
            return branch;
        }

        public void setBranch(Branch branch) {
            this.branch = branch;
        }
    }

    public class Branch {
        private  Collection<Leaf> leafs;

        public Collection<Leaf> getLeafs() {
            ArrayList<Leaf> list = new ArrayList<Leaf>();
            list.add(new Leaf("ALMAFA!!!!"));
            list.add(new Leaf("körtefa"));
            list.add(new Leaf("citromfa"));
            list.add(new Leaf("diofa"));
            return list;
        }
    }

    public class Leaf {
        private String name;

        public Leaf(String name) {
            this.name = name;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    public static void main(String[] args) {
        System.out.println(Nvl.<String>nvl(null, "almafa", "körtefa"));
        System.out.println(Nvl.<String>nvl(null, null, null));
        System.out.println(Nvl.<String>nvl("egy", "ketto", null));
        Nvl test = new Nvl();
        test.nullSafeChainTest();
        test.anyNullTest();

    }

    private static<T> T nvl (T first, T second, T... args) {
        return Stream.concat(
            Stream.of(first,second),
            Stream.of(getValues((T[]) args)))
            .filter(Objects::nonNull)
            .findFirst()
            .orElse(null);
    }

    public void nullSafeChainTest () {
        Branch branch = new Branch();
        Trunk trunk =  new Trunk();
        trunk.setBranch(branch);
        Root root =  new Root();
        root.setTrunk(trunk);

        Collection leafs = Optional.ofNullable(root)
                .map(p -> p.getTrunk())
                .map(p -> p.getBranch())
                .map(p -> p.getLeafs())
                .orElse(Collections.EMPTY_LIST);
        Leaf leaf = (Leaf)leafs.stream().findFirst().orElse(null);
        if (leaf != null) {
            System.out.println(leaf.getName());
        }
    }

    public void anyNullTest () {
        Double d1 = 12.3d;
        String d2 = "3.4d";
        Double d3 = 12.3d;

        if (anyNull(d1, d2, d3)) {
            System.out.println("1: is-null");

        } else {
            System.out.println("1: no-null");
        }

        if (anyNull(d1)) {
            System.out.println("2: is-null");

        } else {
            System.out.println("2: no-null");
        }

        if (anyNull(null, "alma", Boolean.TRUE)) {
            System.out.println("3: is-null");

        } else {
            System.out.println("3: no-null");
        }
        if (anyNull(null, null)) {
            System.out.println("3: is-null");

        } else {
            System.out.println("3: no-null");
        }

    }

    public static<T> boolean anyNull(T first, T... args ) {
        return any(Objects::isNull, first, args);
    }

    public static<T> boolean allNull(T first, T... args ) {
        return all(Objects::isNull, first, args);
    }

    public static<T> boolean allNonNull(T first, T... args ) {
        return all(Objects::nonNull, args);
    }

    public static<T> boolean anyNonNull(T first, T... args ) {
        return any(Objects::nonNull, first, args);
    }

    public static<T> boolean any (Predicate<T> predicate, T first, T... args) {
        return Stream.concat(
            Stream.of(first),
            Stream.of(getValues(args)))
            .anyMatch(predicate);
    }


    public static<T> boolean all (Predicate<T> predicate, T first, T... args) {
        return Stream.concat(
            Stream.of(first),
            Stream.of(getValues((T[]) args)))
            .allMatch(predicate);
    }

    private static <T> T[] getValues( T[] args ) {
        return Optional.ofNullable(args).orElse((T[]) new Object[]{});
    }

}
