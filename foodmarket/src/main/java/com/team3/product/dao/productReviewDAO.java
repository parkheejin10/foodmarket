package com.team3.product.dao;

import java.util.List;
import java.util.Map;

import com.team3.product.vo.pdReviewVO;

public interface productReviewDAO {

	public void reviewWrite(pdReviewVO pd_reviewVO) throws Exception;
	
	public int reviewCountData() throws Exception;

	public List<pdReviewVO> reviewListCriteria(Map map) throws Exception;
	//판매자 페이지	
	public int reviewListCountData(pdReviewVO pd_reviewVO) throws Exception;
	
	public double reviewStarAVG(int product_pd_idx)  throws Exception;
	
	// 구매자 기준 list
	public List<pdReviewVO> reviewListMemberCriteria(Map map) throws Exception;

	public int reviewListMemberCountData(pdReviewVO pd_reviewVO) throws Exception;
	
}
