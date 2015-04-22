/*
 * Copyright (c) 1999-2005 (주)ACTSoft. All rights reserved.
 */
package kr.ckent.common.util;

import java.security.MessageDigest;
import java.util.Calendar;

/**
 * 보안 및 암호화 관련 유틸리티 클래스
 * 
 * @author YoungHo Lee
 *
 */
public class SecurityUtil {

	/**
	 * 기본 생성자
	 */
	public SecurityUtil() {
		super();
	}

	/**
	 * 전달된 문장을 MD5 해쉬하여 16비트 문장으로 반환.
	 * 사용자 비밀번호를 암호화 해서 저장하는 용도 등에 사용한다.
	 * 
	 * @param source 원본 문장
	 * @return 암호화 문장
	 * @throws Exception
	 */
	public static final String convertHash(String source) throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(source.getBytes());
		
		return SecurityUtil.bytesToHex(messageDigest.digest());
	}
	
	/**
	 * 바이트를 사람이 읽을 수 있는 16비트 문장으로 반환.
	 * 
	 * @param source 원본 바이트
	 * @return 암호화 문장
	 */
	public static final String bytesToHex(byte[] source) throws Exception {
		StringBuffer str = new StringBuffer();
		
		for(int i=0; i< source.length; ++i){
			str.append(Character.forDigit((source[i]>>4) & 0x0f, 16));
			str.append(Character.forDigit(source[i] & 0x0f, 16));
		}
		
		return str.toString();
	}
	
	/**
	 * 임의로 중복 되지 않는 일련번호를 생성하여 반환.
	 * 암호키 값 생성 등에 사용한다.
	 * 
	 * @return Integer 일련번호
	 * @throws Exception
	 */
	public static Integer generateSeqNo() throws Exception {
		
		return new Integer((int)(Calendar.getInstance().getTimeInMillis()/1000));
	}

	/**
	 * SecurityUtils 클래스를 테스트 한다.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		Integer testInteger = SecurityUtil.generateSeqNo();
		
		System.out.println(SecurityUtil.convertHash(testInteger.toString()));
	}
}
