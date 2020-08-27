
package com.team3.cart.dao;

import java.util.List;

import com.team3.cart.vo.cartVO;

public interface cartDAO {
	//장바구니 신규 추가
	void insert(cartVO cartdto);
	//장바구니 목록
	List<cartVO> listcart(String mb_id);
	//장바구니 선택삭제
	void delete(cartVO cartdto);
	//장바구니 전체삭제
	void deleteAll(String mb_id);
	//장바구니 금액
	int cartmoney(String mb_id);
	//장바구니 목록 동일상품 확인
	int countCart(String mb_id , int pd_idx);
	//장바구니 기존 상품 갯수추가
	void updateCart(cartVO cartdto);
	//장바구니 상품 수량변경
	void modifyCart(cartVO cartdto);
}

