package kr.ckent.hospital.service.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import kr.ckent.hospital.vo.BoardInfoVO;
import kr.ckent.hospital.vo.CodeApiVO;
import kr.ckent.hospital.vo.PointHistoryVO;
import kr.ckent.hospital.vo.PointManageVO;


@Repository("pointDAO")
public class PointDAO  extends EgovAbstractDAO{



	@SuppressWarnings("unchecked")
	public List<PointHistoryVO> getPointHistoryList(Map<String, Object> param) throws Exception {
		return (List<PointHistoryVO>)list("point.getPointHistoryList", param);
	}

	public void insertPointHistory(Map<String, Object> param) throws Exception {
		insert("point.insertPointHistory" , param);
	}

	@SuppressWarnings("unchecked")
	public List<PointManageVO> getPointMangeList(Map<String, Object> param)	throws Exception {
		return (List<PointManageVO>)list("point.getPointMangeList", param);
	}

	public void updatePointManage(Map<String, Object> param) throws Exception {
		update("point.updatePointManage" , param);

	}

	public void insertPointManage(Map<String, Object> param) throws Exception {
		insert("point.insertPointManage" , param);

	}

	public void deletePointHistoty(Map<String, Object> param) throws Exception{

		delete("point.deletePointHistory",param);
	}

	@SuppressWarnings("unchecked")
	public PointHistoryVO getMemberSavingPoint(Map<String, Object> param) throws Exception{
		return (PointHistoryVO) selectByPk("point.getMemberSavingPoint", param);
	}


}
