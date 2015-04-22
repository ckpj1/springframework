/*
 * Copyright (c) 1999-2005 (주)ACTSoft. All rights reserved.
 */
package kr.ckent.common.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringTokenizer;

import org.springframework.context.support.MessageSourceAccessor;
 
/**
 * 문자열 처리와 관련된 유틸리티 메소드 모음 클래스
 * 
 * @author YoungHo Lee
 *
 */
public class StringUtil {

	/**
	 * 기본 생성자
	 */
	public StringUtil() {
		super();
	}
	
	/**
	 * 에러 추적 로그(StackTrace)를 문자열로 만들어서 반환.
	 * 
	 * @param throwable 예외
	 * @return 문자열
	 * @throws Exception
	 */
	public static final String convertFromStacktraceToString(Throwable throwable) throws Exception {
		String trace = null;
			
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(byteArrayOutputStream);
		throwable.printStackTrace(printStream);
		trace = byteArrayOutputStream.toString();
		printStream.close();
		byteArrayOutputStream.close();
	
		return trace;
	}
	
	/**
	 * 원본 문자열에서 특정 문자열을 삭제하고 반환.
	 * 
	 * @param source 원본 문자열
	 * @param removeStr 삭제할 문자열
	 * @return 삭제된 문자열
	 * @throws Exception
	 */
	public static final String removeString(String source, String removeStr) throws Exception {
		String result = "";
		
		source = source.trim();
		String[] tmpArrayStr = source.split(removeStr);
		
		for(int i=0; i<tmpArrayStr.length; i++){
			result += tmpArrayStr[i];
		}
		
		return result;
	}
	
	/**
	 * 원본 문자열 변수에 값이 있는지 확인.
	 * 문자열이 null 이거나 없다면 true,
	 * 있다면 false를 반환.
	 * 
	 * @param source 원본 문자열
	 * @return 값 여부
	 * @throws Exception
	 */
	public static final boolean isEmpty(String source) throws Exception {
		if(source != null && source.trim().length() > 0){
			return false;
		}
		
		return true;
	}

	/**
	 * 언어 설정 파일에 설정되지 않은 이름을 가져올 때 예외가 아닌 
	 * 지정된 문자를 보여주고자 할 때 사용한다.
	 * 
	 * SpringFrameworks 필수
	 * 
	 * @param messages
	 * @param key
	 * @return String
	 */
	public static final String fmt(MessageSourceAccessor messages, String key) {
		String val = null;

		try {
			val = messages.getMessage(key);
		}
		catch (Exception e) {
			val = "???" + key + "??";
		}

		return val;
	}
	
	/**
	 * 지정된 문자수를 입력받은 문자로 왼쪽부터 채운다.
	 * 
	 * @param source
	 * @param length
	 * @param lParam
	 * @return
	 * @throws Exception
	 */
	public static final String lpad(int source, int length, String lParam) throws Exception {
		String val = new Integer(source).toString();
		
		int cnt = length - val.length();
		
		for(int i=0; i<cnt; i++) {
			val = lParam.concat(val);
		}
		
		return val;
	}

	/**
	 * 지정된 문자수를 입력받은 문자로 오른쪽부터 채운다.
	 * 
	 * @param source
	 * @param length
	 * @param rParam
	 * @return
	 * @throws Exception
	 */
	public static final String rpad(String source, int length, String rParam) throws Exception {
		String val = source;
		
		int cnt = length - val.length();
		
		for(int i=0; i<cnt; i++) {
			val = val.concat(rParam);
		}
		
		return val;
	}
	
	/**
	 * 문장의 내외부 공백 모두 제거
	 * 
	 * @param source
	 * @return
	 * @throws Exception
	 */
	public static final String trimAll(String source) throws Exception {
		source = source.trim();
		source = source.replaceAll(" ", "");
		source = source.replaceAll("\t", "");
		source = source.replaceAll("\n", "");
		
		return source;
	}
	
	/**
	 * 주민등록번호를 이용하여 성별 코드 반환
	 * 
	 * @param ssn
	 * @return
	 * @throws Exception
	 */
	public static final String isSexOfSsn(String ssn) throws Exception {
		String sexChar = ssn.substring(6, 7);
		
		if(sexChar.equals("1") || sexChar.equals("3")){
			return "M"; // 남자
		}
		// 2 or 4
		else {
			return "F"; // 여자
		}
	}
	
	/**
	 * 원본 문자열 변수에 값이 있는지 확인.
	 * 문자열이 null 이거나 없다면 false,
	 * 있다면 true를 반환.
	 * 
	 * @param source 원본 문자열
	 * @return 값 여부
	 * @throws Exception
	 */
	public static final boolean isNotEmpty(String source) throws Exception {
		if(source != null && source.trim().length() > 0){
			return true;
		}
		
		return false;
	}

	/**
	 * StringUtils 클래스의 메소드들을 테스트한다.
	 * 
	 * @param args
	 * @throws Exception
	
	public static void main(String args[]) throws Exception {
		
		System.out.println(StringUtils.convertFromStacktraceToString(new Exception("예외 테스트")));
		
		System.out.println(StringUtils.removeString("2007-11-24", "-"));
		
		System.out.println(StringUtils.isEmpty(""));
		
		System.out.println(StringUtils.isEmpty(null));
		
		System.out.println(StringUtils.isEmpty("good"));

		System.out.println(StringUtils.rpad("11", 6, "0"));
		
		System.out.println(StringUtils.lpad(11, 6, "0"));
		
		System.out.println(StringUtils.trimAll("안 녕 하 \n\n세 	요"));

		System.out.println(StringUtils.isSexOfSsn("7703142221116"));
		
	}
	 */
	
	/**
	 * 현재 달의 날수
	 */
	public static final int getNumberDays(int n_year, int n_month) {
		switch (n_month) {
			case 1:
		    case 3:
		    case 5:
		    case 7:
		    case 8:
		    case 10:
		    case 12:
		    	return (31);

		    case 4:
		    case 6:
		    case 9:
		    case 11:
		       return (30);

		    default:
		    if( ((n_year%4==0)&&(n_year%100!=0)) || (n_year%400==0) )
		    	return (29);   // 2월 윤년계산을 위해서
		    else
		    	return (28);
		}
	}
	
	/**
	 * 입력받은 값이 숫자인지 문자인지 체크
	 */
	public static boolean isNumber(String str) {
		boolean result = true;
		if(str.equals("")) {
			result = false;
		}
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			if(c < 48 || c > 59) {
				result = false;
				break;
			}
			
		}
		return result;
	}
	
	public static String[] strSplit(String message, String tokenStr) { //(구분자문자열, 구분자 )
		//*************************************************************************
		// sectorcode ^ ^ ^ 분리해서 routeDataSrc[] 배열에 담는다.
			StringTokenizer str = new StringTokenizer(message,tokenStr);
			int strCnt=str.countTokens();
			int routeCnt=0;
			String [] routeDataSrc = new String [strCnt];			
			while(str.hasMoreTokens()) {
				routeDataSrc[routeCnt]=str.nextToken();			
				routeCnt++;
			}

			return routeDataSrc;
		}
	
	public static Integer isIntegerChk(Integer val){
		if( val == null || "".equals(val) ){
			return null;
		}else{
			return val;
		}
	}
	
	public static Integer isIntegerChk(String val){
		if( val == null || "".equals(val) ){
			return null;
		}else{
			return Integer.parseInt(val);
		}
	}
	
    public static String biznumformat(String str){
    	
    	String retValue =  str.replaceAll("([0-9]{3})([0-9]{2})([0-9]{5})","$1-$2-$3"); 
    	return retValue;
    }
}
