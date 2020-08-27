package com.team3.product.service;

import java.util.List;
import java.util.Map;

import com.team3.product.vo.PageCriteria;
import com.team3.product.vo.pd_reviewVO;
import com.team3.product.vo.productQnaVO;
import com.team3.product.vo.productVO;



public interface productQnaService {

	public void qnainsert(productQnaVO qvo)throws Exception;
	 
//	 public List<productQnaVO> list(productQnaVO qvo)throws Exception;
	 
	 public int getBoardReRef(productQnaVO qvo) throws Exception;

	 public int maxnum(productQnaVO qvo)throws Exception;
	 
//	 public productQnaVO read(int qna_num)throws Exception;
	 
	 public productQnaVO getBoardReplyInfo(int qna_num)throws Exception;

	 public void qnareplyupdate(productQnaVO qvo)throws Exception;
	 
	 public void qnareplywrite(productQnaVO qvo)throws Exception;
	 
//	 public List<productQnaVO> relist(productQnaVO qvo)throws Exception;
	 
	 public List<productQnaVO> list(Map map)throws Exception;
		
	 public int countData(PageCriteria pCria)throws Exception;
	 
	//구매자 기준 리뷰 리스트 만들기 
	public List<productQnaVO> qnaListMemberCriteria(Map map) throws Exception;
	
	//구매자 id 가지고 가서 review list 최대갯수 구하기 
	public int qnaListMemberCountData(productQnaVO productQnaVO) throws Exception;
}

