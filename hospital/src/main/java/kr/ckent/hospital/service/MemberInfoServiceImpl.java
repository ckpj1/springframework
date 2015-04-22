package kr.ckent.hospital.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.ckent.common.base.AccountIdNotFoundException;
import kr.ckent.common.base.DuplicateNicknmException;
import kr.ckent.hospital.service.dao.BoardInfoDAO;
import kr.ckent.hospital.service.dao.MemberInfoDAO;
import kr.ckent.hospital.service.dao.PointDAO;
import kr.ckent.hospital.vo.MemberInfoVO;
import kr.ckent.hospital.vo.PointManageVO;
import kr.ckent.hospital.vo.PointVO;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.impl.FileManageDAO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


@Service("memberInfoService")
public class MemberInfoServiceImpl  extends AbstractServiceImpl implements MemberInfoService{

	@Resource(name="memberInfoDAO")
	private MemberInfoDAO memberInfoDAO;

	@Resource(name = "FileManageDAO")
	private FileManageDAO fileMngDAO;

	@Resource(name="boardInfoDAO")
	private BoardInfoDAO boardInfoDAO;

	@Resource(name="pointDAO")
	private PointDAO pointDAO;


	@Override
	public List<MemberInfoVO> getMemberInfoList(Map<String, Object> param)	throws Exception {

		return memberInfoDAO.getMemberInfoList(param);
	}

	/***
	 * 회원가입
	 */
	@Override
	public List<MemberInfoVO> insertMemberInfo(Map<String, Object> param) throws Exception {

		List<MemberInfoVO> listMem = new ArrayList<MemberInfoVO>(); 
		Integer savingPoint = 0;

		//저장하기전 아이디랑 닉네임 중복체크  실시.
		MemberInfoVO duplicateVo = memberInfoDAO.getMemberDuplicationCheck(param);

		//중복체크 : 중복된 아이디 및 닉네임이 존재하면 exception 발생.
		if(duplicateVo != null){
			if(param.get("user_id").equals(duplicateVo.getUser_id())){
				throw new AccountIdNotFoundException();
			}

			if(param.get("nick_nm").equals(duplicateVo.getNick_nm())){
				throw new DuplicateNicknmException();
			}

		}else{

			memberInfoDAO.insertMemberInfo(param);

			//회원가입시  가입 포인트 적립.
			this.setMemberPointSaving(String.valueOf(param.get("user_id")), "P0102");

			//로그인한 회원 조회.
			listMem = this.getMemberInfoList(param);

			//회원가입 하면 자동로그인이 되기 떄문에 로그인 포인트도 같이 적립
			this.setMemberPointSaving(String.valueOf(param.get("user_id")), "P0101");
		}

		return listMem;
	}

	/***
	 * 회원 로그인
	 */
	@Override
	public MemberInfoVO getMemberLogin(Map<String, Object> param) 	throws Exception {

		MemberInfoVO returnMemberVO = null;

		try{

			//정상적인 로그인이 실행된 경우 로그인은 1일 1회만 적립되도록 로직 추가
			PointVO mapSavingPointCheck = memberInfoDAO.getMemberSavingPointCheck(param);	

			//오늘 로그인한 기록이 존재하면 포인트 적립 안함
			if(mapSavingPointCheck.getToday_login_cnt() <= 0) {

				//로그인 시도 후 문제 없으면 포인트 적립
				this.setMemberPointSaving(param.get("user_id").toString(), "P0101");
				
				//아래 로직 의미 없음 : 히스토리 테이블에서 전부 관리하기로 결정.
				//memberInfoDAO.updateMemberpointSaving(param);
			}
			
			//로그인 시도
			returnMemberVO = memberInfoDAO.getMemberLogin(param);			
			
		}
		catch(Exception ex)
		{
			throw ex;
		}

		return returnMemberVO;
	}

/***
 * 회원정보 수정
 */
	@Override
	public List<MemberInfoVO> updateMemberInfo(Map<String, Object> param) 	throws Exception {
		List<MemberInfoVO> listMem = null; 

		try{
			//저장하기전 닉네임 중복체크  실시.
			MemberInfoVO duplicateVo = memberInfoDAO.getNicknameDuplicationCheck(param);


			if(duplicateVo != null){

				if(param.get("nick_nm").equals(duplicateVo.getNick_nm())){
					throw new DuplicateNicknmException();
				}

			}else{

				memberInfoDAO.updateMemberInfo(param);

				listMem = memberInfoDAO.getMemberInfoList(param); 
			}			


		}catch(Exception ex){
			throw ex;
		}

		return listMem;
	}

	@Override
	public List<MemberInfoVO> getMemberProfileAtchFileIdList(Map<String, Object> param) throws Exception {

		return memberInfoDAO.getMemberProfileAtchFileIdList(param);
	}

	/***
	 * 아이디/비밀번호체크
	 */
	@Override
	public MemberInfoVO getMemberIdPwdSearch(Map<String, Object> param)
			throws Exception {
		return memberInfoDAO.getMemberIdPwdSearch(param);
	}

	
	
	/***
	 * 회원탈퇴
	 * 		회원탈퇴 순서
	 *
	 *	1. file_detail 삭제
	 * 2. file_master 삭제
	 *	3. board 삭제
 	 * 4. 포인트 삭제
   	 * 5. member 삭제
	 *
	 */
	@Override
	public void deleteMember(Map<String, Object> param) throws Exception {

		List<MemberInfoVO> listmember = memberInfoDAO.getMemberInfoList(param);

		if(listmember.size() > 0){
			MemberInfoVO memVo = listmember.get(0);

			param.clear();
			param.put("atchFileId", memVo.getAtch_file_id());

			//파일 상세 삭제
			fileMngDAO.MemberdeleteFileDetail(param);
			//파일 마스터 삭제
			fileMngDAO.MemberdeleteComtnFile(param);

			//게시판 삭제
			param.clear();
			param.put("user_id", memVo.getUser_id());
			boardInfoDAO.MemberdeleteBoard(param);
			
			//포인트 삭제
			param.clear();
			param.put("user_id", memVo.getUser_id());
			pointDAO.deletePointHistoty(param);
			
			//회원삭제
			memberInfoDAO.deleteMember(param);

		}else{
			throw new AccountIdNotFoundException();
		}

	}

	@Override
	public void setMemberPointSaving(String userId, String pointType) 	throws Exception {

		Map<String, Object> pointParam = new HashMap<String, Object>();

		Integer iPoint = 0;

		pointParam.put("user_id", userId);
		pointParam.put("point_type", pointType); 

		//포인트 타입에 대한 포인트 점수를 가져온다.
		List<PointManageVO> listPointMng = pointDAO.getPointMangeList(pointParam);

		iPoint = listPointMng.get(0).getPoint();

		pointParam.put("point", iPoint);		

		//회원가입 후  회원가입에 대한 포인트 적립.. insertPointHistory
		pointDAO.insertPointHistory(pointParam);
	
	}

	//자동로그인 포인트 적립???
	@Override
	public void setAutoLoginPointSaving(Map<String, Object> param) throws Exception {


	}

	
	/***
	 * 비밀번호 변경.
	 */
	@Override
	public void updateMemberPwdChage(Map<String, String> param) throws Exception {

		memberInfoDAO.updateMemberPwdChange(param);		
	}




}
