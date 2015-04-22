package kr.ckent.common.daumeditor;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import kr.ckent.common.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 강민석(Minseok Kang)
 */

@Controller
public class DaumEditorCtrl {
	
	@Autowired
	private MessageSourceAccessor messages;
	
	@RequestMapping(value="/daumeditor/editor.do")
	public ModelAndView editor(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String targetFormId = ServletRequestUtils.getStringParameter(request, "targetFormId", "cmdFrm");
		String targetInputId = ServletRequestUtils.getStringParameter(request, "targetInputId", "contents");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("targetFormId", targetFormId);
		resultMap.put("targetInputId", targetInputId);
		
		return new ModelAndView("daumeditor/editor", resultMap);
	}
	
	@RequestMapping(value="/daumeditor/popup/image.do")
	public ModelAndView imagePopup(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		return new ModelAndView("daumeditor/popup/image");
	}
	
	/**
	 * upload
	 * */
	@RequestMapping(value="/daumeditor/popup/image.do", method=RequestMethod.POST)
	public Map<String, Object> upload(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;
		MultipartFile mf = mpRequest.getFile("uploadImg");
		
		if (mf != null && !mf.isEmpty()) {
			
			//매월마다 폴더 생성하여 업로드
			Calendar cal = Calendar.getInstance();
			String fullPath = StringUtil.fmt(messages, "file.dir") + StringUtil.fmt(messages, "upload.dir") +"/"+ new SimpleDateFormat("yyyyMM").format(cal.getTime());
			
			//업로드 경로
			File file = new File( fullPath );
			
			if(! file.isDirectory()){	//해당 폴더가 없을 경우 폴더 생성
				new File( fullPath ).mkdirs();
			}
			
			Random random = new Random();
			int randomNumber = random.nextInt(100000000);
			
		   	//파일명 부여
			String originalFileName = mf.getOriginalFilename();
			
			//파일네임 공백, 특수문자 제거
			originalFileName = originalFileName.replaceAll("\\s", ""); //공백제거
			originalFileName = originalFileName.replaceAll("%", "");
			
			String tempFileName[] = originalFileName.split("\\.");
			String newFileName = tempFileName[0] + "_" +randomNumber + "." + tempFileName[tempFileName.length-1];
			File destination = new File(file,newFileName);
			
			//사이즈
			long fileSize = mf.getSize();
			
			//파일업로드
			FileCopyUtils.copy(mf.getInputStream(), new FileOutputStream(destination));
			
			resultMap.put("viewFileName", originalFileName);
			resultMap.put("realFileName", newFileName);
			resultMap.put("fileSize", fileSize);
			
			//업로드경로(상대경로가 아닌 실제 주소가 포함된 경로)
			fullPath = StringUtil.fmt(messages, "url.domain")+"/"+StringUtil.fmt(messages, "upload.dir") +"/"+ new SimpleDateFormat("yyyyMM").format(cal.getTime());
			resultMap.put("fullPath", fullPath);
		}else{
			return null;
		}
		
		return resultMap;
	}
	
}
