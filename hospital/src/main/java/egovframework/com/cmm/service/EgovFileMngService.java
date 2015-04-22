package egovframework.com.cmm.service;

import java.util.List;
import java.util.Map;

import kr.ckent.hospital.vo.FileInfoVO;

/**
 * @Class Name : EgovFileMngService.java
 * @Description : 파일정보의 관리를 위한 서비스 인터페이스
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
public interface EgovFileMngService {

    /**
     * 파일에 대한 목록을 조회한다.
     * 
     * @param fvo
     * @return
     * @throws Exception
     */
    public List<FileVO> selectFileInfs(FileVO fvo) throws Exception;


    /***
     * 2014.05.29 백충기 추가
     * @param param
     * @return
     * @throws Exception
     */
    public List<FileInfoVO> selectAllFileInf(Map<String, Object> param) throws Exception ;
    
    /**
     * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
     * 
     * @param fvo
     * @throws Exception
     */
    public String insertFileInf(FileVO fvo) throws Exception;

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.(회원정보)
     * 
     * @param fvoList
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String insertFileInfs(List fvoList, String memberseq) throws Exception;

    /***
     * 회원사진 정보 수정
     * @param fvoList
     * @param memberseq
     * @return
     * @throws Exception
     */
    public String updateMemberFileInfs(List fvoList, String memberseq) throws Exception;

    
    /***
     * 병원후기 > 새글 작성시 파일 아이디 저장.s
     * @param fvoList
     * @param board_idx
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String insertBoardFileInfs(List fvoList, String board_idx) throws Exception;
    
    /***
     * 병원후기 > 게시글 수정시 파일 아이디 저장.
     * @param fvoList
     * @param memberseq
     * @return
     * @throws Exception
     */
    public String updateBoardFileInfs(List fvoList, String board_idx) throws Exception;


    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
     * 
     * @param fvoList
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void updateFileInfs(List fvoList) throws Exception;

    /**
     * 여러 개의 파일을 삭제한다.
     * 
     * @param fvoList
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void deleteFileInfs(List fvoList) throws Exception;

    /**
     * 하나의 파일을 삭제한다.
     * 
     * @param fvo
     * @throws Exception
     */
    public void deleteFileInf(FileVO fvo) throws Exception;

    /**
     * 파일에 대한 상세정보를 조회한다.
     * 
     * @param fvo
     * @return
     * @throws Exception
     */
    public FileVO selectFileInf(FileVO fvo) throws Exception;

    /**
     * 파일 구분자에 대한 최대값을 구한다.
     * 
     * @param fvo
     * @return
     * @throws Exception
     */
    public int getMaxFileSN(FileVO fvo) throws Exception;

    /**
     * 전체 파일을 삭제한다.
     * 
     * @param fvo
     * @throws Exception
     */
    public void deleteAllFileInf(FileVO fvo) throws Exception;

    /**
     * 파일명 검색에 대한 목록을 조회한다.
     * 
     * @param fvo
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectFileListByFileNm(FileVO fvo) throws Exception;

    /**
     * 이미지 파일에 대한 목록을 조회한다.
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    public List<FileVO> selectImageFileList(FileVO vo) throws Exception;
}
