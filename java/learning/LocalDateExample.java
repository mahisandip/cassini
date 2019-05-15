package my.works.learning;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class LocalDateExample {

	public static void main(String[] args) {
        LocalDate d = LocalDate.of(2016, Month.MARCH, 28);
        LocalDate localDate = d.minusMonths(6);

        long days = ChronoUnit.DAYS.between(localDate, d);

        System.out.println(localDate);
        System.out.println(days);

	}

}
