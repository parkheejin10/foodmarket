package com.team3.product.service;

import java.util.List;
import java.util.Map;

import com.team3.product.vo.pdReviewVO;
import com.team3.product.vo.productVO;

public interface productReviewService {

	//리뷰 입력하기 
	public void reviewWrite(pdReviewVO pd_reviewVO) throws Exception;
	
	//리뷰 전체 갯수 세기
	public int reviewCountData() throws Exception;
	
	//해당 상품에 대한 리뷰 리스트 만들기 
	//상품 번호 들고가서 리뷰 list 불러오기 
	public List<pdReviewVO> reviewListCriteria(Map map) throws Exception;
	
	//상품 번호 가지고 가서 review list 최대갯수 구하기 
	public int reviewListCountData(pdReviewVO pd_reviewVO) throws Exception;
	
	//평점 평균 구하기
	public double reviewStarAVG(int product_pd_idx) throws Exception;
	
	
	//구매자 기준 리뷰 리스트 만들기 
	public List<pdReviewVO> reviewListMemberCriteria(Map map) throws Exception;
	
	//구매자 id 가지고 가서 review list 최대갯수 구하기 
	public int reviewListMemberCountData(pdReviewVO pd_reviewVO) throws Exception;
}
