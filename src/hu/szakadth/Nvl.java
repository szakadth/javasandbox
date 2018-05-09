package hu.szakadth;

import java.util.*;
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
    }

    private static<T> T nvl (T... args) {
        return Stream.of(args).filter(Objects::nonNull).findFirst().orElse(null);
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


}
