/*
 *
 * CurrencyUtil
 * 
 */
package egovframework.com.cmm.util;

import java.lang.reflect.Array;
import java.text.DecimalFormat;

/**
 * 금액 한글변환 유틸 클래스
 *
 * <pre>
 * 금액을 한글로 변환하는 기능을 제공한다.
 * </pre>
 */
public final class EgovCurrencyUtil
{

    /**
     * 금액을 한글로 변환한다. 금액은 크게 만, 억, 조, 경 단위로 자른다.
     * <pre>
     * 사용 예) CurrencyUtil.moneyToKorean("금 ", "111111", " 원정") => "금 일백이십삼만 일천이백삼십사 원정"
     * </pre>
     *
     * @param prefix 한글 금액의 앞자리에 기입하는 글자 (기본값은 "금 ")
     * @param money 금액 문자열
     * @param postfix 한글 금액의 마지막에 기입하는 글자 (기본값은 " 원정")
     * @return 한글로 변환된 금액
     */
    public static String moneyToKorean(String prefix, String money, String postfix)
    {
        String[] bigMonetaryUnit = { "만 ", "억 ", "조 ", "경 " };
        String[] monetaryUnit = { "천", "백", "십" };
        String prefixString = (!isNull(prefix)) ? prefix : "금 ";
        String postfixString = (!isNull(postfix)) ? postfix : " 원정";

        String rtnMoney = null;
        StringBuffer tempMoney = new StringBuffer(prefixString);
        String[] moneyArray = new String[] {};
        String tempString = null;

        int moneyLength = money.length();
        int unitPos = money.length();
        int arrInx = (moneyLength % 4 == 0) ? moneyLength / 4 : moneyLength / 4 + 1;

        moneyArray = new String[arrInx];

        // 4자리 마다 잘라서 배열 생성
        for (int inx = 0; unitPos > 0; inx++) {
            if (unitPos - 4 > 0) {
                tempString = money.substring(unitPos - 4, unitPos);
                moneyArray[inx] = tempString;
                unitPos = unitPos - 4;
            }
            else {
                tempString = money.substring(0, unitPos);
                moneyArray[inx] = lPad(tempString, 4);
                unitPos = 0;
            }
        }

        // 숫자를 한글로 변경 및 단위 추가
        for (int jnx = arrInx - 1; jnx >= 0; jnx--) {
            String tempMon = moneyArray[jnx];
            int tempMonLen = tempMon.length();
            char num = 0;

            for (int knx = 0; knx < tempMonLen; knx++) {
                num = tempMon.charAt(knx);
                tempMoney.append(baseMonToKorean(num));

                if (num != '0') {
                    switch (knx)
                    {
                    case 0: // 천 단위
                        tempMoney.append(monetaryUnit[knx]);
                        break;
                    case 1: // 백 단위
                        tempMoney.append(monetaryUnit[knx]);
                        break;
                    case 2: // 십 단위
                        tempMoney.append(monetaryUnit[knx]);
                        break;
                    default:
                        break;
                    }
                }
            }

            // 만단위 이상 단위 붙이기
            if (toInt(tempMon) > 0 && jnx > 0) {
                tempMoney.append(bigMonetaryUnit[jnx - 1]);
            }
        }

        if (money.equals("0")) {
            tempMoney.append("영");
        }

        tempMoney.append(postfixString);
        rtnMoney = tempMoney.toString();

        return rtnMoney;
    }

    /**
     * 전달받은 Object를 정수로 변환한다.
     *
     * @param obj 변환대상 Object
     * @return int 변환된 정수
     */
    private static int toInt(Object obj)
    {
        int value = 0;

        if (obj == null) {
            value = 0;
        }
        else if (obj instanceof Number) {
            value = ((Number) obj).intValue();
        }
        else {
            try {
                value = Integer.parseInt(toString(obj).replace(",", ""));
            }
            catch (Exception e) {
                value = 0;
            }
        }

        return value;
    }

    /**
     * 전달받은 Object를 문자열로 변환한다.
     *
     * @param obj 변환대상 Object
     * @return String 변환된 문자열
     */
    private static String toString(Object obj)
    {
        String value = null;

        try {
            Class c = obj.getClass();
            if (obj == null) {
                value = "";
            }
            else if (c.isArray()) {
                int length = Array.getLength(obj);
                if (length == 0) {
                    value = "";
                }
                else {
                    Object item = Array.get(obj, 0);
                    if (item == null) {
                        value = "";
                    }
                    else {
                        value = item.toString().trim();
                    }
                }
            }
            else {
                value = obj.toString().trim();
            }
        }
        catch (Exception e) {
            value = "";
        }

        return value;
    }


    /**
     * 숫자형 문자를 한글 문자로 변환한다.
     *
     * @param num 숫자형 문자(0, 1, ..., 8, 9)
     * @return String 변환된 한글 문자("", 일, ..., 팔, 구)
     */
    private static String baseMonToKorean(char num)
    {
        String[] baseMonetaryUnit = { "", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구" };
        String rtnBaseMonetary = null;

        switch (num)
        {
        case '0':
            rtnBaseMonetary = baseMonetaryUnit[0];
            break;
        case '1':
            rtnBaseMonetary = baseMonetaryUnit[1];
            break;
        case '2':
            rtnBaseMonetary = baseMonetaryUnit[2];
            break;
        case '3':
            rtnBaseMonetary = baseMonetaryUnit[3];
            break;
        case '4':
            rtnBaseMonetary = baseMonetaryUnit[4];
            break;
        case '5':
            rtnBaseMonetary = baseMonetaryUnit[5];
            break;
        case '6':
            rtnBaseMonetary = baseMonetaryUnit[6];
            break;
        case '7':
            rtnBaseMonetary = baseMonetaryUnit[7];
            break;
        case '8':
            rtnBaseMonetary = baseMonetaryUnit[8];
            break;
        case '9':
            rtnBaseMonetary = baseMonetaryUnit[9];
            break;
        default:
            break;
        }

        return rtnBaseMonetary;
    }

    /**
     * 문자열이 null 인지 체크한다.
     *
     * @param value String
     * @return boolean 문자열의 null 여부를 반환
     */
    private static boolean isNull(String value)
    {
        return value == null;
    }

    /**
     * 사이즈만큼 특정문자를 채운다.
     * @param src
     * @param size
     * @return String
     */
    private static String lPad(String src, int size)
    {
        String pattern = "";
        double dValue = 0d;

        try {
            dValue = Double.valueOf(src).doubleValue();
        }
        catch (Exception e) {
            dValue = 0d;
        }

        for (int inx = 0; inx < size; inx++)
            pattern += "0";

        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(dValue);
    }

} // End of Class