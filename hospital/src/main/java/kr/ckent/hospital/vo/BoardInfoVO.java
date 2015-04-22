package kr.ckent.hospital.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import kr.ckent.common.base.BaseObject;

@SuppressWarnings("serial")
@XmlRootElement(name="board")
@XmlType(name="board"
			, namespace="kr.ckent.hospital.vo.BoardInfoVO", 
			  propOrder={"board_idx","catalog_type", "sido", "gungu", "contents", "read_cnt", "parent_idx", "reply_num"
							, "step","atch_file_id", "user_id", "reg_dt","modify_dt", "profile_url", "nick_nm", "sido_code"
							, "gungu_code", "catalog_nm","treatment", "comment_cnt","user_atch_file_id", "map_lat", "map_lng", "hospital_address" })
public class BoardInfoVO  extends BaseObject{

	private Integer board_idx;
	private String catalog_type;
	private String sido;
	private String gungu;
	private String contents;
	private Integer read_cnt;
	private Integer parent_idx;
	private Integer reply_num;
	private Integer step;
	private String atch_file_id;
	private String user_id;
	private String reg_dt;
	private String modify_dt;
	private String profile_url;
	private String nick_nm;
	private String sido_code;
	private String gungu_code;
	private String catalog_nm;
	private String treatment;
	private String comment_cnt;
	private String user_atch_file_id;
	private String map_lat;
	private String map_lng;
	private String hospital_address;
	
	public String getAtch_file_id() {
		return atch_file_id;
	}
	@XmlElement(name="atch_file_id")
	public void setAtch_file_id(String atch_file_id) {
		this.atch_file_id = atch_file_id;
	}
	public Integer getBoard_idx() {
		return board_idx;
	}
	@XmlElement(name="board_idx")
	public void setBoard_idx(Integer board_idx) {
		this.board_idx = board_idx;
	}
	
	public String getCatalog_type() {
		return catalog_type;
	}
	@XmlElement(name="catalog_type")
	public void setCatalog_type(String catalog_type) {
		this.catalog_type = catalog_type;
	}
	
	public String getSido() {
		return sido;
	}
	@XmlElement(name="sido")
	public void setSido(String sido) {
		this.sido = sido;
	}
	
	public String getGungu() {
		return gungu;
	}
	@XmlElement(name="gungu")
	public void setGungu(String gungu) {
		this.gungu = gungu;
	}
	
	public String getContents() {
		return contents;
	}
	@XmlElement(name="contents")
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public Integer getRead_cnt() {
		return read_cnt;
	}
	@XmlElement(name="read_cnt")
	public void setRead_cnt(Integer read_cnt) {
		this.read_cnt = read_cnt;
	}
	
	public Integer getParent_idx() {
		return parent_idx;
	}
	@XmlElement(name="parent_idx")
	public void setParent_idx(Integer parent_idx) {
		this.parent_idx = parent_idx;
	}
	
	public Integer getReply_num() {
		return reply_num;
	}
	@XmlElement(name="reply_num")
	public void setReply_num(Integer reply_num) {
		this.reply_num = reply_num;
	}
	
	public Integer getStep() {
		return step;
	}
	@XmlElement(name="step")
	public void setStep(Integer step) {
		this.step = step;
	}
	
	public String getUser_id() {
		return user_id;
	}
	@XmlElement(name="user_id")
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getReg_dt() {
		return reg_dt;
	}
	@XmlElement(name="reg_dt")
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	
	public String getModify_dt() {
		return modify_dt;
	}
	@XmlElement(name="modify_dt")
	public void setModify_dt(String modify_dt) {
		this.modify_dt = modify_dt;
	}
	
	public String getProfile_url() {
		return profile_url;
	}
	@XmlElement(name="profile_url")
	public void setProfile_url(String profile_url) {
		this.profile_url = profile_url;
	}
	
	public String getNick_nm() {
		return nick_nm;
	}
	@XmlElement(name="nick_nm")
	public void setNick_nm(String nick_nm) {
		this.nick_nm = nick_nm;
	}
	
	public String getSido_code() {
		return sido_code;
	}
	@XmlElement(name="sido_code")
	public void setSido_code(String sido_code) {
		this.sido_code = sido_code;
	}
	
	public String getGungu_code() {
		return gungu_code;
	}
	@XmlElement(name="gungu_code")
	public void setGungu_code(String gungu_code) {
		this.gungu_code = gungu_code;
	}
	public String getCatalog_nm() {
		return catalog_nm;
	}
	@XmlElement(name="catalog_nm")
	public void setCatalog_nm(String catalog_nm) {
		this.catalog_nm = catalog_nm;
	}
	
	
	public String getTreatment() {
		return treatment;
	}
	@XmlElement(name="treatment")
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	
	public String getComment_cnt() {
		return comment_cnt;
	}
	@XmlElement(name="comment_cnt")
	public void setComment_cnt(String comment_cnt) {
		this.comment_cnt = comment_cnt;
	}
	public String getUser_atch_file_id() {
		return user_atch_file_id;
	}
	@XmlElement(name="user_atch_file_id")
	public void setUser_atch_file_id(String user_atch_file_id) {
		this.user_atch_file_id = user_atch_file_id;
	}
	
	
	public String getMap_lat() {
		return map_lat;
	}
	@XmlElement(name="map_lat")
	public void setMap_lat(String map_lat) {
		this.map_lat = map_lat;
	}
	
	public String getMap_lng() {
		return map_lng;
	}
	@XmlElement(name="map_lng")
	public void setMap_lng(String map_lng) {
		this.map_lng = map_lng;
	}
	
	public String getHospital_address() {
		return hospital_address;
	}
	@XmlElement(name="hospital_address")
	public void setHospital_address(String hospital_address) {
		this.hospital_address = hospital_address;
	}

	
	
	

	
}
