/*
 *
 * StringUtil
 * 
 */
package egovframework.com.cmm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문자열 처리 유틸 클래스
 * 
 * <pre>
 * 문자열 처리에 대한 각종 기능을 제공한다.
 * </pre>
 */
public class EgovStringUtil
{

    /**
     * 문자열 배열에 문자열이 포함되어 있는 지 여부를 구한다.
     *
     * @param str 문자열, null 가능
     * @param strs 문자열 배열
     * @return boolean 문자열 배열에 문자열이 포함되어 있으면 true
     */
    public static boolean isPartOfArray(String str, String[] strs)
    {
        if (null == str || strs == null || strs.length == 0)
            return false;

        for (int i=0; i < strs.length; i++) {
            if (str.equals(strs[i]))
                return true;
        }

        return false;
    }

    /**
     * 문자열 배열에 문자열이 포함되어 있는 지 여부를 구한다.
     * 단, 문자열의 대소문자를 구분하지 않는다.
     *
     * @param str 문자열, null 가능
     * @param strs 문자열 배열
     * @return boolean 문자열 배열에 문자열이 포함되어 있으면 true
     */
    public static boolean isPartOfArrayIgnoreCase(String str, String[] strs)
    {
        if (null == str || null == strs || strs.length == 0)
            return false;

        for (int i=0; i < strs.length; i++) {
            if (str.equalsIgnoreCase(strs[i]))
                return true;
        }

        return false;
    }

    /**
     * 입력 문자열에서 지정된 위치의 지정된 갯수만큼의 문자열을 지정한 문자열로 치환한다.<br>
     * 입력 문자열의 길이와 변경될 문자열의 길이가 동일해야 한다.<br>
     * 그렇지 않으면 입력 문자열을 그래도 반환한다.
     *
     * @param inString 입력 문자열, null 이면 null을 반환
     * @param pos 변경할 문자열의 위치
     * @param len 변경할 문자열의 길이(한글은 2로 센다)
     * @param repString 변경될 문자열, null 이거나 입력문자열의 길이와 동일하지 않을 경우 입력문자열을 반환 
     * @return String 치환이 끝난 새로운 문자열
     */
    public static String replace2(String inString, int pos, int len, String repString)
    {
        // 입력 문자열이 null일 경우 null을 반환
        if (null == inString)
            return null;

        // 치환 문자열이 null 이거나 치환문자열의 길이와 입력한 길이 값이 일치하지 않을 경우 입력문자열을 반환
        if (null == repString || len != EgovStringUtil.byteLength(repString))
            return inString;

        int orgLen = EgovStringUtil.byteLength(inString);

        // pos와 len의 정합성 Check
        if (pos < 0 || len < 0 || (pos + len + 1 > orgLen))
            return inString;

        StringBuffer sb = new StringBuffer();

        sb.append(EgovStringUtil.byteSubStr(inString, 0, pos));
        sb.append(repString);
        sb.append(EgovStringUtil.byteSubStr(inString, pos + len, orgLen));

        return sb.toString();
    }


    /**
     * 문자열을 지정한 구분자로 구분된 목록으로 만들어 준다.
     *
     * @param str 문자열
     * @param delim 구분자
     * @return List 구분자에 의해 분리된 문자열 목록
     */
    public static List toListByDelimter(String str, String delim)
    {
    	StringTokenizer tokenizer = new StringTokenizer(str, delim);
		List list = new ArrayList();
		while (tokenizer.hasMoreTokens())
			list.add(tokenizer.nextToken());

		return list;
    }


    /**
     * 문자열을 WhiteSpace 문자를 구분자로 분리한 문자배열을 구한다.<br>
     * WhiteSpace 문자 => space(" "), tab("\t"), new line("\n") 등의 눈에 보이지 않는 문자<br>
     * null은 WhiteSpace가 아닌 것으로 처리한다.<br>
     * 문자열 앞뒤의 WhiteSpace는 무시한다.
     *
     * <pre>
     * [사용 예]
     * StringUtil.split(null)         = {}
     * StringUtil.split("  a\tb\nc") = {"a", "b", "c"}
     * </pre>
     * @param str 입력 문자열
     * @return String[] WhiteSpace 문자로 분리된 문자배열, 문자열이 null일 경우 빈 배열을 반환
     */
    public static String[] split(String str) {
        if (null == str) return new String[] { };
        
        return str.trim().split("\\s");
    }


    /**
     * 문자열을 지정한 분리자에 의해 배열로 리턴하는 메서드.
     * @param source 원본 문자열
     * @param separator 분리자
     * @return result 분리자로 나뉘어진 문자열 배열
     */
    public static String[] split(String source, String separator) throws NullPointerException {
        String[] returnVal = null;
        int cnt = 1;

        int index = source.indexOf(separator);
        int index0 = 0;
        while (index >= 0) {
            cnt++;
            index = source.indexOf(separator, index + 1);
        }
        returnVal = new String[cnt];
        cnt = 0;
        index = source.indexOf(separator);
        while (index >= 0) {
            returnVal[cnt] = source.substring(index0, index);
            index0 = index + 1;
            index = source.indexOf(separator, index + 1);
            cnt++;
        }
        returnVal[cnt] = source.substring(index0);

        return returnVal;
    }
    
 	/**
	 * 문자열이 null 또는 빈 문자열("")인지 여부를 확인한다.
     *
	 * @param str 입력 문자열
	 * @return boolean 문자열이 null 또는 빈 문자열("")이면 true 
	 */
	public static boolean isEmpty(String str)
    {
		return null == str || "".equals(str);
	}

     /**
     * 문자열이 영문 + 숫자로 구성되어 있는 지 여부를 확인한다.<br>
     * 여기서 영문은 알파벳 문자('a'~'z' or 'A'~'Z')만을 말하며,<br>
     * 숫자는 '0' ~ '0' 까지의 문자만을 말한다.<br>
     * trim 처리하지 않으며 null이나 WhiteSpace 문자는 영문 또는 숫자가 아닌 것으로 처리한다.
     *
     * <pre>
     * [사용 예]
     * StringUtil.isAlphabetNumberOnly("123")    => true
     * StringUtil.isAlphabetNumberOnly("aBc123") => true
     * StringUtil.isAlphabetNumberOnly("가123")  => false
     * StringUtil.isAlphabetNumberOnly(null)     => false
     * </pre>
     * @param str 입력 문자열
     * @return boolean 문자열이 영문과 숫자로만 구성된 경우 true, null일 경우 false
     */
    public static boolean isAlphabetNumberOnly(String str)
    {
        if (null == str)
            return false;

        return str.matches("^[0-9a-zA-Z]+$");
    }

    /**
     * 문자열을 LPAD 처리한다.
     *
     * <pre>
     * [사용 예]
     * StringUtil.lpad("123", '0', 5) => "00123"
     * StringUtil.lpad("123", '0', 1) => "123"
     * StringUtil.lpad(null, '0', 1)  => null
     * </pre>
     * @param orgStr 대상 문자열
     * @param appender 채울 문자
     * @param length 새로운 문자열의 길이
     * @return String LPAD 처리된 문자열, 입력 문자열이 주어진 길이보다 큰 경우는 입력 문자열을, null일 경우는 null을 반환
     */
    public static String lpad(String orgStr, char appender, int length)
    {
        if (null == orgStr)
            return null;

        int orgLen = orgStr.length();

        if (orgLen >= length)
            return orgStr;

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length - orgLen; i++)
            sb.append(appender);

        sb.append(orgStr);
        return sb.toString();
    }

    /**
     * 문자열을 RPAD 처리한다.
     *
     * <pre>
     * [사용 예]
     * StringUtil.rpad("abc", ' ', 5) => "abc  "
     * StringUtil.rpad("abc", '0', 1) => "abc"
     * StringUtil.rpad(null, '0', 1) => null
     * </pre>
     * @param orgStr 대상 문자열
     * @param appender 채울 문자
     * @param length 새로운 문자열의 길이
     * @return String RPAD 처리된 문자열, 입력 문자열이 주어진 길이보다 큰 경우는 입력 문자열을, null일 경우는 null을 반환
     */
    public static String rpad(String orgStr, char appender, int length)
    {
        if (null == orgStr)
            return null;

        int orgLen = orgStr.length();

        if (orgLen >= length)
            return orgStr;

        StringBuffer sb = new StringBuffer();
        sb.append(orgStr);
        for (int i = 0; i < length - orgLen; i++)
            sb.append(appender);

        return sb.toString();
    }

    /**
     * 문자열 왼쪽의 WhiteSpace 문자들을 제거한다.
     *
     * @param str 대상 문자열
     * @return 왼쪽의 WhiteSpace가 제거된 문자열, 입력 문자열이 null일 경우 null을 반환
     */
    public static String ltrim(String str)
    {
        if (null == str)
            return null;

        return str.replaceFirst("^\\s+", "");
    }

    /**
     * 문자열 오른쪽의 WhiteSpace 문자들을 제거한다.
     *
     * @param str 대상 문자열
     * @return 오른쪽의 WhiteSpace가 제거된 문자열, 입력 문자열이 null일 경우 null을 반환
     */
    public static String rtrim(String str)
    {
        if (null == str)
            return null;

        return str.replaceFirst("\\s+$", "");
    }

    /**
     * 문자열의 왼쪽에서 입력 길이만큼의 문자열을 추출한다.<br>
     * 문자열의 길이가 지정 길이보다 작으면 입력 문자열을 반환한다.<br>
     * 지정 길이값은 한글과 영문을 모두 1로 count 한다.
     *
     * <pre>
     * [사용 예]
     * StringUtil.left("abcd", 3)  => "abc"
     * StringUtil.left("a가bc", 3) => "a가b"
     * StringUtil.left("abcd", 5)  => "abcd"
     * StringUtil.left(null, 5)    => null
     * </pre>
     * @param str 대상 문자열
     * @param len 잘라낼 길이
     * @return String 왼쪽부터 주어진 길이만큼의 문자열, 주어진 길이가 문자열의 길이보다 작으면 입력문자열, 입력 문자열이 null이면 null을 반환
     */
    public static String left(String str, int len)
    {
        if (null == str)
            return null;

        if (str.length() <= len)
            return str;

        return str.substring(0, len);
    }

    /**
     * 문자열의 오른쪽에서 입력 길이만큼의 문자열을 추출한다.<br>
     * 문자열의 길이가 지정 길이보다 작으면 입력 문자열을 반환한다.<br>
     * 지정 길이값은 한글과 영문을 모두 1로 count 한다.
     *
     * <pre>
     * [사용 예]
     * StringUtil.right("abcd", 3)  => "bcd"
     * StringUtil.right("a가bc", 3) => "가bc"
     * StringUtil.right("abcd", 5)  => "abcd"
     * StringUtil.right(null, 5)    => null
     * </pre>
     * @param str 대상 문자열
     * @param len 잘라낼 길이
     * @return String 오른쪽부터 주어진 길이만큼의 문자열, 주어진 길이가 문자열의 길이보다 작으면 입력문자열, 입력 문자열이 null이면 null을 반환
     */
    public static String right(String str, int len)
    {
        if (null == str)
            return null;

        if (str.length() <= len)
            return str;

        return str.substring(str.length() - len);
    }

    /**
     * 문자열을 int형으로 변환한다.<br>
     * 변환하기 전 trim 처리한다.
     *
     * <pre>
     * [사용 예]
     * StringUtil.parseInt("123")    => 123
     * StringUtil.parseInt("  -123") => -123
     * StringUtil.parseInt("abc")    => NumberFormatException 예외 발생
     * StringUtil.parseInt(null)     => NullPointerException 예외 발생
     * </pre>
     * @param str 입력 문자열
     * @return int 변환된 int 값 
     */
    public static int parseInt(String str)
    {
        return Integer.parseInt(str.trim());
    }

    /**
     * 문자열을 long형으로 변환한다.<br>
     * 변환하기 전 trim 처리한다.
     *
     * <pre>
     * [사용 예]
     * StringUtil.parseLong("123")    => 123L
     * StringUtil.parseLong("  -123") => -123L
     * StringUtil.parseLong("abc")    => NumberFormatException 예외 발생
     * StringUtil.parseLong(null)     => NullPointerException 예외 발생
     * </pre>
     * @param str 입력 문자열
     * @return long 변환된 long 값
     */
    public static long parseLong(String str)
    {
        return Long.parseLong(str.trim());
    }

    /**
     * 문자열을 float형으로 변환한다.<br>
     * 변환하기 전 trim 처리한다.
     *
     * <pre>
     * [사용 예]
     * StringUtil.parseFloat("123.0")    => 123.0
     * StringUtil.parseFloat("  -123.0") => -123.0
     * StringUtil.parseFloat("abc")      => NumberFormatException 예외 발생
     * StringUtil.parseFloat(null)       => NullPointerException 예외 발생
     * </pre>
     * @param str 입력 문자열
     * @return float 변환된 float 값
     */
    public static float parseFloat(String str)
    {
        return Float.parseFloat(str.trim());
    }

    /**
     * 문자열을 double형으로 변환한다.<br>
     * 변환하기 전 trim 처리한다.
     *
     * <pre>
     * [사용 예]
     * StringUtil.parseDouble("123.0")    => 123.0
     * StringUtil.parseDouble("  -123.0") => -123.0
     * StringUtil.parseDouble("abc")      => NumberFormatException 예외 발생
     * StringUtil.parseDouble(null)       => NullPointerException 예외 발생
     * </pre>
     * @param str 입력 문자열
     * @return double 변환된 double 값
     */
    public static double parseDouble(String str)
    {
        return Double.parseDouble(str.trim());
    }

    /**
     * 문자열을 byte형으로 변환한다.<br>
     * 변환하기 전 trim 처리한다.<br>
     * 문자열은 "-128" ~ "127" 까지의 값을 가질 수 있다. 
     *
     * <pre>
     * [사용 예]
     * StringUtil.parseByte("127")  => 127
     * StringUtil.parseByte("-128") => -128
     * StringUtil.parseByte("128")  => NumberFormatException 예외 발생. -128 ~ 127 범위를 벗어남.
     * StringUtil.parseByte(null)   => NullPointerException 예외 발생
     * </pre>
     * @param str 입력 문자열
     * @return byte 변환된 byte 값
     */
    public static byte parseByte(String str)
    {
        return Byte.parseByte(str.trim());
    }

    /**
     * 문자열을 BigDecimal형으로 변환한다.<br>
     * 변환하기 전 trim 처리한다.
     *
     * <pre>
     * [사용 예]
     * StringUtil.parseBigDecimal("123.45") => 123.45
     * StringUtil.parseBigDecimal(" -123.4") => -123.4
     * StringUtil.parseBigDecimal("12ac") => NumberFormatException 예외 발생
     * StringUtil.parseBigDecimal(null)   => NullPointerException 예외 발생
     * </pre>
     * @param str 입력 문자열
     * @return BigDecimal 변환된 BigDecimal 값
     */
    public static BigDecimal parseBigDecimal(String str)
    {
        return new BigDecimal(str.trim());
    }

    /**
     * 문자열의 형식을 "yyyyMMdd" 에서 "yyyy-MM-dd" 형식으로 변환한다.
     *
     * <pre>
     * [사용 예]
     * StringUtil.makeDateFormat("20070102") => "2007-01-02"
     * </pre>
     * @param str 입력 문자열
     * @return String 변환된 문자열
     */
    public static String makeDateFormat(String str)
    {
		return str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6);
    }

    /**
     * 문자열의 형식을 "yyyyMMddHHmmss" 에서 "yyyy-MM-dd HH:mm:ss" 형식으로 또는<br>
     * "yyyyMMddHHmmss.fffffffff" 에서 "yyyy-MM-dd HH:mm:ss.fffffffff" 형식으로 변환한다.
     * <pre>
     * [사용 예]
     * StringUtil.makeDateFormat("20070102133456") => "2007-01-02 13:34:56"
     * StringUtil.makeDateFormat("20070102133456.123456789") => "2007-01-02 13:34:56.123456789"
     * </pre>
     * @param str 입력 문자열
     * @return String 변환된 문자열
     */
    public static String makeDateTimeFormat(String str)
    {
    	return str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8) +
    		" " + str.substring(8, 10) + ":" + str.substring(10, 12) + ":" + str.substring(12);
    }

    /**
     * 숫자로 구성된 문자열을 int형 배열로 변환한다.<br>
     * 숫자가 아닌 문자는 배열에 -1로 저장된다.
     *
     * [사용 예]
     * StringUtil.toIntArray("123")    => new int[] {1, 2, 3}
     * StringUtil.toIntArray("1 2\r3") => new int[] {1, -1, 2, -1, 3}
     * StringUtil.toIntArray(null)     => null
     * @param strNum 숫자로 구성된 문자열 
     * @return int[] 변환된 int 배열
     */
    public static int[] toIntArray(String strNum)
    {
        if (null == strNum)
            return null;
        
        int[] arr = new int[strNum.length()];
        for (int i=0; i < strNum.length(); i++) {
            arr[i] = Character.digit(strNum.charAt(i), 10);
        }

        return arr;
    }

    /**
     * 문자열이 null인 경우 빈 문자열("")을 반환한다.
     *
     * @param str 입력 문자열
     * @return String null인 경우 ""을 반환
     */
    public static String nullToBlank(String str)
    {
        if (null == str)
            return "";

        return str;
    }

    /**
     * 입력 Date 객체가 null인 경우 빈문자열("")을 반환하고,<br>
     * 아니면 입력 Date 객체에 대한 toString() 값을 반환한다.
     *
     * @param objDate 입력 java.sql.Date 객체
     * @return String 값이 null인 경우 "", 아니면 inputValue.toString()
     */
    public static String nullToBlank(Date objDate)
    {
        if (null == objDate)
            return "";

        return objDate.toString();
    }

    /**
     * 입력받는 문자가 2 Byte 문자인지 확인한다.<br>
     * CharacterSet은 "EUC-KR"을 따른다.
     *
     * <pre>
     * [사용 예]
     * StringUtil.isTwoByte('a')  => false
     * StringUtil.isTwoByte('$')  => false
     * StringUtil.isTwoByte('\r') => false
     * StringUtil.isTwoByte('가') => true
     * StringUtil.isTwoByte('1')  => true (반각문자임)
     * StringUtil.isTwoByte('１') => true (전각문자임)
     * </pre>
     * @param chr 검사할 문자
     * @return boolean 문자가 2 Byte일 경우 true
     */
    public static boolean isTwoByte(char chr)
    {
        int nValue = 0;
        byte btValue[] = null;

        // 입력된 Character의 Value를 얻는다.
        try {
            btValue = (String.valueOf(chr)).getBytes("EUC-KR");

            if (btValue[0] < 0)
                nValue = btValue[0] + 256;
            else
                nValue = btValue[0];

            // nValue가 0x80 이상인 경우에는 2 Byte 문자이다.
            if (nValue >= 0x80 && btValue.length > 1) {
                return true;
                //실제로 2-byte 문자셋이기 때문에 2^8=256을 곱해준다. 그리고 뒤의 숫자를 더해준다.
                //nValue = nValue*256 + getUnsignedIntFromByte(btValue[1]);
            }

            return false;
        }
        catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    /**
     * 문자열의 길이를 바이트 단위로 센다. 한글은 2로 영문은 1로 센다.
     *
     * <pre>
     * [사용 예]
     * StringUtil.byteLength("abc \r")     => 5
     * StringUtil.byteLength("abc가나")    => 7  (반각문자)
     * StringUtil.byteLength("ａｂｃ가나") => 10 (전각문자)
     * StringUtil.byteLength(null) => NullPointerException 예외 발생
     * </pre>
     * @param str 입력 문자열
     * @return int 문자열의 바이트 단위 길이
     */
    public static int byteLength(String str)
    {
        int len = str.length();
        char a[] = new char[len];

        str.getChars(0, len, a, 0);
        int k = 0;
        for (int i = 0; i < len; i++) {
            if (isTwoByte(a[i]))
                k =  k + 2;
            else
                k++;
        }

        return k;
    }

	/**
     * 문자배열의 길이를 바이트 단위로 센다. 한글은 2로 영문은 1로 센다.
     *
     * <pre>
     * [사용 예]
     * StringUtil.byteLength("abc \r")     => 5
     * StringUtil.byteLength("abc가나")    => 7  (반각문자)
     * StringUtil.byteLength("ａｂｃ가나") => 10 (전각문자)
     * StringUtil.byteLength(null) => NullPointerException 예외 발생
     * </pre>
     * @param chrs 입력 문자배열
     * @return int 문자열의 바이트 단위 길이
     */
    public static int byteLength(char[] chrs)
    {
    	int k = 0;
        for (int i = 0; i < chrs.length; i++) {
            if (EgovStringUtil.isTwoByte(chrs[i]))
                k =  k + 2;
            else
                k++;
        }
        return k;
    }

    /**
     * 문자열을 바이트 단위로 자른다. 한글은 2로 영문은 1로 센다.<br>
     * 자를 위치가 바이트의 중간에 걸릴 경우 아래의 규칙을 따른다.
     *  - 자를 위치가 2 byte의 앞 byte에 걸리면 해당 2 byte를 포함하고<br>
     *  - 자를 위치가 2 byte의 뒤 byte에 걸리면 해당 2 byte를 포함햐지 않는다.  
     *
     * <pre>
     * [사용 예]
     * StringUtil.byteSubStr("abcd가나다라", 2, 4) => "cd"
     * StringUtil.byteSubStr("abcd가나다라", 4, 5) => "가" ('가'의 앞 byte에 걸리므로 포함)
     * StringUtil.byteSubStr("abcd가나다라", 4, 6) => "가"
     * StringUtil.byteSubStr("abcd가나다라", 4, 7) => "가나" ('나'의 앞 byte에 걸리므로 포함) 
     * StringUtil.byteSubStr("abcd가나다라", 5, 5) => ""
     * StringUtil.byteSubStr("abcd가나다라", 5, 4) => ""
     * StringUtil.byteSubStr("abcd가나다라", 5, 6) => "" ('가'의 뒤 byte에 걸리므로 미포함)
     * StringUtil.byteSubStr("abcd가나다라", 5, 7) => "나"
     * StringUtil.byteSubStr("abcd가나다라", 5, 8) => "나다" ('다'의 앞 byte에 걸리므로 포함)
     * StringUtil.byteLength(null) => NullPointerException 예외 발생
     * </pre>
     * @param str 입력 문자열
     * @param begin 시작 위치
     * @param end 종료 위치
     * @return String 바이트 단위로 자른 문자열
     */
    public static String byteSubStr(String str, int begin, int end)
    {
        int len = str.length();
        char a[] = new char[len];
        char b[] = new char[len];

        str.getChars(0, len, a, 0);
        int j = 0, k = 0;
        for (int i = 0; i < len; i++) {
            if (k >= end) break;
            if (k >= begin) {
                b[j] = a[i];
                j++;
            }
            if (isTwoByte(a[i]))
                k = k + 2;
            else
                k++;
        }
        return String.valueOf(b).trim();
    }

    /**
     * List 를 String 배열로 변환한다.
     *
     * @param aList 변환할 List
     * @return String[] 변환된 String 배열
     */
    public static String[] toArrayFromList(List aList)
    {
    	if (null == aList)
    		return null;

    	String[] arr = new String[aList.size()];
		aList.toArray(arr);
		return arr;
    }

    /**
	 * 반각문자로 변경한다
	 * 
	 * @param src 변경할값
	 * @return String 변경된값
	 */
    public static String toHalfChar(String src)
    {
		StringBuffer strBuf = new StringBuffer();
		char c = 0;
		int nSrcLength = src.length();
		for (int i = 0; i < nSrcLength; i++) {
			c = src.charAt(i);
			// 영문이거나 특수 문자 일경우.
			if (c >= '！' && c <= '～') {
				c -= 0xfee0;
			}
			else if (c == '　') {
				c = 0x20;
			}
			// 문자열 버퍼에 변환된 문자를 쌓는다
			strBuf.append(c);
		}
		return strBuf.toString();
	}

	/**
	 * 문자열을 전각문자로 변환한다.
	 * 
	 * @param src 변환할 값
	 * @return String 변환된 값
	 */
    public static String toFullChar(String src)
    {
		// 입력된 스트링이 null 이면 null 을 리턴
		if (src == null)
			return null;

		// 변환된 문자들을 쌓아놓을 StringBuffer 생성
		StringBuffer strBuf = new StringBuffer();
		char c = 0;
		int nSrcLength = src.length();
		for (int i = 0; i < nSrcLength; i++) {
			c = src.charAt(i);
			// 영문이거나 특수 문자일 경우
			if (c >= 0x21 && c <= 0x7e)
				c += 0xfee0;
			// 공백일 경우
			else if (c == 0x20)
				c = 0x3000;

			// 문자열 버퍼에 변환된 문자를 쌓는다.
			strBuf.append(c);
		}
		return strBuf.toString();
	}

    /**
     * 문자열을 전각문자로 변환한다.<br>
     * 단, CR 와 LF 문자는 무시한다.
     *
     * @param src 변경할 값
	 * @return String 변경된 값
     */
    public static String toFullChar4ECG(String src)
	{
		// 입력된 스트링이 null 이면 null 을 리턴
		if (src == null)
			return null;
	
		// 변환된 문자들을 쌓아놓을 StringBuffer 생성
		StringBuffer strBuf = new StringBuffer();
		char c = 0;
		int nSrcLength = src.length();
		for (int i = 0; i < nSrcLength; i++) {
			c = src.charAt(i);
			// 영문이거나 특수 문자일 경우
			if (c >= 0x21 && c <= 0x7e)
				c += 0xfee0;
			// 공백일 경우
			else if (c == 0x20)
				c = 0x3000;
	
			// 문자열 버퍼에 변환된 문자를 쌓는다.
			if (c != 0x0d && c != 0x0a) 
				strBuf.append(c);
		}
		return strBuf.toString();
	}

    /**
     * 문자열에 포함된 null char(0x00) 문자를 Space 문자로 변경해 준다.
     * 
     * @param str 입력 문자열
     * @return String 변환된 문자열
     */
    public static String nil2Space(String str)
    {
    	if (null == str)
    		return null;

    	char c[] = str.toCharArray();
    	int cLen = c.length;
    	for (int i=0; i < cLen; i++) {
    		if (c[i] == 0x00)
    			c[i] = 0x20;
    	}
    	return new String(c);
    }

	/**
	 * 스트림 버퍼를 읽어서 문자열로 반환한다. 
	 * 
	 * @param reader Reader
	 * @return String XML 전문
	 */
    public static String readBuffer(BufferedReader reader, int bufferSize)
	{
		try {
			int readLen = 0;
			char[] buffer = new char[bufferSize];
			StringBuffer sb = new StringBuffer();
			while ((readLen = reader.read(buffer)) != -1) {
				sb.append(buffer, 0, readLen);
				if (readLen != bufferSize)
					break;

				buffer = new char[bufferSize];
			}
			return sb.toString().trim();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return null;
	}

   
	/**
	 * java 변수/함수명 만들어주는함수
	 * 
	 * @param reader Reader
	 * @return String XML 전문
	 */
    public static String toJavaName(String str)
    {
    		str=str.toLowerCase();

          StringBuffer sb = new StringBuffer();
          StringTokenizer st = new StringTokenizer(str.trim(), "_");
          if(st.hasMoreTokens())
          {
              String token = st.nextToken();
              sb.append(token.toLowerCase());
          }
          while(st.hasMoreTokens())
          {
              String token = st.nextToken();
              if(token.length() == 1)
              {
                  sb.append(token.toUpperCase());
              } else
              {
                  sb.append(token.substring(0, 1).toUpperCase());
                  sb.append(token.substring(1).toLowerCase());
              }
          }
          return sb.toString();
    }
    
} // End of Class