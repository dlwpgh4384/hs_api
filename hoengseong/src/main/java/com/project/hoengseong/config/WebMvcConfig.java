package com.project.hoengseong.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.hoengseong.interceptor.TodoInterceptor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry interceptorRegistry){
		System.out.println("===========webConfig interceptor 시작===========");
	    interceptorRegistry.addInterceptor(new TodoInterceptor())
	        .addPathPatterns("/**");	//일단 전체 경로에 interceptor 설정
	}
	
	// 나중에 token이나 jwt 사용 할때 swagger intercepter 부분에서 빼는 내용 나중에 필요시 추가하여 사용
	//https://wonsjung.tistory.com/447
	
	/*
    @Value("${spring.url}")
    private static String URI;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenInterceptor())
                .excludePathPatterns(URI + "/swagger-resources/**", URI + "/swagger-ui/**", URI + "/v3/api-docs", URI + "/api-docs/**")
                .excludePathPatterns("/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs", "/api-docs/**")
                .excludePathPatterns("/signUp", "/signIn", "/error/**", "/reissue")
                .addPathPatterns("/**");
    }

    @Bean
    public JwtTokenInterceptor jwtTokenInterceptor(){
        return new JwtTokenInterceptor();
    }
    */
}