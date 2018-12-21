package hu.szakadth;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by bogrea on 2018.09.26..
 */
public class IntervallTest {
    public static void main( String... args ) {
        new IntervallTest().test();
    }

    public void test() {
        List<Integer> list = Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80);
        Calendar c = Calendar.getInstance();
        c.set(2012, 2, 14);
        Date now = c.getTime();
        c.set(2010, 7, 17);
        Date prev = c.getTime();
        List<Integer> months = IntStream.range(0, getDifferenceInMonths(prev, now)).boxed()
            .collect(Collectors.toList());
        months.forEach(System.out::println);

/*
        AtomicInteger prev = new AtomicInteger(0);
            .peek(p -> prev.set(p)).map(p -> prev.get()).forEach(p -> System.out.printf("intervall: %d - %d", prev.get(), p));
*/    }

    public static int getDifferenceInMonths(final Date startDate, final Date endDate){
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        return diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
    }
}
