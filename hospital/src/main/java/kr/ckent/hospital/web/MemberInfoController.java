package kr.ckent.hospital.web;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ckent.common.base.AccountIdNotFoundException;
import kr.ckent.common.base.AccountPwNotEqualException;
import kr.ckent.common.base.DuplicateNicknmException;
import kr.ckent.hospital.service.MemberInfoService;
import kr.ckent.hospital.service.PointInfoService;
import kr.ckent.hospital.vo.ApiResultList;
import kr.ckent.hospital.vo.MemberInfoVO;
import kr.ckent.hospital.vo.PointHistoryVO;

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
public class MemberInfoController {

	protected final static Logger logger = LoggerFactory.getLogger(MemberInfoController.class);


	@Resource(name="memberInfoService")
	private MemberInfoService memberInfoService;

	@Resource(name = "egovFileIdGnrService")
	private EgovIdGnrService idgenService;

	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name="pointInfoService")
	private PointInfoService pointInfoService;

	/***
	 * 회원가입
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/memberRegister.xml", method=RequestMethod.GET)
	public String memberRegister(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{

		List<MemberInfoVO> listvo = null;
		ApiResultList<MemberInfoVO> resultList = new ApiResultList<MemberInfoVO>();

		Map<String, Object> param = new HashMap();

		String user_id =  URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "user_id", ""), "UTF-8");
		String pwd = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "pwd", "1111"), "UTF-8");
		String nick_nm = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "nick_nm", ""), "UTF-8");
		String intro = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "intro", ""), "UTF-8");
		String sido = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "sido", ""), "UTF-8");
		String gungu = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "gungu", ""), "UTF-8");
		String age = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "age", ""), "UTF-8");
		String phone_num = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "phone_num", ""), "UTF-8");
		String file_yn = ServletRequestUtils.getStringParameter(request, "fileYn", "");

		param.put("user_id", user_id);
		param.put("pwd", pwd);
		param.put("nick_nm", nick_nm);
		param.put("intro", intro);
		param.put("sido", sido);
		param.put("gungu", gungu);
		param.put("age", age);
		param.put("phone_num", phone_num);


		try{
			listvo = memberInfoService.insertMemberInfo(param);

			//파일이 존재할 경우 파일저장에 사용될 nextid 전송.
			String atchfileid = "-1";

			if(file_yn.equals("Y")){
				atchfileid = idgenService.getNextStringId();

				MemberInfoVO tmpvo = listvo.get(0);		 		
				listvo.clear();

				tmpvo.setAtch_file_id(atchfileid);

				listvo.add(tmpvo) ;
			}
			resultList.setFlag("ok");
			resultList.setMessage("로그인 성공");		
		}
		catch(AccountIdNotFoundException ex){

			resultList.setFlag("fail");
			resultList.setMessage("중복된 아이디가 존재합니다.");		
		}
		catch(DuplicateNicknmException ex){
			resultList.setFlag("fail");
			resultList.setMessage("중복된 닉네임이 존재합니다.");		
		}
		catch (Exception ex) {
			resultList.setFlag("fail");
			resultList.setMessage("시스템 에러 발생 ");		
		}
		finally{

			resultList.setItemlist(listvo);
			model.addAttribute("member", resultList);
		}


		return "rest/result";
	}


	/***
	 * 로그인
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/memberlogin.xml", method=RequestMethod.GET)
	public String memberlogin(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{

		List<MemberInfoVO> listvo = new ArrayList<MemberInfoVO>();

		ApiResultList<MemberInfoVO> resultList = new ApiResultList<MemberInfoVO>();

		Map<String, Object> param = new HashMap();
		MemberInfoVO login = null;
		String user_id =  URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "user_id", ""), "UTF-8");
		String pwd = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "pwd", ""), "UTF-8");

		param.put("user_id", user_id);
		param.put("pwd", pwd);

		try{
			//로그인 호출
			login = memberInfoService.getMemberLogin(param);

			resultList.setFlag("ok");
			resultList.setMessage("로그인 성공");		
		}
		catch(AccountIdNotFoundException ex){

			resultList.setFlag("fail");
			resultList.setMessage("아이디가 존재하지 않습니다.");		
		}
		catch(AccountPwNotEqualException ex){
			resultList.setFlag("fail");
			resultList.setMessage("비밀번호가 일치하지 않습니다.");		
		}
		catch (Exception ex) {
			resultList.setFlag("fail");
			resultList.setMessage("시스템 에러 발생");		
		}
		finally{
			listvo.add(login);

			resultList.setTotcnt(String.valueOf(listvo.size()));
			resultList.setItemlist(listvo);
			model.addAttribute("member", resultList);
		}

		return "rest/result";
	}



	//회원정보 수정
	@RequestMapping(value="/memberModify.xml", method=RequestMethod.GET)
	public String memberModify(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{

		List<MemberInfoVO> listvo = null;
		ApiResultList<MemberInfoVO> resultList = new ApiResultList<MemberInfoVO>();

		Map<String, Object> param = new HashMap();

		String user_id =  URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "user_id", ""), "UTF-8");
		String nick_nm = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "nick_nm", ""), "UTF-8");
		String intro = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "intro", ""), "UTF-8");
		String sido = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "sido", ""), "UTF-8");
		String gungu = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "gungu", ""), "UTF-8");
		String age = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "age", ""), "UTF-8");
		String phone_num = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "phone_num", ""), "UTF-8");

		param.put("user_id", user_id);
		param.put("nick_nm", nick_nm);
		param.put("intro", intro);
		param.put("sido", sido);
		param.put("gungu", gungu);
		param.put("age", age);
		param.put("phone_num", phone_num);

	
		try{
			listvo = memberInfoService.updateMemberInfo(param);
			
			resultList.setFlag("ok");
			resultList.setMessage("로그인 성공");		
		}
		catch(DuplicateNicknmException ex){
			resultList.setFlag("fail");
			resultList.setMessage("중복된 닉네임이 존재합니다.");		
		}
		catch (Exception ex) {
			resultList.setFlag("fail");
			resultList.setMessage("시스템 에러 발생 ㅡ.ㅡ ");		
		}
		finally{

			resultList.setItemlist(listvo);
			model.addAttribute("member", resultList);
		}

		return "rest/result";
	}



	//비밀번호 찾기
	@RequestMapping(value="/memberIdPwdSearch.xml", method=RequestMethod.GET)
	public String memberIdPwdSearch(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{

		MemberInfoVO resultvo = null;

		List<MemberInfoVO> listvo = new ArrayList<MemberInfoVO>();
		ApiResultList<MemberInfoVO> resultList = new ApiResultList<MemberInfoVO>();

		Map<String, Object> param = new HashMap<String, Object>();

		String user_id =  URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "user_id", ""), "UTF-8");
		param.put("user_id", user_id);

		try{

			resultvo = memberInfoService.getMemberIdPwdSearch(param);

			resultList.setFlag("ok");
			resultList.setMessage("성공");		
		}
		catch(AccountIdNotFoundException ex){

			resultList.setFlag("fail");
			resultList.setMessage("아이디가 존재하지 않습니다.");		
		}
		catch (Exception ex) {
			resultList.setFlag("fail");
			resultList.setMessage("시스템 에러 발생 ㅡ.ㅡ ");		
		}
		finally{
			listvo.add(resultvo);

			resultList.setItemlist(listvo);
			model.addAttribute("member", resultList);
		}

		return "rest/result";

	}	



	/***
	 * 로그인한 회원이 회원정보 변경 후 데이터 조회
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/MemberInfoList.xml", method=RequestMethod.GET)
	public String getMemberInfoList(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{

		List<MemberInfoVO> listvo = null;
		ApiResultList<MemberInfoVO> resultList = new ApiResultList<MemberInfoVO>();

		String user_id = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "user_id", ""), "UTF-8");

		Map<String, Object> param = new HashMap();
		param.put("user_id", user_id);

		listvo = memberInfoService.getMemberInfoList(param);

		resultList.setFlag("ok");
		resultList.setMessage("성공");
		resultList.setTotcnt(String.valueOf(listvo.size())); 
		resultList.setItemlist(listvo);

		model.addAttribute("post", resultList);

		return "rest/result";
	}


	//회원탈퇴 setMemberWithDraw
	@RequestMapping(value="/memberWithDraw.xml", method=RequestMethod.GET)
	public String memberWithDraw(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{

		ApiResultList<MemberInfoVO> resultList = new ApiResultList<MemberInfoVO>();

		Map<String, Object> param = new HashMap<String, Object>();

		String user_id =  URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "user_id", ""), "UTF-8");

		param.put("user_id", user_id);


		try{
			memberInfoService.deleteMember(param);

			resultList.setFlag("ok");
			resultList.setMessage("성공");		
		}
		catch(AccountIdNotFoundException ex){

			resultList.setFlag("fail");
			resultList.setMessage("아이디가 존재하지 않습니다.");		
		}
		finally{

			model.addAttribute("member", resultList);

		}

		return "rest/result";
	}	


	@RequestMapping(value="/MemberPwdChange.xml", method=RequestMethod.GET)
	public String setMemberPwdChange(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{

		
		ApiResultList<MemberInfoVO> resultList = new ApiResultList<MemberInfoVO>();

		String user_id = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "user_id", ""), "UTF-8");
		String pwd = URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "pwd", ""), "UTF-8");

		Map<String, String> param = new HashMap();
		param.put("user_id", user_id);
		param.put("pwd", pwd);

		try{
		
		memberInfoService.updateMemberPwdChage(param);

		resultList.setFlag("ok");
		resultList.setMessage("비밀번호를 변경 했습니다.");
		
		}
		catch(Exception ex){

			resultList.setFlag("fail");
			resultList.setMessage("비밀번호 변경에 실패했습니다. ");		
		}finally{

			model.addAttribute("member", resultList);			
		}

		return "rest/result";
	}


	/***
	 * 포인트 조회
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/memberSavingPoint.xml", method=RequestMethod.GET)
	public String getMemberSavingPoint(HttpServletRequest request, HttpServletResponse response ,ModelMap model) throws Exception{

		List<PointHistoryVO> listvo = new ArrayList<PointHistoryVO>();

		ApiResultList<PointHistoryVO> resultList = new ApiResultList<PointHistoryVO>();

		Map<String, Object> param = new HashMap();
		PointHistoryVO pointvo = null;
		
		String user_id =  URLDecoder.decode(ServletRequestUtils.getStringParameter(request, "user_id", ""), "UTF-8");

		param.put("user_id", user_id);
		
		try{

			pointvo = pointInfoService.getMemberSavingPoint(param);

			resultList.setFlag("ok");
			resultList.setMessage("포인트 조회 성공");		
		}
		catch (Exception ex) {
			resultList.setFlag("fail");
			resultList.setMessage("시스템 에러 발생");		
		}
		finally{
			listvo.add(pointvo);

			resultList.setTotcnt(String.valueOf(listvo.size()));
			resultList.setItemlist(listvo);
			model.addAttribute("point", resultList);
		}

		return "rest/result";
	}



}
