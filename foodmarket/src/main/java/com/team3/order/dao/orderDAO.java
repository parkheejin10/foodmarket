
package com.team3.order.dao;

import java.util.List;

import com.team3.cart.vo.cartVO;
import com.team3.order.vo.ordersVO;

public interface orderDAO {
	//주문 정보추가
	void insertorder(ordersVO odvo);
	//주문 상품정보 추가
	void insertorder_pd(ordersVO odvo);
	//주문정보 조회
	List<ordersVO> listorder(ordersVO odvo);
	//주문 상세정보 조회
	List<ordersVO> orderdetail(ordersVO odvo);
	//주문완료된  후 장바구니 내 주문상품 삭제
	void deletecart(cartVO cartdto);
	//주문취소 후 정보 삭제
	void delete_order(ordersVO odvo);
	//주문취소 후 상품정보 삭제
	void delete_order_pd(ordersVO odvo);
	//배송상태 변경
	void update_delivery(ordersVO odvo);
	//주문완료 총 합계금액
	String selectpay(ordersVO odvo);
	//주문완료 후 상품 재고 차감
	void updateSub_pd_amount(ordersVO odvo);
	//주문취소 후 상품 재고 추가
	void updateAdd_pd_amount(ordersVO odvo);

}

