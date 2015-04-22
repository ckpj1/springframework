package kr.ckent.hospital.service;

import java.util.List;
import java.util.Map;

import kr.ckent.hospital.vo.BoardInfoVO;
import kr.ckent.hospital.vo.CodeApiVO;
import kr.ckent.hospital.vo.SiGunguVO;



public interface SiGunguService {


	List<SiGunguVO> getSidoList(Map<String, Object> param) throws Exception;
	
	List<SiGunguVO> getGunguList(Map<String, Object> param) throws Exception;

}
