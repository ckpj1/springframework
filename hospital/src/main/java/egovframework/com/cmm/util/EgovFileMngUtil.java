package egovframework.com.cmm.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ckent.common.util.SecurityUtil;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.service.Globals;
import egovframework.com.cmm.util.EgovWebUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


/**
 * @Class Name  : EgovFileMngUtil.java
 * @Description : 메시지 처리 관련 유틸리티
 * @Modification Information
 *
 *     수정일         수정자                   수정내용
 *     -------          --------        ---------------------------
 *   2009.02.13       이삼섭                  최초 생성
 *   2011.08.09       서준식                  utl.fcc패키지와 Dependency제거를 위해 getTimeStamp()메서드 추가
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 02. 13
 * @version 1.0
 * @see
 *
 */
@Component("EgovFileMngUtil")
public class EgovFileMngUtil {

	public static final int BUFF_SIZE = 2048;

	private static final Logger LOG = Logger.getLogger(EgovFileMngUtil.class.getName());
    
	@Resource(name = "egovFileIdGnrService")
    private EgovIdGnrService idgenService;
    
	/**
	 * 첨부파일에 대한 목록 정보를 취득한다.
	 *
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public List<FileVO> parseFileInf(Map<String, MultipartFile> files, String KeyStr, int fileKeyParam, String atchFileId, String storePath) throws Exception {
		int fileKey = fileKeyParam;

		String storePathString = "";
		String atchFileIdString = "";
		 

		if ("".equals(storePath) || storePath == null) {
			storePathString = EgovProperties.getProperty("Globals.fileStorePath");
		} else {
			storePathString = EgovProperties.getProperty(storePath);
		}

		if ("".equals(atchFileId) || atchFileId == null) {
		    atchFileIdString = idgenService.getNextStringId();
		} else {
		    atchFileIdString = atchFileId;
		}
		
		File saveFolder = new File(EgovWebUtil.filePathBlackList(storePathString));

		//디렉토리 생성
		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}
		

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;
		String filePath = "";
		List<FileVO> result  = new ArrayList<FileVO>();
		FileVO fvo;

		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();

			file = entry.getValue();
			String orginFileName = file.getOriginalFilename();

			//--------------------------------------
			// 원 파일명이 없는 경우 처리
			// (첨부가 되지 않은 input file type)
			//--------------------------------------
			if ("".equals(orginFileName)) {
				continue;
			}
			////------------------------------------

			
			int index = orginFileName.lastIndexOf(".");
			
			//파일명을 암호화처리.
			String fileName = UUID.randomUUID()	+ orginFileName.substring(0, index) ;		//파일명	  orginFileName.substring(0, index)
			
			String fileExt = orginFileName.substring(index + 1); //확장자			
//			String newName = fileName + "." + fileExt;
			String newName = KeyStr + getTimeStamp() + fileKey + "." + fileExt;
			
			long _size = file.getSize();

			if (!"".equals(orginFileName)) {
				filePath = storePathString + File.separator + newName;
				file.transferTo(new File(EgovWebUtil.filePathBlackList(filePath)));
			}
			
			
			
			fvo = new FileVO();
			fvo.setFileExtsn(fileExt);
			fvo.setFileStreCours(storePathString);
			fvo.setFileMg(Long.toString(_size));
			fvo.setOrignlFileNm(orginFileName);
			fvo.setStreFileNm(newName);
		    fvo.setAtchFileId(atchFileIdString);
			fvo.setFileSn(String.valueOf(fileKey));

			result.add(fvo);

			fileKey++;
		}

		return result;
	}
	
	
	

	/**
	 * 2011.08.09
	 * 공통 컴포넌트 utl.fcc 패키지와 Dependency제거를 위해 내부 메서드로 추가 정의함
	 * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능
	 *
	 * @param
	 * @return Timestamp 값
	 * @exception MyException
	 * @see
	 */
	private static String getTimeStamp() {

		String rtnStr = null;

		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		String pattern = "yyyyMMddhhmmssSSS";

		try {
			SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
			Timestamp ts = new Timestamp(System.currentTimeMillis());

			rtnStr = sdfCurrent.format(ts.getTime());
		} catch (Exception e) {
			//e.printStackTrace();

			//throw new RuntimeException(e);	// 보안점검 후속조치
			LOG.debug("IGNORED: " + e.getMessage());
		}

		return rtnStr;
	}
}
