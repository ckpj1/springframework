package kr.ckent.hospital.web;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ckent.hospital.service.SiGunguService;
import kr.ckent.hospital.vo.ApiResultList;
import kr.ckent.hospital.vo.SiGunguVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SiGunguController {

	protected final static Logger logger = LoggerFactory.getLogger(SiGunguController.class);


	@Resource(name="sigunguService")
	private SiGunguService sigunguService;

	@RequestMapping(value="/sidoList.xml", method=RequestMethod.GET)
	public String sidoList(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{

		List<SiGunguVO> listvo = new ArrayList<SiGunguVO>();
		ApiResultList<SiGunguVO> reusltList = new ApiResultList<SiGunguVO>();
		Map<String, Object> param = new HashMap<String, Object>();


		listvo = sigunguService.getSidoList(param);

		reusltList.setFlag("ok");
		reusltList.setMessage("성공");		
		
		if(listvo != null && listvo.size() > 0)
			reusltList.setTotcnt(String.valueOf(listvo.size()));
		else
			reusltList.setTotcnt("0");
		
		reusltList.setItemlist(listvo);

		model.addAttribute("post", reusltList);

		return "rest/result";
	}


	@RequestMapping(value="/gunguList.xml", method=RequestMethod.GET)
	public String gunguList(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{

		List<SiGunguVO> listvo = new ArrayList<SiGunguVO>();
		ApiResultList<SiGunguVO> reusltList = new ApiResultList<SiGunguVO>();
		Map<String, Object> param = new HashMap<String, Object>();


		String si_idx =  URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "si_idx", "-1"), "UTF-8");

		param.put("si_idx", si_idx);

		listvo = sigunguService.getGunguList(param);

		reusltList.setFlag("ok");
		reusltList.setMessage("성공");
		
		if(listvo != null && listvo.size() > 0)
			reusltList.setTotcnt(String.valueOf(listvo.size()));
		else
			reusltList.setTotcnt("0");

		reusltList.setItemlist(listvo);

		model.addAttribute("post", reusltList);

		return "rest/result";
	}










}
