/*
 * Copyright (c) 1999-2005 (주)ACTSoft. All rights reserved.
 */
package kr.ckent.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * HTTP/HTTPS 통신 관련 유틸리티 메소드 모음 클래스. 
 * 
 * @author YoungHo Lee
 *
 */
public class HttpUtils {
	
	/** 기본 HTTP 포트 번호 */
	public static final int DEFAULT_HTTP_PORT = 80;
	/** 기본 HTTPS 포트 번호 */
	public static final int DEFAULT_HTTPS_PORT = 443;	

	/**
	 * 기본 생성자
	 */
	public HttpUtils() {
		super();
	}
	
	/**
	 * HttpUtils.getCurrentUrl(HttpServletRequest request, boolean reverse, Integer portNumber) 을 오버 로딩.
	 * portNumber를 기본값으로 사용하고자 할 경우 호출.
	 * 
	 * @param request HttpServletRequest
	 * @param reverse schem 변경 여부 (true, false)
	 * @return 현재 Url
	 * @throws Exception
	 */
	public static final String getCurrentUrl(HttpServletRequest request, boolean reverse) throws Exception {
		
		return HttpUtils.getCurrentUrl(request, reverse, null);
	}
	
	/**
	 * 현재 서비스가 되고 있는 Url 주소를 반환한다.
	 * reverse 값이 true 라면 schme를 변경한 주소를 반환한다.
	 * 
	 * @param request HttpServletRequest
	 * @param reverse schem 변경 여부 (true, false)
	 * @param portNumber schme 변경 시 적용될 포트 번호. null 이면 기본 포트 번호 사용.
	 * @return 현재 Url
	 * @throws Exception
	 */
	public static final String getCurrentUrl(HttpServletRequest request, boolean reverse, Integer portNumber) throws Exception {
		StringBuffer url = new StringBuffer();
		
		String scheme = request.getScheme();
		String serverName = request.getServerName();

		int port = request.getServerPort();
		
		// 서블릿 엔진 버그
		if(port < 0){
			port = HttpUtils.DEFAULT_HTTP_PORT;
		}
		
		String context = request.getContextPath();
		
		if(context == null) {
			context = "";
		}
		
		int contextSize = context.length();
		
		if (contextSize == 0){
			context += "/";
		}
		else if(contextSize > 1){
			int last = context.lastIndexOf("/", contextSize - 1);
			
			if(last > 0) {
				context += "/";
			}
		}
		
		// reverse 가 true 일 때 http -> https, https -> http 로 url을 변경
		if(reverse) {
			if(scheme.equalsIgnoreCase("http")) {
				scheme = "https";
				port = (portNumber == null) ? HttpUtils.DEFAULT_HTTPS_PORT : portNumber.intValue();
			}
			else if(scheme.equalsIgnoreCase("https")) {
				scheme = "http";
				port = (portNumber == null) ? HttpUtils.DEFAULT_HTTP_PORT : portNumber.intValue();
			}
		}
		
		url.append(scheme);
		url.append("://");
		url.append(serverName);
		
		if(
				((scheme.equalsIgnoreCase("http")) && (port!=HttpUtils.DEFAULT_HTTP_PORT)) || 
				((scheme.equalsIgnoreCase("https")) && (port!=HttpUtils.DEFAULT_HTTPS_PORT))
				)
		{
			url.append(":");
			url.append(port);
		}
		
		url.append(context);
		
		return url.toString();
	}

}
