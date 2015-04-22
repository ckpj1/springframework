package egovframework.com.cmm.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kr.ckent.hospital.vo.FileInfoVO;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.FileVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @Class Name : EgovFileMngDAO.java
 * @Description : 파일정보 관리를 위한 데이터 처리 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 25.     이삼섭    최초생성
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 25.
 * @version
 * @see
 *
 */
@Repository("FileManageDAO")
public class FileManageDAO extends EgovAbstractDAO {

	//private static final Logger LOG = Logger.getLogger(this.getClass());

	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.(회원정보)
	 * 
	 * @param fileList
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String insertFileInfs(List fileList, String memberseq) throws Exception {
		FileVO vo = (FileVO)fileList.get(0);
		String atchFileId = vo.getAtchFileId();

		//어플문제로 if문 추가함.
		if(vo.getFileSn().equals("0")){
						
			insert("FileManageDAO.insertFileMaster", vo);

			//파일 저장 후 member테이블 업데이트
			Map<String, Object> param = new HashMap<String,Object>();
			param.put("user_id", memberseq);
			param.put("atchFileId", atchFileId);

			update("mem.updateMemberAtchFileId", param);
		}

		Iterator iter = fileList.iterator();
		while (iter.hasNext()) {
			vo = (FileVO)iter.next();
			
			insert("FileManageDAO.insertFileDetail", vo);
		}

		return atchFileId;
	}
	
	//2014.06.05 추가 : 회원사진 업데이트 관련.
	@SuppressWarnings("unchecked")
	public String updateMemberFileInfs(List fileList, String memberseq) throws Exception{
		FileVO vo = (FileVO)fileList.get(0);
		String atchFileId = vo.getAtchFileId();

		List<FileVO>  listfilevo = list("FileManageDAO.selectFileList", vo);
		
		if(vo.getFileSn().equals("0")){
			
			if(listfilevo.size() <= 0){ //기존에 없으면...신규 등록..
				
				insert("FileManageDAO.insertFileMaster", vo);

				//파일 저장 후 member테이블 업데이트
				Map<String, Object> param = new HashMap<String,Object>();
				param.put("user_id", memberseq);
				param.put("atchFileId", atchFileId);

				update("mem.updateMemberAtchFileId", param);				
			}
			
		}
		
		FileVO tmpVO = (FileVO)selectByPk("FileManageDAO.selectFileInf", vo); 
		
		if(tmpVO != null){ //같은 filesn으로 파일이 들어있는 경우이므로 조회한 filesn을 삭제 후 제 등록한다.
			delete("FileManageDAO.deleteFileDetail", vo);
		}
		
		insert("FileManageDAO.insertFileDetail", vo);
		
		
		return atchFileId;
	}
	

	/**
	 * 병원후기 > 새글 작성시 파일 아이디 저장.
	 * @param fileList
	 * @param memberseq
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String insertBoardFileInfs(List fileList, String board_idx) throws Exception {
		FileVO vo = (FileVO)fileList.get(0);
		String atchFileId = vo.getAtchFileId();

		//어플문제로 if문 추가함.
		if(vo.getFileSn().equals("0")){
			insert("FileManageDAO.insertFileMaster", vo);

			//파일 저장 후 member테이블 업데이트
			Map<String, Object> param = new HashMap<String,Object>();
			param.put("board_idx", board_idx);
			param.put("atchFileId", atchFileId);

			update("board.updateBoardAtchFileId", param);
		}

		Iterator iter = fileList.iterator();
		while (iter.hasNext()) {
			vo = (FileVO)iter.next();

			insert("FileManageDAO.insertFileDetail", vo);
		}

		return atchFileId;
	}

	
	@SuppressWarnings("unchecked")
	public String updateBoardFileInfs(List fileList, String board_idx) throws Exception{
		FileVO vo = (FileVO)fileList.get(0);
		String atchFileId = vo.getAtchFileId();

		List<FileVO>  listfilevo = list("FileManageDAO.selectFileList", vo);
		
		if(vo.getFileSn().equals("0")){
			
			if(listfilevo.size() <= 0){ //기존에 없으면...신규 등록..
				
				insert("FileManageDAO.insertFileMaster", vo);

				//파일 저장 후 board테이블 업데이트
				Map<String, Object> param = new HashMap<String,Object>();
				param.put("board_idx", board_idx);
				param.put("atchFileId", atchFileId);

				update("board.updateBoardAtchFileId", param);			
			}
			
		}
		
		FileVO tmpVO = (FileVO)selectByPk("FileManageDAO.selectFileInf", vo); 
		
		if(tmpVO != null){ //같은 filesn으로 파일이 들어있는 경우이므로 조회한 filesn을 삭제 후 제 등록한다.
			delete("FileManageDAO.deleteFileDetail", vo);
		}
		
		insert("FileManageDAO.insertFileDetail", vo);
		
		
		return atchFileId;
	}
		
	
	
	
	
	
	
	
	
	
	
	//-------------------------------------------------------------------------
	
	
	/**
	 * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public void insertFileInf(FileVO vo) throws Exception {
		insert("FileManageDAO.insertFileMaster", vo);
		insert("FileManageDAO.insertFileDetail", vo);
	}

	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
	 * 
	 * @param fileList
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void updateFileInfs(List fileList) throws Exception {
		FileVO vo;
		Iterator iter = fileList.iterator();
		while (iter.hasNext()) {
			vo = (FileVO)iter.next();

			insert("FileManageDAO.insertFileDetail", vo);
		}
	}

	/**
	 * 여러 개의 파일을 삭제한다.
	 * 
	 * @param fileList
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void deleteFileInfs(List fileList) throws Exception {
		Iterator iter = fileList.iterator();
		FileVO vo;
		while (iter.hasNext()) {
			vo = (FileVO)iter.next();

			delete("FileManageDAO.deleteFileDetail", vo);
		}
	}

	/**
	 * 하나의 파일을 삭제한다.
	 * 
	 * @param fvo
	 * @throws Exception
	 */
	public void deleteFileInf(FileVO fvo) throws Exception {
		delete("FileManageDAO.deleteFileDetail", fvo);
	}

	/**
	 * 파일에 대한 목록을 조회한다.
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<FileVO> selectFileInfs(FileVO vo) throws Exception {
		return list("FileManageDAO.selectFileList", vo);
	}

	/**
	 * 파일 구분자에 대한 최대값을 구한다.
	 * 
	 * @param fvo
	 * @return
	 * @throws Exception
	 */
	public int getMaxFileSN(FileVO fvo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("FileManageDAO.getMaxFileSN", fvo);
	}

	/**
	 * 파일에 대한 상세정보를 조회한다.
	 * 
	 * @param fvo
	 * @return
	 * @throws Exception
	 */
	public FileVO selectFileInf(FileVO fvo) throws Exception {
		return (FileVO)selectByPk("FileManageDAO.selectFileInf", fvo);
	}
	
	/***
	 * 2014.05.29 백충기 추가
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<FileInfoVO> selectAllFileInf(Map<String, Object> param) throws Exception {
		return list("FileManageDAO.selectAllFileInf", param);
	}
	
	
	

	/**
	 * 전체 파일을 삭제한다.
	 * 
	 * @param fvo
	 * @throws Exception
	 */
	public void deleteAllFileInf(FileVO fvo) throws Exception {
		update("FileManageDAO.deleteCOMTNFILE", fvo);
	}

	/**
	 * 파일명 검색에 대한 목록을 조회한다.
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<FileVO> selectFileListByFileNm(FileVO fvo) throws Exception {
		return list("FileManageDAO.selectFileListByFileNm", fvo);
	}

	/**
	 * 파일명 검색에 대한 목록 전체 건수를 조회한다.
	 * 
	 * @param fvo
	 * @return
	 * @throws Exception
	 */
	public int selectFileListCntByFileNm(FileVO fvo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("FileManageDAO.selectFileListCntByFileNm", fvo);
	}

	/**
	 * 이미지 파일에 대한 목록을 조회한다.
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<FileVO> selectImageFileList(FileVO vo) throws Exception {
		return list("FileManageDAO.selectImageFileList", vo);
	}

	//2014.06.14 어플 전용
	/***
	 * detail - 회원탈퇴시 사용
	 * @param param
	 * @throws Exception
	 */
	//detail - 회원탈퇴시 사용
	public void MemberdeleteFileDetail(Map<String, Object> param) throws Exception {
		delete("FileManageDAO.MemberdeleteFileDetail", param);
	}
	/***
	 * master- 회원탈퇴시 사용
	 * @param param
	 * @throws Exception
	 */
	//master- 회원탈퇴시 사용
	public void MemberdeleteComtnFile(Map<String, Object> param) throws Exception {
		delete("FileManageDAO.MemberdeleteCOMTNFILE", param);
	}


}
