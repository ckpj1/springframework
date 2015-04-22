package kr.ckent.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class LoginImgUtil {
	
	/**
	 * 압축
	 * 
	 * @param sourceFile
	 * @param sourcePath
	 * @param zos
	 * @throws Exception
	 */
	private void zipEntry(File sourceFile, String sourcePath,ZipOutputStream zos) throws Exception {

		// sourceFile 이 디렉토리인 경우 하위 파일 리스트 가져와 재귀호출
		if (sourceFile.isDirectory()) {
			if (sourceFile.getName().equalsIgnoreCase(".metadata")) { // .metadata
																		// 디렉토리
																		// return
				return;
			}
			File[] fileArray = sourceFile.listFiles(); // sourceFile 의 하위 파일 리스트
			for (int i = 0; i < fileArray.length; i++) {
				zipEntry(fileArray[i], sourcePath, zos); // 재귀 호출
			}
		} else { // sourcehFile 이 디렉토리가 아닌 경우
			BufferedInputStream bis = null;
			try {
				String sFilePath = sourceFile.getPath();
				String zipEntryName = sFilePath.substring(sourcePath.length() + 1, sFilePath.length());
				bis = new BufferedInputStream(new FileInputStream(sourceFile));
				ZipEntry zentry = new ZipEntry(zipEntryName);
				zentry.setTime(sourceFile.lastModified());
				zos.putNextEntry(zentry);
				byte[] buffer = new byte[8];
				int cnt = 0;
				while ((cnt = bis.read(buffer, 0, 8)) != -1) {
					zos.write(buffer, 0, cnt);
				}
				zos.closeEntry();
			} finally {
				if (bis != null) {
					bis.close();
				}
			}
		}
	}

	/**
	 * 지정된 폴더를 Zip 파일로 압축한다.
	 * 
	 * @param sourcePath
	 *            - 압축 대상 디렉토리
	 * @param output
	 *            - 저장 zip 파일 이름
	 * @throws Exception
	 */
	public void makeZip(String sourcePath) throws Exception {
		// 압축 대상(sourcePath)이 디렉토리나 파일이 아니면 리턴한다.
		File sourceFile = new File(sourcePath);
		if (!sourceFile.isFile() && !sourceFile.isDirectory()) {
			throw new Exception("압축 대상의 파일을 찾을 수가 없습니다.");
		}
	
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ZipOutputStream zos = null;
		try {
			fos = new FileOutputStream(sourcePath + ".zip"); // FileOutputStream
			bos = new BufferedOutputStream(fos); // BufferedStream
			zos = new ZipOutputStream(bos); // ZipOutputStream
			zos.setLevel(8); // 압축 레벨 - 최대 압축률은 9, 디폴트 8
			zipEntry(sourceFile, sourcePath, zos); // Zip 파일 생성
			zos.finish(); // ZipOutputStream finish

		} finally {
			if (zos != null) {
				zos.close();
			}
			if (bos != null) {
				bos.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}
		
	// 파일을 존재여부를 확인하는 메소드
	public Boolean fileIsLive(String isLivefile) {
		File f1 = new File(isLivefile);

		if (f1.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	 //파일을 삭제하는 메소드
	 public static void fileDelete(String deleteFileName) {
	  File I = new File(deleteFileName);
	  I.delete();
	 }

}
