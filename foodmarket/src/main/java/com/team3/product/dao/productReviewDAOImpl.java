package com.team3.product.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.team3.product.vo.pdReviewVO;


@Repository
public class productReviewDAOImpl implements productReviewDAO {
	
	private static final String namespace="com.team3.mapper.productReviewmapper";
	
	@Inject
	private SqlSession sqlSession;
	

	@Override
	public void reviewWrite(pdReviewVO pd_reviewVO) throws Exception {
		sqlSession.insert(namespace + ".reviewWrite", pd_reviewVO);
	}
	
	//전체 리뷰 갯수 불러오기
	@Override
	public int reviewCountData() throws Exception {
		int reviewCountData = sqlSession.selectOne("reviewCountData");
		
		if(reviewCountData ==0) {
			reviewCountData=1;
		}
		
		return reviewCountData;
	}

	@Override
	public List<pdReviewVO> reviewListCriteria(Map map) throws Exception {
		return sqlSession.selectList(namespace + ".reviewListCriteria",map);
	}

	@Override
	public int reviewListCountData(pdReviewVO pd_reviewVO) throws Exception {
		return sqlSession.selectOne("reviewListCountData",pd_reviewVO);
	}

	@Override
	public double reviewStarAVG(int product_pd_idx) throws Exception {
	
		return sqlSession.selectOne("reviewStarAVG",product_pd_idx);
	}
	
	@Override
	public List<pdReviewVO> reviewListMemberCriteria(Map map) throws Exception {
		return sqlSession.selectList(namespace + ".reviewListMemberCriteria",map);
	}

	@Override
	public int reviewListMemberCountData(pdReviewVO pd_reviewVO) throws Exception {
		return sqlSession.selectOne("reviewListMemberCountData",pd_reviewVO);
	}
}


