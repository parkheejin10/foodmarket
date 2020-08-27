package com.team3.product.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

import com.team3.product.dao.productReviewDAO;
import com.team3.product.vo.pd_reviewVO;


@Service
public class productReviewServiceImpl implements productReviewService {
	
	@Inject
	private productReviewDAO productReviewDAO;
	
	@Override
	public void reviewWrite(pd_reviewVO pd_reviewVO) throws Exception{
		productReviewDAO.reviewWrite(pd_reviewVO);
	}
	
	@Override
	public int reviewCountData() throws Exception {
		return productReviewDAO.reviewCountData();
	}

	@Override
	public List<pd_reviewVO> reviewListCriteria(Map map) throws Exception{
		return productReviewDAO.reviewListCriteria(map);
	}

	@Override
	public int reviewListCountData(pd_reviewVO pd_reviewVO) throws Exception{
		return productReviewDAO.reviewListCountData(pd_reviewVO);
	} 
	
	
	//평점 평균 구하기
	@Override
	public double reviewStarAVG(int product_pd_idx) throws Exception{
		return productReviewDAO.reviewStarAVG(product_pd_idx);
	} 
	
	
	@Override
	public List<pd_reviewVO> reviewListMemberCriteria(Map map) throws Exception{
		return productReviewDAO.reviewListMemberCriteria(map);
	}

	@Override
	public int reviewListMemberCountData(pd_reviewVO pd_reviewVO) throws Exception{
		return productReviewDAO.reviewListMemberCountData(pd_reviewVO);
	} 
	
	

}
