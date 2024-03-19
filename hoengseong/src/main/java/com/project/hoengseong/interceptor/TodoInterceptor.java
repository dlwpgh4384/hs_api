package com.project.hoengseong.interceptor;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.project.hoengseong.util.CmmUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class TodoInterceptor implements HandlerInterceptor {
	
	/*
	 * preHandle()-컨트롤러 진입전에 실행
	 * postHandle()-컨트롤러 수행 후에 실행
	 * afterCompletion()-화면으로 가기 직전에 수행
	*/
		
	private static final String ALLOWED_IP = "127.0.0.1";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		log.debug("Request URL::" + request.getRequestURL().toString() + ":: "+ "Start Time=" + System.currentTimeMillis());
		request.setAttribute("startTime", startTime);
		//String clientIp = CmmUtil.getClientIP(request);
		System.out.println("========================preHandle=컨트롤러 진입전======================");
		//System.out.println(clientIp);
		/*
		String ipAddress = request.getRemoteAddr();
		String ipAddress2 = request.getHeader("x-forwarded-for");
		String idAddress3 = CmmUtil.getClientIP(request);
		String idAddress4 = request.getHeader("X-Real-IP");
		String idAddress5 = request.getLocalAddr();
		
		System.out.println(ipAddress);
		System.out.println(ipAddress2);
		System.out.println(idAddress3);
		System.out.println(idAddress4);
		System.out.println(idAddress5);
		System.out.println(InetAddress.getLocalHost().getHostAddress());
		*/
		
		/*
        if (!ipAddress.equals(ALLOWED_IP)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            System.out.println("========================ip1======================"+ALLOWED_IP);
            return false;
        }else {
        	System.out.println("========================ip2======================"+ALLOWED_IP);
        }
        */
        
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		log.debug("Request URL::" + request.getRequestURL().toString() + " Sent to Handler :: "+ "Current Time=" + System.currentTimeMillis());
		System.out.println("========================postHandle=컨트럴러 수행후======================");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		log.debug("Request URL::" + request.getRequestURL().toString() + ":: "+ "End Time=" + System.currentTimeMillis());
		log.debug("Request URL::" + request.getRequestURL().toString() + ":: "+ "Time Taken=" + (System.currentTimeMillis() - startTime));
		System.out.println("========================afterCompletion=화면으로가 가기전======================");
	}
	
}