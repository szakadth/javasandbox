package hu.szakadth;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by bogrea on 2018.03.07..
 */
public class ArrayOfArrayTest {

    public class ClassC {
        private String name;
        private Integer value;

        public ClassC(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }

        public Integer getNullValue() { return null; }

        @Override
        public String toString() {
            return "ClassC{" +
                    "name='" + name + '\'' +
                    ", value=" + value +
                    '}';
        }
    }

    public class ClassB {
        private Collection<ClassC> coll;

        public ClassB(Collection<ClassC> coll) {
            this.coll = coll;
        }

        public Collection<ClassC> getColl() {
            return coll;
        }
    }

    public class ClassA {
        private Collection<ClassB> coll;

        public ClassA(Collection<ClassB> coll) {
            this.coll = coll;
        }

        public Collection<ClassB> getColl() {
            return coll;
        }
    }

    public static void main(String[] args) {
        ArrayOfArrayTest test = new ArrayOfArrayTest();
        test.process();
    }

    public void process() {
        ArrayList<ClassC> collB1 = new ArrayList<ClassC>(){
            {
                add(new ClassC("alma", 1));
                add(new ClassC("banán", 2));
                add(new ClassC("citrom", 3));
                add(new ClassC("dió", 4));
            }};

        ArrayList<ClassC> collB2 = new ArrayList<ClassC>(){
            {
                add(new ClassC("dió", 6));
                add(new ClassC("eper", 7));
                add(new ClassC("füge", 8));
                add(new ClassC("alma", 9));
                add(new ClassC("dió", 10));
            }};

        ArrayList<ClassB> collA1 = new ArrayList<ClassB>(){
            {
                add(new ClassB(collB2));
                add(new ClassB(collB1));
            }};

        ClassA a = new ClassA(collA1);

        ArrayList<String> names = (ArrayList<String>)a.getColl().stream()
                .flatMap(x -> x.getColl().stream())
                .map(p -> p.getName())
                .distinct()
                .collect(Collectors.toList());

        names.stream().forEach(System.out::println);


        Set<String> forecasts = new TreeSet<>();
        for (ClassB classB : a.getColl()) {
            for (ClassC classC : classB.getColl()) {
                forecasts.add(classC.getName());
            }
        }
        System.out.println("-------------------");
        forecasts.forEach(System.out::println);

        System.out.println("----------------getFirst ----------------");

        ClassA a2 = new ClassA(collA1);
        try {
            String first = a2.getColl().stream()
                    .flatMap(x -> x.getColl().stream())
                    .findFirst().map(p -> p.getName()).orElse(null);
            System.out.println(first);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("----------------range(2,5) ----------------");
        IntStream.range(2,5).mapToObj(i -> names.get(i)).forEach(System.out::println);

        System.out.println("----------------groupBy sum ----------------");
        Map<String, Integer> groupBySum = a.getColl().stream()
                .flatMap(x -> x.getColl().stream()).collect(Collectors.groupingBy(ClassC::getName, Collectors.summingInt(ClassC::getValue)));

        System.out.println(groupBySum);

        System.out.println("----------------groupBy avg ----------------");
        Map<String, Double> groupByAvg = a.getColl().stream()
                .flatMap(x -> x.getColl().stream()).collect(Collectors.groupingBy(ClassC::getName, Collectors.averagingInt(ClassC::getValue)));

        System.out.println(groupByAvg);

        System.out.println("----------------groupBy count ----------------");
        Map<String, Long> groupByCount = a.getColl().stream()
                .flatMap(x -> x.getColl().stream()).map(p -> p.getName()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(groupByCount);


        System.out.println("----------------avg ----------------");
        Double avg = OptionalDouble.of(a.getColl().stream()
                .flatMap(x -> x.getColl().stream()).mapToInt(p -> p.getValue()).average().getAsDouble()).orElse(0);

        System.out.println(avg);

        System.out.println("----------------get füge value ----------------");
        Integer fugeValue = a.getColl().stream()
                .flatMap(x -> x.getColl().stream()).filter(p -> "füge".equals(p.getName())).map(p -> p.getValue()).findFirst().orElse(0);

        System.out.println(fugeValue);

        System.out.println("----------------toMap ----------------");
        ArrayList<ClassC> classCarray = new ArrayList<ClassC>() {{
            add(new ClassC("agár", 6));
            add(new ClassC("béka", 7));
            add(new ClassC("cica", 8));
            add(new ClassC("dalmata", 9));
            add(new ClassC("elefánt", 10));

        }};
        Map<String,ClassC> map = classCarray.stream().collect(Collectors.toMap(ClassC::getName, Function.identity()));

        System.out.println(map);
        System.out.println("----------------toArray ----------------");
        List<String> nameArray = classCarray.stream().map(p -> p.getName()).collect(Collectors.toList());
        System.out.println(nameArray);

    }


}
