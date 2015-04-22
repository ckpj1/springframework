package kr.ckent.hospital.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import kr.ckent.common.base.BaseObject;

@SuppressWarnings("serial")
@XmlRootElement(name="sigungu")
@XmlType(name="sigungu", namespace="kr.ckent.hospital.vo.SiGunguVO", propOrder={"gungu_idx","si_idx", "gungu_nm", "si_nm"})
public class SiGunguVO  extends BaseObject{

	
	private String gungu_idx;
	private String si_idx; 
	private String gungu_nm;
	private String si_nm;
	
	
	public String getGungu_idx() {
		return gungu_idx;
	}


	@XmlElement(name="gungu_idx")
	public void setGungu_idx(String gungu_idx) {
		this.gungu_idx = gungu_idx;
	}
	
	
	public String getSi_idx() {
		return si_idx;
	}	
	@XmlElement(name="si_idx")
	public void setSi_idx(String si_idx) {
		this.si_idx = si_idx;
	}
	
	
	public String getGungu_nm() {
		return gungu_nm;
	}	
	@XmlElement(name="gungu_nm")
	public void setGungu_nm(String gungu_nm) {
		this.gungu_nm = gungu_nm;
	}
	
	public String getSi_nm() {
		return si_nm;
	}
	@XmlElement(name="si_nm")
	public void setSi_nm(String si_nm) {
		this.si_nm = si_nm;
	}
	
	
	
	
}
