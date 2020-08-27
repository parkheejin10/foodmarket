package com.team3.cs.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.team3.cs.controller.csController;
import com.team3.cs.dto.AskVO;
import com.team3.cs.dto.Criteria;
import com.team3.cs.dto.CsFileVO;
import com.team3.cs.dto.CsVO;
import com.team3.cs.dto.PageMaker;
import com.team3.cs.service.csService;

@Controller
public class csController {
	
	private static final Logger logger = LoggerFactory.getLogger(csController.class);
	
	@Inject
	private csService service;
	
	// 자주 묻는 질문 페이지
	@RequestMapping(value = "/cs/ask", method = RequestMethod.GET)
	public String ask(Criteria cri, HttpServletRequest request, Model model) throws Exception{
		String keyword = request.getParameter("keyword");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pageStart", cri.getPageStart());
		map.put("perPageNum", cri.getPerPageNum());

		List<AskVO> askList = null;
		
		logger.info("ask");
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		if (keyword == null || keyword == "") {
			if(!(request.getParameter("type")==null||request.getParameter("type")=="")) {
				int type = Integer.parseInt((request.getParameter("type")));
				map.put("type", type);
				pageMaker.setTotalCount(service.countFindAsk(type));
				askList = service.findAsk(map);
			}else {				
			pageMaker.setTotalCount(service.countAsk());
			askList = service.selectAsk(map);
			}
		} else {
			pageMaker.setTotalCount(service.countSearchAsk(keyword));
			map.put("keyword", keyword);
			askList = service.searchAsk(map);
		}
		
		model.addAttribute("askList", askList);
		model.addAttribute("pageMaker", pageMaker);
		
		return "cs/ask";
	}
	
	// 문의 글쓰기 페이지 이동
	@RequestMapping("/cs/write")
	public String write() throws Exception{
		return "cs/csWrite";
	}
	
	// 문의 글쓰기 작업
	@RequestMapping(value="/cs/write", method=RequestMethod.POST)
	public String write(HttpServletRequest request, HttpSession session, CsVO csVO, CsFileVO csFileVO) throws Exception{
		
		csVO.setMember_mb_id(session.getAttribute("mb_id").toString());
		csVO.setCs_re_ref(service.maxCount()+1);
		csFileVO.setBoard_idx(service.maxCount()+1);
	
		service.writeCS(request, csVO, csFileVO);
		return "redirect:/cs/list";
	}
	
	// 주문내역 페이지에서 문의할 경우 글쓰기 페이지 이동
	@RequestMapping("/cs/odWrite")
	public String odWrite() throws Exception{
		return "cs/csWrite";
	}
	
	// 주문 내역 페이지에서 문의글 작성시 처리
	@RequestMapping(value="/cs/odWrite", method=RequestMethod.POST)
	public String odWrite(@RequestParam("od_num") String od_num,@RequestParam("pd_idx") int pd_idx, HttpServletRequest request, HttpSession session, CsVO csVO, CsFileVO csFileVO) throws Exception{
		
		csVO.setMember_mb_id(session.getAttribute("mb_id").toString());
		csVO.setCs_re_ref(service.maxCount()+1);
		csFileVO.setBoard_idx(service.maxCount()+1);
		csVO.setPd_idx(pd_idx);
		csVO.setOd_num(od_num);
		service.writeCS(request, csVO, csFileVO);
		return "redirect:/cs/list";
	}
	
	// 고객 문의 페이지 (로그인한 본인의 문의만 볼 수 있도록 설정)
	@RequestMapping(value="/cs/list", method=RequestMethod.GET)
	public String board(HttpSession session, Criteria cri, Model model) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("member_mb_id", session.getAttribute("mb_id"));
		map.put("pageStart", cri.getPageStart());
		map.put("perPageNum", cri.getPerPageNum());
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.countCS());
		
		List<CsVO> csList = service.selectCS(map);
		model.addAttribute("csList", csList);
		model.addAttribute("pageMaker", pageMaker);
		return "cs/csList";
	}
	
	// 고객 문의 상세 페이지
	@RequestMapping(value="/cs/view", method=RequestMethod.GET)
	public String read(CsVO csVO, Model model) throws Exception{
		logger.info("read");
		
		Map<String, Object> map = service.viewCS(csVO.getCs_idx());
		model.addAttribute("read", map.get("read"));
		model.addAttribute("fileList", map.get("fileList"));
		return "cs/csContent";
	}
	
	// 고객 문의 수정 페이지 이동
	@RequestMapping(value="/cs/update", method=RequestMethod.GET)
	public String update(@RequestParam int cs_idx, Model model) throws Exception{
		logger.info("read");
		Map<String, Object> map = service.viewCS(cs_idx);
		model.addAttribute("read", map.get("read"));
		return "cs/csUpdate";
	}
	
	// 고객 문의 수정 작업
	@RequestMapping(value="/cs/update", method=RequestMethod.POST)
	public String update(CsVO csVO) throws Exception{
		service.updateCS(csVO);
		return "redirect:/cs/list";
	}
	
	// 고객 문의 삭제 작업
	@RequestMapping(value="/cs/delete", method=RequestMethod.GET)
	public String delete(@RequestParam int cs_idx) throws Exception{
		service.deleteCS(cs_idx);
		return "redirect:/cs/list";
	}
	
	// 고객 문의 답글 페이지 이동
	@RequestMapping(value="/cs/reply", method=RequestMethod.GET)
	public String reply(@RequestParam int cs_idx, Model model) throws Exception{
		logger.info("read");
		Map<String, Object> map = service.viewCS(cs_idx);
		model.addAttribute("read", map.get("read"));
		return "cs/csFeedback";
	}
	
	// 고객 문의 답글 작성
	@RequestMapping(value="/cs/reply", method=RequestMethod.POST)
	public String reply(CsVO csVO) throws Exception{
		service.replyCS(csVO);
		return "redirect:/admin/csList";
	}
	
}