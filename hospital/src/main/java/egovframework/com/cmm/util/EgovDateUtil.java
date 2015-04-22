/*
 *
 * DateUtil
 * 
 */
package egovframework.com.cmm.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 날짜 변환 및 비교 유틸 클래스
 * 
 * <pre>
 * yyyyMMdd 형식의 문자열 또는 yyyyMMddHHmmss 형식의 문자열을 java.sql.Date 형으로
 * 변환하거나 두 날짜 간의 년도수, 개월수, 일수를 계산하는 등의 기능을 제공한다.
 * </pre>
 */
public final class EgovDateUtil {

	private static SimpleDateFormat dateFormatter = new SimpleDateFormat(
			"yyyyMMdd");

	private static SimpleDateFormat timeFormatter = new SimpleDateFormat(
			"HHmmss");

	private static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	private static SimpleDateFormat dateMilliTimeFormatter = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");

	/**
	 * 포멧에 따른 현재시간 반환
	 * @param "yyyyMMddHH:mm:ss:SSS" //포멧을 넘김
	 * @return "2005060711:55:11:00"
	 * @throws Exception
	 */
	public static String toDateFormat(String Fmt) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat(Fmt,
				java.util.Locale.US);
		java.util.Date currentTime = getDate();
		String dateString = formatter.format(currentTime);

		return dateString;
	}

	/**
	 * 주어진 날짜를 포멧에 따라 반환
	 * @param "yyyyMMddHH:mm:ss:SSS" //포멧을 넘김
	 * @return "2005060711:55:11:00"
	 * @throws Exception
	 */
	public static String getStringDateFormat(String sDate, String Fmt) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat(Fmt,
				java.util.Locale.US);
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.set(Integer.parseInt(sDate.substring(0, 4)), Integer.parseInt(sDate
				.substring(4, 6)) - 1, Integer.parseInt(sDate.substring(6, 8)));
		java.util.Date currentTime = (java.util.Date) c.getTime();
		String dateString = formatter.format(currentTime);

		return dateString;
	}

	/**
	 * 현재 날짜를 java.sql.Date 형으로 변환한다.
	 * 
	 * @return java.sql.Date 현재 날짜 (Date 형)
	 * @throws Exception
	 *             년월일이 없는 경우 발생
	 */
	public static java.sql.Date getDate() throws Exception {
		return getSystemDate();
	}

	/**
	 * 현재 날짜를 java.sql.Timestamp 형으로 변환한다.
	 * 
	 * @return java.sql.Timestamp 현재 날짜(Timestamp 형)
	 * @throws Exception
	 *             년월일이 없는 경우 발생
	 */
	public static java.sql.Timestamp getTimestamp() throws Exception {
		return new Timestamp(getSystemDate().getTime());
	}

	/**
	 * 현재 날짜를 yyyyMMdd 형식의 문자열로 구한다.
	 * 
	 * <pre>
	 * [사용 예]
	 * DateUtil.toDateString() =&gt; &quot;20070626&quot; (현재 날짜가 출력됨)
	 * </pre>
	 * 
	 * @return String 현재 날짜를 yyyyMMdd 형식으로 변환한 문자열
	 */
	public static String toDateString() {
		return dateFormatter.format(getSystemDate());
	}

	/**
	 * 현재 날짜를 yyyyMMdd 형식의 문자열로 구한다.
	 * 
	 * <pre>
	 * [사용 예]
	 * DateUtil.toDateString() =&gt; &quot;20070626&quot; (현재 날짜가 출력됨)
	 * </pre>
	 * 
	 * @return String 현재 날짜를 yyyyMMdd 형식으로 변환한 문자열
	 */
	public static String toDateStringFmt(String str) {
		SimpleDateFormat fmt = new SimpleDateFormat(str);
		return fmt.format(getSystemDate());
	}

	/**
	 * 현재 날짜를 지정 Formmater 형식의 문자열로 구한다.
	 * 
	 * <pre>
	 * [사용 예]
	 * DateUtil.toDateString(&quot;yyyy-MM-dd&quot;) =&gt; &quot;2007-06-26&quot; (현재 날짜가 출력됨)
	 * </pre>
	 * 
	 * @return String 현재 날짜를 yyyyMMdd 형식으로 변환한 문자열
	 */
	public static String toDateString(String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(getSystemDate());
	}

	/**
	 * java.sql.Date 객체를 yyyyMMdd 형식의 문자열로 변환한다.
	 * 
	 * @param date
	 *            변환대상 java.sql.Date 객체
	 * @return yyyyMMdd 형식으로 변환된 문자열
	 */
	public static String toDateString(java.sql.Date date) {
		if (date == null || date.toString().startsWith("1900"))
			return "";
		return dateFormatter.format(date);
	}

	/**
	 * java.sql.Timestamp 객체를 yyyyMMdd 형식의 문자열로 변환한다.
	 * 
	 * @param timestamp
	 *            변환대상 java.sql.Timestamp 객체
	 * @return String yyyyMMdd 형식으로 변환된 문자열
	 */
	public static String toDateString(java.sql.Timestamp timestamp) {
		if (timestamp == null || timestamp.toString().startsWith("1900"))
			return "";
		return dateFormatter.format(timestamp);
	}

	/**
	 * 현재 시간을 HHmmss 형식의 문자열로 변환한다.
	 * 
	 * @return String 현재 시간을 HHmmss 형식으로 변환한 문자열
	 */
	public static String toTimeString() {
		return timeFormatter.format(getSystemDate());
	}

	/**
	 * java.sql.Date 객체를 HHmmss 형식의 문자열로 변환한다.
	 * 
	 * @param date
	 *            변환대상 java.sql.Date 객체
	 * @return String HHmmss 형식으로 변환된 문자열
	 */
	public static String toTimeString(java.sql.Date date) {
		if (date == null || date.toString().startsWith("1900"))
			return "";
		return timeFormatter.format(date);
	}

	/**
	 * java.sql.Timestamp 객체를 HHmmss 형식의 문자열로 변환한다.
	 * 
	 * @param time
	 *            변환대상 java.sql.Timestamp 객체
	 * @return String HHmmss 형식으로 변환된 문자열
	 */
	public static String toTimeString(java.sql.Timestamp time) {
		if (time == null || time.toString().startsWith("1900"))
			return "";
		return timeFormatter.format(time);
	}

	/**
	 * 현재 날짜를 yyyyMMddHHmmss 형식의 문자열로 변환한다.
	 * 
	 * @return String 현재 날짜+시간을 yyyyMMddHHmmss 형식으로 변환한 문자열
	 */
	public static String toDateTimeString() {
		return dateTimeFormatter.format(getSystemDate());
	}

	/**
	 * 현재 날짜를 yyyyMMddHHmmssSSS 형식의 문자열로 변환한다.
	 * 
	 * @return String 현재 날짜+시간을 yyyyMMddHHmmssSSS 형식으로 변환한 문자열
	 */
	public static String toDateMilliTimeString() {
		return dateMilliTimeFormatter.format(getSystemDate());
	}

	/**
	 * java.sql.Date 객체를 yyyyMMddHHmmss 형식의 문자열로 변환한다.
	 * 
	 * @param date
	 *            변환대상 java.sql.Date 객체
	 * @return String yyyyMMddHHmmss 형식으로 변환된 문자열
	 */
	public static String toDateTimeString(java.sql.Date date) {
		if (date == null || date.toString().startsWith("1900"))
			return "";
		return dateTimeFormatter.format(date);
	}

	/**
	 * java.sql.Timestamp 객체를 yyyyMMddHHmmss 형식의 문자열로 변환한다.
	 * 
	 * @param timestamp
	 *            변환대상 java.sql.Timestamp 객체
	 * @return String yyyyMMddHHmmss 형식으로 변환된 문자열
	 */
	public static String toDateTimeString(java.sql.Timestamp timestamp) {
		if (timestamp == null || timestamp.toString().startsWith("1900"))
			return "";
		return dateTimeFormatter.format(timestamp);
	}

	/**
	 * 날짜(+시간) 문자열을 java.sql.Date 형으로 변환한다.
	 * 
	 * @param strDate
	 *            날짜(+시간) 문자열로 구분자가 있어도 상관없음.
	 * @return java.sql.Date 변환된 Date 객체
	 * @throws Exception
	 *             년월일이 없는 경우 발생
	 */
	public static java.sql.Date toDate(String strDate) throws Exception {
		return new EgovSqlDate(strDate).getDate();
	}

	/**
	 * 날짜(+시간) 문자열을 java.sql.Timestamp 형으로 변환한다.
	 * 
	 * @param strDate
	 *            날짜(+시간) 문자열로 구분자가 있어도 상관없음.
	 * @return java.sql.Timestamp 변환된 Timestamp 객체
	 * @throws Exception
	 *             년월일이 없는 경우 발생
	 */
	public static Timestamp toTimestamp(String strDate) throws Exception {
		return new Timestamp(new EgovSqlDate(strDate).getDate().getTime());
	}

	/**
	 * java.sql.Date 객체를 java.sql.Timestamp 형으로 변환한다.
	 * 
	 * @param date
	 *            변환대상 java.sql.Date 객체
	 * @return java.sql.Timestamp 변환된 Timestamp 객체
	 */
	public static Timestamp toTimestamp(Date date) {
		if (date == null || date.toString().startsWith("1900"))
			return null;
		return new Timestamp(date.getTime());
	}

	/**
	 * 문자열이 유효한 날짜 형식인 지 체크한다. (단, 구분자는 제거하고 체크함)
	 * 
	 * @param strDate
	 *            체크대상 날짜(+시간) 문자열로 구분자가 있어도 상관없음.
	 * @return boolean 유효한 날짜 형식일 경우 true, null일 경우는 false
	 */
	public static boolean isValidDate(String strDate) {
		if (strDate == null)
			return false;

		strDate = strDate.replaceAll("\\D", ""); // 숫자를 제외한 모든 문자를 제거한다.

		try {
			EgovSqlDate sqlDate = new EgovSqlDate(strDate);

			int len = strDate.length();
			if (len >= 4
					&& sqlDate.getYear() != Integer.parseInt(strDate.substring(
							0, 4)))
				return false;

			if (len >= 6
					&& sqlDate.getMonth() != Integer.parseInt(strDate
							.substring(4, 6)))
				return false;

			if (len >= 8
					&& sqlDate.getDay() != Integer.parseInt(strDate.substring(
							6, 8)))
				return false;

			if (len >= 10
					&& sqlDate.getHour() != Integer.parseInt(strDate.substring(
							8, 10)))
				return false;

			if (len >= 12
					&& sqlDate.getMinute() != Integer.parseInt(strDate
							.substring(10, 12)))
				return false;

			if (len >= 14
					&& sqlDate.getSecond() != Integer.parseInt(strDate
							.substring(12, 14)))
				return false;
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * 입력일자에서 지정한 년,월,일을 차감한 날짜를 Date형으로 구한다.
	 * 
	 * @param date
	 *            입력 Date 객체
	 * @param year
	 *            차감 년수
	 * @param month
	 *            차감 개월수
	 * @param day
	 *            차감 일수
	 * @return Date 입력일자에서 년,월,일을 차감한 날짜(Date형)
	 */
	public static Date getBeforeDate(Date date, int year, int month, int day) {
		return new EgovSqlDate(date).beforeDate(year, month, day);
	}

	/**
	 * 입력일자에서 지정 년,월,일을 차감한 날짜를 Date형으로 구한다.
	 * 
	 * <pre>
	 * [사용 예]
	 * DateUtil.getBeforeDate(&quot;20060523&quot;, 1, 1, 1) =&gt; &quot;20050422&quot; 값을 가지는 Date 객체
	 * </pre>
	 * 
	 * @param strDate
	 *            yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
	 * @param year
	 *            차감 년수
	 * @param month
	 *            차감 개월수
	 * @param day
	 *            차감 일수
	 * @return Date 입력일자에서 년,월,일을 차감한 날짜(Date형)
	 * @throws Exception
	 *             입력일자가 날짜 형식이 아닐 경우에 발생
	 */
	public static Date getBeforeDate(String strDate, int year, int month,
			int day) throws Exception {
		return new EgovSqlDate(strDate).beforeDate(year, month, day);
	}

	/**
	 * 입력일자에서 지정 년,월,일을 차감한 날짜를 문자열로 구한다.
	 * 
	 * <pre>
	 * [사용 예]
	 * DateUtil.getBeforeDate(&quot;20060523&quot;, 1, 1, 1) =&gt; &quot;20050422&quot;
	 * </pre>
	 * 
	 * @param strDate
	 *            yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
	 * @param year
	 *            차감 년수
	 * @param month
	 *            차감 개월수
	 * @param day
	 *            차감 일수
	 * @return String 입력일자에서 년,월,일을 차감한 날짜(yyyyMMdd 형식의 문자열)
	 * @throws Exception
	 *             입력일자가 날짜 형식이 아닐 경우에 발생
	 */
	public static String getBeforeDateString(String strDate, int year,
			int month, int day) throws Exception {
		return new EgovSqlDate(new EgovSqlDate(strDate).beforeDate(year, month, day))
				.getDateString();
	}

	/**
	 * 입력일자에서 지정 년,월,일, 시,분,초를 차감한 날짜를 Date형으로 구한다.
	 * 
	 * <pre>
	 * [사용 예]
	 * DateUtil.getBeforeDateString(&quot;20060523123030&quot;, 1, 1, 1, 1, 1, 1) =&gt; &quot;20050422112929&quot; 값을 가지는 Date 객체
	 * </pre>
	 * 
	 * @param strDate
	 *            yyyyMMddHHmmss 형식의 문자열, 구분자가 있어도 상관없음.
	 * @param year
	 *            차감 년수
	 * @param month
	 *            차감 개월수
	 * @param day
	 *            차감 일수
	 * @param hour
	 *            차감 시
	 * @param min
	 *            차감 분
	 * @param sec
	 *            차감 초
	 * @return Date 입력일자에서 년,월,일, 시,분,초를 차감한 날짜(Date형)
	 * @throws Exception
	 *             입력일자가 날짜 형식이 아닐 경우에 발생
	 */
	public static Date getBeforeDate(String strDate, int year, int month,
			int day, int hour, int min, int sec) throws Exception {
		return new EgovSqlDate(strDate)
				.beforeDate(year, month, day, hour, min, sec);
	}

	/**
	 * 입력일자에 지정 년,월,일, 시,분,초을 차감한 날짜를 문자열로 구한다.
	 * 
	 * <pre>
	 * [사용 예]
	 * DateUtil.getBeforeDateString(&quot;20060523123030&quot;, 1, 1, 1, 1, 1, 1) =&gt; &quot;20050422112929&quot;
	 * </pre>
	 * 
	 * @param strDate
	 *            yyyMMddHHmmss 형식의 문자열, 구분자가 있어도 상관없음.
	 * @param year
	 *            차감 년수
	 * @param month
	 *            차감 개월수
	 * @param day
	 *            차감 일수
	 * @param hour
	 *            차감 시
	 * @param min
	 *            차감 분
	 * @param sec
	 *            차감 초
	 * @return String 입력일자에서 년,월,일, 시,분,초를 차감한 날짜(yyyyMMddHHmmss 형식의 문자열)
	 * @throws Exception
	 *             입력일자가 날짜 형식이 아닐 경우에 발생
	 */
	public static String getBeforeDateString(String strDate, int year,
			int month, int day, int hour, int min, int sec) throws Exception {
		return new EgovSqlDate(new EgovSqlDate(strDate).beforeDate(year, month, day,
				hour, min, sec)).getDateTimeString();
	}

	/**
	 * 입력일자에서 지정한 년,월,일을 추가한 날짜를 Date형으로 구한다.
	 * 
	 * @param date
	 *            입력 Date 객체
	 * @param year
	 *            추가 년수
	 * @param month
	 *            추가 개월수
	 * @param day
	 *            추가 일수
	 * @return Date 입력일자에서 년,월,일을 추가한 날짜(Date형)
	 */
	public static Date getAfterDate(Date date, int year, int month, int day) {
		return new EgovSqlDate(date).afterDate(year, month, day);
	}

	/**
	 * 입력일자에서 지정 년,월,일을 추가한 날짜를 Date형으로 구한다.
	 * 
	 * <pre>
	 * [사용 예]
	 * DateUtil.getAfterDate(&quot;20060523&quot;, 1, 1, 1) =&gt; &quot;20070624&quot; 값을 가지는 Date 객체
	 * DateUtil.getAfterDate(&quot;20060523&quot;, -1, -1, -1) =&gt; &quot;20050422&quot; 값을 가지는 Date 객체
	 * </pre>
	 * 
	 * @param strDate
	 *            yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
	 * @param year
	 *            추가 년수
	 * @param month
	 *            추가 개월수
	 * @param day
	 *            추가 일수
	 * @return Date 입력일자에서 년,월,일을 추가한 날짜(Date형)
	 * @throws Exception
	 *             입력일자가 날짜 형식이 아닐 경우에 발생
	 */
	public static Date getAfterDate(String strDate, int year, int month, int day)
			throws Exception {
		return new EgovSqlDate(strDate).afterDate(year, month, day);
	}

	/**
	 * 입력일자에서 지정 년,월,일을 추가한 날짜를 문자열로 구한다.
	 * 
	 * <pre>
	 * [사용 예]
	 * DateUtil.getAfterDateString(&quot;20060523&quot;, 1, 1, 1) =&gt; &quot;20070624&quot;
	 * DateUtil.getAfterDateString(&quot;20060523&quot;, -1, -1, -1) =&gt; &quot;20050422&quot;
	 * </pre>
	 * 
	 * @param strDate
	 *            yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
	 * @param year
	 *            추가 년수
	 * @param month
	 *            추가 개월수
	 * @param day
	 *            추가 일수
	 * @return String 입력일자에서 년,월,일을 추가한 날짜(yyyyMMdd 형식의 문자열)
	 * @throws Exception
	 *             입력일자가 날짜 형식이 아닐 경우에 발생
	 */
	public static String getAfterDateString(String strDate, int year,
			int month, int day) throws Exception {
		return new EgovSqlDate(new EgovSqlDate(strDate).afterDate(year, month, day))
				.getDateString();
	}

	/**
	 * 입력일자에서 지정 년,월,일, 시,분,초를 추가한 날짜를 Date형으로 구한다.
	 * 
	 * <pre>
	 * [사용 예]
	 * DateUtil.getAfterDateString(&quot;20060523123030&quot;, 1, 1, 1, 1, 1, 1) =&gt; &quot;20070624133131&quot; 값을 가지는 Date 객체
	 * DateUtil.getAfterDateString(&quot;20060523123030&quot;, -1, -1, -1, -1, -1, -1) =&gt; &quot;20050422112929&quot; 값을 가지는 Date 객체
	 * </pre>
	 * 
	 * @param strDate
	 *            yyyyMMddHHmmss 형식의 문자열, 구분자가 있어도 상관없음.
	 * @param year
	 *            추가 년수
	 * @param month
	 *            추가 개월수
	 * @param day
	 *            추가 일수
	 * @param hour
	 *            추가 시
	 * @param min
	 *            추가 분
	 * @param sec
	 *            추가 초
	 * @return Date 입력일자에서 년,월,일, 시,분,초를 추가한 날짜(Date형)
	 * @throws Exception
	 *             입력일자가 날짜 형식이 아닐 경우에 발생
	 */
	public static Date getAfterDate(String strDate, int year, int month,
			int day, int hour, int min, int sec) throws Exception {
		return new EgovSqlDate(strDate).afterDate(year, month, day, hour, min, sec);
	}

	/**
	 * 입력일자에 지정 년,월,일, 시,분,초을 추가한 날짜를 문자열로 구한다.
	 * 
	 * <pre>
	 * [사용 예]
	 * DateUtil.getAfterDateString(&quot;20060523123030&quot;, 1, 1, 1, 1, 1, 1) =&gt; &quot;20070624133131&quot;
	 * DateUtil.getAfterDateString(&quot;20060523123030&quot;, -1, -1, -1, -1, -1, -1) =&gt; &quot;20050422112929&quot;
	 * </pre>
	 * 
	 * @param strDate
	 *            yyyMMddHHmmss 형식의 문자열, 구분자가 있어도 상관없음.
	 * @param year
	 *            추가 년수
	 * @param month
	 *            추가 개월수
	 * @param day
	 *            추가 일수
	 * @param hour
	 *            추가 시
	 * @param min
	 *            추가 분
	 * @param sec
	 *            추가 초
	 * @return String 입력일자에서 년,월,일, 시,분,초를 추가한 날짜(yyyyMMddHHmmss 형식의 문자열)
	 * @throws Exception
	 *             입력일자가 날짜 형식이 아닐 경우에 발생
	 */
	public static String getAfterDateString(String strDate, int year,
			int month, int day, int hour, int min, int sec) throws Exception {
		return new EgovSqlDate(new EgovSqlDate(strDate).afterDate(year, month, day,
				hour, min, sec)).getDateTimeString();
	}

	/**
	 * 두 날짜 사이의 날짜수 차이를 구한다.
	 * 
	 * @param beginDate
	 *            yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
	 * @param endDate
	 *            yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
	 * @return long 두 날짜 사이의 날짜수
	 * @throws Exception
	 *             입력값이 날짜 형식이 아닐 경우에 발생
	 */
	public static long getDaysDiff(String beginDate, String endDate)
			throws Exception {
		return getDaysDiff(toDate(beginDate), toDate(endDate));
	}

	/**
	 * 두 날짜 사이의 날짜수 차이를 구한다.
	 * 
	 * @param beginDate
	 *            비교대상 Date1
	 * @param endDate
	 *            비교대상 Date2
	 * @return long 두 날짜 사이의 날짜수
	 */
	public static long getDaysDiff(java.sql.Date beginDate,
			java.sql.Date endDate) {
		if (endDate.after(beginDate))
			return doGetDaysDiff(beginDate, endDate);
		return -doGetDaysDiff(endDate, beginDate);
	}

	/**
	 * 두 날짜 사이의 날짜수 차이를 구한다.
	 * 
	 * @param beginDate
	 *            비교대상 Date1
	 * @param endDate
	 *            비교대상 Date2
	 * @return long 두 날짜 사이의 날짜수
	 */
	private static long doGetDaysDiff(java.sql.Date beginDate,
			java.sql.Date endDate) {
		long diff = endDate.getTime() - beginDate.getTime();

		return diff / (24 * 3600 * 1000);
	}

	/**
	 * 두 날짜 사이의 개월수 차이를 구한다.
	 * 
	 * @param beginDate
	 *            yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
	 * @param endDate
	 *            yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
	 * @return int 두 날짜 사이의 개월수 차이, beginDate가 endDate 보다 더 나중일 경우에는 음수
	 * @throws Exception
	 *             날짜 입력 값이 날짜 형식이 아닐 경우에 발생
	 */
	public static int getMonthsDiff(String beginDate, String endDate)
			throws Exception {
		return getMonthsDiff(toDate(beginDate), toDate(endDate));
	}

	/**
	 * 두 날짜 사이의 개월수 차이를 구한다.
	 * 
	 * @param beginDate
	 *            비교대상 Date1
	 * @param endDate
	 *            비교대상 Date2
	 * @return int 두 날짜 사이의 개월수 차이, beginDate가 endDate 보다 더 나중일 경우에는 음수
	 * @throws Exception
	 *             날짜 입력 값이 날짜 형식이 아닐 경우에 발생
	 */
	public static int getMonthsDiff(java.sql.Date beginDate,
			java.sql.Date endDate) {
		if (endDate.after(beginDate))
			return doGetMonthsDiff(beginDate, endDate);

		return -doGetMonthsDiff(endDate, beginDate);
	}

	/**
	 * 두 날짜 사이의 개월수 차이를 구한다.
	 * 
	 * @param beginDate
	 *            비교대상 Date1
	 * @param endDate
	 *            비교대상 Date2
	 * @return int 두 날짜 사이의 개월수 차이, beginDate가 endDate 보다 더 나중일 경우에는 음수
	 * @throws Exception
	 *             날짜 입력 값이 날짜 형식이 아닐 경우에 발생
	 */
	private static int doGetMonthsDiff(java.sql.Date beginDate,
			java.sql.Date endDate) {
		EgovSqlDate begin = new EgovSqlDate(beginDate);
		EgovSqlDate end = new EgovSqlDate(endDate);

		int years = end.getYear() - begin.getYear();
		int months = end.getMonth() - begin.getMonth();
		int days = end.getDay() - begin.getDay();

		if (years == 0) {
			if (months >= 0)
				return (years * 12 + months + (days < 0 ? -1 : 0));

			return (years * 12 + months + (days > 0 ? 1 : 0));
		} else if (years > 0)
			return (years * 12 + months + (days < 0 ? -1 : 0));
		else
			return (years * 12 + months + (days > 0 ? 1 : 0));
	}

	/**
	 * 두 날짜 사이의 년도수 차이를 구한다.
	 * 
	 * @param beginDate
	 *            yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
	 * @param endDate
	 *            yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
	 * @return int 두 날짜 사이의 년도수 차이, beginDate가 endDate 보다 더 나중일 경우에는 음수
	 * @throws Exception
	 *             날짜 입력 값이 날짜 형식이 아닐 경우에 발생
	 */
	public static int getYearsDiff(String beginDate, String endDate)
			throws Exception {
		return getYearsDiff(toDate(beginDate), toDate(endDate));
	}

	/**
	 * 두 날짜 사이의 년도수 차이를 구한다.
	 * 
	 * @param beginDate
	 *            비교대상 Date1
	 * @param endDate
	 *            비교대상 Date2
	 * @return int 두 날짜 사이의 년도수 차이, beginDate가 endDate 보다 더 나중일 경우에는 음수
	 */
	public static int getYearsDiff(java.sql.Date beginDate,
			java.sql.Date endDate) {
		if (endDate.after(beginDate))
			return doGetYearsDiff(beginDate, endDate);

		return -doGetYearsDiff(endDate, beginDate);
	}

	/**
	 * 두 날짜 사이의 년도수 차이를 구한다.
	 * 
	 * @param beginDate
	 *            비교대상 Date1
	 * @param endDate
	 *            비교대상 Date2
	 * @return int 두 날짜 사이의 년도수 차이, beginDate가 endDate 보다 더 나중일 경우에는 음수
	 */
	private static int doGetYearsDiff(java.sql.Date beginDate,
			java.sql.Date endDate) {
		int months = getMonthsDiff(beginDate, endDate);

		return months / 12;
	}

	/**
	 * 두 날짜 사이의 날짜수를 구한다. 1일을 넘어서면 2일로 계산한다.
	 * 
	 * @param beginDate
	 *            yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
	 * @param endDate
	 *            yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
	 * @return long 두 날짜 사이의 날짜수
	 * @throws Exception
	 *             입력값이 날짜 형식이 아닐 경우에 발생
	 */
	public static long getDaysDiffAbove(String beginDate, String endDate)
			throws Exception {
		return getDaysDiffAbove(toDate(beginDate), toDate(endDate));
	}

	/**
	 * 두 날짜 사이의 날짜수를 구한다. 1일을 넘어서면 2일로 계산한다.
	 * 
	 * @param beginDate
	 *            비교대상 Date1
	 * @param endDate
	 *            비교대상 Date2
	 * @return long 두 날짜 사이의 날짜수
	 */
	public static long getDaysDiffAbove(java.sql.Date beginDate,
			java.sql.Date endDate) {
		if (endDate.after(beginDate))
			return doGetDaysDiffAbove(beginDate, endDate);

		return -doGetDaysDiffAbove(endDate, beginDate);
	}

	/**
	 * 두 날짜 사이의 날짜수를 구한다. 1일을 넘어서면 2일로 계산한다.
	 * 
	 * @param beginDate
	 *            비교대상 Date1
	 * @param endDate
	 *            비교대상 Date2
	 * @return long 두 날짜 사이의 날짜수
	 */
	private static long doGetDaysDiffAbove(java.sql.Date beginDate,
			java.sql.Date endDate) {
		long diff = endDate.getTime() - beginDate.getTime();

		double dayDiff = diff / (24 * 3600 * 1000.0);

		return (long) Math.ceil(dayDiff);
	}

	/**
	 * 두 날짜 사이의 개월수를 구한다. 1개월을 넘어서면 2개월로 계산한다.
	 * 
	 * @param beginDate
	 *            yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
	 * @param endDate
	 *            yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
	 * @return int 두 날짜 사이의 개월수
	 * @throws Exception
	 *             입력값이 날짜 형식이 아닐 경우에 발생
	 */
	public static int getMonthsDiffAbove(String beginDate, String endDate)
			throws Exception {
		return getMonthsDiffAbove(toDate(beginDate), toDate(endDate));
	}

	/**
	 * 두 날짜 사이의 개월수를 구한다. 1개월을 넘어서면 2개월로 계산한다.
	 * 
	 * @param beginDate
	 *            비교대상 Date1
	 * @param endDate
	 *            비교대상 Date2
	 * @return int 두 날짜 사이의 개월수
	 * @throws Exception
	 *             입력값이 날짜 형식이 아닐 경우에 발생
	 */
	public static int getMonthsDiffAbove(java.sql.Date beginDate,
			java.sql.Date endDate) {
		if (endDate.after(beginDate))
			return doGetMonthsDiffAbove(beginDate, endDate);

		return -doGetMonthsDiffAbove(endDate, beginDate);
	}

	/**
	 * 두 날짜 사이의 개월수를 구한다. 1개월을 넘어서면 2개월로 계산한다.
	 * 
	 * @param beginDate
	 *            비교대상 Date1
	 * @param endDate
	 *            비교대상 Date2
	 * @return int 두 날짜 사이의 개월수
	 * @throws Exception
	 *             입력값이 날짜 형식이 아닐 경우에 발생
	 */
	private static int doGetMonthsDiffAbove(java.sql.Date beginDate,
			java.sql.Date endDate) {
		EgovSqlDate begin = new EgovSqlDate(beginDate);
		EgovSqlDate end = new EgovSqlDate(endDate);

		int years = end.getYear() - begin.getYear();
		int months = end.getMonth() - begin.getMonth();
		int days = end.getDay() - begin.getDay();

		if (years == 0) {
			if (days == 0)
				return months;

			return (months + (days > 0 ? 1 : 0));
		} else if (years > 0) {
			if (days == 0)
				return years * 12 + months;

			return (years * 12 + months + (days < 0 ? 0 : 1));
		} else
			return (years * 12 + months + (days > 0 ? 1 : 0));
	}

	/**
	 * 두 날짜 사이의 년도수를 구한다. 1년을 넘어서면 2년으로 계산한다.
	 * 
	 * @param beginDate
	 *            yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
	 * @param endDate
	 *            yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
	 * @return int 두 날짜 사이의 년도수
	 * @throws Exception
	 *             입력값이 날짜 형식이 아닐 경우에 발생
	 */
	public static int getYearsDiffAbove(String beginDate, String endDate)
			throws Exception {
		return getYearsDiffAbove(EgovDateUtil.toDate(beginDate), EgovDateUtil
				.toDate(endDate));
	}

	/**
	 * 두 날짜 사이의 년도수를 구한다. 1년을 넘어서면 2년으로 계산한다.
	 * 
	 * @param beginDate
	 *            비교대상 Date1
	 * @param endDate
	 *            비교대상 Date2
	 * @return int 두 날짜 사이의 년도수
	 */
	public static int getYearsDiffAbove(java.sql.Date beginDate,
			java.sql.Date endDate) {
		if (endDate.after(beginDate))
			return doGetYearsDiffAbove(beginDate, endDate);

		return -doGetYearsDiffAbove(endDate, beginDate);
	}

	/**
	 * 두 날짜 사이의 년도수를 구한다. 1년을 넘어서면 2년으로 계산한다.
	 * 
	 * @param beginDate
	 *            비교대상 Date1
	 * @param endDate
	 *            비교대상 Date2
	 * @return int 두 날짜 사이의 년도수
	 */
	private static int doGetYearsDiffAbove(java.sql.Date beginDate,
			java.sql.Date endDate) {
		int months = getMonthsDiffAbove(beginDate, endDate);

		return (int) Math.ceil(months / 12.);
	}

	/**
	 * java.util.Date 객체를 java.sql.Date 객체로 변환한다.
	 * 
	 * @param utilDate
	 *            변환대상 java.util.Date 객체
	 * @return java.sql.Date 변환된 객체
	 */
	public static java.sql.Date toSqlType(java.util.Date utilDate) {
		if (utilDate == null)
			return null;

		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

		return sqlDate;
	}

	/**
	 * 날짜의 분기 값을 구한다.
	 * 
	 * @param strDate
	 *            yyyyMMdd 형식의 문자열, 구분자는 허용하지 않음
	 * @return int 날짜에 대한 분기 값(1 ~ 4)
	 */
	public static int getQuater(String strDate) {
		String[] pd = parseDate(strDate);
		if (pd == null)
			return -1;

		int month = Integer.parseInt(pd[1]);

		return (month - 1) / 3 + 1;
	}

	/**
	 * 두 날짜 사이의 차이를 년월일을 구분하여 배열로 가져온다.<br>
	 * 입력 날짜의 순서에 상관없이 큰 날짜에서 작은 날짜의 차이를 계산한다.
	 * 
	 * @param aDate
	 *            비교대상 문자열1 - yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
	 * @param bDate
	 *            비교대상 문자열2 - yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
	 * @return int[] 두 날짜 사이의 차이를 int[] {년,월,일} 배열로 반환
	 * @throws Exception
	 *             입력값이 날짜 형식이 아닐 경우에 발생
	 */
	public static int[] getDifference(String aDate, String bDate)
			throws Exception {
		return getDifference(toDate(aDate), toDate(bDate));
	}

	/**
	 * 두 날짜 사이의 차이를 년월일을 구분하여 배열로 가져온다.<br>
	 * 입력 날짜의 순서에 상관없이 큰 날짜에서 작은 날짜의 차이를 계산한다.
	 * 
	 * @param aDate
	 *            비교대상 Date1
	 * @param bDate
	 *            비교대상 Date2
	 * @return int[] 두 날짜 사이의 차이를 int[] {년,월,일} 배열로 반환
	 */
	public static int[] getDifference(Date aDate, Date bDate) {
		EgovSqlDate begin = new EgovSqlDate(aDate);
		EgovSqlDate end = new EgovSqlDate(bDate);

		int mm = Math.abs(getMonthsDiff(aDate, bDate));
		int yy = mm / 12;
		int dd = 0;

		mm = mm % 12;

		if (aDate.before(bDate)) {
			if (begin.getDay() == end.getDay())
				dd = 0;
			else if (begin.getDay() < end.getDay())
				dd = end.getDay() - begin.getDay();
			else
				dd = begin.getLastDayOfMonth() - begin.getDay() + end.getDay();
		} else {
			if (begin.getDay() == end.getDay())
				dd = 0;
			else if (end.getDay() < begin.getDay())
				dd = begin.getDay() - end.getDay();
			else
				dd = end.getLastDayOfMonth() - end.getDay() + begin.getDay();
		}

		return new int[] { yy, mm, dd };
	}

	/**
	 * 시스템의 현재 시간을 구한다.
	 * 
	 * @return Date 시스템의 현재 시간
	 */
	private static java.sql.Date getSystemDate() {
		return new java.sql.Date(System.currentTimeMillis());
	}

	/**
	 * 입력 날짜를 년,월,일로 구분된 문자배열로 변환한다.<br>
	 * 
	 * @param strDate
	 *            날짜 - yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
	 * @return String[] 입력 날짜를 String[] {년,월,일} 배열로 반환
	 */
	private static String[] parseDate(String strDate) {
		if (strDate == null)
			return null;

		strDate = strDate.replaceAll("^\\D+$", "");
		if (strDate.length() >= 10)
			return new String[] { strDate.substring(0, 4),
					strDate.substring(4, 6), strDate.substring(6, 8) };
		else if (strDate.length() >= 8)
			return new String[] { strDate.substring(0, 4),
					strDate.substring(4, 6), strDate.substring(6, 8) };
		else if (strDate.length() >= 6)
			return new String[] { strDate.substring(0, 4),
					strDate.substring(4, 6), "00" };
		else if (strDate.length() >= 4)
			return new String[] { strDate.substring(0, 4), "00", "00" };
		else
			return null;
	}

	/**
	 * 입력 시간을 1970/01/01일 부터 계산된 초로 변환한다.
	 * 
	 * @param strYear
	 *            년도
	 * @param strMonth
	 *            월
	 * @param strDay
	 *            일
	 * @param strHour
	 *            시
	 * @param strMinute
	 *            분
	 * @param strSecond
	 *            초
	 * @return long 입력 시간에 대한 1970/01/01일 이후의 초환산 값
	 */
	public static long convDateTime2Number(String strYear, String strMonth,
			String strDay, String strHour, String strMinute, String strSecond) {
		try {
			String strDate = strYear + strMonth + strDay;
			String strTime = strHour + strMinute + strSecond;

			if (!isValidDate(strDate))
				return 0;
			// if (!CheckUtil.isValidTime(strTime)) return 0;

			int year = Integer.parseInt(strYear);
			int month = Integer.parseInt(strMonth) - 1; // 월은 0부터 시작하므로 -1을 해줘야
														// 한다.
			int day = Integer.parseInt(strDay);
			int hour = Integer.parseInt(strHour);
			int minute = Integer.parseInt(strMinute);
			int second = Integer.parseInt(strSecond);

			Calendar cal = Calendar.getInstance();
			cal.set(year, month, day, hour, minute, second);
			java.util.Date date = cal.getTime();

			return date.getTime();
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 입력 시간을 1970/01/01일 부터 계산된 초로 변환한다.
	 * 
	 * @param strDate
	 *            날짜 (yyyyMMdd 형식)
	 * @param strTime
	 *            시간 (HHmmss 형식)
	 * @return long 입력 시간에 대한 1970/01/01일 이후의 초환산 값
	 */
	public static long convDateTime2Number(String strDate, String strTime) {
		try {
			if (!isValidDate(strDate))
				return 0;
			// if (!CheckUtil.isValidTime(strTime)) return 0;

			String sYear = strDate.substring(0, 4);
			String sMonth = strDate.substring(4, 6);
			String sDay = strDate.substring(6, 8);
			String sHour = strTime.substring(0, 2);
			String sMinute = strTime.substring(2, 4);
			String sSecond = strTime.substring(4, 6);

			return convDateTime2Number(sYear, sMonth, sDay, sHour, sMinute,
					sSecond);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 밀리초 단위의 시간(long)을 yyyyMMddHHmmss 형태의 문자열로 변환한다.
	 * 
	 * @param lDateTime
	 *            밀리초 단위 시간(long)
	 * @return String 밀리초 단위 시간을 yyyyMMddHHmmss 형식의 문자열로 변환
	 */
	public static String convNumber2String(long lDateTime) {
		SimpleDateFormat dateTimeFormatter1 = new SimpleDateFormat(
				"yyyyMMddHHmmssSSS");

		try {
			return dateTimeFormatter1.format(new java.util.Date(lDateTime));
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 밀리초 단위의 시간(문자열)을 yyyyMMddHHmmss 형태의 문자열로 변환한다.
	 * 
	 * @param strDateTime
	 *            밀리초 단위 시간(문자열)
	 * @return String 밀리초 단위 시간을 yyyyMMddHHmmss 형식의 문자열로 변환
	 */
	public static String convNumber2String(String strDateTime) {
		return convNumber2String(Long.parseLong(strDateTime));
	}

} // End of Class