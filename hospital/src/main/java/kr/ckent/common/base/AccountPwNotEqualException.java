/*
 * Copyright (c) 1999-2005 (주)ACTSoft. All rights reserved.
 */

package kr.ckent.common.base;

/**
 * 계정 비밀번호가 일치하지 않을 때 발생시키는 예외
 * 
 * 
 * @author Hong ByungChan
 *
 */
public class AccountPwNotEqualException extends Exception {

	private static final long serialVersionUID = -7880045815288455223L;

	/**
	 * 
	 */
	public AccountPwNotEqualException() {
		super();
	}

	/**
	 * @param message
	 */
	public AccountPwNotEqualException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AccountPwNotEqualException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AccountPwNotEqualException(String message, Throwable cause) {
		super(message, cause);
	}

}
