package com.team3.product.service;

import java.util.List;
import java.util.Map;

import com.team3.product.vo.pdReviewVO;
import com.team3.product.vo.pdWishlistVO;
import com.team3.product.vo.productVO;

public interface productWishListService {
	
	//있는지 없는지
	public int wishListSelect(pdWishlistVO pd_wishlistVO) throws Exception;

	//insert
	public void wishListWrite(pdWishlistVO pd_wishlistVO) throws Exception;
	
	//count
	public int wishListCountData() throws Exception;

	//delete
	public void wishListDelete(pdWishlistVO pd_wishlistVO) throws Exception;
	
	//상품번호 기준 찜 갯수 가져오기 
	public int wishListProductCountData(int product_pd_idx) throws Exception;
	
	//구매자 기준 list들고오기 
	public List<productVO> wishListMember(Map map) throws Exception;
	
	public int wishListMemberCountData(String mb_id) throws Exception;
	
//	public double reviewStarAVG(int product_pd_idx) throws Exception;
}
