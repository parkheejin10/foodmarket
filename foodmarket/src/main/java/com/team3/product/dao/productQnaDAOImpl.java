package com.team3.product.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.team3.product.vo.PageCriteria;
import com.team3.product.vo.pd_reviewVO;
import com.team3.product.vo.productQnaVO;
import com.team3.product.vo.productVO;




@Repository 
public class productQnaDAOImpl implements productQnaDAO{

	private static final String namespace="com.team3.mapper.qnamapper";
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void qnainsert(productQnaVO qvo) throws Exception {
		sqlSession.insert("productqnainsert", qvo);
	}

//	@Override
//	public List<productQnaVO> list(productQnaVO qvo) throws Exception {
//		
//		return sqlSession.selectList("productqnalist");
//	}

	@Override
	public int getBoardReRef(productQnaVO qvo) throws Exception {
		
		return sqlSession.selectOne("getBoardReRef",qvo);
	}

	@Override
	public int maxnum(productQnaVO qvo) throws Exception {
		
		return sqlSession.selectOne("maxnum", qvo);
	}

	@Override
	public void qnareplyupdate(productQnaVO qvo) throws Exception {
		
		sqlSession.update("qnareplyupdate", qvo);
	}

	@Override
	public void qnareplywrite(productQnaVO qvo) throws Exception {
		
		sqlSession.insert("qnareplywrite",qvo);
	}

	@Override
	public productQnaVO getBoardReplyInfo(int qna_num) throws Exception {
		
		return sqlSession.selectOne("getBoardReplyInfo",qna_num);
	}

	@Override
	public List<productQnaVO> list(Map map) throws Exception {
		
		return sqlSession.selectList("productqnalist", map);
	}

	@Override
	public int countData(PageCriteria pCria) throws Exception {
		
		return sqlSession.selectOne("countData", pCria);
	}


	
//	@Override
//	public productQnaVO read(int qna_num) throws Exception {
//		
//		return sqlSession.selectOne("qnaread",qna_num);
//	}


	
	@Override
	public List<productQnaVO> qnaListMemberCriteria(Map map) throws Exception {
		return sqlSession.selectList(namespace+".qnaListMemberCriteria",map);
	}

	@Override
	public int qnaListMemberCountData(productQnaVO productQnaVO) throws Exception {
		return sqlSession.selectOne(namespace+".qnaListMemberCountData",productQnaVO);
	}
	


}
