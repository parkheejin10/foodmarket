package com.team3.admin.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.team3.admin.vo.adminVO;
import com.team3.admin.vo.recommandVO;
import com.team3.cart.vo.cartVO;
import com.team3.member.vo.memberVO;
import com.team3.order.vo.ordersVO;
import com.team3.product.vo.productVO;

@Repository
public class adminDAOImp implements adminDAO{
	@Inject
	private SqlSession sqlSession;
	
	private static final String Namespace="com.team3.mapper.adminMapper";
	
	// 관리자 로그인
	@Override
	public String AdminLogin(adminVO avo) throws Exception {
		String am_id=sqlSession.selectOne(Namespace+".loginAdmin",avo);
		return am_id;
	}
	
	//============	메인	==============
	// 메인페이지 인기상품
	@Override
	public List<productVO> mostSoldItem() throws Exception {
		return sqlSession.selectList(Namespace+".mostSoldItem");
	}

	@Override
	public List<productVO> popularItem(Map<String, Integer> pd_idx) throws Exception {
		return sqlSession.selectList(Namespace + ".popularItem", pd_idx);
	}
	
	
	// ============= 방문자 저장/업데이트 ================
	@Override
	public boolean todayVisit() throws Exception {
		boolean todayVisit ; 
		String where= sqlSession.selectOne(Namespace + ".whereTodayVisit");
		if(where == null) {
			todayVisit = false;
		} else {
			todayVisit = true;
		}
		return todayVisit;
	}

	@Override
	public void insertTodayVisit() throws Exception{
		sqlSession.insert(Namespace + ".todayVisit");
	}
	@Override
	public void updateChrome() throws Exception{
		sqlSession.update(Namespace+".chrTodayVisit");
	}
	@Override
	public void updateEdge() throws Exception{
		sqlSession.update(Namespace+".edgTodayVisit");
	}
	@Override
	public void updateExplorer() throws Exception{
		sqlSession.update(Namespace+".expTodayVisit");
	}
	@Override
	public void updateSafari() throws Exception{
		sqlSession.update(Namespace+".safTodayVisit");
	}
	@Override
	public void updateEtc() throws Exception{
		sqlSession.update(Namespace+".etcTodayVisit");
	}
	// ============금일 방문자 통계 조회=============
	@Override
	public int visitCount() throws Exception {
		int visitCount = sqlSession.selectOne(Namespace+".visitCount"); 
		return visitCount; 
	}
	@Override
	public int chrome() throws Exception {
		int chrome = sqlSession.selectOne(Namespace+".chrome"); 
		return chrome;
	}

	@Override
	public int edge() throws Exception {
		int edge = sqlSession.selectOne(Namespace+".edge"); 
		return edge;
	}

	@Override
	public int explorer() throws Exception {
		int explorer = sqlSession.selectOne(Namespace+".explorer"); 
		return explorer;
	}

	@Override
	public int safari() throws Exception {
		int safari = sqlSession.selectOne(Namespace+".safari"); 
		return safari;
	}

	@Override
	public int etc() throws Exception {
		int etc = sqlSession.selectOne(Namespace+".etc"); 
		return etc;
	}
	
// ================== 방문자 그래프 =================	
	@Override
	public List<adminVO> monthVisit() throws Exception {
		return sqlSession.selectList(Namespace+".monthVisit");
	}
//==================== admin/index 페이지(빅데이터)  ============================

	// 신규 가입자
	@Override
	public int todayMember(Map<String, Object> map) throws Exception {
		int todayMember=sqlSession.selectOne(Namespace+".todayMember",map);
		return todayMember;
	}
	// 당일 주문량
	@Override
	public int todayOrder(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne(Namespace+".todayOrder", map);
	}
	// 당일 매출액
	@Override
	public String todaySales(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne(Namespace+".todaySales", map);
	}
	// 달 별 주문내역
	@Override
	public List<ordersVO> pdOrderList(String thisMonth) throws Exception {
		return sqlSession.selectList(Namespace+".pdOrderList",thisMonth);
	}
	// pd_idx로 카테고리 출력
	@Override
	public List<ordersVO> productPd_category(Map<String, Object> orderMap) throws Exception {
		return sqlSession.selectList(Namespace+".productPd_category", orderMap);
	}
	@Override
	public int orderTotal(int od_idx) throws Exception {
		return sqlSession.selectOne(Namespace+".");
	}
	
	
//==================== admin/index 페이지(빅데이터)  ============================
	
	@Override
	public int categoryProduct(String pd_category) throws Exception {
		return sqlSession.selectOne(Namespace+".categoryProduct",pd_category);
	}
	
	
//==================== 전체 회원/상품 리스트  ============================
	
	// 전체 회원 출력
	@Override
	public List<memberVO> MemberList(Map<String, Object> map) throws Exception {
		return sqlSession.selectList(Namespace+".selectMember",map);
	}
	// 전체 회원 수
	@Override
	public int MemberCount() throws Exception {
		return sqlSession.selectOne(Namespace+".memberCount");
	}
	// 전체 상품 출력
	@Override
	public List<productVO> ProductList(Map<String, Object> map) throws Exception {
		return sqlSession.selectList(Namespace+".productList",map);
	}	
	// 전체 상품 수
	@Override
	public int ProductCount() throws Exception {
		return sqlSession.selectOne(Namespace+".productCount");
	}
	// 판매자/구매자별 회원 목록
	@Override
	public List<memberVO> SellerList(Map<String, Object> map) throws Exception {
		return sqlSession.selectList(Namespace+".sellerList", map);
	}
	// 판매자/구매자별 회원 수
	@Override
	public int SellerCount(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne(Namespace+".sellerCount", map);
	}
	// 카테고리별 상품 출력
	@Override
	public List<productVO> CategoryList(Map<String, Object> map) throws Exception {
		return sqlSession.selectList(Namespace+".categoryList",map);
	}
	// 카테고리별 상품 수
	@Override
	public int categoryCount(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne(Namespace+".categoryCount", map);
	}
	
//==================== 판매자관련 리스트  ============================
	
	// 판매자별 상품 출력
	@Override
	public List<productVO> SellList(Map<String, Object> map) throws Exception {
		return sqlSession.selectList(Namespace+".sellList",map);
	}
	// 판매자별 상품 수
	@Override
	public int sellerProductCount(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne(Namespace+".sellerProductCount",map);
	}
	// 판매자+카테고리별 상품 출력
	@Override
	public List<productVO> sellerCategoryList(Map<String, Object> map) throws Exception {
		return sqlSession.selectList(Namespace+".sellerCategoryList", map);
	}
	// 판매자+카테고리별 상품 수
	@Override
	public int sellerCategoryCount(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne(Namespace+".sellerCategoryCount",map);
	}
	// 판매자별 판매 내역 - mb_id
	@Override
	public List<ordersVO> sellerOrderList(Map<String, Object> map) throws Exception {
		return sqlSession.selectList(Namespace+".sellerOrderList",map);
	}
	@Override
	public int sellProductCount(Map<String, Object> map) {
		return sqlSession.selectOne(Namespace+".sellProductCount",map);
	}
	
//==================== 구매자 관련 리스트  ============================
	
	// 구매자별 구매 내역
	@Override
	public List<ordersVO> orderList(Map<String, Object> map) throws Exception {
		return sqlSession.selectList(Namespace+".orderList", map);
	}
	
	// 구매자별 구매 상품 수
	@Override
	public int customerOrderCount(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne(Namespace+".customerOrderCount", map);
	}
	// 구매자별 장바구니 목록
	@Override
	public List<cartVO> cartList(Map<String, Object> map) throws Exception {
		return sqlSession.selectList(Namespace+".cartList",map);
	}
	// 구매자별 장바구니 수
	@Override
	public int cartCount(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne(Namespace+".cartCount", map);
	}
	
//============================ 상품 관련 리스트  ============================
	
	

//============================ 추천상품  ============================
	// 1. od_num별 pd_idx 출력
	@Override
	public List<ordersVO> selectOd_pd_idx(ordersVO odvo) {
		return sqlSession.selectList(Namespace+".selectOd_pd_idx",odvo);
	}
	// 2. select recommand score : 기존에 입력되어있는 데이터가 있는지 확인
	@Override
	public int selectScore(recommandVO rVO) {
		return sqlSession.selectOne(Namespace+".selectScore", rVO);
	}
	// 3-1. insert recommand : 기존의 데이터가 없으면 insert
	@Override
	public void insertRecommand(recommandVO rVO) {
		sqlSession.insert(Namespace+".insertRecommand", rVO);
	}
	// 3-2. update recommand score : 기존의 데이터가 있으면 update score+1
	@Override
	public void updateScore(recommandVO rVO) {
		sqlSession.update(Namespace+".updateScore",rVO);
	}
	// 최근 구매 상품
	@Override
	public int orderListMb_id(String mb_id) {
		return sqlSession.selectOne(Namespace+".orderListMb_id", mb_id);
	}
	// 최근 구매 상품과 관련된 추천 상품 리스트
	@Override
	public List<recommandVO> re_pd_idxList(int pd_idx) {
		return sqlSession.selectList(Namespace+".re_pd_idxList", pd_idx);
	}
	

}