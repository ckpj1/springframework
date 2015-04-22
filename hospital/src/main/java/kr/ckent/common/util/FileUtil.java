/*
 * Copyright (c) 1999-2005 (주)ACTSoft. All rights reserved.
 */
package kr.ckent.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
 
/**
 * 파일 업로드, 다운로드, 삭제
 * 
 * @author YoungHo Lee
 * @deprecated  
 *
 */

public class FileUtil {

	/**
	 * 기본 생성자
	 */
	public FileUtil() {
		super();
	}
	
	/**
	 * 파일 업로드
	 * 
	 * @param HttpServletRequest request, String path
	 * @return Map. 
	 * 			flag(boolean) : 업로드 성공 여부
	 * 			viewFileName(String) : 원 파일명
	 * 			realFileName(String) : 업로드시 변경한 파일명
	 * 			fileSize(long) : 파일 크기
	 * @throws Exception
	 */
	public static final Map<String, Object> upload(HttpServletRequest request, String path) throws Exception {
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest)request;
    	MultipartFile multiFile = mpRequest.getFile("uploadFile");
    	long fileSize = multiFile.getSize();
    	
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("flag", false);
    	
    	String viewFileName = "";
    	String realFileName = "";
    	if (fileSize > 0) {
    		InputStream stream = multiFile.getInputStream();
    		
    		viewFileName = multiFile.getOriginalFilename();
    		
    		UUID uuid = UUID.randomUUID();
    		realFileName = uuid.toString();
    		
            OutputStream bos = new FileOutputStream(path + realFileName);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.close();
            stream.close();
            
            result.put("flag", true);
            result.put("viewFileName", viewFileName);
            result.put("realFileName", realFileName);
            result.put("fileSize", fileSize);            
    	}
    	
    	return result;
	}
	
	/**
	 * 파일 업로드
	 * 
	 * @param HttpServletRequest request, String path, String fileName
	 * @return Map. 
	 * 			flag(boolean) : 업로드 성공 여부
	 * 			viewFileName(String) : 원 파일명
	 * 			realFileName(String) : 업로드시 변경한 파일명
	 * 			fileSize(long) : 파일 크기
	 * @throws Exception
	 */
	public static final Map<String, Object> upload(HttpServletRequest request, String path, String fileName) throws Exception {
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest)request;
    	MultipartFile multiFile = mpRequest.getFile(fileName);
    	if( multiFile == null ){
    		return null;
    	}

    	long fileSize = multiFile.getSize();
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("flag", false);
    	
    	String viewFileName = "";
    	String realFileName = "";
    	if (fileSize > 0) {
    		InputStream stream = multiFile.getInputStream();
    		
    		viewFileName = multiFile.getOriginalFilename();
    		
    		UUID uuid = UUID.randomUUID();
    		realFileName = uuid.toString();
    		
            OutputStream bos = new FileOutputStream(path + "/" + realFileName);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.close();
            stream.close();
            
            result.put("flag", true);
            result.put("viewFileName", viewFileName);
            result.put("realFileName", realFileName);
            result.put("fileSize", fileSize);            
    	}else{
    		return null;
    	}
    	
    	return result;
	}	
	
	/**
	 * 파일 다운로드
	 * 
	 * @param HttpServletRequest request, HttpServletResponse response, String path, String viewFileName, String realFileName
	 * @return void
	 * @throws Exception
	 */
	public static final void download(HttpServletRequest request, HttpServletResponse response, String path, String viewFileName, String realFileName) throws Exception {
		String filePath = path + realFileName;
        File tempFile = new File(filePath);

        viewFileName = viewFileName.substring(viewFileName.lastIndexOf("/") + 1);

        String agentType = request.getHeader("Accept-Encoding");
        try {
            if (!tempFile.exists() || !tempFile.canRead()) {
                PrintWriter out = response.getWriter();
                out.println("<script>alert('File Not Found');history.back();</script>");
                return;
            }
        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('File Not Found');history.back();</script>");
            return;
        }

        boolean flag = false;
        if (agentType != null && agentType.indexOf("gzip") >= 0)
            flag = true;

        flag = false;

        if (flag) {
            response.setHeader("Content-Encoding", "gzip");
            response.setHeader("Content-disposition", "attachment;filename=" + viewFileName);
            javax.servlet.ServletOutputStream servletoutputstream = response.getOutputStream();
            java.util.zip.GZIPOutputStream gzipoutputstream = new java.util.zip.GZIPOutputStream(servletoutputstream);

            byte readByte[] = new byte[4096];
            try {
                BufferedInputStream bufferedinputstream = new BufferedInputStream(new FileInputStream(tempFile));
                int i;
                while ((i = bufferedinputstream.read(readByte, 0, 4096)) != -1)
                	gzipoutputstream.write(readByte, 0, i);
                bufferedinputstream.close();
            } catch (Exception _ex) {
            }
            
            gzipoutputstream.close();
            servletoutputstream.close();
        } else {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=" + new String((viewFileName.replaceAll(" ", "")).getBytes("euc-kr"),"8859_1"));
            javax.servlet.ServletOutputStream servletoutputstream1 = response.getOutputStream();
            byte readByte[] = new byte[4096];
            try {
                BufferedInputStream bufferedinputstream = new BufferedInputStream(new FileInputStream(tempFile));
                int i;
                while ((i = bufferedinputstream.read(readByte, 0, 4096)) != -1)
                	servletoutputstream1.write(readByte, 0, i);
                bufferedinputstream.close();
            } catch (Exception _ex) {
            }
            servletoutputstream1.flush();
            servletoutputstream1.close();
        }
	}
	
	/**
	 * 파일 삭제
	 * 
	 * @param String path, String fileName
	 * @return boolean 파일 삭제 여부
	 * @throws Exception
	 */
	public static final boolean delete(String path, String fileName) throws Exception {
		String filePath = path + fileName;
        File file = new File(filePath);
        
        return file.delete();
	}
}
