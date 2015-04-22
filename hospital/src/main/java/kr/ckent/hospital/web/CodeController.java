package kr.ckent.hospital.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ckent.hospital.service.CodeService;
import kr.ckent.hospital.vo.ApiResultList;
import kr.ckent.hospital.vo.CodeApiVO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CodeController {
	
	@Resource(name="codeService")
	private CodeService codeService;

	@RequestMapping(value="/code.xml", method=RequestMethod.GET)
	public String code(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{

		List<CodeApiVO> listvo = null;
		ApiResultList<CodeApiVO> resultList = new ApiResultList<CodeApiVO>();

		Map<String, Object> param = new HashMap();
		param.put("group_code", "A01");	
		
		listvo = codeService.getCodelist(param);
		
		resultList.setFlag("ok");
		resultList.setMessage("성공");
		resultList.setTotcnt(String.valueOf(listvo.size())); 
		resultList.setItemlist(listvo);
		
		model.addAttribute("post", resultList);
		
		return "rest/result";
	}
	
}
