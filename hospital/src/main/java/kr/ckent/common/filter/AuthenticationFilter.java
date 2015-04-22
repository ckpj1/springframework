/*
 * Copyright (c) 1999-2005 (주)ACTSoft. All rights reserved.
 */
package kr.ckent.common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author YoungHo Lee
 *
 */
public class AuthenticationFilter implements Filter {

	// apache commons 로깅 변수
	private final Log logger = LogFactory.getLog(getClass());
	
	private String redirectUrl = null;
	private List<String> sessionNames = null;
	private Map<String, String> excludeUrls = null;

	/**
	 * 기본 생성자
	 */
	public AuthenticationFilter() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		logger.info(getClass().getName() + " destroy() start.");
		
		this.redirectUrl = null;
		this.sessionNames = null;
		this.excludeUrls = null;
		
		logger.info(getClass().getName() + " destroy() end.");
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			/*
			if(logger.isDebugEnabled()) {
				logger.debug(getClass().getName() + " doFilter() start.");
			}
			*/
			
			HttpServletRequest requestHttp = (HttpServletRequest) request;
			HttpServletResponse responseHttp = (HttpServletResponse) response;
	
			String servletPath = requestHttp.getServletPath();
			String contextPath = requestHttp.getContextPath();

			if(contextPath.lastIndexOf("/") == -1 || contextPath.lastIndexOf("/") != (contextPath.length() - 1)){
				contextPath = contextPath.concat("/");
			}
			
			String redirectFullUrl = responseHttp.encodeURL(contextPath + this.redirectUrl);
			
			/*
			if(logger.isDebugEnabled()){
				logger.debug("servletPath : " + servletPath);
				logger.debug("contextPath : " + contextPath);
				logger.debug("redirectFullUrl : " + redirectFullUrl);
			}
			*/
			
			// 세션 검증 제외 URL 검사
			
			if((servletPath.trim().length() == 0) || (servletPath.equals("/"))){
				chain.doFilter(request, response);
				
				return;
			}
	
			String excludeUrl = excludeUrls.get(servletPath);
			if(excludeUrl!=null && excludeUrl.equals("exclude-url")){
				chain.doFilter(request, response);
				return;
			}
	
			// 세션 검증
			
			HttpSession session = requestHttp.getSession();
			
			boolean sessionOut = false;
			Object sessionObject = null;
						
			// logger.info("dump parameters...");
			for(String sessionName : this.sessionNames) {
				sessionObject = session.getAttribute(sessionName);
				
		
				if(logger.isDebugEnabled()) {
					logger.debug("sessionName : " + sessionName);
					logger.debug("sessionObject : " + sessionObject);
				}
				
				
				if(sessionObject==null) {
					sessionOut = true;
					break;
				}
				
				sessionObject = null;
			}
	
			if(sessionOut){
				logger.info("session out... " + redirectFullUrl);
				responseHttp.sendRedirect(redirectFullUrl);
			}
			else {
				chain.doFilter(request, response);
			}
		}
		finally {
			/*
			if(logger.isDebugEnabled()) {
				logger.debug(getClass().getName() + " doFilter() end.");
			}
			*/
		}
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		logger.info(getClass().getName() + " init() start.");
		
		this.sessionNames = new ArrayList<String>();
		this.excludeUrls = new HashMap<String, String>();
		
		// init parameter 이름을 가지고 초기화 설정
		Enumeration initParameterNames =config.getInitParameterNames();
		
		String parameterName = null;
		String parameterValue = null;
		logger.info("dump parameters...");
		while(initParameterNames.hasMoreElements()){
			parameterName = (String)initParameterNames.nextElement();
			parameterValue = config.getInitParameter(parameterName);
			
			if(parameterValue.equals("session-name")) {
				this.sessionNames.add(parameterName);
			}
			else if(parameterValue.equals("redirect-url")) {
				this.redirectUrl = parameterName;
			}
			else if(parameterValue.equals("exclude-url")) {
				this.excludeUrls.put(parameterName, parameterValue);
			}
			
			if(logger.isDebugEnabled()) {
				logger.debug("parameterName : " + parameterName);
				logger.debug("parameterValue : " + parameterValue);
			}
		}
		
		logger.info(getClass().getName() + " init() end.");
	}
	
}
