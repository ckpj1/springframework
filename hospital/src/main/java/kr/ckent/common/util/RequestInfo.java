package kr.ckent.common.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Class Name  : RequestInfo.java
 * @Description : Request의 객체 정보를 가져오는 클래스
 */
public class RequestInfo {
	
	public static HttpServletRequest getCurrentRequest(){
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

		HttpServletRequest hsr = sra.getRequest();
		return hsr;
	}

}
