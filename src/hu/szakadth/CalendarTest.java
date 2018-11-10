package hu.szakadth;

import java.util.Calendar;

/**
 * Created by bogrea on 2018.07.18..
 */
public class CalendarTest {
    public static void main( String... args ) {
        Calendar first = Calendar.getInstance();
        System.out.println("Current date : " + (first.get(Calendar.MONTH) + 1) + "-"
            + first.get(Calendar.DATE) + "-" + first.get(Calendar.YEAR));

        first = Calendar.getInstance();
        first.add(Calendar.YEAR, -1);
        first.set(Calendar.DAY_OF_YEAR, 1);

        System.out.println("date before 1 years : " + (first.get(Calendar.MONTH) + 1) + "-"
            + first.get(Calendar.DATE) + "-" + first.get(Calendar.YEAR));
        Calendar last = Calendar.getInstance();
        last.setTime(first.getTime());
        last.add(Calendar.YEAR, 1);
        last.add(Calendar.DATE, -1);
        System.out.println("date before 1 years : " + (last.get(Calendar.MONTH) + 1) + "-"
            + last.get(Calendar.DATE) + "-" + last.get(Calendar.YEAR));

    }
}
