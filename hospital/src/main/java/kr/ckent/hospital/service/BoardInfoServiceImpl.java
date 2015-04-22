package kr.ckent.hospital.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import kr.ckent.hospital.service.dao.BoardInfoDAO;
import kr.ckent.hospital.service.dao.CodeDAO;
import kr.ckent.hospital.service.dao.MemberInfoDAO;
import kr.ckent.hospital.service.dao.PointDAO;
import kr.ckent.hospital.vo.BoardInfoVO;
import kr.ckent.hospital.vo.CodeApiVO;
import kr.ckent.hospital.vo.PointManageVO;
import kr.ckent.hospital.vo.PointVO;


@Service("boardInfoService")
public class BoardInfoServiceImpl  extends AbstractServiceImpl implements BoardInfoService{

	@Resource(name="boardInfoDAO")
	private BoardInfoDAO boardInfoDAO;

	@Resource(name="pointDAO")
	private PointDAO pointDAO;


	@Resource(name="memberInfoDAO")
	private MemberInfoDAO memberInfoDAO;

	@Override
	public List<BoardInfoVO> getBoardList(Map<String, Object> param)
			throws Exception {

		return boardInfoDAO.getBoardList(param);
	}

	/***
	 * 신규 후기 작성
	 */
	@Override
	public Integer insertBoard(Map<String, Object> param) throws Exception {

		try{

			//글 저장.
			boardInfoDAO.insertBoard(param);


			//max값 조회
			Integer lastIndex = boardInfoDAO.getBoardLastIndex();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("board_idx", lastIndex);

			boardInfoDAO.updateBoardParentIndex(map);			


			//1일 적립이 최대 200점을 넘을경우 포인트 적립 불가
			PointVO mapSavingPointCheck = memberInfoDAO.getMemberSavingPointCheck(param);	

			if(mapSavingPointCheck.getToday_tot_point() < 200){ //200보다 크면 적립 불가, 200보다 작으면 199점이라도 무조건 적립..

				this.setMemberPointSaving(String.valueOf(param.get("user_id")), "P0104");			// P0104:후기글
			}

			return lastIndex;

		}catch(Exception ex){
			throw ex;
		}

	}


	@Override
	public Integer getBoardLastIndex() throws Exception {

		return boardInfoDAO.getBoardLastIndex();
	}


	@Override
	public void updateBoardParentIndex(Map<String, Object> param) throws Exception {

		boardInfoDAO.updateBoardParentIndex(param);
	}

	@Override
	public List<BoardInfoVO> getBoardCommentList(Map<String, Object> param) 	throws Exception {

		return boardInfoDAO.getBoardCommentList(param);
	}

	
	/***
	 * 댓글 작성
	 */
	@Override
	public void insertComment(Map<String, Object> param) throws Exception {


		try{
			//댓글 작성
			boardInfoDAO.insertComment(param);

			//1일 적립이 최대 200점을 넘을경우 포인트 적립 불가
			PointVO mapSavingPointCheck = memberInfoDAO.getMemberSavingPointCheck(param);	

			if(mapSavingPointCheck.getToday_tot_point() < 200){ //50보다 크면 적립 불가, 200보다 작으면 199점이라도 무조건 적립..

				this.setMemberPointSaving(String.valueOf(param.get("user_id")), "P0103" ); 		// P0103:댓글
			}
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	public List<BoardInfoVO> updateBoard(Map<String, Object> param) throws Exception {

		boardInfoDAO.updateBoard(param);

		List<BoardInfoVO> resultvo = boardInfoDAO.getBoardDetail(param);

		return resultvo;
	}


	@Override
	public void updateComment(Map<String, Object> param) throws Exception {

		boardInfoDAO.updateComment(param);
	}

	//사용안함
	@Override
	public void deleteComment(Map<String, Object> param) throws Exception {

		boardInfoDAO.deleteComment(param);
	}

	/***
	 * 후기 / 댓글 삭제
	 * 후기 삭제 : P0106
	 * 댓글 삭제 : P0107
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteBoard(Map<String, Object> param) throws Exception {

		String point_type = "";

		try{		
			
			//삭제전 게시글의 작성자 가져오기
			BoardInfoVO vo = boardInfoDAO.getBoardUserId(param);
			
			 param.put("user_id", vo.getUser_id());
			
			if(param.get("board_type").equals("board")){ //후기인 경우 게시글을 포함한 모든 댓글 삭제
				boardInfoDAO.deleteBoard(param);

				point_type = "P0106"; //후기삭제

			}
			else{ //댓글인경우 댓글 삭제
				boardInfoDAO.deleteComment(param);

				point_type = "P0107"; //댓글 삭제
			}

			PointVO mapSavingPointCheck = memberInfoDAO.getMemberSavingPointCheck(param);	

			if(mapSavingPointCheck.getToday_tot_point() < 200){ //200보다 크면 적립 불가, 200보다 작으면 199점이라도 무조건 적립..

				//포인트 히스토리 테이블에 적립내용 저장.
				this.setMemberPointSaving(String.valueOf(param.get("user_id")), point_type);
			}

		}catch(Exception ex)
		{
			throw ex;
		}
	}

	@Override
	public List<BoardInfoVO> getBoardDetail(Map<String, Object> param)	throws Exception {

		return boardInfoDAO.getBoardDetail(param);
	}

	@Override
	public void setMemberPointSaving(String userId, String pointType) throws Exception {


		Map<String, Object> pointParam = new HashMap<String, Object>();

		//		Integer savingPoint = 0;
		Integer iPoint = 0;


		pointParam.put("user_id", userId);
		pointParam.put("point_type", pointType); 

		//포인트 타입에 대한 포인트 점수를 가져온다.
		List<PointManageVO> listPointMng = pointDAO.getPointMangeList(pointParam);

		iPoint = listPointMng.get(0).getPoint();

		pointParam.put("point", iPoint);		

		//회원가입 후  회원가입에 대한 포인트 적립.. insertPointHistory
		pointDAO.insertPointHistory(pointParam);

		//포인트 누적 점수 계산 후 적립 업데이트
		//		savingPoint = originalPoint + iPoint;		
		//
		//		return savingPoint;
	}


}
