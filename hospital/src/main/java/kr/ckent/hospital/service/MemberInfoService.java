package kr.ckent.hospital.service;

import java.util.List;
import java.util.Map;

import kr.ckent.hospital.service.dao.MemberInfoDAO;
import kr.ckent.hospital.vo.CodeApiVO;
import kr.ckent.hospital.vo.MemberInfoVO;



public interface MemberInfoService extends BasePointService{


	List<MemberInfoVO>  getMemberInfoList(Map<String, Object> param) throws Exception;
	
	List<MemberInfoVO>  insertMemberInfo(Map<String, Object> param) throws Exception;
	
	MemberInfoVO  getMemberLogin(Map<String, Object> param) throws Exception;
	
	List<MemberInfoVO>  updateMemberInfo(Map<String, Object> param) throws Exception;
	
	List<MemberInfoVO>  getMemberProfileAtchFileIdList(Map<String, Object> param) throws Exception;
	
	MemberInfoVO getMemberIdPwdSearch(Map<String, Object> param) throws Exception;
	
	void deleteMember(Map<String, Object> param) throws Exception;
	
	
	/***
	 * 회원이 자동로그인인 상태인 경우 1일 1회 로그인 적립을 시도.
	 * @throws Exception
	 */
	void  setAutoLoginPointSaving(Map<String, Object> param) throws Exception;
	
	void updateMemberPwdChage(Map<String, String> param) throws Exception;
	
	
}

