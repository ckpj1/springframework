package kr.ckent.hospital.service;

import java.util.List;
import java.util.Map;

import kr.ckent.hospital.vo.BoardInfoVO;
import kr.ckent.hospital.vo.CodeApiVO;



public interface BoardInfoService extends BasePointService {


	List<BoardInfoVO> getBoardList(Map<String, Object> param) throws Exception;
	
	/***
	 * 후기글 작성
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Integer insertBoard(Map<String, Object> param) throws Exception;
	
	Integer getBoardLastIndex() throws Exception;
	
	void updateBoardParentIndex(Map<String, Object> param) throws Exception;
	
	List<BoardInfoVO> getBoardCommentList(Map<String, Object> param) throws Exception;

	/***
	 * 댓글 작성
	 * @param param
	 * @throws Exception
	 */
	void insertComment(Map<String, Object> param) throws Exception;
	
	List<BoardInfoVO> updateBoard(Map<String, Object> param) throws Exception;
	
	void updateComment(Map<String, Object> param) throws Exception;
	
	void deleteComment(Map<String, Object> param) throws Exception;
	
	/***
	 * 작성한 후기글 삭제
	 * @param param
	 * @throws Exception
	 */
	void deleteBoard(Map<String, Object> param) throws Exception;
	
	List<BoardInfoVO> getBoardDetail(Map<String, Object> param) throws Exception;
	
}
