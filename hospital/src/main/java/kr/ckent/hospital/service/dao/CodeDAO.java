package kr.ckent.hospital.service.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import kr.ckent.hospital.vo.CodeApiVO;


@Repository("codeDAO")
public class CodeDAO  extends EgovAbstractDAO{

	@SuppressWarnings("unchecked")
	public List<CodeApiVO> getCodelist(Map param) throws Exception {

		return (List<CodeApiVO>)list("code.getCodeList", param);
	}
}
