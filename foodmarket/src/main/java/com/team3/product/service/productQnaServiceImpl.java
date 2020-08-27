package com.team3.product.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.team3.product.dao.productQnaDAO;
import com.team3.product.vo.PageCriteria;
import com.team3.product.vo.pd_reviewVO;
import com.team3.product.vo.productQnaVO;


@Service
public class productQnaServiceImpl implements productQnaService {

	@Inject
	private productQnaDAO productqnadao;
	
	@Override
	public void qnainsert(productQnaVO qvo) throws Exception {
		
		
		int maxqna_num = productqnadao.maxnum(qvo);
		qvo.setQna_re_ref(maxqna_num);
		
		
		productqnadao.qnainsert(qvo);
		
	}
	
	
//	@Override
//	public List<productQnaVO> list(productQnaVO qvo) throws Exception {
//		
//		return productqnadao.list(qvo);
//	}


	@Override
	public int getBoardReRef(productQnaVO qvo) throws Exception {
		
		return productqnadao.getBoardReRef(qvo);
	}


	@Override
	public int maxnum(productQnaVO qvo) throws Exception {
		
		return productqnadao.maxnum(qvo);
	}


	@Override
	public void qnareplyupdate(productQnaVO qvo) throws Exception {
		
		productqnadao.qnareplyupdate(qvo);
	}


	@Override
	public void qnareplywrite(productQnaVO qvo) throws Exception {
		
		int maxqna_num = productqnadao.maxnum(qvo);
		qvo.setQna_re_ref(maxqna_num);
		
		productqnadao.qnareplywrite(qvo);
	}


	@Override
	public productQnaVO getBoardReplyInfo(int qna_num) throws Exception {
		
		return productqnadao.getBoardReplyInfo(qna_num);
	}


	@Override
	public List<productQnaVO> list(Map map) throws Exception {
		
		return productqnadao.list(map);
	}


	@Override
	public int countData(PageCriteria pCria) throws Exception {
		
		return productqnadao.countData(pCria);
	}





	


//	@Override
//	public productQnaVO read(int qna_num) throws Exception {
//		
//		return productqnadao.read(qna_num);
//	}

	//구매자 기준으로 Qna list 불러오기
	@Override
	public List<productQnaVO> qnaListMemberCriteria(Map map) throws Exception{
		return productqnadao.qnaListMemberCriteria(map);
	}

	@Override
	public int qnaListMemberCountData(productQnaVO productQnaVO) throws Exception{
		return productqnadao.qnaListMemberCountData(productQnaVO);
	} 
	
	
}
