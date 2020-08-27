package com.team3.product.dao;

import java.util.List;
import java.util.Map;

import com.team3.member.vo.MemberVO;
import com.team3.product.vo.FindCriteria;
import com.team3.product.vo.productVO;

public interface productDAO {
	public void insert(productVO bvo) throws Exception;

	public productVO read(Integer bid) throws Exception;

	public void update(productVO bvo) throws Exception;

	public void hitUp(Integer bid) throws Exception;

	public void delete(Integer bid) throws Exception;

	// 판매자 페이지
	public List<productVO> sellerListCriteria(Map map) throws Exception;

	// 판매자 페이지
	public int sellerListCountData(MemberVO mvo) throws Exception;

	// 상품 전체 갯수 세기
	public int listCountData() throws Exception;

	public List<productVO> listFind(FindCriteria findCri) throws Exception;

	public int findCountData(FindCriteria findCri) throws Exception;
}
