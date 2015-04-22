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
public class DuplicateNicknmException extends Exception {

	private static final long serialVersionUID = 5964667060051221334L;

	/**
	 * 
	 */
	public DuplicateNicknmException() {
		super();
	}

	/**
	 * @param message
	 */
	public DuplicateNicknmException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DuplicateNicknmException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DuplicateNicknmException(String message, Throwable cause) {
		super(message, cause);
	}

}
