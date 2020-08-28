package com.team3.product.service;

import java.util.List;
import java.util.Map;

import com.team3.member.vo.memberVO;
import com.team3.product.vo.findCriteria;
import com.team3.product.vo.productVO;


public interface productService {
	public void write(productVO productvo) throws Exception;
	
	public productVO read(Integer bid) throws Exception;
		
	public void modify(productVO productvo) throws Exception;
	
	public void hitUp(Integer bid) throws Exception;
	
	public void remove(Integer bid) throws Exception;
		
	//판매자 페이지
	public List<productVO> sellerListCriteria(Map map) throws Exception;
	//판매자 페이지
	public int sellerListCountData(memberVO mvo) throws Exception;
	
	//상품 전체 갯수 세기
	public int listCountData() throws Exception;
	
	public List<productVO> listFind(findCriteria findCri) throws Exception;
	
	public int findCountData(findCriteria findCri) throws Exception;
	
}
