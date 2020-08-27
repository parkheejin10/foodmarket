package com.team3.product.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.team3.member.vo.MemberVO;
import com.team3.product.dao.productDAO;
import com.team3.product.vo.FindCriteria;
import com.team3.product.vo.productVO;


@Service
public class productServiceImpl implements productService {
	
	@Inject
	private productDAO productDAO;
	
	@Override
	public void write(productVO productvo) throws Exception{
		productDAO.insert(productvo);
	}
	
	@Override
	public productVO read(Integer bid) throws Exception{
		return productDAO.read(bid);
	}
//	@Override
//	public productVO readtest(Integer bid) throws Exception{
//		return productDAO.readtest(bid);
//	}
	
	@Override
	public void modify(productVO productvo) throws Exception{
		productDAO.update(productvo);
	}
	
	@Override
	public void hitUp(Integer bid) throws Exception{
		productDAO.hitUp(bid);
	}
	
	@Override
	public void remove(Integer bid) throws Exception{
		productDAO.delete(bid);
	}
	
//	@Override
//	public List<productVO> list() throws Exception{
//		return productDAO.list();
//	}
//	
	
	
	//판매자 페이지 
	@Override
	public List<productVO> sellerListCriteria(Map map) throws Exception{
		return productDAO.sellerListCriteria(map);
	}
	//판매자 페이지
	@Override
	public int sellerListCountData(MemberVO mvo) throws Exception{
		return productDAO.sellerListCountData(mvo);
	} 
	
	//상품 전체 갯수 세기
	@Override
	public int listCountData() throws Exception {
		return productDAO.listCountData();
	}
	
	@Override
	public List<productVO> listFind(FindCriteria findCri) throws Exception{
		return productDAO.listFind(findCri);
	}
	
	@Override
	public int findCountData(FindCriteria findCri) throws Exception{
		return productDAO.findCountData(findCri);
	}


}
