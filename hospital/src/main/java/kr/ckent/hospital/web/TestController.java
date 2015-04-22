package kr.ckent.hospital.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

import kr.ckent.hospital.vo.ApiResultList;
import kr.ckent.hospital.vo.CodeApiVO;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovFileMngUtil;

@Controller
public class TestController {


	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;
	
	@RequestMapping(value="/memo.do", method=RequestMethod.GET)
	@ResponseBody
	public  Map list() throws Exception{

		Map result = new HashMap();

		result.put("result", "aa");

		return result;
	}

	@RequestMapping(value="/memo2.do", method=RequestMethod.GET)
	public void test(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// set page parameters
		JSONObject retObj = new JSONObject();


		//		Enumeration eParam = request.getParameterNames();
		//		 while (eParam.hasMoreElements()) {
		//		  String pName = (String)eParam.nextElement();
		//		  String pValue = request.getParameter(pName);
		//
		//		  System.out.println(pName + " : " + pValue);
		//		 }

		
		List<CodeApiVO> listvo = new ArrayList();
	
		
		for(int i = 0; i < 5; i++)
		{
			CodeApiVO vo = new CodeApiVO();
			
			vo.setCode("aa");
			vo.setCode_nm("aa");
			
			listvo.add(vo);
		}
		
		retObj.put("rows",	  listvo);

		// response
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		writer.print(JSONValue.toJSONString(retObj));
	}
	
	@RequestMapping(value="/memo3.do", method=RequestMethod.GET)
	public ModelAndView test3(HttpServletRequest request, HttpServletResponse response) throws Exception{

		List<CodeApiVO> listvo = new ArrayList();
	
		ModelAndView mav = new ModelAndView();
		
		for(int i = 0; i < 5; i++)
		{
			CodeApiVO vo = new CodeApiVO();
			
			vo.setCode("aa");
			vo.setCode_nm("aa");
			
			listvo.add(vo);
		}
		
		mav.addObject("list", listvo);
		mav.setViewName("jsonView");
		
		return mav;
		
	}

	

	@RequestMapping(value="/memo4.do", method=RequestMethod.GET)
	public ModelAndView test4(HttpServletRequest request, HttpServletResponse response) throws Exception{

		List<CodeApiVO> listvo = new ArrayList();
		
		ModelAndView mav = new ModelAndView();
		
//		for(int i = 0; i < 5; i++)
//		{
//			CodeAPIVO vo = new CodeAPIVO();
//			
//			vo.setCode("aa");
//			vo.setCode_nm("aa");
//			
//			listvo.add(vo);
//		}
//		
//		mav.addObject("xmlData", listvo);
//		mav.setViewName("xmlView");
//		
		return mav;
	}
	
	

	@RequestMapping(value="/memo5.xml", method=RequestMethod.GET)
	public String test5(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{

		List<CodeApiVO> listvo = new ArrayList();
		ApiResultList<CodeApiVO> reusltList = new ApiResultList<CodeApiVO>();
	
		CodeApiVO vo= null;
		
		for(int i = 0; i < 5; i++)
		{
			 vo = new CodeApiVO();
			
			vo.setCode("aa");
			vo.setCode_nm("aa");
			
			listvo.add(vo);
		}
		
		reusltList.setFlag("ok");
		reusltList.setMessage("성공");
		reusltList.setItemlist(listvo);
		
		model.addAttribute("post", reusltList);
		

		
		return "rest/result";
	}
	
	private final String UPLOAD_DIRECTORY = "D:/usr";
	
	@RequestMapping(value="/memo22.do", method=RequestMethod.GET)
	public void download2(HttpServletRequest request,HttpServletResponse response ) {

		try {
			String filePathToBeServed = UPLOAD_DIRECTORY + File.separator
					+ "11.jpg";
			System.out.println(filePathToBeServed);
			File fileToDownload = new File(filePathToBeServed);
			InputStream inputStream = new FileInputStream(fileToDownload);

			IOUtils.copy(inputStream, response.getOutputStream());
			response.flushBuffer();

		} catch (Exception e) {
			e.printStackTrace();
		}
//		return null;
	}
	
	
	
//	//첨부파일 조회
//	if(detailVO.getAtchFileId() != null){
//
//		FileVO fvo = new FileVO();
//		fvo.setAtchFileId(detailVO.getAtchFileId());
//
//		FileList = fileMngService.selectFileInfs(fvo);
//	}
//
//
//					<a href="#" onclick="window.open(encodeURI('<c:url value='/cmm/FileDown.do?'/>atchFileId=${file.atchFileId}&fileSn=${file.fileSn}'))"><c:out
//									value="${file.orignlFileNm}" /></a>&nbsp;(<c:out value="${file.fileMg}" /> KB)
	
	
	
}
