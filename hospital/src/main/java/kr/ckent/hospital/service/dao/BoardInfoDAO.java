package kr.ckent.hospital.service.dao;

import java.util.List;
import java.util.Map;

import kr.ckent.hospital.vo.BoardInfoVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


@Repository("boardInfoDAO")
public class BoardInfoDAO  extends EgovAbstractDAO{

	
	@SuppressWarnings("unchecked")
	public List<BoardInfoVO> getBoardList(Map<String, Object> param)	throws Exception {
	
		return (List<BoardInfoVO>)list("board.getBoardList", param);
	}

	@SuppressWarnings("unchecked")
	public List<BoardInfoVO> getBoardDetail(Map<String, Object> param)	throws Exception {
		
		return (List<BoardInfoVO>)list("board.getBoardDetail", param);
	}

	
	public void insertBoard(Map<String, Object> param) throws Exception {
 
		insert("board.insertBoard", param);
	}


	public Integer getBoardLastIndex() throws Exception {

		return (Integer) selectByPk("board.getBoardLastIndex",null);
	}

	public void updateBoardParentIndex(Map<String, Object> param) throws Exception {
		
			update("board.updateBoardParentIndex", param);
	}


	@SuppressWarnings("unchecked")
	public List<BoardInfoVO> getBoardCommentList(Map<String, Object> param)	throws Exception {

		return list("board.getBoardCommentList", param);
	}


	public void insertComment(Map<String, Object> param) throws Exception {

		insert("board.insertComment", param);
	}


	public void updateBoard(Map<String, Object> param) throws Exception {
		
		update("board.updateBoard", param);
	}

	public void updateComment(Map<String, Object> param) throws Exception {
		
		update("board.updateComment", param);
		
	}


	public void deleteComment(Map<String, Object> param) throws Exception {
 
		delete("board.deleteComment", param);
	}

	public void deleteBoard(Map<String, Object> param) throws Exception {

		delete("board.deleteBoard", param);
	}
	
	
	/***
	 * 회원탈퇴시게시글 삭제
	 * @param param
	 * @throws Exception
	 */
	public void MemberdeleteBoard(Map<String, Object> param) throws Exception {

		delete("board.MemberdeleteBoard", param);
	}
	
	public BoardInfoVO getBoardUserId(Map<String, Object> param) throws Exception{
		
		return (BoardInfoVO) selectByPk("board.getBoardUserId", param);
	}
	
	
	
}
