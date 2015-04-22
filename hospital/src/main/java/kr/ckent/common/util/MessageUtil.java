/*
 * Copyright (c) 1999-2005 (주)ACTSoft. All rights reserved.
 */
package kr.ckent.common.util;

import org.springframework.context.support.MessageSourceAccessor;

/**
 * @author YoungHo Lee
 *
 */
public class MessageUtil {

	/**
	 * 기본 생성자
	 */
	public MessageUtil() {
		super();
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

}
