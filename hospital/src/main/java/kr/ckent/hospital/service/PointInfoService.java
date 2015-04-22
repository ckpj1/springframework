package kr.ckent.hospital.service;

import java.util.List;
import java.util.Map;

import kr.ckent.hospital.vo.BoardInfoVO;
import kr.ckent.hospital.vo.CodeApiVO;
import kr.ckent.hospital.vo.PointHistoryVO;
import kr.ckent.hospital.vo.PointManageVO;



public interface PointInfoService {


	List<PointHistoryVO> getPointHistoryList(Map<String, Object> param) throws Exception;
	
	void insertPointHistory(Map<String, Object> param) throws Exception;
	

	List<PointManageVO> getPointMangeList(Map<String, Object> param) throws Exception;

	void updatePointManage(Map<String, Object> param) throws Exception;

	void insertPointManage(Map<String, Object> param) throws Exception;
	
	void deletePointHistoty(Map<String, Object> param) throws Exception;

	
	PointHistoryVO getMemberSavingPoint(Map<String, Object> param) throws Exception;
	
	
}
