
package com.team3.member.dao;


import java.util.List;

import com.team3.member.vo.memberVO;
import com.team3.order.vo.ordersVO;


public interface memberDAO {
	
	// ID중복체크
	public String idCheck(memberVO mVO) throws Exception;
	// email 중복체크
	public String emailCheck(memberVO mVO);
	// 비밀번호 찾기
	public memberVO findCheck(memberVO mVO);
	// 회원가입
	public void MemberJoin(memberVO mVO) throws Exception;
	// 로그인
	public boolean Login(memberVO mVO) throws Exception;
	// 회원정보
	public memberVO MemberInfo(memberVO mVO) throws Exception;
	// 회원정보-ID/PW 일치여부확인
	public boolean MemberCheck(memberVO mVO) throws Exception;
	// 회원정보 수정
	public void MemberModify(memberVO mVO) throws Exception;
	// 비밀번호 수정
	public void MemberModifyPw(memberVO mVO) throws Exception;
	// 비밀번호 찾기
	public void ChangePw(memberVO mVO) throws Exception;
	// 회원탈퇴
	public void MemberDelete(memberVO mVO) throws Exception;
	
	// 지워도 될까?
	public String adminId(memberVO mVO) throws Exception;
}

