package com.team3.product.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.team3.member.vo.memberVO;
import com.team3.product.vo.findCriteria;
import com.team3.product.vo.productVO;


@Repository
public class productDAOImpl implements productDAO {
	
	private static final String namespace="com.team3.mapper.productmapper";
	
	@Inject
	private SqlSession sqlSession;
	
	
	
	@Override
	public void insert(productVO productvo) throws Exception{
		sqlSession.insert(namespace + ".productinsert", productvo);
	}
	// 
	@Override
	public productVO read(Integer bid) throws Exception{
		return sqlSession.selectOne(namespace + ".read", bid);
	}
	// 
	@Override
	public void update(productVO bvo) throws Exception{
		sqlSession.update("productupdate", bvo);
	}
	// 
	@Override
	public void hitUp(Integer bid) throws Exception{
		sqlSession.update("hitUp", bid);
	}
	// 
	@Override
	public void delete(Integer bid) throws Exception{
		sqlSession.delete("productdelete", bid);
	}
	
	//판매자 페이지 
	@Override
	public List<productVO> sellerListCriteria(Map map) throws Exception{
		return sqlSession.selectList(namespace + ".sellerListCriteria",map);
	}
	//판매자 페이지 
	@Override
	public int sellerListCountData(memberVO mvo) throws Exception {
		return sqlSession.selectOne(namespace+".sellerListCountData", mvo);
	}
	//상품 전체 갯수 세기
	@Override
	public int listCountData() throws Exception {
		return sqlSession.selectOne(namespace+".listCountData");
	}
	// 
	@Override
	public List<productVO> listFind(findCriteria findCri) throws Exception{
		return sqlSession.selectList("listFind", findCri);
	}
	// 
	@Override
	public int findCountData(findCriteria findCri) throws Exception{
		return sqlSession.selectOne("findCountData", findCri);
	}
}


