package utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

public class DateTimeUtil {
	
	private static String defaultDateTimeFormat;

	private static String defaultDateFormatInt; 
	
	private static ZoneId defaultZoneId;
	
	public final static String YEAR = "yyyy";
	
	public final static String SIMPLE_YEAR = "yy";
	
	public final static String YEAR_OF_WEEK = "YYYY";
	
	public final static String MONTH = "MM";
	
	public final static String MONTH_SIMPLE_NAME = "MMM";
	
	public final static String MONTH_FULL_NMAE = "MMMM";
	
	public final static String WEEK_IN_YEAR = "ww";
	
	public final static String WEEK_IN_MONTH = "W";  
	
	public final static String DAY_IN_YEAR = "DDD";
	
	public final static String DAY_IN_MONTH = "dd";
	
	public final static String DAY_OF_WEEK_IN_MONTH = "F";
	
	public final static String SIMPLE_DAY_NAME = "E";
	
	public final static String FULL_DAY_NAME = "EEEE";
	
	public final static String DAY_NUMBER_OF_WEEK = "u";   
	
	public final static String AM_PM_MARKER = "a";
	
	public final static String HOUR_24 = "HH";
	
	public final static String HOUR_AM_PM = "hh";
	
	public final static String MINUTE = "mm";
	
	public final static String SECOND = "ss";
	
	public final static String MILLISECOND = "SSS";
	
	public final static String GENERAL_TIME_ZONE_NAME = "z";
	
	public final static String GENERAL_TIME_ZONE_NUMBER = "Z";
	
	public final static int TOTAL_WORKING_DAY_PER_WEEK = 5;

	public final static int TOTAL_DAYS_PER_WEEK = 7;
	
	
	/**
	 * Post construct date time utility class for usage
	 */
	@PostConstruct
	public void init() {
		defaultDateTimeFormat = "yyyy-MM-dd HH:mm:ss.SSS";
		defaultDateFormatInt = YEAR + MONTH + DAY_IN_MONTH; //yyyyMMdd
		defaultZoneId = ZoneId.systemDefault();
	}
	
	public static LocalDateTime getSystemDateTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime;
	}
	
	/**
	 * Retrieve the current year
	 */
	public static int getCurrentYear() {
		return getSystemDateTime().getYear();
	}
	
	/**
	 * Retrieve the current application date and time in milliseconds 
	 */
	public static long getCurrentMillis() {
		return getSystemDateTime().atZone(defaultZoneId).toOffsetDateTime().toInstant().toEpochMilli();
	}
	
	/**
	 * Retrieve the current application date and time in time stamp format
	 */
	public static Timestamp getCurrentTimestamp() {
		return Timestamp.valueOf(getSystemDateTime());
	}
	
	/**
	 * Get the current application date and time in string format
	 */
	public static String getCurrentDateString(String format) {
		return convertDateString(getSystemDateTime(), format);
	}
	
	/**
	 * Get current application date information in integer format
	 */
	public static int getCurrentDateInt() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(defaultDateFormatInt);
		return Integer.parseInt(getSystemDateTime().format(dateTimeFormatter));
	}
	
	/**
	 * Get date information in integer format based on provided local date time
	 */
	public static int getDateInt(LocalDateTime localDateTime) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(defaultDateFormatInt);
		return Integer.parseInt(localDateTime.format(dateTimeFormatter));
	}

	/**
	 * Get current application date and time information in java.sql.Date format
	 */
	public static Date getCurrentSQLDate() {
		return Date.valueOf(getSystemDateTime().toLocalDate());
	}
	
	/**
	 * Get date and time information with zero values
	 */
	public static LocalDateTime getZeroDateTime() {
		return LocalDateTime.of(0001, 01, 01, 0, 0, 0, 0);
	}
	
	/**
	 * Get date and time information with zero values
	 */
	public static Timestamp getZeroTimestamp() {
		return Timestamp.valueOf(getZeroDateTime());
	}
	
	/**
	 * Get date and time information based on the milliseconds provided
	 */
	public static LocalDateTime getDateTimeByMillis(long milliseconds) {
		return Instant.ofEpochMilli(milliseconds).atZone(defaultZoneId).toLocalDateTime();
	}
	
	/**
	 * Convert to corresponding date object based on the date and time in string format and the date format
	 */
	public static Object getObjectByString(Class<?> clazz, String dateTimeString, String dateFormat) {
		//logDebug(DateTimeUtil.class, "getObjectByString(Class, String, String)");
		LocalDateTime dateTime = getDateTimeByString(dateTimeString, dateFormat);
		
		if (LocalDateTime.class.isAssignableFrom(clazz)) {
			return dateTime;
		} else if (LocalDate.class.isAssignableFrom(clazz)) {
			return dateTime.toLocalDate();
		} else if (LocalTime.class.isAssignableFrom(clazz)) {
			return dateTime.toLocalTime();
		} else if (Date.class.isAssignableFrom(clazz)) {
			return convertSQLDate(dateTime);
		} else if (Timestamp.class.isAssignableFrom(clazz)) {
			return convertTimestamp(dateTime);
		}
		
		return null;
	}
	
	/**
	 * Get date and time information based on the date and time string format
	 */
	public static LocalDateTime getDateTimeByString(String dateTimeString, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return LocalDateTime.parse(dateTimeString, formatter);
	}
	
	/**
	 * Get date information based on the date string format
	 */
	public static LocalDate getDateByString(String dateString, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return LocalDate.parse(dateString, formatter);
	}
	
	/**
	 * Get date information based on the date string format
	 */
	public static LocalDate getDateByStringStrict(String dateString, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format).withResolverStyle(ResolverStyle.STRICT);
		return LocalDate.parse(dateString, formatter);
	}
	
	/**
	 * Get sql date information based on the date string format
	 */
	public static Date getSQLDateByString(String dateString, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return Date.valueOf(LocalDate.parse(dateString, formatter));
	}
	
	/**
	 * Get time information based on the time string format
	 */
	public static LocalTime getTimeByString(String timeString, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return LocalTime.parse(timeString, formatter);
	}
	
	/**
	 * Convert date time object into milliseconds as date 
	 */
	public static long convertToMilliSeconds(LocalDateTime dateTime) {
		return dateTime.atZone(defaultZoneId).toOffsetDateTime().toInstant().toEpochMilli();
	}
	
	/**
	 * Convert date time object into the default string format configured in the application properties file
	 */
	public static String convertDateString(LocalDateTime dateTime) {
		return convertDateString(dateTime, null);
	}
	
	/**
	 * Convert date time object into a string format given as a parameter
	 */
	public static String convertDateString(LocalDateTime dateTime, String dateFormat) {
		if (dateFormat == null || dateFormat.isEmpty()) { 	
			dateFormat = defaultDateTimeFormat;
		}
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
		return dateTime.format(dateTimeFormatter);
	}
	
	/**
	 * Convert string into date time object given as a parameter
	 */
	public static Date convertStringDate(String inputString, String dateFormat) {
		Date sqlDate = null;
		try {
			java.util.Date date = new SimpleDateFormat(dateFormat).parse(inputString);
			sqlDate = new Date(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sqlDate;
	}
	
	/**
	 * Convert into java.sql.Date object based on the date in integer format
	 */
	public static Date convertSQLDate(int intDate) {
		LocalDate localDate = DateTimeUtil.getDateByString(String.valueOf(intDate), defaultDateFormatInt);
		return Date.valueOf(localDate);
	}
	
	/**
	 * Convert into java.sql.Date object from LocalDateTime object
	 */
	public static Date convertSQLDate(LocalDateTime dateTime) {
		return Date.valueOf(dateTime.toLocalDate());
	}
	
	/**
	* Convert into Timestamp format from String object, expected string format yyyyMMddHHmmssSSS
	*/
	public static Timestamp convertTimestamp(String dateTimeString) {
		if (dateTimeString == null || dateTimeString.isEmpty()
			|| dateTimeString.length() != 17) {
			throw new IllegalArgumentException("dateTimeString is not the correct length of 17");
		}
		Integer millisecondIndex = 14;
		String timestampDateTimeFormat = "yyyyMMddHHmmss.SSS";
		String formattedDateTimeString = new StringBuilder(dateTimeString)
											.insert(millisecondIndex, ".")
											.toString();
		return Timestamp.valueOf(getDateTimeByString(formattedDateTimeString, timestampDateTimeFormat));
	}

	
	/**
	 * Convert into java.sql.Date object from Timestamp object
	 */
	public static Date convertSQLDate(Timestamp dateTime) {
		return Date.valueOf(dateTime.toLocalDateTime().toLocalDate());
	}

	/**
	 * Convert into Timestamp format from LocalDateTime object
	 */
	public static Timestamp convertTimestamp(LocalDateTime dateTime) {
		return Timestamp.valueOf(dateTime);
	}
	
	/**
	 * Convert into Timestamp format from java.sql.Date
	 */
	public static Timestamp convertTimestamp(Date date) {
		return Timestamp.from(date.toInstant());
	}
	
	/**
	 * Convert into Timestamp format based on the date in integer format
	 */
	public static Timestamp convertTimestamp(int intDate) {
		LocalDateTime localDateTime = DateTimeUtil.getDateTimeByString(String.valueOf(intDate), defaultDateFormatInt);
		return Timestamp.valueOf(localDateTime);
	}
	
	/**
	 * Compute the days differences between two date time
	 */
	public static long computeDaysDiff(Temporal startDateTime, Temporal endDateTime) {
		Duration duration = Duration.between(startDateTime, endDateTime);
		return duration.toDays();
	}
	
	/**
	 * Compute the time differences in milliseconds between two date time
	 */
	public static long computeMillisDiff(Temporal startDateTime, Temporal endDateTime) {
		Duration duration = Duration.between(startDateTime, endDateTime);
		return duration.toMillis();
	}
	
	/**
	 * Compute the time differences in nanoseconds between two date time
	 */
	public static long computeNanosDiff(Temporal startDateTime, Temporal endDateTime) {
		Duration duration = Duration.between(startDateTime, endDateTime);
		return duration.toNanos();
	}

	/**
	 * Compare two dates that is in integer format and determine whether the first date is before or after
	 * the second date.
	 */
	public static int compareDate(int firstDate, int secondDate, String dateFormat) {
		int returnValue = 2;

		LocalDate date1 = getDateByString(String.valueOf(firstDate), dateFormat);
		LocalDate date2 = getDateByString(String.valueOf(secondDate), dateFormat);

		if (date1.compareTo(date2) > 0) {
			returnValue = 1;
		} else if (date1.compareTo(date2) < 0) {
			returnValue = -1;
		} else if (date1.compareTo(date2) == 0) {
			returnValue = 0;
		}
		
		return returnValue;
	}

	/**
	 * Plus X number of days to the date
	 */
	public static Date addDays(Date date, int numOfDays) {
		LocalDate localDate = date.toLocalDate();
		return Date.valueOf(localDate.plusDays(numOfDays));
	}

	/**
	 * Plus X number of weeks to the date
	 */
	public static Date addWeeks(Date date, int numOfWeeks) {
		LocalDate localDate = date.toLocalDate();
		return Date.valueOf(localDate.plusWeeks(numOfWeeks).minusDays(1));
	}
	
	/**
	 * Plus X number of months to the date
	 */
	public static Date addMths(Date date, int numOfMths) {
		LocalDate localDate = date.toLocalDate();
		return Date.valueOf(localDate.plusMonths(numOfMths));
	}
	
	/**
	 * Plus X number of months to the date
	 */
	public static Date addYrs(Date date, int numOfYrs) {
		LocalDate localDate = date.toLocalDate();
		return Date.valueOf(localDate.plusYears(numOfYrs));
	}
	
	/**
	 * Subtract X number of days from date
	 */
	public static Date subtractDays(Date date, int days) {
		LocalDate localDate = date.toLocalDate();
		return Date.valueOf(localDate.minusDays(days));
	}
	
	/**
	 * Method to check whether the data type is a Date Time data type
	 */
	public static boolean isDateClass(Class<?> checkClass) {		
		if (checkClass.isArray()) {
			return false;
		}
		
		boolean isJavaTimePackage = checkClass.getPackage().getName().contains("java.time");
		boolean isSQLDate = Date.class.isAssignableFrom(checkClass);
		boolean isTimeStamp = Timestamp.class.isAssignableFrom(checkClass);
		
		return (isJavaTimePackage || isSQLDate || isTimeStamp);
	}
	
	/**
	 * Get First day of the month for X date
	 */
	public static Date getFirstDayOfMth(Date date){
			LocalDate localDate = date.toLocalDate();
			LocalDate  firstOfMonth = localDate.withDayOfMonth(1);
			return Date.valueOf(firstOfMonth);
	}
	
	/**
	 * Get Last day of the month for X date
	 */
	public static Date getLastDayOfMth(Date date){
			LocalDate localDate = date.toLocalDate();
			LocalDate lastOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
			return Date.valueOf(lastOfMonth);
	}
	
	/**
	 * Get First day of  next month for X date
	 */
	public static Date getFirstDayOfNextMth(Date date){
			LocalDate localDate = date.toLocalDate();
			LocalDate  firstOfNextMonth = localDate.with(TemporalAdjusters.firstDayOfNextMonth());
			return Date.valueOf(firstOfNextMonth);
	}
	
	/**
	 * Get Last day of  previous month for X date
	 */
	public static Date getLastDayOfPrevMth(Date date){
			LocalDate localDate = date.toLocalDate();
			LocalDate lastOfPrevMonth = (localDate.minusMonths(1)).with(TemporalAdjusters.lastDayOfMonth());
			return Date.valueOf(lastOfPrevMonth);
	}	
	
	/**
	 * Get First day of  next quarter for X date
	 */
	public static Date getFirstDayOfNextQuarter(Date date){
			LocalDate localDate = date.toLocalDate();
			Month curMonth = localDate.getMonth();
			Month firstMonthOfQuarter = curMonth.firstMonthOfQuarter();
			//get first month of next quarter
			Month firstMonthOfNextQuarter = firstMonthOfQuarter.plus(3);
			//return first day of next quarter
			return Date.valueOf(localDate.withMonth(firstMonthOfNextQuarter.getValue()).withDayOfMonth(1));
		
	}
	
	/**
	 * Get First day of  current quarter for X date
	 */
	public static Date getFirstDayOfCurQuarter(Date date){
			LocalDate localDate = date.toLocalDate();
			Month curMonth = localDate.getMonth();
			Month firstMonthOfQuarter = curMonth.firstMonthOfQuarter();
			//first day of the first month of the quarter
			return Date.valueOf(localDate.withMonth(firstMonthOfQuarter.getValue()).withDayOfMonth(1));
	}
	
	/**
	 * Get Last day of  previous quarter for X date
	 */
	public static Date getLastDayOfPrevQuarter(Date date){
			LocalDate localDate = date.toLocalDate();
			Month curMonth = localDate.getMonth();
			Month firstMonthOfQuarter = curMonth.firstMonthOfQuarter();
			Month lastMonthOfPrevQuarter = firstMonthOfQuarter.minus(1);
			//last day of last month of previous quarter
			return Date.valueOf(localDate.withMonth(lastMonthOfPrevQuarter.getValue()).with(TemporalAdjusters.lastDayOfMonth()));
	}
	
	/**
	 * Get Last day of the quarter month for X date
	 */
	public static Date getLastDayOfQuarter(Date date){
		LocalDate localDate = date.toLocalDate();
		LocalDate lastOfMonth = localDate.plusMonths(4);
		return Date.valueOf(lastOfMonth);
	}
	
	/**
	 * Get Last day of the year for X date
	 */
	public static Date getLastDayOfYear(Date date){
		LocalDate localDate = date.toLocalDate();
		LocalDate lastOfYear = localDate.with(TemporalAdjusters.lastDayOfYear());
		return Date.valueOf(lastOfYear);
	}
	
	/**
	 * Check if given date is within start date and end date
	 */
	public static boolean isDateWithinRange(Date givenDte, Date sttDte, Date endDte, boolean inclusive){

		boolean isInRange = false;
		
		if(inclusive){
			if((givenDte.equals(sttDte) || givenDte.after(sttDte)) && 
			   (givenDte.equals(endDte) || givenDte.before(endDte))){
				isInRange = true;
			}
		}else{
			if(givenDte.after(sttDte) && givenDte.before(endDte)){
				isInRange = true;
			}
		}
		return isInRange;
	}

	/**
	 * Check if firstDate date is before/equal to second date
	 */
	public static boolean isDateBeforeOrEqual(Date firstDate, Date secondDate){

		boolean isBeforeEqual = false;
		
		if(firstDate.before(secondDate) || firstDate.equals(secondDate)){
			isBeforeEqual = true;
		}

		return isBeforeEqual;
	}
	
	
	/**
	 * Convert date time object into a string format given as a parameter
	 */
	public static String convertDatetoString(java.util.Date dateTime, String dateFormat) {
		SimpleDateFormat sm = new SimpleDateFormat(dateFormat);
	    String strDate = sm.format(dateTime);
	    return strDate;
	}
	
	/**
	 * Calculate the difference between two dates 
	 */
	public static String getTimeDifference(java.util.Date date, java.util.Date date2) {
		long miliSeconds = date2.getTime() - date.getTime();
		int hrs = (int) TimeUnit.MILLISECONDS.toHours(miliSeconds);
		int min = (int) TimeUnit.MILLISECONDS.toMinutes(miliSeconds) % 60;
		int sec = (int) TimeUnit.MILLISECONDS.toSeconds(miliSeconds) % 60;
		return String.format("%02d:%02d:%02d", hrs, min, sec);
	}
	
	/**
	 * Add x days to Current Date and return the date
	 */
	public static Date addDaystoCurr(int days){
    	Date effDte = DateTimeUtil.getCurrentSQLDate();
    	return DateTimeUtil.addDays(effDte,days);
	}
	
	/**
	 * Minus x days from Current Date and return the date
	 */
	public static Date minusDaysfromCurr(int days){
	   Date effDte = DateTimeUtil.getCurrentSQLDate();
	   return DateTimeUtil.subtractDays(effDte,days);
	}

	/**
	 * Retrieve the first day of next year
	 */
	public static Date getFirstDayOfNextYear(Date date) {
		LocalDate localDate = date.toLocalDate();
		LocalDate firstOfNextYear = localDate.with(TemporalAdjusters.firstDayOfNextYear());
		return Date.valueOf(firstOfNextYear);
	}
	
	/** 
	 * Check if input date is not equal to zero.
	*/
	public static boolean isZeroDate(int intdate) {
		return intdate == 0;
	}

	/**
	* Check if input date is greater than other dates.
	*/
	public static boolean isGreaterOrEqualsDate(int intdate1, int intdate2) {
		return intdate1 >= intdate2;
	}

	/**
	* Check if input date is greater than other dates.
	*/
	public static boolean isLessOrEqualsDate(int intdate1, int intdate2) {
		return intdate1 <= intdate2;
	}

	/**
	* Check if date in string is equal to zero.
	*/
	public static boolean isZeroDate(String strdate) {
		return strdate == null || "00000000".equals(strdate);
	}
	
	/**
	 * Set Date part of the date format
	 */
	public static String setDateDate(String fullDate, String dd) {
		String year  = getDateYear(fullDate);
		String month = getDateMonth(fullDate);
		String date  = dd;
		
		return year+month+date;
	}

	/**
	 * Set Month part of the date format
	 */
	public static String setDateMonth(String fullDate, String mm) {
		String year  = getDateYear(fullDate);
		String month = mm;
		String date  = getDateDate(fullDate);
		
		return year+month+date;
	}

	/**
	 * Set Year part of the date format
	 */
	public static String setDateYear(String fullDate, String yyyy) {
		String year  = yyyy;
		String month = getDateMonth(fullDate);
		String date  = getDateDate(fullDate);
		
		return year+month+date;
	}
	
	/**
	 * Get Date part of the date format
	 */
	public static int getDateDate(Integer fullDate) {
		String date = getDateDate(fullDate.toString());
		return Integer.parseInt(date);
	}

	/**
	 * Get Month part of the date format
	 */
	public static int getDateMonth(Integer fullDate) {
		String month = getDateMonth(fullDate.toString());
		return Integer.parseInt(month);
	}

	/**
	 * Get Year part of the date format
	 */
	public static int getDateYear(Integer fullDate) {
		String year = getDateYear(fullDate.toString());
		return Integer.parseInt(year);
	}

	/**
	 * Get Date part of the date format
	 */
	public static String getDateDate(String fullDate) {
		return fullDate.length() >= 8 ? fullDate.substring(6, 8) : "00";
	}

	/**
	 * Get Month part of the date format
	 */
	public static String getDateMonth(String fullDate) {
		return fullDate.length() >= 6 ? fullDate.substring(4, 6) : "00";
	}

	/**
	 * Get Year part of the date format
	 */
	public static String getDateYear(String fullDate) {
		return fullDate.length() >= 4 ? fullDate.substring(0, 4) : "0000";
	}
	
	/**
	 * Get shortname of the month based on the passed in value.
	 */
	public static String getMonthShortName(int month) {
		return new DateFormatSymbols().getShortMonths()[month - 1];
	}
	
	/**
	 * Get SQL Date in the format of yyyy-MM-dd
	 */
	public static Date convertIntToSqlDate(Integer value) {

		if (value == 0) {
			LocalDate localDate = DateTimeUtil.getDateByString(String.valueOf("00010101"), defaultDateFormatInt);
			return Date.valueOf(localDate);
		} else {
			LocalDate localDate = DateTimeUtil.getDateByString(String.valueOf(value), defaultDateFormatInt);
			return Date.valueOf(localDate);
		}
	}
	
	/**
	 * Get date in Integer
	 */
	public static int convertSqlDateToInt(Date date) {
	
		DateFormat df = new SimpleDateFormat(defaultDateFormatInt);
		String dateInString = df.format(date);
		
		if ("01010001".equals(dateInString)) {
			return 0;
		} else {
			return Integer.valueOf(dateInString);
		}
	}
	
	/**
	 * Subtract X number of months from date
	 */
	public static Date subtractMths(Date date, int noOfMonths){
		LocalDate localDate = date.toLocalDate();
		return Date.valueOf(localDate.minusMonths(noOfMonths));
	}
	
	/**
	 * Subtract X number of years from date
	 */
	public static Date subtractYrs(Date date, int noOfYears){
		LocalDate localDate = date.toLocalDate();
		return Date.valueOf(localDate.minusYears(noOfYears));
	}
	
	/**
	 * To get difference between 2 dates based on the format being parse
	 */
	public static Map<String,Integer> getDateDifference(Date dateFrom, Date dateTo, String format){
		
		Period period = Period.between(dateFrom.toLocalDate(), dateTo.toLocalDate());

		Map<String,Integer> diffMap = new HashMap<>();
		switch(format){
			case "DD" : 
				diffMap.put("DD", Integer.valueOf((int)ChronoUnit.DAYS.between(dateFrom.toLocalDate(), dateTo.toLocalDate()))); 
				break;	
			case "MM" : 
				
				//To cater cobol behavior, round up when month is same and day for dateFrom is more than day for dateTo
				if((dateFrom.toLocalDate().getMonth()==dateTo.toLocalDate().getMonth())&& dateFrom.toLocalDate().getDayOfMonth()>dateTo.toLocalDate().getDayOfMonth()){
					diffMap.put("MM", Integer.valueOf((int)ChronoUnit.MONTHS.between(dateFrom.toLocalDate(), dateTo.toLocalDate())+1)); 
				}else{
					diffMap.put("MM", Integer.valueOf((int)ChronoUnit.MONTHS.between(dateFrom.toLocalDate(), dateTo.toLocalDate()))); 
				}
				break;	
			case "YY" : 
				diffMap.put("YY", Integer.valueOf((int)ChronoUnit.YEARS.between(dateFrom.toLocalDate(), dateTo.toLocalDate()))); 
				break;
			case "MD" : 
				diffMap.put("MM", Integer.valueOf((int)ChronoUnit.MONTHS.between(dateFrom.toLocalDate(), dateTo.toLocalDate())));
				
				//To cater cobol behavior, round up when month is same and day for dateFrom is more than day for dateTo
				if((dateFrom.toLocalDate().getMonth()==dateTo.toLocalDate().getMonth())&& dateFrom.toLocalDate().getDayOfMonth()>dateTo.toLocalDate().getDayOfMonth()){
					diffMap.put("DD", Integer.valueOf((int)ChronoUnit.DAYS.between(dateFrom.toLocalDate().plusMonths(ChronoUnit.MONTHS.between(dateFrom.toLocalDate(), dateTo.toLocalDate())), dateTo.toLocalDate())+1));
				}else{
					diffMap.put("DD", Integer.valueOf((int)ChronoUnit.DAYS.between(dateFrom.toLocalDate().plusMonths(ChronoUnit.MONTHS.between(dateFrom.toLocalDate(), dateTo.toLocalDate())), dateTo.toLocalDate())));
				}
				break;
			case "YM" : 
				diffMap.put("YY", Integer.valueOf((int)ChronoUnit.YEARS.between(dateFrom.toLocalDate(), dateTo.toLocalDate())));
				diffMap.put("MM", Integer.valueOf((int)ChronoUnit.MONTHS.between(dateFrom.toLocalDate().plusYears(ChronoUnit.YEARS.between(dateFrom.toLocalDate(), dateTo.toLocalDate())), dateTo.toLocalDate())));
				break;
			case "YD" : 
				diffMap.put("YY", Integer.valueOf(period.getYears()));
				diffMap.put("MM", Integer.valueOf(period.getMonths()));
				
				//To cater cobol behavior, round up when month is same and day for dateFrom is more than day for dateTo
				if((dateFrom.toLocalDate().getMonth()==dateTo.toLocalDate().getMonth())&& dateFrom.toLocalDate().getDayOfMonth()>dateTo.toLocalDate().getDayOfMonth()){
					diffMap.put("DD", Integer.valueOf(period.getDays()+1));
				}else{
					diffMap.put("DD", Integer.valueOf(period.getDays()));
				}
				break;
			default : break;
		}

		return diffMap;
	}
		
	/**
	 * To get the age based on year and month
	 */
	public static Integer getAgeWithYearMonth(int dateEffectiveYrs, int dateDobYrs, 
			int dateEffectiveMth, int dateDobMth, String calTag){
		int diffMth = ((dateEffectiveYrs - dateDobYrs)*12)+(dateEffectiveMth-dateDobMth);
		int ageGen = diffMth/12;
		Integer age = 0;
		String ageStr = String.valueOf(ageGen);
		if(ageStr.contains(".")){
			String[] s = ageStr.split(".");
			if(s.length>0){
				age = Integer.parseInt(s[0]);
			}
		}else{
			age = ageGen;
		}

		if(calTag.equals("L") && (dateEffectiveMth == dateDobMth)){
			age = age.intValue() - 1;
		}

		return age;
	}
	
	/**
	 * To get the age based on the day, month, year
	 */
	public static Integer getAgeWithDayMonthYear(int dateEffectiveYrs, int dateDobYrs,
			int dateEffectiveMth, int dateDobMth, int dateEffectiveDay, int dateDobDay){
		Integer age = 0;
		int diffDay = dateEffectiveDay - dateDobDay;
		int diffMth = dateEffectiveMth - dateDobMth;
		int diffYear = dateEffectiveYrs - dateDobYrs;
		
		if(diffDay<0){
			diffMth = diffMth - 1;
		}
		
		if(diffMth<0){
			diffYear = diffYear - 1;
		}
		
		age = diffYear;

		return age;
	}
	
	/**
	 * To get the map consist of day, month, year, century from the dateInput, 
	 * if day or month passed in is zero, it will be assign to one
	 */
	public static Map<String, Integer> getYearMonthDayFromDateInteger(Integer dateInput){
		
		String dateInputStr = String.valueOf(dateInput);
		Map<String, Integer> outputMap = new HashMap<>();

		if(dateInputStr.length()==8){

			outputMap.put("YY", Integer.parseInt(dateInputStr.substring(0, 4)));
			outputMap.put("YA", Integer.parseInt(dateInputStr.substring(2, 4)));
			
			//If dob month is not set, set to 01
			if(Integer.parseInt(dateInputStr.substring(4, 6))==0){
				outputMap.put("MM",1);
			}else{
				outputMap.put("MM", Integer.parseInt(dateInputStr.substring(4, 6)));
			}
			
			//If dob day is not set, set to 01
			if(Integer.parseInt(dateInputStr.substring(6, 8))==0){
				outputMap.put("DD",1);
			}else{
				outputMap.put("DD", Integer.parseInt(dateInputStr.substring(6, 8)));
			}
			
			outputMap.put("CC", Integer.parseInt(dateInputStr.substring(0, 2)));
			
		}else{
			outputMap.put("DD", 0);
			outputMap.put("MM", 0);
			outputMap.put("YY", 0);
		}
		
		return outputMap;
	}

	public static Date dateFrom(LocalDate date) {
		if (date == null) {
			return null;
		}
		java.util.Date dteUtil = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return new java.sql.Date(dteUtil.getTime());
	}

}
