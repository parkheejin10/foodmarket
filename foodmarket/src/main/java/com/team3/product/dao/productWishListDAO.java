package com.team3.product.dao;

import java.util.List;
import java.util.Map;

import com.team3.product.vo.pd_reviewVO;
import com.team3.product.vo.pd_wishlistVO;
import com.team3.product.vo.productVO;

public interface productWishListDAO {
	
	public int wishListSelect(pd_wishlistVO pd_wishlistVO) throws Exception;

	public void wishListWrite(pd_wishlistVO pd_wishlistVO) throws Exception;
	
	public int wishListCountData() throws Exception;
	
	public void wishListDelete(pd_wishlistVO pd_wishlistVO) throws Exception;
	
	public int wishListProductCountData(int product_pd_idx) throws Exception;
	
	public List<productVO> wishListMember(Map map) throws Exception;
	
	public int wishListMemberCountData(String mb_id) throws Exception;
//
//	public List<pd_reviewVO> reviewListCriteria(Map map) throws Exception;
//	//판매자 페이지	
//	public int reviewListCountData(pd_reviewVO pd_reviewVO) throws Exception;
//	
//	public double reviewStarAVG(int product_pd_idx)  throws Exception;

}
