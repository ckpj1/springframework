package kr.ckent.hospital.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import kr.ckent.hospital.service.dao.CodeDAO;
import kr.ckent.hospital.vo.CodeApiVO;


@Service("codeService")
public class CodeServiceImpl  extends AbstractServiceImpl implements CodeService{

	@Resource(name="codeDAO")
	private CodeDAO codeDAO;
	
	@Override
	public List<CodeApiVO> getCodelist(Map<String, Object> param) throws Exception {

		return codeDAO.getCodelist(param);
	}

}
