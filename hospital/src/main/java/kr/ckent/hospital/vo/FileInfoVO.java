package kr.ckent.hospital.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import kr.ckent.common.base.BaseObject;

@SuppressWarnings("serial")
@XmlRootElement(name="file")
@XmlType(name="file", namespace="kr.ckent.hospital.vo.FileInfoVO", propOrder={"atch_file_id","file_sn","profile_url"})
public class FileInfoVO  extends BaseObject{

	private String atch_file_id;
	private String file_sn;
	private String profile_url;

	public String getAtch_file_id() {
		return atch_file_id;
	}
	@XmlElement(name="atch_file_id")
	public void setAtch_file_id(String atch_file_id) {
		this.atch_file_id = atch_file_id;
	}
	public String getFile_sn() {
		return file_sn;
	}
	@XmlElement(name="file_sn")
	public void setFile_sn(String file_sn) {
		this.file_sn = file_sn;
	}
	public String getProfile_url() {
		return profile_url;
	}
	@XmlElement(name="profile_url")
	public void setProfile_url(String profile_url) {
		this.profile_url = profile_url;
	}


	
}
