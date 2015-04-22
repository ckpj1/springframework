package kr.ckent.hospital.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import kr.ckent.common.base.BaseObject;

@SuppressWarnings("serial")
@XmlRootElement(name="point")
@XmlType(name="point"
				, namespace="kr.ckent.hospital.vo.PointHistoryVO"
				, propOrder={"p_idx", "user_id" , "nick_nm", "point_type", "point_nm", "saving_point", "saving_dt"})
public class PointHistoryVO  extends BaseObject{


	private Integer p_idx; 
	private String user_id;
	private String nick_nm;
	private String point_type;
	private String point_nm;
	private String saving_point;
	private String saving_dt;
	
	
	public Integer getP_idx() {
		return p_idx;
	}

	@XmlElement(name="p_idx")
	public void setP_idx(Integer p_idx) {
		this.p_idx = p_idx;
	}
	
	public String getUser_id() {
		return user_id;
	}
	@XmlElement(name="user_id")
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getNick_nm() {
		return nick_nm;
	}
	@XmlElement(name="nick_nm")
	public void setNick_nm(String nick_nm) {
		this.nick_nm = nick_nm;
	}
	
	public String getPoint_type() {
		return point_type;
	}
	@XmlElement(name="point_type")
	public void setPoint_type(String point_type) {
		this.point_type = point_type;
	}
	
	public String getPoint_nm() {
		return point_nm;
	}
	@XmlElement(name="point_nm")
	public void setPoint_nm(String point_nm) {
		this.point_nm = point_nm;
	}
	
	public String getSaving_point() {
		return saving_point;
	}
	@XmlElement(name="saving_point")
	public void setSaving_point(String saving_point) {
		this.saving_point = saving_point;
	}
	
	public String getSaving_dt() {
		return saving_dt;
	}
	@XmlElement(name="saving_dt")
	public void setSaving_dt(String saving_dt) {
		this.saving_dt = saving_dt;
	}
	
	

	

	
}
