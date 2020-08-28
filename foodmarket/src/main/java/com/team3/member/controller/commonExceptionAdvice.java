package com.team3.member.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
/*
 * [예외처리]

 스프링 MVC를 사용할 때 Controller 쪽에서 Exception을 처리하기 위해서
 사용하는 방식
 
   . @ExceptionHandler 어노테이션을 사용하는 방식
   . @ControllerAdvice를 이용하는 방식
   . @ResponseStatus를 이용한 Http 상태코드 처리방식
   
   이중에서 가장 많이 사용하는 방식은 @ControllerAdvice를 이용하는 방식이다.
   
   @ControllerAdvice를 이용하는 방식: 공통의 Exception 처리 전용 객체를 사용하는 방법
   
   사용방식 >
     - 클래스에 @ControllerAdvice 라는 어노테이션을 선언
     - 각 메소드에 @ExceptionHandler를 이용해서 적절한 타입의 Exception을 처리

 */
@ControllerAdvice //@ControllerAdvice 현재 이 클래스가 컨트롤러에서 발생하는 예외를 전문적으로 처리하는 클래스임을 알려준다. 
public class commonExceptionAdvice {
	
	
//	@ExceptionHandler(Exception.class)
//	public String common(Exception e){
//		logger.info(e.toString());
//		
//		return "err_exception";
//	}
//	
//	@ExceptionHandler(Exception.class)
//	private ModelAndView errMAV(Exception e){
//		
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("/err_exception");
//		mav.addObject("exception", e);
//		
//		return mav;		
//	}
	
	
}
