package kr.ckent.hospital.service.dao;

import java.util.List;
import java.util.Map;

import kr.ckent.hospital.vo.BoardInfoVO;
import kr.ckent.hospital.vo.SiGunguVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


@Repository("sigunguDAO")
public class SiGunguDAO  extends EgovAbstractDAO{

	
	@SuppressWarnings("unchecked")
	public List<SiGunguVO> getSidoList(Map<String, Object> param)	throws Exception {

		return list("sido.getSidoList", param);
	}
	

	@SuppressWarnings("unchecked")
	public List<SiGunguVO> getGunguList(Map<String, Object> param) throws Exception {


		return list("sido.getGunguList", param);
	}



	
}
