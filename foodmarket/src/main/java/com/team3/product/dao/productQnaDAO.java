package com.team3.product.dao;

import java.util.List;
import java.util.Map;

import com.team3.product.vo.pageCriteria;
import com.team3.product.vo.pdReviewVO;
import com.team3.product.vo.productQnaVO;
import com.team3.product.vo.productVO;


public interface productQnaDAO {

	 public void qnainsert(productQnaVO qvo)throws Exception;
	
//	 public List<productQnaVO> list(productQnaVO qvo)throws Exception;
	 
	 public int getBoardReRef(productQnaVO qvo) throws Exception;
	 
	 public int maxnum(productQnaVO qvo)throws Exception;
	 
//	 public productQnaVO read(int qna_num)throws Exception;
	 
	 public productQnaVO getBoardReplyInfo(int qna_num)throws Exception;
	 
	 public void qnareplyupdate(productQnaVO qvo)throws Exception;
	 
	 public void qnareplywrite(productQnaVO qvo)throws Exception;

	 public List<productQnaVO> list(Map map)throws Exception;
		
	 public int countData(pageCriteria pCria)throws Exception;
	 
	// 구매자 기준 list
	public List<productQnaVO> qnaListMemberCriteria(Map map) throws Exception;

	public int qnaListMemberCountData(productQnaVO productQnaVO) throws Exception;
}
