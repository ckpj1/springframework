package kr.ckent.hospital.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;




@XmlRootElement(name="result")
@XmlType(name="itemlist", namespace="kr.ckent.hospital.vo.ApiResultList",  propOrder={ "flag","message","totcnt",   "itemlist"} )
public class ApiResultList<T> {
	
	private String flag="";
	private String message="";
	private String totcnt = "0";
	
	private List<T> itemlist;


	public ApiResultList() {
		super();
	}
	
	public ApiResultList(List<T> itemlist) {
		this.itemlist = itemlist;
	}
	
	
	@XmlElementWrapper(name="itemlist")
	@XmlElementRefs(
			{
				@XmlElementRef(type=CodeApiVO.class),
				@XmlElementRef(type=MemberInfoVO.class),
				@XmlElementRef(type=BoardInfoVO.class),
				@XmlElementRef(type=SiGunguVO.class),
				@XmlElementRef(type=FileInfoVO.class),
				@XmlElementRef(type=PointHistoryVO.class),
				@XmlElementRef(type=PointManageVO.class)
			})
	public List<T> getItemlist() {
		return itemlist;
	}

	public void setItemlist(List<T> itemlist) {
		this.itemlist = itemlist;
	}


	public String getFlag() {
		return flag;
	}
	@XmlElement(name="flag")
	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	@XmlElement(name="message")
	public void setMessage(String message) {
		this.message = message;
	}
	
	

	public String getTotcnt() {
		return totcnt;
	}

	@XmlElement(name="totcnt")
	public void setTotcnt(String totcnt) {
		this.totcnt = totcnt;
	}

	
}
