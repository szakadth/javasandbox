package hu.szakadth;

import hu.szakadth.exception.RException;
import hu.szakadth.r.RCallerExecutor;
import hu.szakadth.r.RServeExecutor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Main {

    private static final int NUM_OF_PARAMS = 10;
    private static DateFormat df = new SimpleDateFormat("yyyy.MM.dd");



    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.process();
    }

    public void process() {


        Set values = new HashSet<Value>();
        Random rand = new Random();
        rand.setSeed(new Date().getTime());

        try {
            LocalDate startDate = LocalDate.of(2017, 1, 1);
            Supplier<Value> valueSupplier = () -> new Value(asDate(startDate.plusDays(rand.nextInt(5 * 365))), rand.nextDouble() * 20d);
            ArrayList<Value> array = Stream.generate(valueSupplier).limit(NUM_OF_PARAMS).sorted(new Comparator<Value>() {
                @Override
                public int compare(Value o1, Value o2) {
                    return o1.getDate().compareTo(o2.getDate());
                }
            }).collect(Collectors.toCollection(ArrayList::new));

            Date minDate = Optional.ofNullable(array.stream().map(u -> u.getDate()).min(Date::compareTo).get()).orElse(new Date());
            System.out.println(minDate);


            final AtomicInteger count = new AtomicInteger(0);
            array.stream().forEach(v -> System.out.println(String.format("%4d -> %s", count.incrementAndGet(), v.toString())));

            Predicate<Value> filter = (p) -> p.getValue() > 10.0d && p.getDate().after(asDate(LocalDate.of(2019,1,1)));
            Double sum = array.stream().filter(filter).map(p -> p.getValue()).reduce(0.0d, Double::sum);
            System.out.println(String.format("Sum: %15.7f", sum));

            double[] csv = (double[])array.stream()
                    .mapToDouble(v -> v.getValue()).toArray(); // collect(Collectors.joining(",", "c(", ")"));


            String frequency = "1600";
            IRExecutor executor = new RCallerExecutor();
            double[] result = executor.evaluate("library(mFilter)")
                    .assign("input_data", csv)
                    .evaluate("hp_output <- hpfilter(input_data, freq=" + frequency + ")")
                    .evaluate("hp_output$cycle").getResultAsDoubleArray();

            count.set(0);
            DoubleStream.of(result).boxed().forEach(v -> {
                System.out.println(String.format("%4d -> %15.7f", count.incrementAndGet(), v));
            });
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public class Value {
        private Date date;
        private Double value;

        public Value(Date date, Double value) {
            this.date = date;
            this.value = value;
        }

        public Value(String date, Double value) throws ParseException {
            setDateAsString(date);
            this.value = value;
        }

        public void setDateAsString(String date) throws ParseException {
            this.date = df.parse(date);
        }

        public Date getDate() {
            return date;
        }


        public Double getValue() {
            return value;
        }

        public String getDateAsString() {
            return df.format(this.date);
        }

        @Override
        public String toString() {
            return "Value{" +
                    "date=" + getDateAsString() +
                    ", value=" + value +
                    '}';
        }
    }
}
