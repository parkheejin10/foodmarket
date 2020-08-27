package com.team3.product.dao;

import java.util.List;
import java.util.Map;

import com.team3.product.vo.pd_reviewVO;

public interface productReviewDAO {

	public void reviewWrite(pd_reviewVO pd_reviewVO) throws Exception;
	
	public int reviewCountData() throws Exception;

	public List<pd_reviewVO> reviewListCriteria(Map map) throws Exception;
	//판매자 페이지	
	public int reviewListCountData(pd_reviewVO pd_reviewVO) throws Exception;
	
	public double reviewStarAVG(int product_pd_idx)  throws Exception;
	
	// 구매자 기준 list
	public List<pd_reviewVO> reviewListMemberCriteria(Map map) throws Exception;

	public int reviewListMemberCountData(pd_reviewVO pd_reviewVO) throws Exception;
	
}
