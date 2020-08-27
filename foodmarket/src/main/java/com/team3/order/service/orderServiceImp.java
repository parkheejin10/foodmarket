
package com.team3.order.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.team3.cart.vo.cartVO;
import com.team3.order.dao.orderDAO;
import com.team3.order.vo.ordersVO;

@Service
public class orderServiceImp implements orderService {

	@Inject
	orderDAO orderdao;
	//주문 정보 추가
	@Override
	public void insertorder(ordersVO odvo) {
		orderdao.insertorder(odvo);
	}
	//주문 상품정보 추가
	@Override
	public void insertorder_pd(ordersVO odvo) {
		orderdao.insertorder_pd(odvo);
	}
	//주문 상세정보 조회
	@Override
	public List<ordersVO> orderdetail(ordersVO odvo) {
		return orderdao.orderdetail(odvo);
	}
	//주문완료 후 상품 재고 차감
	@Override
	public void updateSub_pd_amount(ordersVO odvo) {
		orderdao.updateSub_pd_amount(odvo);
		
	}
	//주문완료 총 합계 금액
	@Override
	public String selectpay(ordersVO odvo) {
		
		return orderdao.selectpay(odvo);
	}
	//주문 정보 조회
	@Override
	public List<ordersVO> listorder(ordersVO odvo) {
		return orderdao.listorder(odvo);
	}
	//주문취소 후 상품 재고 추가
	@Override
	public void updateAdd_pd_amount(ordersVO odvo) {
		orderdao.updateAdd_pd_amount(odvo);
		
	}
	//주문완료 후 장바구니 내 주문상품 삭제
	@Override
	public void deletecart(cartVO cartdto) {
		orderdao.deletecart(cartdto);
		
	}
	//배송상태 변경
	@Override
	public void update_delivery(ordersVO odvo) {
		orderdao.update_delivery(odvo);
	}
	//주문취소 후 주문정보 삭제
	@Override
	public void delete_order(ordersVO odvo) {
		orderdao.delete_order(odvo);
	}
	//주문취소 후 주문 상품정보 삭제
	@Override
	public void delete_order_pd(ordersVO odvo) {
		orderdao.delete_order_pd(odvo);
	}

	
}

