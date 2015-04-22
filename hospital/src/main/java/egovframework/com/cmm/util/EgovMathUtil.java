/*
 *
 * MathUtil
 * 
 */
package egovframework.com.cmm.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * 수치 연산 유틸 클래스
 *
 * <pre>
 * 반올림 등의 처리를 하며 숫자로 구성된 문자열 간의 사칙연산 기능을 제공하고 있다.
 * </pre>
 */
public final class EgovMathUtil
{

    /**
     * 기본 생성자<br>
     * 외부에서 Instance를 생성할 수 없도록 private로 선언한다.
     */
    private EgovMathUtil()
    {
        ; // Nothing
    }

    /**
     * Number 객체를 지정한 숫자형식을 이용한 문자열로 변환한다.
     * 잘못된 숫자형식이 지정될 경우 COME0202 오류를 발생시킨다.
     * 숫자형식 문자열에는 다음의 문자를 사용할 수 있다.
     *   - 0 : 해당 위치에 값이 없으면 0으로 채운다. 단, 0은 형식문장열의 맨 앞에 나올 수 없으며,
     *         보통 소수점 이하 자리수에 강제로 0 값을 채울 때 사용한다.
     *   - # : 해당 위치에 값이 없으면 0으로 채우지 않는다.
     *   - , : 천단위 위치를 지정한다.
     *   - . : 소수점 위치를 지정한다.
     * <pre>
     * [사용 예]
     * MathUtil.formatNumber(new BigDecimal("12345.67"), "#####0.###") => 12345.67
     * MathUtil.formatNumber(new BigDecimal("12345.67"), "###,##0.###") => 12,345.67
     * MathUtil.formatNumber(new BigDecimal("12345.67"), "###,##0.000") => 12,345.670
     * MathUtil.formatNumber(new BigDecimal("-12345.67"), "#####0.###") => -12345.67
     * MathUtil.formatNumber(new BigDecimal("-12345.67"), "###,##0.###") => -12,345.67
     * MathUtil.formatNumber(new BigDecimal("-12345.67"), "###,##0.000") => -12,345.670
     * </pre>
     * 
     * @param number 변환할 Number 객체(BigDecimal, Long, Integer, ...)
     * @param format 숫자형식 문자열
     * @return String 변환된 문자열
     */
    public static String formatNumber(Number number, String format)
    {
    	DecimalFormat df = new DecimalFormat(format);
    	return df.format(number);
    }

    /**
     * long 형 숫자 값을 지정한 숫자형식을 이용한 문자열로 변환한다.
     * 잘못된 숫자형식이 지정될 경우 COME0202 오류를 발생시킨다.
     * 숫자형식 문자열에는 다음의 문자를 사용할 수 있다.
     *   - 0 : 해당 위치에 값이 없으면 0으로 채운다. 단, 0은 형식문장열의 맨 앞에 나올 수 없으며,
     *         보통 소수점 이하 자리수에 강제로 0 값을 채울 때 사용한다.
     *   - # : 해당 위치에 값이 없으면 0으로 채우지 않는다.
     *   - , : 천단위 위치를 지정한다.
     *   - . : 소수점 위치를 지정한다.
     * <pre>
     * [사용 예]
     * MathUtil.formatNumber(new BigDecimal("12345.67"), "#####0.###") => 12345.67
     * MathUtil.formatNumber(new BigDecimal("12345.67"), "###,##0.###") => 12,345.67
     * MathUtil.formatNumber(new BigDecimal("12345.67"), "###,##0.000") => 12,345.670
     * MathUtil.formatNumber(new BigDecimal("-12345.67"), "#####0.###") => -12345.67
     * MathUtil.formatNumber(new BigDecimal("-12345.67"), "###,##0.###") => -12,345.67
     * MathUtil.formatNumber(new BigDecimal("-12345.67"), "###,##0.000") => -12,345.670
     * </pre>
     * 
     * @param number 변환할 long 형 숫자 값
     * @param format 숫자형식 문자열
     * @return String 변환된 문자열
     */
    public static String formatNumber(long number, String format)
    {
	    	DecimalFormat df = new DecimalFormat(format);
	    	return df.format(number);
    }

    /**
     * double 형 숫자 값을 지정한 숫자형식을 이용한 문자열로 변환한다.
     * 잘못된 숫자형식이 지정될 경우 COME0202 오류를 발생시킨다.
     * 숫자형식 문자열에는 다음의 문자를 사용할 수 있다.
     *   - 0 : 해당 위치에 값이 없으면 0으로 채운다. 단, 0은 형식문장열의 맨 앞에 나올 수 없으며,
     *         보통 소수점 이하 자리수에 강제로 0 값을 채울 때 사용한다.
     *   - # : 해당 위치에 값이 없으면 0으로 채우지 않는다.
     *   - , : 천단위 위치를 지정한다.
     *   - . : 소수점 위치를 지정한다.
     * <pre>
     * [사용 예]
     * MathUtil.formatNumber(new BigDecimal("12345.67"), "#####0.###") => 12345.67
     * MathUtil.formatNumber(new BigDecimal("12345.67"), "###,##0.###") => 12,345.67
     * MathUtil.formatNumber(new BigDecimal("12345.67"), "###,##0.000") => 12,345.670
     * MathUtil.formatNumber(new BigDecimal("-12345.67"), "#####0.###") => -12345.67
     * MathUtil.formatNumber(new BigDecimal("-12345.67"), "###,##0.###") => -12,345.67
     * MathUtil.formatNumber(new BigDecimal("-12345.67"), "###,##0.000") => -12,345.670
     * </pre>
     * 
     * @param number 변환할 double 형 숫자 값
     * @param format 숫자형식 문자열
     * @return String 변환된 문자열
     */
    public static String formatNumber(double number, String format)
    {
	    	DecimalFormat df = new DecimalFormat(format);
	    	return df.format(number);
    }

    /**
     * double 형 숫자 값을 지정한 숫자형식을 이용한 문자열로 변환한다.
     * 잘못된 숫자형식이 지정될 경우 COME0202 오류를 발생시킨다.
     * 숫자형식 문자열에는 다음의 문자를 사용할 수 있다.
     *   - 0 : 해당 위치에 값이 없으면 0으로 채운다. 단, 0은 형식문장열의 맨 앞에 나올 수 없으며,
     *         보통 소수점 이하 자리수에 강제로 0 값을 채울 때 사용한다.
     *   - # : 해당 위치에 값이 없으면 0으로 채우지 않는다.
     *   - , : 천단위 위치를 지정한다.
     *   - . : 소수점 위치를 지정한다.
     * <pre>
     * [사용 예]
     * MathUtil.formatNumber(new BigDecimal("12345.67"), "#####0.###") => 12345.67
     * MathUtil.formatNumber(new BigDecimal("12345.67"), "###,##0.###") => 12,345.67
     * MathUtil.formatNumber(new BigDecimal("12345.67"), "###,##0.000") => 12,345.670
     * MathUtil.formatNumber(new BigDecimal("-12345.67"), "#####0.###") => -12345.67
     * MathUtil.formatNumber(new BigDecimal("-12345.67"), "###,##0.###") => -12,345.67
     * MathUtil.formatNumber(new BigDecimal("-12345.67"), "###,##0.000") => -12,345.670
     * </pre>
     * 
     * @param number 변환할 double 형 숫자 값
     * @param format 숫자형식 문자열
     * @return String 변환된 문자열
     */
    public static String formatNumberFromObj(Object obj, String format)
    {
    		long number = toLongValue(obj);
	    	DecimalFormat df = new DecimalFormat(format);
	    	return df.format(number);
    }    
    
    /**
     * 이 메소드는 객체형 데이터를 정수형으로 변환하는 기능을 제공한다.<br>
     * 주의할 점은 객체가 정수형 범위를 넘어가는 값을 가지고 있을 경우<br>
     * 해당 값을 제대로 처리하지 못하여 엉뚱한 값을 반환할 수도 있다는 점을 고려해야 한다.
     * 
     * @param value 객체형 데이터
     * @return int 정수형 데이터
     * @throws ClassCastException 객체형 데이터가 정수형으로 변환할 수 없는
     *          객체일 경우 ClassCastException을 유발한다. 
     */
    public static int toIntValue(Object value)
    {
    	if (value instanceof Integer)
        	return ((Integer) value).intValue();
        else if (value instanceof String)
        	return Integer.valueOf((String) value).intValue();
        else if (value instanceof BigDecimal)
        	return ((BigDecimal) value).intValue();
        else if (value instanceof BigInteger)
        	return ((BigInteger) value).intValue();
        else
        	return (Integer.parseInt(value.toString()));
    }

    /**
     * 이 메소드는 객체형 데이터를 long 형으로 변환하는 기능을 제공한다.  
     *
     * @param value 객체형 데이터
     * @return long long 형 데이터
     * @throws ClassCastException 객체형 데이터가 long 형으로 변환할 수 없는
     *          객체일 경우 ClassCastException을 유발한다. 
     */
    public static long toLongValue(Object value)
    {
    	if (value instanceof Integer)
        	return ((Integer) value).longValue();
        else if (value instanceof String)
        	return Long.valueOf((String) value).longValue();
        else if (value instanceof BigDecimal)
        	return ((BigDecimal) value).longValue();
        else if (value instanceof BigInteger)
        	return ((BigInteger) value).longValue();
        else
        	return (Long.parseLong(value.toString()));
    }

    /**
     * BigDecimal 객체를 int 형으로 변환한다. 소수점 이하 값은 제거한다.
     *
     * <pre>
     * [사용 예]
     * MathUtil.toInt(new BigDecimal(1234.56)) => 1234   
     * MathUtil.toInt(new BigDecimal(4567.89)) => 4567
     * </pre>
     * @param bd BigDecimal 객체
     * @return int 변환된 int 형 숫자
     */
    public static int toInt(BigDecimal bd)
    {
    	return bd.toBigInteger().intValue();    	
    }

    /**
     * 숫자형 문자열을 BigDecimal 객체로 변환한다.
     *
     * <pre>
     * [사용 예]
     * MathUtil.toBigDecimal("5000.0")  
     * </pre>
     * @param d 숫자형 문자열
     * @return BigDecimal 변환된 객체
     */
    public static BigDecimal toBigDecimal(String d)
    {
        return new BigDecimal(d);
    }

    /**
     * double, float 형의 숫자를 BigDecimal 객체로 변환한다.
     *
     * <pre>
     * [사용 예]
     * MathUtil.toBigDecimal( 5000.0 )  
     * </pre>
     * @param d double, float 형의 숫자
     * @return BigDecimal 변환된 객체
     */
    public static BigDecimal toBigDecimal(double d)
    {
        return new BigDecimal(String.valueOf(d));
    }

    /**
     * long, int 형의 숫자를 BigDecimal 객체로 변환한다.
     *
     * <pre>
     * [사용 예]
     * MathUtil.toBigDecimal( 5 )
     * MathUtil.toBigDecimal( 5000L )   
     * </pre>
     * @param d long, int 형의 숫자
     * @return BigDecimal 변환된 객체
     */
    public static BigDecimal toBigDecimal(long d)
    {
        return new BigDecimal(d);
    }

    /**
     * Number 형 객체를 BigDecimal 객체로 변환한다.
     *
     * <pre>
     * [사용 예]
     * MathUtil.toBigDecimal( null ) => 0
     * MathUtil.toBigDecimal( new Integer(5) )  
     * MathUtil.toBigDecimal( new Long(5000L) )   
     * MathUtil.toBigDecimal( new Double(5000.0) )   
     * </pre>
     * @param d Number 형 숫자
     * @return BigDecimal 변환된 객체 (null일 경우는 0)
     */
    public static BigDecimal toBigDecimal(Number d)
    {
        if (d == null)
            return new BigDecimal(0);
        else if (d instanceof BigDecimal)
            return (BigDecimal)d;
        else if (d instanceof Double)
            return new BigDecimal(d.doubleValue() + "");
        else if (d instanceof Float)
            return new BigDecimal(d.floatValue() + "");
        else
            return new BigDecimal(d.longValue() + "");
    }

    /**
     * double 형의 숫자에 대한 절대값을 구한다.
     *
     * @param d double 형 숫자
     * @return double 절대값
     */
    public static double abs(double d)
    {
        return Math.abs(d);
    }

    /**
     * Primitive 형의 숫자를 지정한 자리수에서 반올림 한다.
     *
     * <pre>
     * [사용 예]
     * MathUtil.round( 5555, 2 ) => 5600.0
     * MathUtil.round( 5555.555, -2 ) => 5555.56
     * </pre>
     * @param d 모든 Primitive 형의 숫자
     * @param position 반올림할 자리수. 음수(-)일 경우에는 반올림할 소숫점 자리수
     * @return double 지정한 자리수에서 반올림된 double 형 숫자
     */
    public static double round(double d, int position)
    {
        if (position == 0)
            return Math.round(d);

        double posNum = Math.pow(10, position);

        return Math.round(d / posNum) * posNum;
    }

    /**
     * long 형 숫자를 지정된 자리수에서 반올림 한다.
     *
     * <pre>
     * [사용 예]
     * MathUtil.round( 5555L, 2 ) => 5600.0
     * MathUtil.round( 5555L, -2 ) => 5555
     * </pre>
     * @param l long 형 숫자
     * @param position 반올림할 자리수. 음수(-)일 경우 0으로 간주
     * @return long 지정된 자리수에서 반올림된 long 형 숫자
     */
    public static long round(long l, int position)
    {
        return (long)round((double)l, position);
    }

    /**
     * BigDecimal 데이터를 지정된 자리수에서 반올림 한다.
     *
     * <pre>
     * [사용 예]
     * MathUtil.round( new BigDecimal(5555), 2 ) => 5600.0 값을 갖고 있는 BigDecimal 객체
     * MathUtil.round( new BigDecimal(5555.555), -2 ) => 5555.56 값을 갖고 있는 BigDecimal 객체
     * </pre>
     * @param bd 모든 형의 숫자
     * @param position 반올림할 자리수. 음수(-)일 경우에는 반올림할 소숫점 자리수
     * @return BigDecimal 지정된 자리수에서 반올림된 BigDecimal 객체
     */
    public static BigDecimal round(BigDecimal bd, int position)
    {
        if (position < 0) {
            bd = bd.setScale(-position, BigDecimal.ROUND_HALF_UP);
        }
        else {
            double roundedNum = round(bd.doubleValue(), position);
            bd = new BigDecimal(roundedNum);
        }
        return bd;
    }

    /**
     * Primitive 형의 숫자를 지정된 자리수에서 내림한다.
     *
     * <pre>
     * [사용 예]
     * MathUtil.floor( 5555, 2 ) => 5500.0
     * MathUtil.floor( 5555.555, -2 ) => 5555.55
     * </pre>
     *
     * @param d 모든 Primitive 형의 숫자
     * @param position 내림할 자리수. 음수(-)일 경우 내림할 소숫점 자리수
     * @return double 지정한 자리수에서 내림된 double 형 숫자
     */
    public static double floor(double d, int position)
    {
        if (position == 0)
            return Math.floor(d);

        double posNum = Math.pow(10, position);

        return Math.floor(d / posNum) * posNum;
    }

    /**
     * long 형 숫자를 지정된 자리수에서 내림한다.
     *
     * <pre>
     * [사용 예]
     * MathUtil.floor( 5555L, 2 ) => 5500.0
     * MathUtil.floor( 5555L, -2 ) => 5555
     * </pre>
     * @param l long 형 숫자
     * @param position 내림할 자리수. 음수(-)일 경우 0으로 간주
     * @return long 지정된 자리수에서 내림된 long 형 숫자
     */
    public static long floor(long l, int position)
    {
        return (long)floor((double)l, position);
    }

    /**
     * BigDecimal 데이터를 지정된 자리수에서 내림한다.
     *
     * <pre>
     * [사용 예]
     * MathUtil.floor( new BigDecimal(5555), 2 ) => 5500.0 값을 갖고 있는 BigDecimal 객체
     * MathUtil.floor( new BigDecimal(5555.555), -2 ) => 5555.55 값을 갖고 있는 BigDecimal 객체
     * </pre>
     * @param bd 모든 형의 숫자
     * @param position 내림할 자리수. 음수(-)일 경우에는 내림할 소숫점 자리수
     * @return BIgDecimal 지정된 자리수에서 내림된 BigDecimal 객체
     */
    public static BigDecimal floor(BigDecimal bd, int position)
    {
        if (position < 0)
            bd = bd.setScale(-position, BigDecimal.ROUND_FLOOR);
        else {
            double flooredNum = floor(bd.doubleValue(), position);
            bd = new BigDecimal(flooredNum);
        }
        return bd;
    }

    /**
     * Primitive 형의 숫자를 지정된 자리수에서 올림한다.
     *
     * <pre>
     * [사용 예]
     * MathUtil.ceil( 5555, 2 ) => 5600.0
     * MathUtil.ceil( 5555.555, -2 ) => 5555.56
     * </pre>
     * @param d 모든 Primitive 형의 숫자
     * @param position 올림할 자리수. 음수(-)일 경우 올림할 소숫점 자리수
     * @return double 지정한 자리수에서 올림된 double 형 숫자
     */
    public static double ceil(double d, int position)
    {
        if (position == 0)
            return Math.ceil(d);

        double posNum = Math.pow(10, position);

        return Math.ceil(d / posNum) * posNum;
    }

    /**
     * long 형 숫자를 지정된 자리수에서 올림한다.
     *
     * <pre>
     * [사용 예]
     * MathUtil.ceil( 5555L, 2 ) => 5600.0
     * MathUtil.ceil( 5555L, -2 ) => 5555
     * </pre>
     * @param l long 형의 숫자.
     * @param position 올림할 자리수. 음수(-)일 경우 0으로 간주
     * @return long 지정된 자리수에서 올림된 long 형 숫자
     */
    public static long ceil(long l, int position)
    {
        return (long)ceil((double)l, position);
    }

    /**
     * BigDecimal 데이터를 지정된 자리수에서 올림한다.
     *
     * <pre>
     * [사용 예]
     * MathUtil.floor( new BigDecimal(5555), 2 ) => 5600.0 값을 갖고 있는 BigDecimal 객체
     * MathUtil.floor( new BigDecimal(5555.555), -2 ) => 5555.56 값을 갖고 있는 BigDecimal 객체
     * </pre>
     * @param bd 모든 형의 숫자
     * @param position 올림할 자리수. 음수(-)일 경우 올림할 소숫점 자리수
     * @return BigDecimal 지정된 자리수에서 올림된 BigDecimal 객체
     */
    public static BigDecimal ceil(BigDecimal bd, int position)
    {
        if (position < 0)
            bd = bd.setScale(-position, BigDecimal.ROUND_CEILING);
        else {
            double ceiledNum = ceil(bd.doubleValue(), position);
            bd = new BigDecimal(ceiledNum);
        }
        return bd;
    }

    /**
     * 숫자형 문자열 더하기 연산
     *
     * <pre>
     * [사용 예]
     * MathUtil.plus("5", "10") => "15"
     * MathUtil.plus("5.5", "10.50") => "16.00" (소숫점이 많은 값을 기준으로 소숫점이 출력)
     * </pre>
     * @param aStr 숫자형 문자열. null 이거나 숫자 변환 불가능시 예외 발생
     * @param bStr 숫자형 문자열. null 이거나 숫자 변환 불가능시 예외 발생
     * @return String 더하기 연산된 숫자형 문자열
     */
    public static String plus(String aStr, String bStr)
    {
        BigDecimal aBig = new BigDecimal(aStr.trim());
        BigDecimal bBig = new BigDecimal(bStr.trim());

        return aBig.add(bBig).toString();
    }

    /**
     * 두개 이상의 숫자형 문자열 더하기 연산
     * 
     * <pre>
     * [사용 예]
     * MathUtil.plus(new String[]{"2", "3", "5.0"}) => "10.0" (소숫점이 많은 값을 기준으로 소숫점이 출력)
     * </pre>
     * @param strNums 숫자형 문자배열. 배열에 숫자 변환 불가능 문자열이 있을 경우 예외 발생
     * @return String 더하기 연산된 숫자형 문자열
     * @throws ProFrameApplicationException
     */
    public static String plus(String[] strNums) throws Exception
    {
    	// COMS6202 = 최소 두 개 이상의 연산 대상이 필요합니다.
        if (strNums == null || strNums.length < 2)
            throw new Exception("최소 두 개 이상의 연산 대상이 필요합니다.");

        BigDecimal big1 = new BigDecimal(strNums[0].trim());
        for (int i = 1; i < strNums.length; i++) {
            BigDecimal aBig = new BigDecimal(strNums[i].trim());
            big1 = big1.add(aBig);
        }

        return big1.toString();
    }

    /**
     * 숫자형 문자열 빼기 연산
     *
     * <pre>
     * [사용 예]
     * MathUtil.minus("5", "10") => "-5"
     * MathUtil.minus("5.5", "10.50") => "-5.00" (소숫점이 많은 값을 기준으로 소숫점이 출력)
     * </pre>
     * @param aStr 숫자형 문자열. null 이거나 숫자 변환 불가능시 예외 발생
     * @param bStr 숫자형 문자열. null 이거나 숫자 변환 불가능시 예외 발생
     * @return String 빼기 연산된 숫자형 문자열
     */
    public static String minus(String aStr, String bStr)
    {
        BigDecimal aBig = new BigDecimal(aStr.trim());
        BigDecimal bBig = new BigDecimal(bStr.trim());

        return aBig.subtract(bBig).toString();
    }

    /**
     * 숫자형 문자열 곱하기 연산
     *
     * <pre>
     * [사용 예]
     * MathUtil.multiply("5", "10") => "50"
     * MathUtil.multiply("5.5", "10.50") => "57.750" (두개 값의 소숫점 자리수가 더해져서 소숫점이 출력)
     * </pre>
     * @param aStr 숫자형 문자열. null 이거나 숫자 변환 불가능시 예외 발생
     * @param bStr 숫자형 문자열. null 이거나 숫자 변환 불가능시 예외 발생
     * @return String 곱하기 연산된 숫자형 문자열
     */
    public static String multiply(String aStr, String bStr)
    {
        BigDecimal aBig = new BigDecimal(aStr.trim());
        BigDecimal bBig = new BigDecimal(bStr.trim());

        return aBig.multiply(bBig).toString();
    }

    /**
     * 두개 이상의 숫자형 문자열 곱하기 연산
     *
     * <pre>
     * [사용 예]
     * MathUtil.multiply(new String[]{"2", "3", "5.0"}) => "30.0" (소숫점이 많은 값을 기준으로 소숫점이 출력)
     * </pre>
     * @param strNums 숫자형 문자배열. 배열에 숫자 변환 불가능 문자열이 있을 경우 예외 발생
     * @return String 곱하기 연산된 숫자형 문자열
     * @throws ProFrameApplicationException
     */
    public static String multiply(String[] strNums) throws Exception
    {
    	// COMS6202 = 최소 두 개 이상의 연산 대상이 필요합니다.
        if (strNums == null || strNums.length < 2)
            throw new Exception("최소 두 개 이상의 연산 대상이 필요합니다.");

        BigDecimal big1 = new BigDecimal(strNums[0].trim());
        for (int i = 1; i < strNums.length; i++) {
            BigDecimal aBig = new BigDecimal(strNums[i].trim());
            big1 = big1.multiply(aBig);
        }

        return big1.toString();
    }

    /**
     * 숫자형 문자열 나누기 연산
     *
     * <pre>
     * [사용 예]
     * MathUtil.divide("5", "10") => "0.5"
     * MathUtil.divide("5.5", "10.50") => "0.52" (소숫점이 많은 값을 기준으로 소숫점이 출력)
     * </pre>
     * @param aStr 숫자형 문자열. null 이거나 숫자 변환 불가능시 예외 발생
     * @param bStr 숫자형 문자열. null 이거나 숫자 변환 불가능시 예외 발생
     * @return String 나누기 연산된 숫자형 문자열
     */
    public static String divide(String aStr, String bStr)
    {
        BigDecimal aBig = new BigDecimal(aStr.trim());
        BigDecimal bBig = new BigDecimal(bStr.trim());

        int scale = Math.max(aBig.scale(), bBig.scale());

        return aBig.divide(bBig, scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 숫자형 문자열 나누기 연산
     *
     * <pre>
     * [사용 예]
     * MathUtil.divide("5", "10", 1) => "0.5"
     * MathUtil.divide("5.5", "10.2", 2) => "0.54" (반올림 처리)
     * </pre>
     * @param aStr 숫자형 문자열. null 이거나 숫자 변환 불가능시 예외 발생
     * @param bStr 숫자형 문자열. null 이거나 숫자 변환 불가능시 예외 발생
     * @param scale 소숫점 자리수로 0보다 큰 정수 값. 음수 값 입력 시 예외 발생
     * @return String 나누기 연산된 숫자 문자열
     */
    public static String divide(String aStr, String bStr, int scale)
    {
        BigDecimal aBig = new BigDecimal(aStr.trim());
        BigDecimal bBig = new BigDecimal(bStr.trim());

        return aBig.divide(bBig, scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 두개의 숫자형 문자열을 비교한 결과를 구한다.<br>
     * 결과는 -1, 0, 1로 반환된다.
     *
     * <pre>
     * [사용 예]
     * MathUtil.compareTo("5", "10")  => -1
     * MathUtil.compareTo("5.0", "5") => 0
     * MathUtil.compareTo("5", "1")   => 1
     * </pre>
     * @param aStr 숫자형 문자열. null 이거나 숫자 변환 불가능시 예외 발생
     * @param bStr 숫자형 문자열. null 이거나 숫자 변환 불가능시 예외 발생
     * @return String 첫번째 숫자가 더 크면 1, 같으면 0, 더 작으면 -1을 반환
     */
    public static int compareTo(String aStr, String bStr)
    {
        BigDecimal aBig = new BigDecimal(aStr.trim());
        BigDecimal bBig = new BigDecimal(bStr.trim());

        return aBig.compareTo(bBig);
    }

} // End of Class