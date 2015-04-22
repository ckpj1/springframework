/*
 *
 * SqlDate
 * 
 */
package egovframework.com.cmm.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;


/**
 * 날짜 연산 및 변환 유틸 클래스
 *
 * <pre>
 * 이 클래스는 일반적인 유틸 클래스와 달리 새로운 인스턴스 객체를 생성한 후에 사용한다.
 *
 * java.sql.Date 객체 또는 날짜 형식의 문자열 년/월/일 등을 매개변수로 객체를 생성할 수 있으며,
 * 객체 생성 후에는 날짜 연산 및 변환, 여러 패턴으로의 출력 등 다양한 기능을 사용할 수 있다.
 *
 * SqlDate 클래스에서 대부분의 날짜 연산 및 변환 기능이 제공되지만 SqlDate 클래스가 구현하기
 * 힘든 기능이나 static 메소드가 더 편한 기능은 DateUtil 클래스에서 추가적으로 제공한다.
 *
 * 예) 두 날짜의 일수 차이 비교시
 * DateUtil.getDaysDiff("20060101", "20060201") => 31
 *
 * 예) 날짜 문자열의 유효성을 체크할 때
 * DateUtil.isValidDate("20061313") => false
 *
 * 예) 날짜에 대한 년, 월, 일을 각각 가져올 경우
 * SqlDate sqlDate = new SqlDate("20061212");
 * sqlDate.getYear()  => 2006 년
 * sqlDate.getMonth()  => 12 월
 * sqlDate.getDay()  => 12 일
 * </pre>
 */

public final class EgovSqlDate
{

    /* 인스턴스 변수 선언 */
    private GregorianCalendar cal;

    private static SimpleDateFormat textFormatter = new SimpleDateFormat("yyyy년 MM월 dd일");

    private static SimpleDateFormat textTimeFormatter = new SimpleDateFormat("HH시 mm분 ss초");

    private static HashMap dateFormatters = new HashMap();

    private static String[] daysOfWeekKorNames = new String[] { "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일" };

    private static String[] daysOfWeekEngNames = new String[] { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };

    /**
     * 기본 생성자<br>
     * 현재 날짜에 대한 SqlDate 객체를 생성한다.
     */
    public EgovSqlDate()
    {
        this(new Date(System.currentTimeMillis()));
    }

    /**
     * 문자열을 통한 생성자<br>
     * 문자열에 대한 SqlDate 객체를 생성한다.
     *
     * @param strDate yyyyMMdd 또는 yyyyMMddHHmmss 형식의 문자열, 구분자가 있어도 상관없음.
     * @throws ProFrameApplicationException 입력값이 날짜 형식이 아닐 경우 발생
     */
    public EgovSqlDate(String strDate) throws Exception
    {
        cal = toCalendar(strDate);
    }

    /**
     * java.sql.Date 객체를 통한 생성자<br>
     * Date 객체에 대한 SqlDate 객체를 생성한다.
     *
     * @param date java.sql.Date 객체
     */
    public EgovSqlDate(Date date)
    {
        cal = new GregorianCalendar();
        cal.setTime(date);
    }

    /**
     * java.util.Date 객체를 통한 생성자<br>
     * Date 객체에 대한 SqlDate 객체를 생성한다.
     *
     * @param date
     */
    public EgovSqlDate(java.util.Date date)
    {
        cal = new GregorianCalendar();
        cal.setTime(date);
    }

    /**
     * long 형 시간 값을 통한 생성자
     *
     * @param time long형 시간 값
     */
    public EgovSqlDate(long time)
    {
        cal = new GregorianCalendar();
        cal.setTimeInMillis(time);
    }

    /**
     * int 형 년, 월, 일을 통한 생성자
     *
     * @param year 년도
     * @param month 월(1 부터 시작)
     * @param day 일자
     */
    public EgovSqlDate(int year, int month, int day)
    {
        cal = new GregorianCalendar(year, month - 1, day);
    }

    /**
     * int 형 년, 월, 일, 시,분,초를 통한 생성자
     *
     * @param year 년도
     * @param month 월(1 부터 시작)
     * @param day 일자
     * @param hour 시
     * @param min 분
     * @param sec 초
     */
    public EgovSqlDate(int year, int month, int day, int hour, int min, int sec)
    {
        cal = new GregorianCalendar(year, month - 1, day, hour, min, sec);
    }

    /**
     * yyyy<구분자>MM<구분자>dd 형식의 날짜형 문자열을 구한다.
     *
     * @param delim 날짜 구분자
     * @return String 구분자를 포함한 날짜형 문자열
     */
    public String getDateString(String delim)
    {
        return getFormatter("yyyy" + delim + "MM" + delim + "dd").format(getDate());
    }

    /**
     * HH(0-23)<구분자>mm<구분자>ss 형식의 시간형 문자열을 구한다.
     *
     * @param delim 시간 구분자
     * @return String 구분자를 포함한 시간형 문자열
     */
    public String getTimeString(String delim)
    {
        return getFormatter("HH" + delim + "mm" + delim + "ss").format(getDate());
    }

    /**
     * yyyy<날짜구분자>MM<날짜구분자>dd HH(0-23)<시간구분자>mm<시간구분자>ss 형식의 날짜시간형 문자열을 구한다.  
     *
     * @param dateDeim 날짜 구분자
     * @param timeDelim 시간 구분자
     * @return String 구분자를 포함한 날짜시간형 문자열
     */
    public String getDateTimeString(String dateDeim, String timeDelim)
    {
        return getFormatter("yyyy" + dateDeim + "MM" + dateDeim + "dd HH" + timeDelim + "mm" + timeDelim + "ss").format(getDate());
    }

    /**
     * yyyyMMdd 형식의 날짜형 문자열을 구한다.
     *
     * @return String 구분자 없는 yyyyMMdd 형식의 날짜형 문자열
     */
    public String getDateString()
    {
        return getBlankDateString();
    }

    /**
     * HHmmss 형식의 시간형 문자열을 구한다.
     *
     * @return String HHmmss 형식의 시간형 문자열
     */
    public String getTimeString()
    {
        return getBlankTimeString();
    }

    /**
     * yyyyMMddHHmmss 형식의 날짜시간형 문자열을 구한다.
     *
     * @return String yyyyMMddHHmmss 형식의 날짜시간형 문자열
     */
    public String getDateTimeString()
    {
        return getBlankDateString() + getBlankTimeString();
    }

    /**
     * yyyyMMdd 형식의 구분자없는 날짜형 문자열을 구한다.
     *
     * @return String yyyyMMdd 형식의 날짜형 문자열
     */
    public String getBlankDateString()
    {
        return getFormatter("yyyyMMdd").format(getDate());
    }

    /**
     * yyyy-MM-dd 형식의 날짜형 문자열을 구한다.
     *
     * @return String yyyy-MM-dd 형식의 날짜형 문자열
     */
    public String getDashDateString()
    {
        return getFormatter("yyyy-MM-dd").format(getDate());
    }

    /**
     * yyyy/MM/dd 형식의 날짜형 문자열을 구한다.
     *
     * @return String yyyy/MM/dd 형식의 날짜형 문자열
     */
    public String getSlashDateString()
    {
        return getFormatter("yyyy/MM/dd").format(getDate());
    }

    /**
     * yyyy.MM.dd 형식의 날짜형 문자열을 구한다.
     *
     * @return String yyyy.MM.dd 형식의 날짜형 문자열
     */
    public String getDotDateString()
    {
        return getFormatter("yyyy.MM.dd").format(getDate());
    }

    /**
     * HHmmss 형식의 시간형 문자열을 구한다.
     *
     * @return String HHmmss 형식의 시간형 문자열
     */
    public String getBlankTimeString()
    {
        return getFormatter("HHmmss").format(getDate());
    }

    /**
     * HH:mm:ss 형식의 시간형 문자열을 구한다.
     *
     * @return String HH:mm:ss 형식의 시간형 문자열
     */
    public String getColonTimeString()
    {
        return getFormatter("HH:mm:ss").format(getDate());
    }

    /**
     * yyyy년 MM월 dd일 형식의 날짜형 문자열을 구한다.
     *
     * @return String yyyy년 MM월 dd일 형식의 날짜형 문자열
     */
    public String getTextDateString()
    {
        return textFormatter.format(getDate());
    }

    /**
     * HH시 mm분 ss초 형식의 문자열을 구한다.
     *
     * @return HH시 mm분 ss초 형식의 시간형 문자열
     */
    public String getTextTimeString()
    {
        return textTimeFormatter.format(getDate());
    }

    /**
     * yyyy년 MM월 dd일 HH시 mm분 ss초 형식의 날짜시간형 문자열을 구한다.
     *
     * @return yyyy년 MM월 dd일 HH시 mm분 ss초 형식의 날짜시간형 문자열
     */
    public String getTextDateTimeString()
    {
        return textFormatter.format(getDate()) + " " + textTimeFormatter.format(getDate());
    }

    /**
     * 날짜형 문자열을 Calendar 객체로 변환한다.
     *
     * @param strDate yyyyMMdd 또는 yyyyMMddHHmmss 형식의 날짜(+시간)형 문자열, 구분자가 있어도 상관없음.
     * @return GregorianCalendar 변환된 Calendar 객체
     * @throws ProFrameApplicationException 년월일이 없는 경우에 예외 발생
     */
    private GregorianCalendar toCalendar(String strDate) throws Exception
    {
        if (strDate == null)
            strDate = "";

        // 숫자 외의 모든 문자를 제거한다. 
        strDate = strDate.replaceAll("\\D", "");

        int len = strDate.length();

        if (len < 8)
            // COMS6001 = 날짜형식이 올바르지 않습니다. [${}]
            throw new Exception("날짜형식이 올바르지 않습니다.");

        // 년, 월, 일, 시, 분, 초 분리
        int year = Integer.parseInt(strDate.substring(0, 4));
        int mon = Integer.parseInt(strDate.substring(4, 6));
        int day = Integer.parseInt(strDate.substring(6, 8));
        int hour = 0;
        int min = 0;
        int sec = 0;

        if (len > 9)
            hour = Integer.parseInt(strDate.substring(8, 10));
        if (len > 11)
            min = Integer.parseInt(strDate.substring(10, 12));
        if (len > 13)
            sec = Integer.parseInt(strDate.substring(12, 14));

        return new GregorianCalendar(year, mon - 1, day, hour, min, sec);
    }

    /**
     * 년도를 구한다.
     *
     * @return int 년도
     */
    public int getYear()
    {
        return cal.get(Calendar.YEAR);
    }

    /**
     * 월을 구한다. (1 ~ 12)
     *
     * @return int 월
     */
    public int getMonth()
    {
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 일자를 구한다.
     *
     * @return int 일자
     */
    public int getDay()
    {
        return cal.get(Calendar.DATE);
    }

    /**
     * 시간을 구한다. (0 ~ 23)
     *
     * @return 시
     */
    public int getHour()
    {
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 분을 구한다. (0 ~ 59)
     *
     * @return 분
     */
    public int getMinute()
    {
        return cal.get(Calendar.MINUTE);
    }

    /**
     *  초를 구한다. (0 ~ 59)
     *
     * @return int 초
     */
    public int getSecond()
    {
        return cal.get(Calendar.SECOND);
    }

    /**
     * 밀리초를 구한다. (0 ~ 999) 
     *
     * @return int 밀리초
     */
    public int getMilliSecond()
    {
        return cal.get(Calendar.MILLISECOND);
    }

    /**
     * 지정 년,월,일 만큼 추가한 날짜를 Date형으로 구한다.
     *
     * @param year 년
     * @param month 월
     * @param day 일
     * @return Date 지정 년,월,일 만큼 추가된 날짜
     */
    public Date afterDate(int year, int month, int day)
    {
        GregorianCalendar aCal = (GregorianCalendar) cal.clone();
        aCal.add(Calendar.YEAR,  year);
        aCal.add(Calendar.MONTH, month);
        aCal.add(Calendar.DATE,  day);

        return new java.sql.Date(aCal.getTimeInMillis());
    }

    /**
     * 지정 년,월,일,시,분,초 만큼 추가한 날짜시간을 Date형으로 구한다.
     *
     * @param year 년
     * @param month 월
     * @param day 일
     * @param hour 시
     * @param min 분
     * @param sec 초
     * @return Date 지정 년,월,일,시,분,초 만큼 추가된 날짜
     */
    public Date afterDate(int year, int month, int day, int hour, int min, int sec)
    {
        GregorianCalendar aCal = (GregorianCalendar) cal.clone();
        aCal.add(Calendar.YEAR          , year);
        aCal.add(Calendar.MONTH         , month);
        aCal.add(Calendar.DATE          , day);
        aCal.add(Calendar.HOUR_OF_DAY   , hour);
        aCal.add(Calendar.MINUTE        , min);
        aCal.add(Calendar.SECOND        , sec);

        return new java.sql.Date(aCal.getTimeInMillis());
    }

    /**
     * 지정 년,월,일 만큼 차감한 날짜를 Date형으로 구한다.
     *
     * @param year 년
     * @param month 월
     * @param day 일
     * @return Date 지정 년,월,일 만큼 차감된 날짜
     */
    public Date beforeDate(int year, int month, int day)
    {
    	return afterDate(-year, -month, -day);
    }

    /**
     * 지정 년,월,일,시,분,초 만큼 차감한 날짜시간을 Date형으로 구한다.
     *
     * @param year 년
     * @param month 월
     * @param day 일
     * @param hour 시
     * @param min 분
     * @param sec 초
     * @return Date 지정 년,월,일,시,분,초 만큼 차감된 날짜
     */
    public Date beforeDate(int year, int month, int day, int hour, int min, int sec)
    {
        return afterDate(-year, -month, -day, -hour, -min, -sec);
    }

    /**
     * 지정 년도 만큼 추가한 날짜를 Date형으로 구한다.
     *
     * @param year 년수
     * @return Date 지정 년도 만큼 추가된 날짜
     */
    public Date afterYear(int year)
    {
        return add(Calendar.YEAR, year);
    }

    /**
     * 지정 개월수 만큼 추가한 날짜를 Date형으로 구한다.
     *
     * @param month 개월수
     * @return Date 지정 개월수 만큼 추가된 날짜
     */
    public Date afterMonth(int month)
    {
        return add(Calendar.MONTH, month);
    }

    /**
     * 지정 일수 만큼 추가한 날짜를 Date형으로 구한다.
     *
     * @param day 일수
     * @return Date 지정 일수 만큼 추가된 날짜
     */
    public Date afterDay(int day)
    {
        return add(Calendar.DATE, day);
    }

    /**
     * 지정 년도 만큼 차감한 날짜를 Date형으로 구한다.
     *
     * @param year 년수
     * @return Date 지정 년도 만큼 차감된 날짜
     */
    public Date beforeYear(int year)
    {
        return add(Calendar.YEAR, -year);
    }

    /**
     * 지정 개월수 만큼 차감한 날짜를 Date형으로 구한다.
     *
     * @param month 개월수
     * @return Date 지정 개월수 만큼 차감된 날짜
     */
    public Date beforeMonth(int month)
    {
        return add(Calendar.MONTH, -month);
    }

    /**
     * 지정 일수 만큼 차감한 날짜를 Date형으로 구한다.
     *
     * @param day 일수
     * @return Date 지정 일수 만큼 차감된 날짜
     */
    public Date beforeDay(int day)
    {
        return add(Calendar.DATE, -day);
    }

    /**
     * 지정 날짜 요소에 해당 값을 추가 또는 차감한 날짜를 Date형으로 구한다.
     *
     * @param field 날짜 요소(년, 월, 일, 시, 분, 초)
     * @param amount 양수(+)일 경우 추가, 음수(-)일 경우 차감이 된다.
     * @return Date 지정 값만큼 지정 날짜 요소에서 추가 또는 차감된 날짜  
     */
    private Date add(int field, int amount)
    {
        GregorianCalendar aCal = (GregorianCalendar) cal.clone();
        aCal.add(field, amount);

        return new java.sql.Date(aCal.getTimeInMillis());
    }

	/**
	 * 지정 날짜와 비교하여 이후인 지 여부를 구한다.
	 *
	 * @param aDate 비교 날짜
	 * @return boolean 이후인 경우에 true
	 */
	public boolean isAfter(Date aDate)
    {
		return cal.after(aDate);
	}

    /**
     * 요일을 int 값으로 구한다. (1: 일요일, 2: 월요일, ... , 7: 토요일)
     *
     * @return int 날짜에 대한 Calendar 요일상수 값
     */
    public int getDayOfWeek()
    {
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 요일을 한글명으로 구한다. (일요일, 월요일, .. 토요일)
     *
     * @return String 한글 요일명
     */
    public String getDayOfWeekInKorean()
    {
        return daysOfWeekKorNames[cal.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 요일을 달력표기 영문 약어명으로 구한다. (SUN, MON, .. SAT)
     *
     * @return String 영문 요일명
     */
    public String getDayOfWeekInEnglish()
    {
        return daysOfWeekEngNames[cal.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 월의 마지막 일자를 구한다.
     *
     * @return int 월의 마지막 일자
     */
    public int getLastDayOfMonth()
    {
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 날짜에 대해 밀리초로 환산된 값을 구한다. (1970년 1월 1일 0시 0분 0초 기준으로 계산)
     *
     * <pre>
     * [사용 예]
     * new SqlDate("19700130").getTimeInMillis() => 2473200000 = 29 * 24 * 3600 * 1000 (29일을 밀리초로 환산한 값.)
     * </pre>
     * @return long 밀리초로 환산된 날짜
     */
    public long getTimeInMillis()
    {
        return getDate().getTime();
    }

    /**
     * Calendar를 Date 객체로 형 변환
     *
     * <pre>
     * [사용 예]
     * new SqlDate().getDate() => new java.sql.Date(System.currentTimeMillis())와 같은 결과
     * </pre>
     * @return java.sql.Date 객체
     */
    public Date getDate()
    {
        return new java.sql.Date(cal.getTimeInMillis());
    }

    /**
     * 지정한 날짜형식을 가지는 DateFormat 객체를 생성한다.
     *
     * @param pattern 날짜형식
     * @return SimpleDateFormat
     */
    private SimpleDateFormat getFormatter(String pattern)
    {
        if (dateFormatters == null) dateFormatters = new HashMap();

        if (dateFormatters.containsKey(pattern))
            return (SimpleDateFormat) dateFormatters.get(pattern);

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        dateFormatters.put(pattern, formatter);
        return formatter;
    }

    /**
     * 객체에 대한 문자열 출력 값을 구한다. 
     *
     * <pre>
     * [사용 예]
     * new SqlDate("19700130").toString() => "Fri Jan 30 00:00:00 KST 1970"
     * @return String
     */
    public String toString()
    {
        return cal.getTime().toString();
    }

} // End of Class