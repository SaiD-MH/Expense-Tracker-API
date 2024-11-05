package backend.expenses.service.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class TimeRange {


    public static List<LocalDate> calculateDayRanges(int numberOfDays) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate endDate = LocalDate.parse(formatter.format(LocalDate.now()));
        LocalDate startDate = endDate.minusDays(numberOfDays);

        return Arrays.asList(startDate, endDate);
    }

    public static List<LocalDate> calculateMonthRanges(int numberOfMonths) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate endDate = LocalDate.parse(formatter.format(LocalDate.now()));
        LocalDate startDate = endDate.minusMonths(numberOfMonths);


        return Arrays.asList(startDate, endDate);

    }


    public static List<LocalDate> validateCustomRange(String startDate, String endDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        if (end.isAfter(start) || end.isEqual(start)) {

            return Arrays.asList(start, end);

        }

        return Arrays.asList();
    }

}
