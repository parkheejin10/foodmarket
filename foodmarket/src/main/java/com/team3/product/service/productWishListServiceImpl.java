package com.team3.product.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

import com.team3.product.dao.productReviewDAO;
import com.team3.product.dao.productWishListDAO;
import com.team3.product.vo.pdReviewVO;
import com.team3.product.vo.pdWishlistVO;
import com.team3.product.vo.productVO;


@Service
public class productWishListServiceImpl implements productWishListService {
	
	@Inject
	private productWishListDAO productWishListDAO;
	
	@Override
	public int wishListSelect(pdWishlistVO pd_wishlistVO) throws Exception{
		return productWishListDAO.wishListSelect(pd_wishlistVO);
	} 
	
	@Override
	public void wishListWrite(pdWishlistVO pd_wishlistVO) throws Exception{
		productWishListDAO.wishListWrite(pd_wishlistVO);
	}
	
	@Override
	public int wishListCountData() throws Exception {
		return productWishListDAO.wishListCountData();
	}
	
	
	//삭제
	@Override
	public void wishListDelete(pdWishlistVO pd_wishlistVO) throws Exception{
		productWishListDAO.wishListDelete(pd_wishlistVO);
	}
	
	@Override
	public int wishListProductCountData(int product_pd_idx) throws Exception {
		return productWishListDAO.wishListProductCountData(product_pd_idx);
	}
	
	@Override
	public List<productVO> wishListMember(Map map) throws Exception{
		return productWishListDAO.wishListMember(map);
	}
	
	@Override
	public int wishListMemberCountData(String mb_id) throws Exception {
		return productWishListDAO.wishListMemberCountData(mb_id);
	}
	
//
//	@Override
//	public List<pd_reviewVO> reviewListCriteria(Map map) throws Exception{
//		return productReviewDAO.reviewListCriteria(map);
//	}
//

//	
//	//평점 평균 구하기
//	@Override
//	public double reviewStarAVG(int product_pd_idx) throws Exception{
//		return productReviewDAO.reviewStarAVG(product_pd_idx);
//	} 
//	

}
