package kr.ckent.hospital.service;

import java.util.List;
import java.util.Map;

import kr.ckent.hospital.vo.CodeApiVO;



public interface CodeService {


	List<CodeApiVO> getCodelist(Map<String, Object> param) throws Exception;
	
	

}
