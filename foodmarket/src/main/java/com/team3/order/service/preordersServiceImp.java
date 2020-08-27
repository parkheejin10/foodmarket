
package com.team3.order.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.team3.order.dao.preordersDAO;
import com.team3.order.vo.preordersVO;

@Service
public class preordersServiceImp implements preordersService {

	@Inject
	preordersDAO preordersdao;
	//임시 결제 정보 추가
	@Override
	public void insertorder(preordersVO preordersvo) {
		preordersdao.insertorder(preordersvo);
	}
	//임시 결제 정보 조회
	@Override
	public List<preordersVO> listpre(String mb_id) {
	
		return preordersdao.listpre(mb_id);
	}
	//결제할 총 금액
	@Override
	public int preSumMoney(String mb_id) {
		
		return preordersdao.preSumMoney(mb_id);
	}
	//임시 결제 정보 유무 확인
	@Override
	public int countpre(String mb_id) {
		// TODO Auto-generated method stub
		return preordersdao.countpre(mb_id);
	}
	//결제 정보 삭제
	@Override
	public void deletepre(String mb_id) {
		preordersdao.deletepre(mb_id);
	}

}

