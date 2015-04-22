/*
 *
 * TimeUtil
 * 
 */
 package egovframework.com.cmm.util;
 
import java.sql.Date;
import java.sql.Time;

/**
 * java.sql.Time 처리 유틸 클래스
 *
 * <pre>
 * 년월일을 제외한 시간만을 다루기 위한 클래스로써 시간 변환 및 다양한 형식으로 출력하는 등의 기능을 제공한다.
 * </pre>
 */
public class EgovTimeUtil
{

    /**
     * 시간형 문자열을 Time 객체로 변환한다. (구분자 가능)
     *
     * <pre>
     * [사용 예]
     * TimeUtil.toTime("12/30/00") => 12시 30분 00초가 적용된 Time 객체
     * TimeUtil.toTime("123000")   => 12시 30분 00초가 적용된 Time 객체
     * </pre>
     * @param strTime 시간형 문자열
     * @return Time 변환된 Time 객체
     * @throws ProFrameApplicationException 시분초 형식에 오류가 있는 경우 발생
     */
    public static Time toTime(String strTime) throws Exception
    {
        if (strTime == null)
            return null;

        // 숫자 외의 모든 문자 제거
        strTime = strTime.replaceAll("\\D", "");

        int len = strTime.length();

        // 길이 체크
        if (len < 6)
            // COMS6003 = 시간형식이 올바르지 않습니다. [${}] 
            throw new Exception("시간형식이 올바르지 않습니다.");

        // 시, 분, 초 분리
        int hour = Integer.parseInt(strTime.substring(0, 2));
        int min  = Integer.parseInt(strTime.substring(2, 4));
        int sec  = Integer.parseInt(strTime.substring(4, 6));

        EgovSqlDate sqlDate = new EgovSqlDate(0,0,0, hour, min, sec);

        Time aTime = new Time(sqlDate.getTimeInMillis());

        return aTime;
    }

    /**
     * 시간형 문자열을 0시 0분 0초를 기준으로 하여 밀리초 단위로 환산한 값을 구한다.
     *
     * <pre>
     * [사용 예]
     * TimeUtil.toMilliSeconds("12/30/00") => 45000000 = (12 * 3600 + 30 * 60 + 0) * 1000 
     * </pre>
     * @param strTime 시간형 문자열
     * @return long 밀리초로 환산된 값. null일 경우는 -1
     * @throws ProFrameApplicationException 시분초 형식에 오류가 있는 경우 발생 
     */
    public static long toMilliSeconds(String strTime) throws Exception
    {
        return toMilliSeconds(toTime(strTime));
    }

    /**
     * Time 객체를 0시 0분 0초를 기준으로 하여 밀리초 단위로 환산한 값을 구한다.
     *
     * @param time Time 객체
     * @return long 밀리초로 환산된 값. null일 경우는 -1
     */
    public static long toMilliSeconds(Time time)
    {
        if (time == null) return -1;

        Date date = new Date(time.getTime());
        EgovSqlDate sqlDate = new EgovSqlDate(date);

        return sqlDate.getHour() * 3600 * 1000 + sqlDate.getMinute() * 60 * 1000 + sqlDate.getSecond() * 1000;
    }

} // End of Class