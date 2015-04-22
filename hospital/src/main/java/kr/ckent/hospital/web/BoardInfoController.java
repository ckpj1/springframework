package kr.ckent.hospital.web;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ckent.hospital.service.BoardInfoService;
import kr.ckent.hospital.vo.ApiResultList;
import kr.ckent.hospital.vo.BoardInfoVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.util.EgovFileMngUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Controller
public class BoardInfoController {

	protected final static Logger logger = LoggerFactory.getLogger(BoardInfoController.class);


	@Resource(name="boardInfoService")
	private BoardInfoService boardInfoService;

	@Resource(name = "egovFileIdGnrService")
	private EgovIdGnrService idgenService;

	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;

	/***
	 * 게시글 조회
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/boardlist.xml", method=RequestMethod.GET)
	public String boardlist(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{

		List<BoardInfoVO> listvo = null;
		ApiResultList<BoardInfoVO> resultList = new ApiResultList<BoardInfoVO>();

		Integer startIdx =  ServletRequestUtils.getIntParameter(request, "start", 0);
		Integer limit = ServletRequestUtils.getIntParameter(request, "limit", 15);
		String catalog_type = ServletRequestUtils.getStringParameter(request, "catalog", "");
		String searchKeyword = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "searchKeyword", ""), "UTF-8");

		Map<String, Object> param = new HashMap<String, Object>();
		//차후에 조회조건 초가
		param.put("startidx", startIdx);
		param.put("limit", limit);
		param.put("catalog_type",catalog_type);
		param.put("searchKeyword",searchKeyword);

		listvo = boardInfoService.getBoardList(param);

		resultList.setFlag("ok");
		resultList.setMessage("성공");
		resultList.setTotcnt(String.valueOf(listvo.size())); 
		resultList.setItemlist(listvo);

		model.addAttribute("post", resultList);

		return "rest/result";
	}


	/***
	 * 게시글 저장
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/boardInsert.xml", method=RequestMethod.GET)
	public String boardInsert(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{

		ApiResultList<BoardInfoVO> resultList = new ApiResultList<BoardInfoVO>();

		Map<String, Object> param = new HashMap<String, Object>();
		Integer board_idx = 0;

		String catalog_type =  URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "catalog_type", ""), "UTF-8");
		String sido = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "sido", ""), "UTF-8");
		String gungu = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "gungu", ""), "UTF-8");
		String treatment = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "treatment", ""), "UTF-8"); //치료부위
		String contents = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "contents", ""), "UTF-8");
		String user_id = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "user_id", ""), "UTF-8");
		
		String map_lat = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "map_lat", ""), "UTF-8");
		String map_lng = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "map_lng", ""), "UTF-8");
		String hospital_address = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "hospital_address", ""), "UTF-8");
		
		
		String file_yn = ServletRequestUtils.getStringParameter(request, "fileYn", "");
		Integer point = ServletRequestUtils.getIntParameter(request, "point", 0);

		param.put("catalog_type", catalog_type);
		param.put("sido", sido);
		param.put("gungu", gungu);
		param.put("treatment", treatment);
		param.put("contents", contents);
		param.put("user_id", user_id);
		param.put("map_lat", map_lat);
		param.put("map_lng", map_lng);
		param.put("hospital_address", hospital_address);

		param.put("point", point);
		

		try{

				board_idx = boardInfoService.insertBoard(param);

				//게시글일 경우 파일 아이디 조회
				BoardInfoVO tmpvo = new BoardInfoVO();
				List<BoardInfoVO> tmplist = new ArrayList<BoardInfoVO>();
				String atchfileid = "-1";

				if(file_yn.equals("Y")){
					atchfileid = idgenService.getNextStringId();
					tmpvo.setAtch_file_id(atchfileid);
				}
				
				tmpvo.setBoard_idx(board_idx);
				
				tmplist.add(tmpvo);
				
				resultList.setFlag("ok");		
				resultList.setMessage("성공");	
				resultList.setItemlist(tmplist);


		}
		catch(Exception ex){
			resultList.setFlag("fail");
			resultList.setMessage(ex.getMessage());		
		}finally{

			model.addAttribute("member", resultList);

		}						

		return "rest/result";
	}

	/***
	 * 게시글 삭제
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/boardDelete.xml", method=RequestMethod.GET)
	public String boardDelete(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{

		ApiResultList<BoardInfoVO> resultList = new ApiResultList<BoardInfoVO>();

		Map<String, Object> param = new HashMap<String, Object>();

		String board_type = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "board_type", ""), "UTF-8"); //게시글/댓글 타입
		String board_idx = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "board_idx", "0"), "UTF-8");
		
		//2014.07.27 추가 : 게시글 삭제시에 적립된 포인트와 아이디를 추가로 받아옴.
		Integer point = ServletRequestUtils.getIntParameter(request, "point", 0);
		String user_id = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "user_id", ""), "UTF-8");
		
		param.put("board_type", board_type);
		param.put("board_idx", board_idx);	
		param.put("point", point);
		param.put("user_id", user_id);

		try{
			//게시글과 댓글을 삭제한다.
			boardInfoService.deleteBoard(param);

			resultList.setFlag("ok");
			resultList.setMessage("성공");		
		}
		catch(Exception ex){
			resultList.setFlag("fail");
			resultList.setMessage(ex.getMessage());		
		}finally{

			model.addAttribute("member", resultList);

		}						

		return "rest/result";
	}

	/***
	 * 게시글/댓글 수정
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/boardUpdate.xml", method=RequestMethod.GET)
	public String boardUpdate(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{

		ApiResultList<BoardInfoVO> resultList = new ApiResultList<BoardInfoVO>();
		
		Map<String, Object> param = new HashMap<String, Object>();

		String board_idx = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "board_idx", "0"), "UTF-8"); 
		String sido = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "sido", "0"), "UTF-8"); 
		String gungu = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "gungu", "0"), "UTF-8"); 
		String catalog_type = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "catalog_type", "0"), "UTF-8"); 
		String treatment = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "treatment", ""), "UTF-8"); //치료부위
		String contents = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "contents", ""), "UTF-8");

		param.put("board_idx", board_idx);
		param.put("sido", sido);
		param.put("gungu", gungu);
		param.put("catalog_type", catalog_type);
		param.put("treatment", treatment);
		param.put("contents", contents);

		try{
			
			List<BoardInfoVO> resultvo =  boardInfoService.updateBoard(param);

			resultList.setFlag("ok");
			resultList.setTotcnt("0");
			resultList.setMessage("성공");		
			resultList.setItemlist(resultvo);
			
		}
		catch(Exception ex){
			resultList.setFlag("fail");
			resultList.setTotcnt("0");
			resultList.setMessage(ex.getMessage());		
		}finally{
			model.addAttribute("member", resultList);
		}						

		return "rest/result";
	}

	/***
	 * 상세 게시글 보기
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/boardDetail.xml", method=RequestMethod.GET)
	public String boardDetail(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{

		List<BoardInfoVO> listvo = null;
		ApiResultList<BoardInfoVO> resultList = new ApiResultList<BoardInfoVO>();


		String catalog_type = ServletRequestUtils.getStringParameter(request, "catalog_type", "");
		String board_idx = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "board_idx", ""), "UTF-8");

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("board_idx",board_idx);

		listvo = boardInfoService.getBoardDetail(param);

		resultList.setFlag("ok");
		resultList.setMessage("성공");
		resultList.setTotcnt(String.valueOf(listvo.size())); 
		resultList.setItemlist(listvo);

		model.addAttribute("post", resultList);

		return "rest/result";
	}

	
//---------------------------------아래부터 댓글 관련 -------------------------------------------------------------

	/***
	 * 댓글 리스트
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/commentList.xml", method=RequestMethod.GET)
	public String commentList(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{

		List<BoardInfoVO> listvo = null;
		ApiResultList<BoardInfoVO> resultList = new ApiResultList<BoardInfoVO>();

		Integer startIdx =  ServletRequestUtils.getIntParameter(request, "start", 0);
		Integer limit = ServletRequestUtils.getIntParameter(request, "limit", 15);
		String board_idx = ServletRequestUtils.getStringParameter(request, "board_idx", "-1");

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("startidx", startIdx);
		param.put("limit", limit);
		param.put("board_idx",board_idx);

		listvo = boardInfoService.getBoardCommentList(param);

		resultList.setFlag("ok");
		resultList.setMessage("성공");
		resultList.setTotcnt(String.valueOf(listvo.size())); 
		resultList.setItemlist(listvo);

		model.addAttribute("post", resultList);

		return "rest/result";
	}

	
	/***
	 * 댓글 수정
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/commentUpdate.xml", method=RequestMethod.GET)
	public String commentUpdate(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{

		ApiResultList<BoardInfoVO> resultList = new ApiResultList<BoardInfoVO>();

		Map<String, Object> param = new HashMap<String, Object>();

		String board_idx = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "board_idx", "0"), "UTF-8"); 
		String contents = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "contents", ""), "UTF-8");

		param.put("board_idx", board_idx);
		param.put("contents", contents);

		try{
			
			boardInfoService.updateComment(param);

			resultList.setFlag("ok");
			resultList.setTotcnt("0");
			resultList.setMessage("성공");		
		}
		catch(Exception ex){
			resultList.setFlag("fail");
			resultList.setTotcnt("0");
			resultList.setMessage(ex.getMessage());		
		}finally{

			model.addAttribute("member", resultList);

		}						

		return "rest/result";
	}
	

	@RequestMapping(value="/commentInsert.xml", method=RequestMethod.GET)
	public String commentInsert(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{

		ApiResultList<BoardInfoVO> resultList = new ApiResultList<BoardInfoVO>();

		Map<String, Object> param = new HashMap<String, Object>();


		String catalog_type =  URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "catalog_type", ""), "UTF-8");
		String sido = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "sido", ""), "UTF-8");
		String gungu = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "gungu", ""), "UTF-8");
		String contents = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "contents", ""), "UTF-8");
		String user_id = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "user_id", ""), "UTF-8");
		String parent_idx = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "parent_idx", "0"), "UTF-8"); 

		Integer point = ServletRequestUtils.getIntParameter(request, "point", 0);

		param.put("catalog_type", catalog_type);
		param.put("sido", sido);
		param.put("gungu", gungu);
		param.put("contents", contents);
		param.put("user_id", user_id);
		param.put("point", point);

		try{

				param.put("parent_idx", parent_idx);

				boardInfoService.insertComment(param);

				resultList.setFlag("ok");		
				resultList.setMessage("성공");	

		}
		catch(Exception ex){
			resultList.setFlag("fail");
			resultList.setMessage(ex.getMessage());		
		}finally{

			model.addAttribute("member", resultList);

		}						

		return "rest/result";
	}


}
