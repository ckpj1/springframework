package egovframework.com.cmm.util;

import java.io.Reader;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

//import oracle.sql.CLOB;

import org.springframework.ui.ModelMap;

public class EgovDataUtil {
	
	/**
	 * Row의 컬럼값을 return 한다.
	 * 
	 * @param 	- HashMap (row)
	 * 			- String (컬럼명)
	 * @return String
	 * @throws Exception 
	 * @exception Exception
	 */
	public static String getColumnString(Object row, String columnNm) throws Exception {
		String value="";
		 
		try{
			Object obj = ((HashMap)row).get(columnNm);
			
			if (obj ==null){
				value="";
			}else if (obj instanceof String){
				value = (String)obj;
/*
			} else if (obj instanceof CLOB) {
				Reader reader = null; 
				char buffer[] = new char[1024]; 
				int nReadCnt; 
				StringBuffer sb = new StringBuffer(); 
				if (value != null) { 
					reader = ((CLOB)obj).getCharacterStream(); 
					while ((nReadCnt = reader.read(buffer)) != -1) 
					sb.append(buffer, 0, nReadCnt); 
					reader.close(); 
					reader = null;
					value = sb.toString();
				}
*/
			}else{
				value = obj.toString();
			}

		}catch(NullPointerException e){
			
		}
		
		return value;
	}
	
	/**
	 * Dataset Row의 삭제여부를 확인한다.
	 * 
	 * @param HashMap
	 * @return boolean
	 * @exception Exception
	 */
	public static boolean isDeleteRow(HashMap row) {
		if (row == null)
			return false;
		if (((String) row.get("ROWTYPE")).equals("DELETE")) {
			return true;
		}
		return false;
	}

	/**
	 * Dataset Row의 수정여부를 확인한다.
	 * 
	 * @param HashMap
	 * @return boolean
	 * @exception Exception
	 */
	public static boolean isUpdateRow(HashMap row) {
		if (row == null)
			return false;
		if (((String) row.get("ROWTYPE")).equals("UPDATE")) {
			return true;
		}
		return false;
	}

	/**
	 * Dataset Row의 신규여부를 확인한다.
	 * 
	 * @param HashMap
	 * @return boolean
	 * @exception Exception
	 */
	public static boolean isInsertRow(HashMap row) {
		if (row == null)
			return false;
		if (((String) row.get("ROWTYPE")).equals("INSERT")) {
			return true;
		}
		return false;
	}

	/**
	 * ModelMap 에서 Dataset을 추출하여 첫번째 row 를 반환한다.
	 * 
	 * @param 	- ModelMap
	 * 			- key (데이타셋 id)
	 * @return HashMap
	 * @exception Exception
	 */
	public static HashMap getFirstRowFromList(ModelMap model, String key) {
		ArrayList list = (ArrayList) model.get(key);

		if (list == null)
			return null;
		if (list.size() == 0)
			return null;

		return (HashMap) list.get(0);
	}

	/**
	 * ModelMap 에서 Dataset을 추출한다.
	 * 
	 * @param 	- ModelMap
	 * 			- key (데이타셋 id)
	 * @return ArrayList
	 * @exception Exception
	 */
	public static ArrayList getList(ModelMap model, String key) {
		ArrayList al;
		try {
			al = (ArrayList) model.get(key);
		} catch (NullPointerException nex) {
			return null;
		}
		return al;
	}

	/**
	 * ModelMap 에서 insert상태의 Dataset을 추출한다.
	 * 
	 * @param 	- ModelMap
	 * 			- key (데이타셋 id)
	 * @return ArrayList
	 * @exception Exception
	 */
	public static ArrayList getInsertList(ModelMap model, String key) {
		ArrayList inList;
		ArrayList outList;
		try {
			inList = (ArrayList) model.get(key);
			outList = new ArrayList();
			for (int i = 0; i < inList.size(); i++) {
				HashMap row = (HashMap) inList.get(i);
				if (EgovDataUtil.isInsertRow(row)) {
					outList.add(row);
				}
			}
		} catch (NullPointerException nex) {
			return null;
		}
		return outList;
	}

	/**
	 * ModelMap 에서 update상태의 Dataset을 추출한다.
	 * 
	 * @param 	- ModelMap
	 * 			- key (데이타셋 id)
	 * @return ArrayList
	 * @exception Exception
	 */
	public static ArrayList getUpdateList(ModelMap model, String key) {
		ArrayList inList;
		ArrayList outList;
		try {
			inList = (ArrayList) model.get(key);
			outList = new ArrayList();
			for (int i = 0; i < inList.size(); i++) {
				HashMap row = (HashMap) inList.get(i);
				if (EgovDataUtil.isUpdateRow(row)) {
					outList.add(row);
				}
			}
		} catch (NullPointerException nex) {
			return null;
		}
		return outList;
	}

	/**
	 * ModelMap 에서 delete상태의 Dataset을 추출한다.
	 * 
	 * @param 	- ModelMap
	 * 			- key (데이타셋 id)
	 * @return ArrayList
	 * @exception Exception
	 */
	public static ArrayList getDeleteList(ModelMap model, String key) {
		ArrayList inList;
		ArrayList outList;
		try {
			inList = (ArrayList) model.get(key);
			outList = new ArrayList();
			for (int i = 0; i < inList.size(); i++) {
				HashMap row = (HashMap) inList.get(i);
				if (EgovDataUtil.isDeleteRow(row)) {
					outList.add(row);
				}
			}
		} catch (NullPointerException nex) {
			return null;
		}
		return outList;
	}

    /**
     * 입력 문자열이 null 또는 공백인 지 체크한다.
     *
     * @param inputValue 입력 문자열
     * @return boolean 입력 문자열이 null 또는 공백이면 true 아니면 false  
     */
    public static boolean isEmpty(String inputValue) 
    {
        return inputValue == null || "".equals(inputValue.trim());
    }

    /**
     * 입력 Object가 null인 지 체크한다.
     *
     * @param inputObject 입력 Object
     * @return boolean 입력 Object가 null이면 true 아니면 false
     */
    public static boolean isNull(Object inputObject) 
    {
        return inputObject == null;
    }

    /**
     * 입력 문자열의 값이 숫자형 값인 지 체크한다.
     *
     * @param inputValue 입력 문자열
     * @return 숫자형 값이면 true 아니면 false
     */
    public static boolean isNumber(String inputValue)
    {
        try {
            new BigDecimal(inputValue);
            return true;
        } 
        catch (Exception e) {
            return false;
        }
    }

    /**
     * 입력 문자열의 값이 유효한 날짜형 값인 지 체크한다.
     *
     * @param inputDate 입력 문자열 (yyyyMMdd 형식의 8자리 날짜형 문자열)
     * @return boolen 유효한 날짜형 값이면 true 아니면 false
     */
    public static boolean isValidDate(String inputDate)
    {
        try {
            if (isEmpty(inputDate) || inputDate.trim().length() != 8 || !isNumber(inputDate))
                return false;

            // 입력 문자열을 년, 월, 일로 분리한다. 
            int yearInt  = Integer.parseInt(inputDate.substring(0, 4));
            int monthInt = Integer.parseInt(inputDate.substring(4, 6));
            int dayInt   = Integer.parseInt(inputDate.substring(6, 8));

            // 1900년대와 2000년대를 구분한다. 
            if (yearInt < 100) yearInt += 2000;

            // Calendar의 Month 값은 0부터 시작하므로 -1
            monthInt--;

            // Calendar1 -> Variable -> Calendar2 를 수행한 후,
            // Calendar1 과 Calendar2가 동일한 지 비교하는 방식을 사용
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR,  yearInt);
            cal.set(Calendar.MONTH, monthInt);
            cal.set(Calendar.DATE,  dayInt);

            int fYear  = cal.get(Calendar.YEAR);
            int fMonth = cal.get(Calendar.MONTH);
            int fDay   = cal.get(Calendar.DATE);

            if ((fYear != yearInt) || (fMonth != monthInt) || (fDay != dayInt)) return false;

            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * 입력 문자열의 값이 유효한 시간형 값인 지 체크한다.
     *
     * @param inputTime 입력 문자열 (HHmmss 형식의 6자리 시간형 문자열)
     * @return boolen 유효한 시간형 값이면 true 아니면 false
     */
    public static boolean isValidTime(String inputTime)
    {
        try {
            if (isEmpty(inputTime) || inputTime.trim().length() != 6 || !isNumber(inputTime))
                return false;

            // 입력 문자열을 시, 분, 초로 분리한다.
            int hourInt     = Integer.parseInt(inputTime.substring(0, 2));
            int minuteInt   = Integer.parseInt(inputTime.substring(2, 4));
            int secondInt   = Integer.parseInt(inputTime.substring(4, 6));

            // 시, 분, 초의 범위를 체크
            if (hourInt < 0 || hourInt > 24) return false;
            if (minuteInt < 0 || minuteInt > 59) return false;
            if (secondInt < 0 || secondInt > 59) return false;

            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
