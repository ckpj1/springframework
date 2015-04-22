/*
 * Copyright (c) 1999-2005 (주)ACTSoft. All rights reserved.
 */
package kr.ckent.common.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import sun.misc.*;



/**
 * 쿠키 관련 유틸리티 클래스
 * 
 * @author YoungHo Lee
 *
 */
public class CookieUtil {

	/**
	 * 기본 생성자
	 */
	public CookieUtil() {
		super();
	}

	/**
	 * 쿠키를 생성하고 respose에 등록한다.
	 * 
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @param encoding
	 * @throws Exception
	 */
	public static final void addCookie(
			HttpServletResponse response, 
			String cookieName, 
			String cookieValue,  
            String encoding) throws Exception {
		
		addCookie(response, cookieName, cookieValue, null, null, null, null, null, encoding);
		
		return ;
	}
	
	/**
	 * 쿠키를 생성하고 response에 등록한다.
	 * 
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @param domain
	 * @param path
	 * @param maxAge
	 * @param isSecure
	 * @param version
	 * @param encoding
	 * @throws Exception
	 */
	public static final void addCookie(
			HttpServletResponse response, 
			String cookieName, 
			String cookieValue,  
            String domain, 
            String path, 
            Integer maxAge,
            Boolean isSecure,
            Integer version,
            String encoding) throws Exception {
		
		Cookie cookie = new Cookie(cookieName, URLEncoder.encode(cookieValue, encoding));
	
		if((domain != null) && (domain.length() > 0)) {
			cookie.setDomain(domain);
		}
		
		if((path != null) && (path.length() > 0)) {
			cookie.setPath(path);
		}
		
		if(maxAge != null) {
			cookie.setMaxAge(maxAge);
		}
		
		if(isSecure != null) {
			cookie.setSecure(isSecure.booleanValue());
		}
		
		if(version != null) {
			cookie.setVersion(version.intValue());
		}
		
		response.addCookie(cookie);

		return ;
	}
	
	/**
	 * 쿠키를 제거한다.
	 * 
	 * @param response
	 * @param cookieName
	 * @throws Exception
	 */
	public static final void removeCookie(HttpServletResponse response, String cookieName) throws Exception {
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setMaxAge(0);

		response.addCookie(cookie);
		
		return;
	}
	
	/**
	 * 쿠키의 존재 여부를 반환한다.
	 * 
	 * @param request
	 * @param cookieName
	 * @return
	 * @throws Exception
	 */
	public static final boolean existCookie(HttpServletRequest request, String cookieName) throws Exception {
		Cookie[] cookies = request.getCookies();
		
		boolean result = false;
		
        if (cookies != null) {
        	for(Cookie cookie : cookies) {
        		if(cookie.getName().equals(cookieName)) {
        			result = true;
        			break;
        		}
        	}
        }
        
        return result;
	}
	
	/**
	 * 쿠키의 값을 반환한다.
	 * 
	 * @param request
	 * @param cookieName
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static final String getCookieValue(HttpServletRequest request, String cookieName, String encoding) throws Exception {
		Cookie[] cookies = request.getCookies();
		
		String result = null;
		
        if (cookies != null) {
        	for(Cookie cookie : cookies) {
        		if(cookie.getName().equals(cookieName)) {
        			result = URLDecoder.decode(cookie.getValue(), encoding);
        			break;
        		}
        	}
        }
        
        return result;
	}
	
	/**
	 * BASE64 Encoder. 2008-05-11 LJW
	 *
	 * @param str
	 * @return
	 * @throws java.io.Exception
	 */
	public static final String YSLEnc(String str){
	  
		String result = "";
	  
		BASE64Encoder encoder = new BASE64Encoder();
		byte[] b1 = str.getBytes();
		result = encoder.encode(b1);
	    
		//result = XOR(result);
		
		return result;
	}


	/**
	 * BASE64 Decoder. 2008-05-11 LJW
	 *	
	 * @param str
	 * @return
	 * @throws java.io.Exception
	 */
	public static final String YSLDec(String str){
		
		String result = "";
		//String xorStr = XOR(str);
		try{
			
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] b1 = decoder.decodeBuffer(str);
			result = new String(b1);
			
		} catch (IOException ex){
		}
		
		return result;
	}
	
	public static final String XOR(String code){
	  //암호화 키
	  String Xorkey = "gkrtnfwjdqhdnjsgkrtnfwjdqhdnjsgkrtnfwjdqhdnjsgkrtnfwjdqhdnjsgkrtnfwjdqhdnjs";
	  byte keyChar[] = Xorkey.getBytes();
	  //byte keyChar[] = {0x01, 0x03, 0x01, 0x05, 0x01, 0x03, 0x01, 0x01};
	  
	  //암호화할 대상 
	  byte codeChar[] = new byte[code.getBytes().length]; //code의 문자열 길이만큼의 배열을 만든다.
	  codeChar = code.getBytes(); //code를 Byte형으로 변환한다.
   
	  //XOR 연산
	  for(int i=0, j=0; i< code.getBytes().length; i++){
	   codeChar[i] = (byte) (codeChar[i] ^ keyChar[j]); //code의 한문자와 key의 한문자를 ^(XOR)연산을 한후 byte형으로 변환한다.
	   j = (++j < keyChar.length ? j : 0); //j의 값이 key문자열의 길이보다 커질경우 0으로 아닐경우는 j의 값을 갖는다.
	  }
 
	  return new String(codeChar) ; //byte배열인 code를 String으로 변환하여 반환한다.
	}
}
