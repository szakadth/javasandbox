package hu.szakadth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by bogrea on 2018.04.04..
 */
public class NullCheck {

    public static void main(String[] args) {
        ArrayList<String> array = null;
        ArrayList<String> nullsafearray = Optional.ofNullable(array).orElse(new ArrayList<String>());


        for (String s : nullsafearray) {
            System.out.println(s);
        }
        if (Optional.ofNullable(isValid()).orElse(false)) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }

        ArrayList<String> list = new ArrayList<String>(){{
            add("alma");
        }};
        System.out.println(list.get(0));
        System.out.println(list.size());
 //       System.out.println(list.get(1));

        TestArrayHolder tl = new TestArrayHolder();

        Optional<List<String>> sl = Optional.of(tl.getList());
        System.out.println("Len: " + sl.map(List::size).orElse(0));

        tl.setList(list);
        System.out.println("Len: " + (Optional.ofNullable(tl).map(TestArrayHolder::getList).orElse(Collections.emptyList())
            .stream().count()));


        if (Optional.ofNullable(list).map(p->!p.isEmpty()).orElse(false)) {
            System.out.println("Not Empty");
        } else {
            System.out.println("Empty (or null)");
        }
        TestClass tc = new TestClass();
        if (Optional.ofNullable(tc).map(TestClass::isOk).orElse(false)) {
            System.out.println("OK");
        } else {
            System.out.println("NOT OK");
        }

        if (Optional.ofNullable(tc).map(TestClass::getText).isPresent()) {
            System.out.println("Present");
        } else {
            System.out.println("NOT Present");
        }



    }

    private static Boolean isValid() {
        return null;
    }

    private static class TestClass {

        public Boolean isOk() {
            return true;
        }
        public String getText() {
            return "Almafa";
        }
    }

    private static class TestArrayHolder {
        private ArrayList<String> list;

        public List<String> getList() {
            return list;
        }

        public void setList(ArrayList<String> newList) {
            list = newList;
        }
    }

}
