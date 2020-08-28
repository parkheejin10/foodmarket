package com.team3.admin.dao;

import java.util.List;
import java.util.Map;

import com.team3.admin.vo.adminVO;
import com.team3.admin.vo.recommandVO;
import com.team3.cart.vo.cartVO;
import com.team3.member.vo.memberVO;
import com.team3.order.vo.ordersVO;
import com.team3.product.vo.productVO;

public interface adminDAO {
	public List<memberVO> MemberList(Map<String, Object> map)throws Exception;
	public List<memberVO> SellerList(Map<String, Object> map)throws Exception;
	// 방문자 정보 저장
	public boolean todayVisit() throws Exception;
	public void insertTodayVisit() throws Exception;	
	public void updateChrome() throws Exception;
	public void updateEdge() throws Exception;
	public void updateExplorer() throws Exception;
	public void updateSafari() throws Exception;
	public void updateEtc() throws Exception;
	
	// 방문자 정보 카운트
	public int visitCount() throws Exception;
	public int chrome() throws Exception;
	public int edge() throws Exception;
	public int explorer() throws Exception;
	public int safari() throws Exception;
	public int etc() throws Exception;
	
	public List<adminVO> monthVisit() throws Exception;
	
	public List<productVO> mostSoldItem() throws Exception;
	public List<productVO> popularItem(Map<String, Integer> map) throws Exception;
	
	public String AdminLogin(adminVO avo) throws Exception;
	public List<productVO> ProductList(Map<String, Object> map)throws Exception;
	public List<productVO> CategoryList(Map<String, Object> map)throws Exception;
	public List<productVO> SellList(Map<String, Object> map)throws Exception;
	public int MemberCount()throws Exception;
	public int ProductCount()throws Exception ;
	public int todayMember(Map<String, Object> map) throws Exception;
	public List<ordersVO> orderList(Map<String, Object> map) throws Exception;
	public int sellerProductCount(Map<String, Object> map) throws Exception;
	public int customerOrderCount(Map<String, Object> map) throws Exception;
	public int SellerCount(Map<String, Object> map) throws Exception;
	public int todayOrder(Map<String, Object> map) throws Exception;
	public String todaySales(Map<String, Object> map) throws Exception;
	public int categoryCount(Map<String, Object> map) throws Exception;
	public List<productVO> sellerCategoryList(Map<String, Object> map) throws Exception;
	public int sellerCategoryCount(Map<String, Object> map) throws Exception;
	public List<cartVO> cartList(Map<String, Object> map) throws Exception;
	public int cartCount(Map<String, Object> map) throws Exception;
	public int categoryProduct(String pd_category) throws Exception;
	public List<ordersVO> productPd_category(Map<String, Object> orderMap) throws Exception;
	public List<ordersVO> sellerOrderList(Map<String, Object> orderMap) throws Exception;
	public int orderTotal(int od_idx) throws Exception;
	public List<ordersVO> pdOrderList(String thisMonth)throws Exception;
	public List<ordersVO> selectOd_pd_idx(ordersVO odvo);
	public void insertRecommand(recommandVO rVO);
	public int selectScore(recommandVO rVO);
	public void updateScore(recommandVO rVO);
	public int orderListMb_id(String mb_id);
	public List<recommandVO> re_pd_idxList(int pd_idx);
	public int sellProductCount(Map<String, Object> map);
}
