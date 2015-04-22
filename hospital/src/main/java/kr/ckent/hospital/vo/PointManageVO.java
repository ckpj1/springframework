package kr.ckent.hospital.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import kr.ckent.common.base.BaseObject;

@SuppressWarnings("serial")
@XmlRootElement(name="point")
@XmlType(name="point"
, namespace="kr.ckent.hospital.vo.PointManageVO"
, propOrder={"idx", "point_type", "point_nm", "point", "p_desc", "del_yn", "reg_dt"})
public class PointManageVO  extends BaseObject{


	private Integer idx;
	private String point_type;
	private String point_nm;
	private Integer point;
	private String p_desc;
	private String del_yn;
	private String reg_dt;


	public Integer getIdx() {
		return idx;
	}
	@XmlElement(name="user_id")
	public void setIdx(Integer idx) {
		this.idx = idx;
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

	public Integer getPoint() {
		return point;
	}
	@XmlElement(name="point")
	public void setPoint(Integer point) {
		this.point = point;
	}

	public String getP_desc() {
		return p_desc;
	}
	@XmlElement(name="p_desc")
	public void setP_desc(String p_desc) {
		this.p_desc = p_desc;
	}

	public String getDel_yn() {
		return del_yn;
	}
	@XmlElement(name="del_yn")
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}

	public String getReg_dt() {
		return reg_dt;
	}
	@XmlElement(name="reg_dt")
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}



}
