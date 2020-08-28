package com.team3.product.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.team3.product.vo.pdReviewVO;
import com.team3.product.vo.pdWishlistVO;
import com.team3.product.vo.productVO;


@Repository
public class productWishListDAOImpl implements productWishListDAO {
	
	private static final String namespace="com.team3.mapper.productWishListmapper";
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public int wishListSelect(pdWishlistVO pd_wishlistVO) throws Exception {
		return sqlSession.selectOne("wishListSelect",pd_wishlistVO);
	}
	
	@Override
	public void wishListWrite(pdWishlistVO pd_wishlistVO) throws Exception {
		sqlSession.insert(namespace + ".wishListWrite", pd_wishlistVO);
	}

	@Override
	public int wishListCountData() throws Exception {
		return sqlSession.selectOne("wishListCountData");
	}

	@Override
	public void wishListDelete(pdWishlistVO pd_wishlistVO) throws Exception {
		sqlSession.delete(namespace + ".wishListDelete", pd_wishlistVO);
	}
	
	@Override
	public int wishListProductCountData(int product_pd_idx) throws Exception {
		return sqlSession.selectOne("wishListProductCountData", product_pd_idx);
	}
	
	@Override
	public List<productVO> wishListMember(Map map) throws Exception {
		return sqlSession.selectList(namespace + ".wishListMember",map);
	}
	
	
	@Override
	public int wishListMemberCountData(String mb_id) throws Exception {
		return sqlSession.selectOne("wishListMemberCountData", mb_id);
	}
	
	
//	@Override
//	public List<pd_reviewVO> reviewListCriteria(Map map) throws Exception {
//		return sqlSession.selectList(namespace + ".reviewListCriteria",map);
//	}
//
//	@Override
//	public int reviewListCountData(pd_reviewVO pd_reviewVO) throws Exception {
//		return sqlSession.selectOne("reviewListCountData",pd_reviewVO);
//	}
//
//	@Override
//	public double reviewStarAVG(int product_pd_idx) throws Exception {
//	
//		return sqlSession.selectOne("reviewStarAVG",product_pd_idx);
//	}
}


