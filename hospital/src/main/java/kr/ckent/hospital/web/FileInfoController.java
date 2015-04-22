package kr.ckent.hospital.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ckent.hospital.service.MemberInfoService;
import kr.ckent.hospital.vo.ApiResultList;
import kr.ckent.hospital.vo.CodeApiVO;
import kr.ckent.hospital.vo.FileInfoVO;
import kr.ckent.hospital.vo.MemberInfoVO;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovFileMngUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Controller
public class FileInfoController {

	protected final static Logger logger = LoggerFactory.getLogger(FileInfoController.class);


	@Resource(name="memberInfoService")
	private MemberInfoService memberInfoService;

	@Resource(name = "egovFileIdGnrService")
	private EgovIdGnrService idgenService;

	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;


	//임시용..
	private String UPLOAD_DIRECTORY = "E:/eclipse-jee-luna-R-win32/eclipse/workspace/hospital/src/main/webapp/uploadFile";
	
	

//	private String UPLOAD_DIRECTORY  = EgovProperties.getProperty("Globals.fileStorePath");

	//파일목록조회	    //memberProfileImgList
	@RequestMapping(value="/atchfileImgList.xml", method=RequestMethod.GET)
	public String atchfileImgList(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{

		List<FileInfoVO> listvo = null;
		ApiResultList<FileInfoVO> resultList = new ApiResultList<FileInfoVO>();


		Map<String, Object> param = new HashMap();

		String atch_file_id =  URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "atchfileid", ""), "UTF-8");

		param.put("atchFileId", atch_file_id);

		listvo = fileMngService.selectAllFileInf(param);

		resultList.setFlag("ok");
		resultList.setMessage("성공");		
		resultList.setTotcnt(String.valueOf(listvo.size()));
		resultList.setItemlist(listvo);

		model.addAttribute("file", resultList);

		return "rest/result";
	}
	
	
	@RequestMapping(value = "/download.do")
	public String download(HttpServletRequest request,HttpServletResponse response) {

		String fileId = ServletRequestUtils.getStringParameter(request, "fileId", "");

		try {
			String filePathToBeServed = UPLOAD_DIRECTORY + File.separator	+ fileId;

			File fileToDownload = new File(filePathToBeServed);
			InputStream inputStream = new FileInputStream(fileToDownload);

			IOUtils.copy(inputStream, response.getOutputStream());
			response.flushBuffer();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	//파일업로드(회원가입시 회원사진)
	@RequestMapping(value="/fileupload.xml", method=RequestMethod.POST)
	public String fileupload(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{

		List<MemberInfoVO> listvo = new ArrayList();
		ApiResultList<MemberInfoVO> reusltList = new ApiResultList<MemberInfoVO>();


		List<FileVO> resultFiles = null;

		String atchFileId =  URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "atchfileid", "-1"), "UTF-8");
		String fileSn =  ServletRequestUtils.getStringParameter(request, "fileSn", "");
		String memberseq =  URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "memberSeq", "-1"), "UTF-8");

		String board_idx =  URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "boardIdx", "-1"), "UTF-8");
		String category =  URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "category", ""), "UTF-8");


		//category 종류 : NEW_MEMBER, EDIT_MEMBER, NEW_BOARD

		//첨부파일 저장.
		
		if (request instanceof MultipartHttpServletRequest) {

			MultipartHttpServletRequest multiPartRequest = (MultipartHttpServletRequest)request;

			final Map<String, MultipartFile> files = multiPartRequest.getFileMap();

			if(!files.isEmpty()){
				//첨부파일 해당 폴더 위치에 저장
				
				if(category.indexOf("MEMBER") > -1){ //회원관련
					
					resultFiles = fileUtil.parseFileInf(files, "MEMBER_", Integer.valueOf(fileSn)  , atchFileId, "");

					if(category.indexOf("NEW") > -1){ //신규
						atchFileId = fileMngService.insertFileInfs(resultFiles, memberseq);	

					}else if(category.indexOf("EDIT") > -1){ //수정
						atchFileId = fileMngService.updateMemberFileInfs(resultFiles, memberseq);	
					}				
				}else if(category.indexOf("BOARD") > -1){ //게시판 관련
					//첨부파일 해당 폴더 위치에 저장
					resultFiles = fileUtil.parseFileInf(files, "BOARD_", Integer.valueOf(fileSn)  , atchFileId, "");

					if(category.indexOf("NEW") > -1)
						atchFileId = fileMngService.insertBoardFileInfs(resultFiles, board_idx);
					else if(category.indexOf("EDIT") > -1){
						atchFileId = fileMngService.updateBoardFileInfs(resultFiles, board_idx);
					}
				}
				
			}
		}		
		
		reusltList.setFlag("ok");
		reusltList.setMessage("성공");
		reusltList.setItemlist(listvo);

		model.addAttribute("post", reusltList);

		return "rest/result";
	}
	
	
	
	
	
	
	
	
	
	

//	@RequestMapping(value="/boardfileupload.xml", method=RequestMethod.POST)
//	public String boardfileupload(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{
//
//		List<CodeApiVO> listvo = new ArrayList();
//		ApiResultList<CodeApiVO> reusltList = new ApiResultList<CodeApiVO>();
//
//
//		List<FileVO> _result = null;
//
//		String _atchFileId =  URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "fileSeq", "-1"), "UTF-8");
//		String fileSn =  ServletRequestUtils.getStringParameter(request, "fileSn", "");
//		String board_idx =  URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "boardIdx", "-1"), "UTF-8");
//
//
//		//첨부파일 저장.
//		if (request instanceof MultipartHttpServletRequest) {
//
//			MultipartHttpServletRequest multiPartRequest = (MultipartHttpServletRequest)request;
//
//			final Map<String, MultipartFile> files = multiPartRequest.getFileMap();
//
//			if(!files.isEmpty()){
//				//첨부파일 해당 폴더 위치에 저장
//				_result = fileUtil.parseFileInf(files, "BOARD_", Integer.valueOf(fileSn)  , _atchFileId, "");
//
//				_atchFileId = fileMngService.insertBoardFileInfs(_result, board_idx);		
//
//			}
//		}		
//
//		reusltList.setFlag("ok");
//		reusltList.setMessage("성공");
//		reusltList.setItemlist(listvo);
//
//		model.addAttribute("post", reusltList);
//
//
//
//		return "rest/result";
//	}

	
	
	
	
	
	
	
	
	

}
