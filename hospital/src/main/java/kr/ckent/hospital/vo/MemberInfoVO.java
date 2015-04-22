package kr.ckent.hospital.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import kr.ckent.common.base.BaseObject;

@SuppressWarnings("serial")
@XmlRootElement(name="member")
@XmlType(name="member"
				, namespace="kr.ckent.hospital.vo.MemberInfoVO"
				, propOrder={"user_id", "pwd", "nick_nm", "intro", "sido", "gungu", "age", "login_cnt", "atch_file_id", 
								   "reg_dt", "profile_url","logingfailsuccessyn","login_result_msg", "board_cnt", "comment_cnt", 
								   "sido_code","gungu_code", "phone_num", "point"})
public class MemberInfoVO  extends BaseObject{


	private String user_id;
	private String pwd;
	private String nick_nm;
	private String intro;
	private String sido;
	private String gungu;
	private String age;
	private Integer login_cnt;
	private String atch_file_id;
	private String reg_dt;
	private String profile_url;
	//로그인 성공여부 플래그
	private String logingfailsuccessyn;
	private String login_result_msg;
	private String board_cnt;
	private String comment_cnt;
	private String sido_code;
	private String gungu_code;
	private String phone_num;
	private Integer point;
	
	
	public String getUser_id() {
		return user_id;
	}
	@XmlElement(name="user_id")
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPwd() {
		return pwd;
	}
	@XmlElement(name="pwd")
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNick_nm() {
		return nick_nm;
	}
	@XmlElement(name="nick_nm")
	public void setNick_nm(String nick_nm) {
		this.nick_nm = nick_nm;
	}
	public String getIntro() {
		return intro;
	}
	@XmlElement(name="intro")
	public void setIntro(String intro) {
		this.intro = intro;
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
	public String getAge() {
		return age;
	}
	@XmlElement(name="age")
	public void setAge(String age) {
		this.age = age;
	}
	public Integer getLogin_cnt() {
		return login_cnt;
	}
	@XmlElement(name="login_cnt")
	public void setLogin_cnt(Integer login_cnt) {
		this.login_cnt = login_cnt;
	}
	public String getAtch_file_id() {
		return atch_file_id;
	}
	@XmlElement(name="atch_file_id")
	public void setAtch_file_id(String atch_file_id) {
		this.atch_file_id = atch_file_id;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	@XmlElement(name="reg_dt")
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	
	public String getProfile_url() {
		return profile_url;
	}
	@XmlElement(name="profile_url")
	public void setProfile_url(String profile_url) {
		this.profile_url = profile_url;
	}
	
	public String getLogingfailsuccessyn() {
		return logingfailsuccessyn;
	}
	@XmlElement(name="logingfailsuccessyn")
	public void setLogingfailsuccessyn(String logingfailsuccessyn) {
		this.logingfailsuccessyn = logingfailsuccessyn;
	}
	public String getLogin_result_msg() {
		return login_result_msg;
	}
	
	@XmlElement(name="login_result_msg")
	public void setLogin_result_msg(String login_result_msg) {
		this.login_result_msg = login_result_msg;
	}
	
	
	public String getBoard_cnt() {
		return board_cnt;
	}
	@XmlElement(name="board_cnt")
	public void setBoard_cnt(String board_cnt) {
		this.board_cnt = board_cnt;
	}
	
	
	public String getComment_cnt() {
		return comment_cnt;
	}
	@XmlElement(name="comment_cnt")
	public void setComment_cnt(String comment_cnt) {
		this.comment_cnt = comment_cnt;
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
	public String getPhone_num() {
		return phone_num;
	}
	@XmlElement(name="phone_num")
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	public Integer getPoint() {
		return point;
	}
	@XmlElement(name="point")
	public void setPoint(Integer point) {
		this.point = point;
	}

	
	
	

	
}
