package kr.ckent.hospital.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.ckent.hospital.service.dao.PointDAO;
import kr.ckent.hospital.vo.PointHistoryVO;
import kr.ckent.hospital.vo.PointManageVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("pointInfoService")
public class PointInfoServiceImpl extends AbstractServiceImpl implements PointInfoService{


	@Resource(name="pointDAO")
	private PointDAO pointDAO;
	
	@Override
	public List<PointHistoryVO> getPointHistoryList(Map<String, Object> param)	throws Exception {
		
		return pointDAO.getPointHistoryList(param);
	}

	@Override
	public void insertPointHistory(Map<String, Object> param) throws Exception {
		pointDAO.insertPointHistory(param);
		
	}

	@Override
	public List<PointManageVO> getPointMangeList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return pointDAO.getPointMangeList(param);
	}

	@Override
	public void updatePointManage(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		pointDAO.updatePointManage(param);
	}

	@Override
	public void insertPointManage(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		pointDAO.insertPointManage(param);
	}

	@Override
	public void deletePointHistoty(Map<String, Object> param) throws Exception {
		pointDAO.deletePointHistoty(param);
		
	}

	@Override
	public PointHistoryVO getMemberSavingPoint(Map<String, Object> param) 	throws Exception {

		return pointDAO.getMemberSavingPoint(param);
	}

}
