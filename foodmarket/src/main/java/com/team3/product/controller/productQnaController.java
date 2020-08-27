package com.team3.product.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team3.member.service.MemberService;
import com.team3.member.vo.MemberVO;
import com.team3.product.service.productQnaService;
import com.team3.product.vo.PageCriteria;
import com.team3.product.vo.PagingMaker;
import com.team3.product.vo.pd_reviewVO;
import com.team3.product.vo.productQnaVO;
import com.team3.product.vo.productVO;



@Controller
@RequestMapping("/product/*")
public class productQnaController {

	@Inject
	productQnaService productqnaservice ;
	
	@Inject
	MemberService memservice;
	
	
	private static final Logger logger = LoggerFactory.getLogger(productQnaController.class);

	
	//----------------------   상품문의 입력페이지  ---------------------------
	
	@RequestMapping(value ="/qnawrite", method = RequestMethod.GET)
	public void writeGET(@RequestParam(value="pd_idx",defaultValue="0") int pd_idx, productVO productVO,MemberVO mvo, productQnaVO qvo, Model model,HttpSession session)throws Exception {
		
		logger.info("게시글 입력~~~");
	
		String mb_id = (String)session.getAttribute("mb_id");
		qvo.setMb_id(mb_id);
		
		qvo.setPd_idx(pd_idx);
		model.addAttribute("qvo",qvo);
		
		System.out.println("세션2"+mb_id);
		System.out.println(qvo.getMb_id());
		System.out.println(qvo.getPd_idx());
	
	}
	


	//-----------------------  상품문의 처리페이지  ---------------------------
		@RequestMapping(value ="/qnawrite", method = RequestMethod.POST)
		@ResponseBody
		public void writePost(@RequestParam(value="pd_idx",required = false) Integer pd_idx,productVO productVO,productQnaVO qvo, Model model, RedirectAttributes reAttr,HttpServletRequest request)throws Exception{		//리턴타입 string 이라는거는 직접view를 보여주겠다!
		logger.info("롸이트포스트!  "); 
		logger.info(qvo.toString());
	
	
		model.addAttribute("qvo",qvo);

		System.out.println("컨트롤러:"+ qvo.getMb_id());
		System.out.println("컨트롤러:"+ qvo.getPd_idx());
		System.out.println("컨트롤러:"+ qvo.getQna_content());
		
	
		productqnaservice.qnainsert(qvo);		//처리  
		
		
		reAttr.addFlashAttribute("result", "success"); 

		}
		
		

	
		
	//------------------------  답글 작성화면 페이지   ----------------------------
	@RequestMapping(value ="/qnareplywrite", method = RequestMethod.GET)
	public void replywriteGET(@RequestParam(value="pd_idx",defaultValue="0") int pd_idx,@RequestParam("qna_num")int qna_num,@ModelAttribute productQnaVO qvo, Model model,HttpServletRequest request ,HttpSession session)throws Exception {
		logger.info("게시글 입력~~~");

		String mb_id = (String)session.getAttribute("mb_id");
		qvo.setMb_id(mb_id);
		qvo.setPd_idx(pd_idx);
		
		
		model.addAttribute("qvo",qvo);

		
		System.out.println("reply Mb_id:"+qvo.getMb_id());
		System.out.println("reply Pd_idx:"+qvo.getPd_idx());
		System.out.println("reply:"+qvo.getQna_re_ref());
		System.out.println("reply:"+qvo.getQna_re_lev());
		System.out.println("reply:"+qvo.getQna_re_seq());
		System.out.println("reply Qna_num:"+qvo.getQna_num());

		
		
	}
	
	
	
//----------------------   답글작성 처리페이지  ------------------------------
	
	
	@RequestMapping(value ="/qnareplywrite", method = RequestMethod.POST)
	@ResponseBody
	public void replywritePost(HttpSession session,productQnaVO qvo,Model model, RedirectAttributes reAttr)throws Exception{		//리턴타입 string 이라는거는 직접view를 보여주겠다!
	logger.info("답변답변ㅂ답변입니당!  "); 
	logger.info(qvo.toString());
	
	String mb_id = (String)session.getAttribute("mb_id");
	qvo.setMb_id(mb_id);
	
	System.out.println(qvo.getQna_content());
	productqnaservice.qnareplyupdate(qvo);
	System.out.println("업데이답답답");
	productqnaservice.qnareplywrite(qvo);
	System.out.println("인설트리플 완료 ");

	
	reAttr.addFlashAttribute("result", "success"); 
	

	}
		

	//구매자 마이페이지에서 불러오기 
	//기존 게시판 목록과 같은 형식
	@RequestMapping(value="/productQnaListMember", method=RequestMethod.GET)
	public void productWishListMember(productVO productVO , productQnaVO productQnaVO, PageCriteria pCri, Model model, HttpSession session) throws Exception{
		//세션값 가져오기
		String mb_id = session.getAttribute("mb_id").toString();
		productQnaVO.setMb_id(mb_id);	
		//id값 기준으로 
		//List를 불러와서 
		
		Map map = new HashMap();
		map.put("mb_id",mb_id);
		map.put("startPage", pCri.getStartPage());
		map.put("numPerPage", pCri.getNumPerPage());
		
		//join
		List<productQnaVO> QnaListMember = productqnaservice.qnaListMemberCriteria(map);
		
		model.addAttribute("QnaListMember",QnaListMember);
		
		PagingMaker pagingMaker = new PagingMaker();
		pagingMaker.setCri(pCri);		
		pagingMaker.setTotalData(productqnaservice.qnaListMemberCountData(productQnaVO));
		model.addAttribute("pagingMaker", pagingMaker);
		
		
		System.out.println("QnaListMember :     "+QnaListMember);
		System.out.println(mb_id+"의 리뷰 갯수 :     "+productqnaservice.qnaListMemberCountData(productQnaVO));
	}
	
	
	
	
}
