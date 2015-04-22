package kr.ckent.hospital.service.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import kr.ckent.common.base.AccountIdNotFoundException;
import kr.ckent.common.base.AccountPwNotEqualException;
import kr.ckent.hospital.vo.CodeApiVO;
import kr.ckent.hospital.vo.MemberInfoVO;
import kr.ckent.hospital.vo.PointVO;



@Repository("memberInfoDAO")
public class MemberInfoDAO  extends EgovAbstractDAO{

	@SuppressWarnings("unchecked")
	public List<MemberInfoVO> getMemberInfoList(Map<String, Object> param) throws Exception {

		return (List<MemberInfoVO>)list("mem.getMemberInfoList", param);
	}

	@SuppressWarnings("unchecked")
	public void insertMemberInfo(Map<String, Object> param) throws Exception {

		insert("mem.insertMember", param);		
	}


	/***
	 * 로그인
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public MemberInfoVO getMemberLogin(Map<String, Object> param) throws Exception {

		MemberInfoVO login = null;

		login =  (MemberInfoVO)selectByPk("mem.getMemberLogin", param);

		if((login == null) || (login.getUser_id() == null)) {
			// 계정 정보가 존재하지 않습니다.
			throw new AccountIdNotFoundException();
		}

		String password = (String) param.get("pwd");
		if(!password.equals(login.getPwd())) {
			// 비밀번호가 일치하지 않습니다.
			throw new AccountPwNotEqualException();
		}
		return login;
	}
	
	//중복체크
	public MemberInfoVO getMemberDuplicationCheck(Map<String, Object> param) throws Exception {

		MemberInfoVO login = null;

		login =  (MemberInfoVO)selectByPk("mem.getMemberDuplicationCheck", param);


		return login;
	}

	//getNicknameDuplicationCheck
	public MemberInfoVO getNicknameDuplicationCheck(Map<String, Object> param) throws Exception {

		MemberInfoVO login = null;

		login =  (MemberInfoVO)selectByPk("mem.getNicknameDuplicationCheck", param);


		return login;
	}
	
	
	@SuppressWarnings("unchecked")
	public void updateMemberInfo(Map<String, Object> param) throws Exception {

		insert("mem.updateMember", param);		
	}


	@SuppressWarnings("unchecked")
	public List<MemberInfoVO> getMemberProfileAtchFileIdList(Map<String, Object> param) throws Exception {

		return (List<MemberInfoVO>)list("mem.getMemberProfileAtchFileIdList", param);
	}
	
	public MemberInfoVO getMemberIdPwdSearch(Map<String, Object> param) throws Exception {
	
		MemberInfoVO login = null;
		login =  (MemberInfoVO)selectByPk("mem.getMemberIdPwdSearch", param);
		
		if((login == null) || (login.getUser_id() == null)) {
			// 계정 정보가 존재하지 않습니다.
			throw new AccountIdNotFoundException();
		}
		
		return login;

	}
	
	public void deleteMember(Map<String, Object> param) throws Exception{
		
		delete("mem.deleteMember", param);
	}
	
	
	//회원 테이블에 포인트 업데이트
	public void updateMemberpointSaving(Map<String, Object> param) throws Exception{
		
		delete("mem.updateMemberPointSaving", param);
	}
	
	/***
	 * 적립된 포인트를 가져옴.
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public PointVO getMemberSavingPointCheck(Map<String, Object> param) throws Exception{
	
		List<PointVO> listResult=  list("mem.getMemberSavingPointCheck", param);
		PointVO returnVO = new PointVO();
		
		for(PointVO vo : listResult){
			
			returnVO.setToday_tot_point(vo.getToday_tot_point());
			returnVO.setToday_login_cnt(vo.getToday_login_cnt());
		}
		
		
		return returnVO;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getMemberSavingPointCheck1(Map<String, Object> param) throws Exception{
		
		return (Map<String, Object>) selectByPk("mem.getMemberSavingPointCheck", param);
	}


	
	@SuppressWarnings("unchecked")
	public void updateMemberPwdChange(Map<String, String> param) throws Exception {

		update("mem.updateMemberPwdChange", param);		
	}

	
}
