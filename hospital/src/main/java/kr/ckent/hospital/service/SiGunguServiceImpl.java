package kr.ckent.hospital.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import kr.ckent.hospital.service.dao.CodeDAO;
import kr.ckent.hospital.service.dao.MemberInfoDAO;
import kr.ckent.hospital.service.dao.SiGunguDAO;
import kr.ckent.hospital.vo.CodeApiVO;
import kr.ckent.hospital.vo.MemberInfoVO;
import kr.ckent.hospital.vo.SiGunguVO;


@Service("sigunguService")
public class SiGunguServiceImpl  extends AbstractServiceImpl implements SiGunguService{

	@Resource(name="sigunguDAO")
	private SiGunguDAO sigunguDAO;

	@Override
	public List<SiGunguVO> getSidoList(Map<String, Object> param)	throws Exception {

		return sigunguDAO.getSidoList(param);
	}

	@Override
	public List<SiGunguVO> getGunguList(Map<String, Object> param) throws Exception {

		return sigunguDAO.getGunguList(param);
	}



}
