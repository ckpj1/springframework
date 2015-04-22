package kr.ckent.hospital.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import kr.ckent.common.base.BaseObject;

@SuppressWarnings("serial")
@XmlRootElement(name="result")
@XmlType(name="comcode", namespace="kr.ckent.hospital.vo.CodeAPIVO", propOrder={"code","code_nm"})
public class CodeApiVO  extends BaseObject{

	private String code;
	private String code_nm;
	
	
	public String getCode() {
		return code;
	}
	@XmlElement(name="code")
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode_nm() {
		return code_nm;
	}
	@XmlElement(name="code_nm")
	public void setCode_nm(String code_nm) {
		this.code_nm = code_nm;
	}
	

	
}
