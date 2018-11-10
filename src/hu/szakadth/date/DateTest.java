package hu.szakadth.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by bogrea on 2018.09.04..
 */
public class DateTest {

    public static void main( String... args ) {

        new DateTest().diffDateTest();
    }

    public void diffDateTest() {
        Calendar c = Calendar.getInstance();
        c.set(2021, 2, 14);
        Date prev = c.getTime();
        c.set(2010, 7, 17);
        Date now = c.getTime();

        System.out.println(Math.ceil(getDifferenceInMonthsCheckingDays(now, prev) / 12d));







//        System.out.printf("Years between: %d", getDateDiffInYearRoundedUp(prev, now));
//
//        int diffMonth = getDifferenceInMonths(prev, now);
//        Double eadMaturity = Math.ceil((getDifferenceInMonths(now, prev) + 1d) / 12d);
//        System.out.printf("\n\nMonth between: %d, %f", diffMonth, eadMaturity);

    }

    private int getDateDiffInYearRoundedUp(Date d1, Date d2) {
        LocalDate ld1 = asLocalDate(d1);
        LocalDate ld2 = asLocalDate(d2);
        return (int)Math.ceil(ChronoUnit.DAYS.between(ld1, ld2) / 365d);

    }

    private long getDateDiffInYear(Date d1, Date d2) {
        LocalDate ld1 = asLocalDate(d1);
        LocalDate ld2 = asLocalDate(d2);
        return ChronoUnit.YEARS.between(ld1, ld2);

    }

    public static LocalDate asLocalDate(java.util.Date date) {
        return asLocalDate(date, ZoneId.systemDefault());
    }

    /**
     * Creates {@link LocalDate} from {@code java.util.Date} or it's subclasses. Null-safe.
     */
    public static LocalDate asLocalDate(java.util.Date date, ZoneId zone) {
        if (date == null)
            return null;

        if (date instanceof java.sql.Date)
            return ((java.sql.Date) date).toLocalDate();
        else
            return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDate();
    }

    public static int getDifferenceInMonths(final Date startDate, final Date endDate){
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        return diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
    }

    public static int getDifferenceInMonthsCheckingDays(final Date startDate, final Date endDate){
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        return getDifferenceInMonths(startDate, endDate)
            + (endCalendar.get(Calendar.DAY_OF_MONTH) - startCalendar.get(Calendar.DAY_OF_MONTH) > 0 ? 1 : 0);
    }


}
