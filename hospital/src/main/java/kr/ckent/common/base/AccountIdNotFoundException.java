/*
 * Copyright (c) 1999-2005 (주)ACTSoft. All rights reserved.
 */

package kr.ckent.common.base;

/**
 * 계정 아이디 정보를 찾을 수 없을 경우 발생시키는 예외
 * 
 * @author Hong ByungChan
 *
 */
public class AccountIdNotFoundException extends Exception {

	private static final long serialVersionUID = 5964667060051221334L;

	/**
	 * 
	 */
	public AccountIdNotFoundException() {
		super();
	}

	/**
	 * @param message
	 */
	public AccountIdNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AccountIdNotFoundException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AccountIdNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
