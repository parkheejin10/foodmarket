
package com.team3.member.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.team3.member.vo.memberVO;
import com.team3.order.vo.ordersVO;


public interface memberService {

	//회원가입
	public void MemberJoin(memberVO mVO) throws Exception; 
	// 회원가입 이메일 인증
	public String emailCheck(memberVO mVO);
	// 비밀번호 찾기-조회
	public memberVO findCheck(memberVO mVO);
	// 비밀번호 찾기-수정
	public void ChangePw(memberVO mVO) throws Exception;
	// 회원가입 아이디 중복체크
	public String idCheck(memberVO mVO) throws Exception;
	// 로그인
	public boolean Login(memberVO mVO, HttpSession session) throws Exception;
	// 회원정보조회
	public memberVO MemberInfo(memberVO mVO) throws Exception;
	// 회원정보 수정
	public void MemberModify(memberVO mVO) throws Exception;
	// 회원정보-ID/PW 일치여부확인
	public boolean MemberCheck(memberVO mVO) throws Exception;
	// 비밀번호 변경 - 회원정보
	public void MemberModifyPw(memberVO mVO) throws Exception;
	// 회원 탈퇴
	public void MemberDelete(memberVO mVO) throws Exception;
	// 로그아웃	
	public void logout(HttpSession session);
	
	// 몰라이거 성균아 살려줘 이게뭐야
	public String adminId(memberVO mVO) throws Exception;
	
}

