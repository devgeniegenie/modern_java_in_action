package chapter12;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Date;
import java.util.Locale;

import static java.time.temporal.TemporalAdjusters.*;

public class DateTimeExamples {
    public static void main(String[] args) {
        userOldDate();
        useLocalDate();
        useDateFormatter();
    }

    private static void userOldDate() {
        Date date = new Date(117, 8, 21);
        System.out.println(date);
    }

    private static void useLocalDate() {
        LocalDate date = LocalDate.of(2017, 9, 21);
        int year = date.getYear();
        Month month = date.getMonth();
        int month3 = date.getMonthValue();
        int day = date.getDayOfMonth();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();
        LocalDate temp = LocalDate.now();

        int year2 = date.get(ChronoField.YEAR);
        int month2 = date.get(ChronoField.MONTH_OF_YEAR);
        int day2 = date.get(ChronoField.DAY_OF_MONTH);

        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();

        LocalDate date2 = LocalDate.parse("2017-09-21");
        LocalTime time2 = LocalTime.parse("13:45:20");

        //2017-09-21T13:45:20
        LocalDateTime dt1 = LocalDateTime.of(2017, Month.SEPTEMBER, 21, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);

        LocalDate date1 = dt1.toLocalDate();
        LocalTime time1 = dt1.toLocalTime();

        Instant instant = Instant.ofEpochSecond(44 * 365 * 86400);
        Instant now = Instant.now();

        Duration d1 = Duration.between(LocalTime.of(13, 45, 10), time2);
        Duration d2 = Duration.between(instant, now);

        System.out.println(d1.getSeconds());
        System.out.println(d2.getSeconds());

        Period tenDays = Period.between(LocalDate.of(2017, 9, 11), LocalDate.of(2017, 9, 21));

        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);

        Period tenDays2 = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);

    }

    private static void useTemporalAdjuster() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        date = date.with(nextOrSame(DayOfWeek.SUNDAY));
        System.out.println(date);
        date = date.with(lastDayOfMonth());
        System.out.println(date);

    }

    private static class NextWorkingDay implements TemporalAdjuster {

        @Override
        public Temporal adjustInto(Temporal temporal) {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
            else if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        }
    }

    private static void useDateFormatter() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(formatter);

        DateTimeFormatter koranFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.KOREA);
        String formattedDate2 = date.format(koranFormatter);
        System.out.println(formattedDate2);

        DateTimeFormatter complexFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);
        System.out.println(date.format(complexFormatter));
    }

}
