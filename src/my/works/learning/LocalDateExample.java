package my.works.learning;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class LocalDateExample {

	public static void findDays() {
        LocalDate d = LocalDate.of(2016, Month.MARCH, 28);
        LocalDate localDate = d.minusMonths(6);

        long days = ChronoUnit.DAYS.between(localDate, d);

        System.out.println(localDate);
        System.out.println(days);

	}
	
	public static String convertDateString(LocalDateTime dateTime, String dateFormat) {
		   
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
		return dateTime.format(dateTimeFormatter);
	}
	
	public static void customTest() {
		LocalDate now = LocalDate.now();
		Long retDays = 5l;
		LocalDate then = now.minusDays(retDays);
		Date date = Date.valueOf(then);
		System.out.println(date.toString());
	}

}
