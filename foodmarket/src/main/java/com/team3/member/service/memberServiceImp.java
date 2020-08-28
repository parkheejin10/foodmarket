
package com.team3.member.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.team3.member.dao.memberDAO;
import com.team3.member.vo.memberVO;
import com.team3.order.vo.ordersVO;

@Service
public class memberServiceImp implements memberService {

	@Inject
	private memberDAO mDao;

	
	public void setDAO(memberDAO mDao) {
		this.mDao = mDao;
	}

	// 1. 회원가입
	@Override
	public void MemberJoin(memberVO mVO) throws Exception {
		mDao.MemberJoin(mVO);
	}
	@Override
	public memberVO findCheck(memberVO mVO) {
		memberVO result = mDao.findCheck(mVO);
		return result;
	}
	@Override
	public void ChangePw(memberVO mVO) throws Exception {
		mDao.ChangePw(mVO);
	}

	@Override
	public String emailCheck(memberVO mVO) {
		String result = mDao.emailCheck(mVO);
		System.out.println("서비스: " + result);
		System.out.println(mVO.getMb_email());
		return result;
	}
	// 1_1. ID 중복 체크
	@Override
	public String idCheck(memberVO mVO) throws Exception {
		String result = mDao.idCheck(mVO);
		System.out.println("서비스: " + result);
		System.out.println(mVO.getMb_id());
		return result;
	}

	// 2. 로그인 처리
	@Override
	public boolean Login(memberVO mVO, HttpSession session) throws Exception {
		boolean result = mDao.Login(mVO);
		
		if (result) {
			memberVO mVOInfo = MemberInfo(mVO);
			
			session.setAttribute("mb_id", mVOInfo.getMb_id());
			session.setAttribute("mb_name", mVOInfo.getMb_name());
			session.setAttribute("mb_seller", mVOInfo.getMb_seller());
		}
		return result;
	}

	// 3. 회원정보 출력
	@Override
	public memberVO MemberInfo(memberVO mVO) throws Exception {
		return mDao.MemberInfo(mVO);
	}
	// 3_1. 회원정보 수정
	@Override
	public void MemberModify(memberVO mVO) throws Exception {
		mDao.MemberModify(mVO);
	}
	// 3_2. 회원정보-ID/PW 일치여부확인
	@Override
	public boolean MemberCheck(memberVO mVO) throws Exception {
		return mDao.MemberCheck(mVO);
	}

	// 3_3. 비밀번호 변경 - 회원정보
	@Override
	public void MemberModifyPw(memberVO mVO) throws Exception {
		mDao.MemberModifyPw(mVO);
	}
	

	// 6. 로그아웃
	@Override
	public void logout(HttpSession session) {
		session.invalidate();
	}
	// 7. 회원탈퇴
	@Override
	public void MemberDelete(memberVO mVO) throws Exception {
		mDao.MemberDelete(mVO);
	}
	

	@Override
	public String adminId(memberVO mVO) throws Exception {
		String resultID = mDao.adminId(mVO);
		return resultID;
	}
}

