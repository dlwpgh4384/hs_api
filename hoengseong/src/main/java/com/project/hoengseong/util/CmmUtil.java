package com.project.hoengseong.util;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.project.hoengseong.user.model.CarInfoDTO;

import jakarta.servlet.http.HttpServletRequest;



@Component
public class CmmUtil {
		
  /**
   * 문자열이 빈 값인지 확인한다.
   * 
   * @param text
   * @return 문자열이 Null이거나 빈값이면 true를 반환한다.
   */
  public static boolean isEmpty(String text) {
    return text == null || text.length() <= 0;
  }

  /**
   * 문자열이 빈 값이 아닌지 확인한다.
   * 
   * @param text
   * @return 문자열이 비어있지 않으면 true를 반환한다.
   */
  public static boolean isNotEmpty(String text) {
    return text != null && text.length() > 0;
  }

  /**
   * 오늘날짜로부터 대상 날짜 사이에 일자를 반환한다.
   * 오늘날짜보다 과거는 음수, 미래는 양수로 반환된다.
   * 
   * @param targetDate 비교 대상 날짜 (yyyy-MM-dd 형식)
   * @return
   */
  public static int getDistanceDayFromToday(String targetDate) {
    if (isEmpty(targetDate)) {
      return 0;
    }
    int val = 0;
    try {
      Calendar calendar = Calendar.getInstance();
      int baseDateYear, baseDateMonth, baseDateDay;
      baseDateYear = calendar.get(Calendar.YEAR);
      baseDateMonth = calendar.get(Calendar.MONTH) + 1;
      baseDateDay = calendar.get(Calendar.DAY_OF_MONTH);

      int targetDateYear, targetDateMonth, targetDateDay;
      targetDateYear = Integer.parseInt(targetDate.substring(0, 4));
      targetDateMonth = Integer.parseInt(targetDate.substring(5, 7));
      targetDateDay = Integer.parseInt(targetDate.substring(8, 10));

      val = getDistanceDay(baseDateYear, baseDateMonth, baseDateDay, targetDateYear,
          targetDateMonth, targetDateDay);
    } catch (NumberFormatException e) {
    }
    return val;
  }

  /**
   * 기준날짜로부터 대상 날짜 사이에 일자를 반환한다.
   * 기준날짜보다 과거는 음수, 미래는 양수로 반환된다.
   * 
   * @param baseDate   비교 기준 날짜 (yyyy-MM-dd 형식)
   * @param targetDate 비교 대상 날짜 (yyyy-MM-dd 형식)
   * @return
   */
  public static int getDistanceDay(String baseDate, String targetDate) {
    if (isEmpty(baseDate) || isEmpty(targetDate)) {
      return 0;
    }
    int val = 0;
    try {
      int baseDateYear, baseDateMonth, baseDateDay;
      baseDateYear = Integer.parseInt(baseDate.substring(0, 4));
      baseDateMonth = Integer.parseInt(baseDate.substring(5, 7));
      baseDateDay = Integer.parseInt(baseDate.substring(8, 10));

      int targetDateYear, targetDateMonth, targetDateDay;
      targetDateYear = Integer.parseInt(targetDate.substring(0, 4));
      targetDateMonth = Integer.parseInt(targetDate.substring(5, 7));
      targetDateDay = Integer.parseInt(targetDate.substring(8, 10));

      val = getDistanceDay(baseDateYear, baseDateMonth, baseDateDay, targetDateYear,
          targetDateMonth, targetDateDay);
    } catch (NumberFormatException e) {
    }
    return val;
  }

  private static int getDistanceDay(int baseYear, int baseMonth, int baseDay, int targetYear,
      int targetMonth, int targetDay) {
    Calendar baseCalendar = Calendar.getInstance();
    baseCalendar.set(Calendar.YEAR, baseYear);
    baseCalendar.set(Calendar.MONTH, baseMonth - 1);
    baseCalendar.set(Calendar.DAY_OF_MONTH, baseDay);
    baseCalendar.set(Calendar.HOUR, 0);
    baseCalendar.set(Calendar.MINUTE, 0);
    baseCalendar.set(Calendar.SECOND, 0);

    Calendar targetCalendar = Calendar.getInstance();
    targetCalendar.set(Calendar.YEAR, targetYear);
    targetCalendar.set(Calendar.MONTH, targetMonth - 1);
    targetCalendar.set(Calendar.DAY_OF_MONTH, targetDay);
    targetCalendar.set(Calendar.HOUR, 0);
    targetCalendar.set(Calendar.MINUTE, 0);
    targetCalendar.set(Calendar.SECOND, 0);

    return (int) ((targetCalendar.getTimeInMillis() - baseCalendar.getTimeInMillis())
        / (24 * 60 * 60 * 1000));
  }

  /**
   * 문자열을 int로 변환
   * 
   * @param text
   * @return
   */
  public static int parseInt(String str) {
    try {
      return Integer.parseInt(str);
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  /**
   * Ajax 요청 결과 JsonObject 반환
   * 
   * @param result   요청 결과
   * @param errorMsg 오류 메시지
   * @return
   */
 
  public static JsonObject getResultObject(boolean result, String errorMsg) {
    return getResultObject(result, errorMsg, null);
  }
 
  
  public static JsonObject getRsObject(String result, String errorMsg) {
	  return getResultObject(result, errorMsg);
  }

  /**
   * Ajax를 통한 등록, 수정 및 삭제에 대한 처리 결과 JsonObject 반환
   * 
   * @param result   요청 결과
   * @param errorMsg 오류 메시지
   * @param data     결과 데이터
   * @return
   */
  /*
  public static JsonObject getResultObject(boolean result, String errorMsg, JsonElement data) {
    JsonObject resultObj = new JsonObject();
    resultObj.addProperty("result", result);
    resultObj.addProperty("errorMsg", errorMsg);
    if (data != null) {
      resultObj.add("data", data);
    }
    return resultObj;
  }
  */
  public static JsonObject getResultObject(String result, String errorMsg) {
      JsonObject resultObject = new JsonObject();
      resultObject.addProperty("resultCode", result);
      resultObject.addProperty("resultMsg", errorMsg);

      return resultObject;
  }
  public static JsonObject getResultObject(boolean result, String errorMsg, Object data) {
      JsonObject resultObject = new JsonObject();
      resultObject.addProperty("resultCode", result);
      resultObject.addProperty("resultMsg", errorMsg);
      resultObject.addProperty("data", data != null ? data.toString() : ""); // 데이터가 null이 아니면 toString()을 호출하여 문자열로 변환

      return resultObject;
  }
  
  /**
   * 문자열을 휴대폰 번호 형식으로 변환
   * 
   * @param str
   * @return
   */
  public static String convertPhoneStr(String str) {
    if (isNotEmpty(str)) {
      return str.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
    } else {
      return "";
    }
  }

  /**
   * 문자열을 숫자만 남도록 변환
   * 
   * @param str
   * @return
   */
  public static String convertNumberStr(String str) {
    if (isNotEmpty(str)) {
      return str.replaceAll("[^0-9]", "");
    } else {
      return "";
    }
  }
  
  /**
   * Response Header 생성
   * <pre>Response Header 생성</pre>
   * @param response
   * @param response
   * @return
   * @exception IOException, BaseException
   */
  public static HttpHeaders requestHeader() throws IOException {

      HttpHeaders headers = new HttpHeaders();
      headers.add(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
      //headers.add(HttpHeaders.ACCEPT_ENCODING, "application/json;charset=UTF-8");
      //headers.add(HttpHeaders.ACCEPT, "application/json");
      //headers.add("Cache-Control", "no-cache");
      //headers.set("Pragma", "no-cache");

      return headers;
  }

  /**
   * Response Header 생성
   * <pre>Response Header 생성</pre>
   * @param response
   * @param response
   * @return
   * @exception IOException, BaseException
   */
  public static HttpHeaders requestHeader(String dbn) throws IOException {

      HttpHeaders headers = new HttpHeaders();

      if (dbn != "" || dbn != null) {
          headers.add(HttpHeaders.CONTENT_TYPE, dbn);
      }else {
          headers.add(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
      }

      //headers.add(HttpHeaders.ACCEPT_ENCODING, "application/json;charset=UTF-8");
      //headers.add(HttpHeaders.ACCEPT, "application/json");
      //headers.add("Cache-Control", "no-cache");
      //headers.set("Pragma", "no-cache");

      return headers;
  }

  public static HttpHeaders badResponseHeader() throws IOException {

      HttpHeaders responseHeaders = new HttpHeaders();
      responseHeaders.set("Content-Type", "text/plain; charset=utf-8");
      responseHeaders.setContentLength(0);
      responseHeaders.setCacheControl("no-store,no-cache"); //캐싱 설정 X
      responseHeaders.setPragma("no-cache"); //캐싱 설정 X

      return responseHeaders;
  }
  
  
  public static String getClientIP(HttpServletRequest request) {
      String[] headers = {"Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR", "X-Real-IP", "X-RealIP", "REMOTE_ADDR"};
      String ip = request.getHeader("X-Forwarded-For");

      for (String header : headers) {
          if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
              ip = request.getHeader(header);
          }
      }

      if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
          ip = request.getRemoteAddr();
      }

      if(ip.equals("0:0:0:0:0:0:0:1")){
          ip = "127.0.0.1";
      }

      return ip;
  }
  
  public static String generateFailJson() {	//api Catch 부분
	  return "{\"resultCode\": 401, \"resultMsg\": \"Fail\"}";
  }
  
  public static String generateResJson(Object data, String gbn) {
    String responseData = new Gson().toJson(data);
    if (responseData != null && !responseData.isEmpty()) {
    	if(!("S").equals(gbn)){
    		if(data.equals(0)) {	//update, insert 실패
    			return "{\"resultCode\": 400, \"resultMsg\": \"Fail\"}";
    		}else {	//update, insert 성공
    			return "{\"resultCode\": 200, \"resultMsg\": \"Success\"}";
    		}
    	}else {	//조회성공
    		return "{\"resultCode\": 200, \"resultMsg\": \"Success\", \"data\": " + responseData + "}";
    	}
    } else {	//조회실패, update 실패
        return "{\"resultCode\": 400, \"resultMsg\": \"Fail\"}";
    }
  }

}
