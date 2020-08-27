
package com.team3.order.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.team3.cart.vo.cartVO;
import com.team3.order.vo.ordersVO;

@Repository
public class orderDAOImp implements orderDAO {
	
	@Inject
	SqlSession sqlSession;
	
	private static final String Namespace = "com.team3.mapper.orderMapper";
	
	//주문 정보 추가
	@Override
	public void insertorder(ordersVO odvo) {
		sqlSession.insert(Namespace + ".insertorder" , odvo);
	}
	//주문 상품정보 추가
	@Override
	public void insertorder_pd(ordersVO odvo) {
		sqlSession.insert(Namespace + ".od_pd_insert", odvo);
	}
	//주문 정보 조회
	@Override
	public List<ordersVO> listorder(ordersVO odvo) {
		return sqlSession.selectList(Namespace + ".selectorder", odvo);
	}
	//주문 상세정보 조회
	@Override
	public List<ordersVO> orderdetail(ordersVO odvo) {
		return sqlSession.selectList(Namespace + ".selectorderdetail", odvo);
	}
	//주문완료 후 장바구니 내 주문상품 삭제
	@Override
	public void deletecart(cartVO cartdto) {
		sqlSession.delete(Namespace + ".orderdeletecart", cartdto);
		
	}
	
	//주문 총 합계 금액
	@Override
	public String selectpay(ordersVO odvo) {
		
		return sqlSession.selectOne(Namespace + ".selectpay", odvo);
	}
	
	//배송상태 변경
	@Override
	public void update_delivery(ordersVO odvo) {
		sqlSession.update(Namespace+".update_delivery", odvo);
	}
	
	//주문완료 후 상품 재고 차감
	@Override
	public void updateSub_pd_amount(ordersVO odvo) {
		sqlSession.update(Namespace + ".updateSub_pd_amount", odvo);
		
	}
	//주문취소 후 상품 재고 추가
	@Override
	public void updateAdd_pd_amount(ordersVO odvo) {
		sqlSession.update(Namespace + ".updateAdd_pd_amount", odvo);
		
	}
	
	//주문취소 후 주문정보 삭제
	@Override
	public void delete_order(ordersVO odvo) {
		sqlSession.delete(Namespace + ".delete_order" , odvo);
	}
	
	//주문취소 후 주문상품정보 삭제
	@Override
	public void delete_order_pd(ordersVO odvo) {
		sqlSession.delete(Namespace + ".delete_order_pd" , odvo);
	}
	
}

