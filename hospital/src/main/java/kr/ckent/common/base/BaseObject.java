/*
 * Copyright (c) 1999-2005 (주)ACTSoft. All rights reserved.
 */
package kr.ckent.common.base;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * equals(), hashCode() 그리고 toString() 메소드를 자동 생성한다.
 * 
 * @author Hong ByungChan
 */
public abstract class BaseObject implements Serializable {

	/**
	 * 기본 생성자
	 */
	public BaseObject() {
		super();
	}

	/**
	 * BaseObject를 상속 받은 클래스의 기본 equals() 메소드를 오버라이딩 한다.
	 */
	@Override
	public boolean equals(Object obj) {
		
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * BaseObject를 상속 받은 클래스의 기본 hashCode() 메소드를 오버라이딩 한다.
	 */
	@Override
	public int hashCode() {
		
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/**
	 * BaseObject를 상속 받은 클래스의 기본 toString() 메소드를 오버라이딩 한다.
	 */
	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
