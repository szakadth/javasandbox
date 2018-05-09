package hu.szakadth.interfacetest;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by bogrea on 2018.03.22..
 */
public class IfTest {

    private ArrayList<TestImpl> persons = new ArrayList<TestImpl>(){{
        add(new TestImpl("Budapest", 1980));
        add(new TestImpl("Szeged", 1955));
        add(new TestImpl("Tata", 1989));
        add(new TestImpl("Győr", 1993));
    }};
    public static void main(String[] args) {
        new IfTest().process();
    }

    public void process() {
        System.out.println(TestConst.C1);
        persons.stream().forEach(p -> System.out.println(p.getCity()));
    }
}
