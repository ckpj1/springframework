/*
 *
 * ByteUtil
 * 
 */
package egovframework.com.cmm.util;

/**
 * 바이트 관련 유틸 클래스
 *
 * <pre>
 * 문자열에서 바이트 길이를 계산하거나, 바이트 단위로 문자열을 잘라내는 등의 기능을 제공한다.
 * </pre>
 */
public final class EgovByteUtil
{

	/**
	 * 입력 문자열을 byte 배열로 변환한다.
	 *
	 * @param str 입력 문자열
	 * @return byte 배열
	 */
	public final static byte[] toBytes(String str)
    {
		if (str == null) return null;

		return str.getBytes();
	}

	/**
	 * 입력 문자열의 바이트 길이를 구한다.
     *
	 * @param str 입력 문자열
	 * @return 바이트 길이
	 */
	public final static int length(String str)
    {
		if (str == null) return 0;

		return str.getBytes().length;
	}

	/**
	 * 입력 문자열을 바이트 단위로 잘라서 문자열로 반환한다.
	 * <pre>
	 * 사용 예)
	 * ByteUtil.substring("가나다라", 0, 4) => "가나"
	 * ByteUtil.substring("가나", 0, 6) => 문자열의 바이트 길이를 벗어나므로 예외 발생
	 * </pre>
     *
	 * @param str 입력 문자열
	 * @param bgnIdx 시작위치
	 * @param endIdx 끝위치
	 * @return 바이트 단위로 잘라낸 문자열 
	 */
	public final static String substring(String str, int bgnIdx, int endIdx)
    {
		if (str == null) return null;

		byte[] orgB = str.getBytes();
		byte[] newB = new byte[endIdx - bgnIdx];

		for (int i = bgnIdx; i < endIdx; i++)
			newB[i - bgnIdx] = orgB[i];

        return new String(newB);
	}

    /**
	 * 입력 문자열을 바이트 단위로 지정한 위치 ~ 마지막까지 잘라서 문자열로 반환한다.
	 * <pre>
	 * 사용 예)
	 * ByteUtil.substring("가나다라", 2) => "나다라"
	 * ByteUtil.substring("가나", 6) => 문자열의 바이트 길이를 벗어나므로 예외 발생
	 * </pre>
	 *
	 * @param str 입력 문자열
	 * @param bgnIdx 시작위치
	 * @return 바이트 단위로 잘라낸 문자열
	 */
	public final static String substring(String str, int bgnIdx)
    {
	    if (str == null) return null;

		return substring(str, bgnIdx, str.getBytes().length);
	}

    /**
     * 문자열을 지정문자로 LPAD 처리한 바이트 단위로 지정한 길이의 문자열로 반환한다.<br>
     * 입력 문자열이 주어진 길이보다 큰 경우는 입력 문자열을 그대로 반환한다.<br>
     * 입력 문자열이 null일 경우는 null을 반환한다.
     * <pre>
     * 사용 예)
     * ByteUtil.lpad("123", '0', 5) => "00123"
     * ByteUtil.lpad("123", '0', 1) => "123"
     * ByteUtil.lpad(null, '0', 1) => null
     * ByteUtil.lpad("123", '가', 5) => "가123"
     * ByteUtil.lpad("abc", '가', 6) => "가?abc" - 한글이 1 바이트로 표현되어 글자가 깨진다. 
     * </pre>
     *
     * @param orgStr 변경할 문자열
     * @param appender Padding 문자
     * @param byteLen 반환할 문자열의 바이트 단위 길이
     * @return 지정한 바이트 단위 길이의 문자열
     */
    public final static String lpad(String orgStr, char appender, int byteLen)
    {
        if (orgStr == null)
            return null;

        int orgLen = length(orgStr);

        if (orgLen >= byteLen)
            return orgStr;

        byte[] b = new byte[byteLen];
    	String chr = Character.toString(appender);
    	byte[] c = chr.getBytes();
    	if (c.length > 1) {
            for (int i = 0; i < byteLen - orgLen; i++) {
            	b[i] = c[0];
            	i++;
            	if (i < byteLen)
                	b[i] = c[1];
            }
    	}
        else {
            for (int i = 0; i < byteLen - orgLen; i++)
            	b[i] = c[0];
    	}

    	byte[] a = orgStr.getBytes();
    	System.arraycopy(a, 0, b, byteLen-orgLen, orgLen);

    	return new String(b);
    }

    /**
     * 문자열을 지정문자로 RPAD 처리한 바이트 단위로 지정한 길이의 문자열로 반환한다.<br>
     * 입력 문자열이 주어진 길이보다 큰 경우는 입력 문자열을 그대로 반환한다.<br>
     * 입력 문자열이 null일 경우는 null을 반환한다.
     * <pre>
     * 사용 예)
     * ByteUtil.rpad("123", '0', 5) => "12300"
     * ByteUtil.rpad("123", '0', 1) => "123"
     * ByteUtil.rpad(null, '0', 1) => null
     * ByteUtil.rpad("123", '가', 5) => "123가"
     * ByteUtil.rpad("abc", '가', 6) => "abc가?" - 한글이 1byte 로 표현되어 글자가 깨진다. 
     * </pre>
     * 
     * @param orgStr 변경할 문자열
     * @param appender Padding 문자
     * @param byteLen 반환할 문자열의 바이트 단위 길이
     * @return 지정한 바이트 단위 길이의 문자열
     */
    public final static String rpad(String orgStr, char appender, int byteLen)
    {
        if (orgStr == null)
            return null;

        int orgLen = length(orgStr);

        if (orgLen >= byteLen)
            return orgStr;

        byte[] a = orgStr.getBytes();
    	byte[] b = new byte[byteLen];

    	System.arraycopy(a, 0, b, 0, orgLen);

    	String chr = Character.toString(appender);
    	byte[] c = chr.getBytes();

    	if (c.length > 1) {
            for (int i = orgLen; i < byteLen; i++) {
            	b[i] = c[0];
            	i++;
            	if (i < byteLen)
                	b[i] = c[1];
            }
    	}
        else {
            for (int i = orgLen; i < byteLen; i++)
            	b[i] = c[0];
    	}
        return new String(b);
    }

    /**
	 * 입력 문자열을 바이트 단위로 지정한 길이만큼씩 잘라서 문자배열로 반환한다.<br>
	 * 입력 문자열에 대한 trim 처리는 하지 않는다.
	 * <pre>
	 * 사용 예)
	 * ByteUtil.split("가나다라abcdef", 2) => String[] {"가", "나", "다", "라", "ab", "cd", "ef"}
	 * ByteUtil.split("가나다라abcdef", 3) => String[] {"가?", "ご?", "라a", "bcd", "ef"}
	 * ByteUtil.substring("", 2) => 입력 문자열이 빈 문자열이거나 null일 경우 null을 반환한다.
	 * </pre>
     *  
	 * @param str 입력 문자열
	 * @param length 길이(바이트 단위)
	 * @return 바이트 단위로 잘라낸 문자열 
	 */
	public final static String[] split(String str, int length)
    {
		if (str == null) return null;

		int cnt = 0;
		int pos1 = 0;
		int pos2 = 0;
		int size = 0;
		byte[] orgB = str.getBytes();
		int byteLen = orgB.length;
		if (byteLen == 0) return null;

		size = byteLen / length;
		if (byteLen % length != 0)
			size++;

		String[] retArr = new String[size];
		for (int i=0; i < size; i++) {
			if (pos1 + length > byteLen)
				pos2 = byteLen - pos1;
			else
				pos2 = length;

			byte[] newB = new byte[pos2];
			System.arraycopy(orgB, pos1, newB, 0, pos2);
			retArr[cnt] = new String(newB); 
			cnt++;
			pos1 = pos1 + length;
		}
        return retArr;
	}

    /**
     * 단위 클래스 테스트
     * @param args
     */
    public static void main(String[] args)
    {
        // lpad 테스트
        System.out.println(EgovByteUtil.lpad("123", '0', 5));
        System.out.println(EgovByteUtil.lpad("123", '0', 1));
        System.out.println(EgovByteUtil.lpad(null, '0', 1));
        System.out.println(EgovByteUtil.lpad("123", '가', 5));
        System.out.println(EgovByteUtil.lpad("abc", '가', 6));
        System.out.println(EgovByteUtil.rpad("123", '0', 5));
        System.out.println(EgovByteUtil.rpad("123", '0', 1));
        System.out.println(EgovByteUtil.rpad(null, '0', 1));
        System.out.println(EgovByteUtil.rpad("123", '가', 5));
        System.out.println(EgovByteUtil.rpad("abc", '가', 6));
    }

} // End of Class