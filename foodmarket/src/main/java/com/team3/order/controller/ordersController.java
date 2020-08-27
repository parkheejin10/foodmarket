
package com.team3.order.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.team3.admin.service.AdminService;
import com.team3.admin.vo.RecommandVO;
import com.team3.cart.service.cartService;
import com.team3.cart.vo.cartVO;
import com.team3.member.service.MemberService;
import com.team3.member.vo.MemberVO;
import com.team3.order.service.orderService;
import com.team3.order.service.preordersService;
import com.team3.order.vo.ordersVO;
import com.team3.order.vo.preordersVO;

@Controller
@RequestMapping("/orders/*")
public class ordersController {
	
	@Inject
	preordersService preordersservice;
	
	@Inject
	MemberService ms;
	
	@Inject
	orderService orderservice;

	@Inject
	cartService cartservice;
	
	@Inject
	AdminService adminService;

	//주문취소
	@RequestMapping(value="orderCancel" , method = RequestMethod.POST)
	public String orderCancel1(HttpSession session,
			@RequestParam(value="chbox[]") List<Integer> chbox ,
			@ModelAttribute ordersVO odvo,Model model) throws ParseException {

		int pd_idx = 0;
		
			for(int i : chbox) {
				pd_idx = i;
				odvo.setPd_idx(pd_idx);
				//주문취소된 상품 재고수량 추가
				orderservice.updateAdd_pd_amount(odvo);
				//주문취소된 회원정보 삭제
				orderservice.delete_order(odvo);
				//주문취소된 상품정보 삭제
				orderservice.delete_order_pd(odvo);
			}
			return "/orders/orderlist";
	}
	
	//주문완료 정보 추가
	@RequestMapping(value="orderinsert" , method = RequestMethod.POST)
	public String orderinsert(HttpSession session ,
			@RequestParam(value="chbox[]") List<Integer> chbox ,
			@ModelAttribute ordersVO odvo,Model model) throws ParseException {
		
		//주문조회할 주문번호 생성
		 Calendar cal = Calendar.getInstance();
		 int year = cal.get(Calendar.YEAR);
		 String ym = year + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		 String ymd = ym +  new DecimalFormat("00").format(cal.get(Calendar.DATE));
		 String subNum = "";
		 
		 for(int i = 1; i <= 6; i ++) {
		  subNum += (int)(Math.random() * 10);
		 }
		 //주문번호
		 String od_num = ymd + "_" + subNum;
		 
		 odvo.setOd_num(od_num);
		 
		String mb_id = (String)session.getAttribute("mb_id");
		odvo.setMb_id(mb_id);
		int pd_idx = 0;
		cartVO cartvo = new cartVO();
		cartvo.setMb_id(mb_id);
		
		preordersVO prevo = new preordersVO();
		prevo.setMb_id(mb_id);

		if(mb_id != null) {

			for(int i : chbox) {
				pd_idx = i;
				//주문 vo에 상품번호 값 넣기
				odvo.setPd_idx(pd_idx);
				//장바구니 vo에  상품번호 값 넣기
				cartvo.setPd_idx(pd_idx);
				//주문 정보 추가
				orderservice.insertorder(odvo);
				//주문 상품정보 추가
				orderservice.insertorder_pd(odvo);
				//주문완료된 상품 장바구니 내역 삭제
				orderservice.deletecart(cartvo);
				//주문완료된 상품 재고수량 차감
				orderservice.updateSub_pd_amount(odvo);
			}
			
			// 추천상품 : recommand db에 담기
			// 1. od_num별 pd_idx 출력
			List<ordersVO> pd_idxList = adminService.selectOd_pd_idx(odvo); 
			
			RecommandVO rVO = new RecommandVO();
			for (int i = 0; i < pd_idxList.size(); i++) {
				for (int j = i+1; j < pd_idxList.size(); j++) {
					rVO.setRe_pd_idx(pd_idxList.get(i).getPd_idx());
					rVO.setRe_pd_idx2(pd_idxList.get(j).getPd_idx());
					// 2. select recommand score : 기존에 입력되어있는 데이터가 있는지 확인
					int score = adminService.selectScore(rVO);
					
					if (score>0) {
						rVO.setRe_score(score+1);
						// 3-1. insert recommand : 기존의 데이터가 없으면 insert
						adminService.updateScore(rVO);
					}else {
						//3 -2. update recommand score : 기존의 데이터가 있으면 update score+1
						adminService.insertRecommand(rVO);
					}
					
				}
			}
			//주문완료 후 임시결제 정보 삭제
			preordersservice.deletepre(mb_id);
			return "/orders/orderlist";
		}else {
			return "redirect:/member/login";
		}


	}

	@RequestMapping(value="orderinsert" , method = RequestMethod.GET)
	public String orderinsert1(HttpSession session ,
			@RequestParam(value="chbox[]") List<Integer> chbox ,
			@ModelAttribute ordersVO odvo,Model model) throws ParseException {
		
		
		 Calendar cal = Calendar.getInstance();
		 int year = cal.get(Calendar.YEAR);
		 String ym = year + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		 String ymd = ym +  new DecimalFormat("00").format(cal.get(Calendar.DATE));
		 String subNum = "";
		 
		 for(int i = 1; i <= 6; i ++) {
		  subNum += (int)(Math.random() * 10);
		 }
		 
		 String od_num = ymd + "_" + subNum;
		 
		 odvo.setOd_num(od_num);
		 
		String mb_id = (String)session.getAttribute("mb_id");
		odvo.setMb_id(mb_id);
		int pd_idx = 0;
//		int od_amount = 0;
		cartVO cartdto = new cartVO();
		cartdto.setMb_id(mb_id);
		
		preordersVO prevo = new preordersVO();
		prevo.setMb_id(mb_id);
		model.addAttribute("od_total" , odvo.getOd_total());
		System.out.println("인설트겟 : " + odvo.getOd_total());
		if(mb_id != null) {
//			for(int i=0; i < chbox.size(); i ++) {
//				pd_idx = chbox.get(i);
//				System.out.println("chbox.get(i)" + chbox.get(i));
//				odvo.setPd_idx(pd_idx);
//				od_amount = chbox1.get(i);
//				odvo.setOd_amount(od_amount);
//				
//				cartdto.setPd_idx(pd_idx);
//				
//				orderservice.insertorder(odvo);
//				orderservice.deletecart(cartdto);
//			}
			for(int i : chbox) {
				pd_idx = i;
				odvo.setPd_idx(pd_idx);
				
				cartdto.setPd_idx(pd_idx);
				
				orderservice.insertorder(odvo);
				orderservice.insertorder_pd(odvo);
				
//				orderservice.deletecart(cartdto);
			}
		
//			preordersservice.deletepre(mb_id);
			return "redirect:/orders/payment";
		}else {
			return "redirect:/member/login";
			
		}

	}

	//완료주문 목록
	@RequestMapping("/orderlist")
	public String orderlist(HttpSession session , @ModelAttribute ordersVO odvo, Model model) throws Exception {
		String returnPage ;
		String se_id = (String) session.getAttribute("mb_id");
		if (se_id == null) {
			returnPage = "redirect:/";
		}else {
		String mb_id = se_id;
		odvo.setMb_id(mb_id);

		List<ordersVO> odlist1 = orderservice.listorder(odvo);
		
		returnPage="orders/orderlist";
		model.addAttribute("odlist", odlist1);
		}
		return returnPage;
		
	}
	


	//완료주문 상세정보 조회
	@RequestMapping(value="orderDetailList", method = RequestMethod.GET)
	public void orderDetailList(HttpSession session ,
			@RequestParam("od_num") String od_num,
//			@RequestParam("od_delivery") String od_delivery,
			@ModelAttribute ordersVO odvo ,Model model) {
		String mb_id = (String)session.getAttribute("mb_id");
		odvo.setMb_id(mb_id);

		odvo.setOd_num(od_num);
//		odvo.setOd_delivery(od_delivery);
		//주문번호 값
		model.addAttribute("od_num" , odvo.getOd_num());
		//배송상태 값
		model.addAttribute("od_delivery" , odvo.getOd_delivery());
		//완료주문 상세정보 조회
		List<ordersVO> detail = orderservice.orderdetail(odvo);
		System.out.println("orderDetailList ~~  "+detail);
		model.addAttribute("detail" , detail);
	}
	
	/*--------------------------------------------------------------------*/
	//임시 결제 주문 컨트롤러
	
	//임시 결제 추가
	@RequestMapping(value="preinsert" , method= RequestMethod.POST)
	public String insertpreorder(HttpSession session,
			@RequestParam(value= "chbox[]") List<String> chArr , 
			@ModelAttribute preordersVO preordersvo) throws Exception{
		
		String mb_id = (String)session.getAttribute("mb_id");
	
		int cart_idx = 0;
		//임시 결제내역 유무 조회
		int countpre = preordersservice.countpre(mb_id);
		
		if(countpre == 0) {

			preordersvo.setMb_id(mb_id);
			for(String i : chArr) {
				cart_idx = Integer.parseInt(i);
				//결제할 상품들 상품번호
				preordersvo.setCart_idx(cart_idx);
				//임시 결제 추가
				preordersservice.insertorder(preordersvo);
			}
			System.out.println(preordersvo);
			return "redirect:/orders/prelist";
		}else {
			return "redirect:/cart/cart";
		}
	}
	@RequestMapping("preorderDelete")
	public String preDelete(HttpSession session , ordersVO odvo) {
		String mb_id = (String)session.getAttribute("mb_id");
		odvo.setMb_id(mb_id);
		
		preordersservice.deletepre(mb_id);
		
		return "redirect:/cart/list";
		
	}
	//결제 할 정보조회(회원 및 상품 정보)
	@RequestMapping("prelist")
	public ModelAndView listpreorder(HttpSession session , ModelAndView mav) throws Exception {
		
		String mb_id = (String)session.getAttribute("mb_id");
		MemberVO mvo = new MemberVO();
		mvo.setMb_id(mb_id);
		
		MemberVO membervo = ms.MemberInfo(mvo);

		Map<String, Object> map = new HashMap();
//		int countpre = preordersservice.countpre(mb_id);
		
			if(mb_id != null) {
				
				//결제할 정보 조회
				List<preordersVO> preodlist = preordersservice.listpre(mb_id);
				
				//결제 총 합계 금액
				int preSumMoney = preordersservice.preSumMoney(mb_id);
				//배송비 확인
				int delivery = preSumMoney >= 30000 ? 0 : 2500;
				//장바구니 금액
				map.put("preSumMoney", preSumMoney);
				//배송비
				map.put("delivery" , delivery);
				//주문할 총금액
				map.put("allsum" , preSumMoney + delivery);
				//결제할 정보내역
				map.put("list", preodlist);
				//장바구니 상품갯수
				map.put("count" , preodlist.size());
				
				
				mav.setViewName("/orders/preorder");
				mav.addObject("map" , map);
				mav.addObject("mem" , membervo);
				
				return mav;
			}else {
				return new ModelAndView("redirect:/member/login" , "" , null);
			}
	}
//	
//	
//	@RequestMapping("trans")
//	public String trans(HttpSession session , @ModelAttribute ordersVO odvo , 
//			Model model) {
//		
//		String mb_id = (String)session.getAttribute("mb_id");
//		odvo.setMb_id(mb_id);
//		
//		List<ordersVO> odlist = orderservice.listorder(odvo);
//		
//		model.addAttribute("odlist", odlist);
//		return "orders/trans";
//	}
		
	//주문 결제 api 팝업창
	@RequestMapping("/payment")
	public String pay(Model model , @RequestParam (value="od_total") int od_total,
			@RequestParam(value="od_pdname")String pd_name,
			@ModelAttribute ordersVO odvo) throws Exception {
		odvo.setOd_total(od_total);
		odvo.setPd_name(pd_name);
		//주문할 총 합계 금액
		model.addAttribute("num" , odvo.getOd_total());
		//주문할 상품명
		model.addAttribute("pd_name" , odvo.getPd_name());
		
		return "/orders/payment";
	}
}













