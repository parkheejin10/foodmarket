package com.team3.product.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team3.member.service.MemberService;
import com.team3.member.vo.MemberVO;
import com.team3.product.service.productQnaService;
import com.team3.product.service.productReviewService;
import com.team3.product.service.productService;
import com.team3.product.vo.FindCriteria;
import com.team3.product.vo.PageCriteria;
import com.team3.product.vo.PagingMaker;
import com.team3.product.vo.pd_reviewVO;
import com.team3.product.vo.productQnaVO;
import com.team3.product.vo.productVO;

@Controller
@RequestMapping("/product/*")
public class productController {
	private static final Logger logger = LoggerFactory.getLogger(productController.class);
	
	@Inject
	private productService productService;
	
	@Inject
	private MemberService memberservice;
	
	@Inject
	private productReviewService productReviewService;
	
	@Inject
	private productQnaService productqnaservice;
	
	
	@RequestMapping(value="/productSellerList", method=RequestMethod.GET)
	public void productSellerList(MemberVO mvo, PageCriteria pCri, Model model, HttpSession session) throws Exception{
		logger.info("productSellerList");
		
		//세션값 가져오기
		String mb_id = session.getAttribute("mb_id").toString();
		mvo.setMb_id(mb_id);
//		System.out.println("\n\n 세션값!!!!   "+mb_id+"\n\n");
		int seller=(int) session.getAttribute("mb_seller");
		model.addAttribute("seller",seller);
		
		Map map = new HashMap();
		map.put("mb_id",mb_id);
		map.put("startPage", pCri.getStartPage());
		map.put("numPerPage", pCri.getNumPerPage());
		
		//파라미터값 2개 페이지값, member값
		model.addAttribute("list", productService.sellerListCriteria(map));
		
		PagingMaker pagingMaker = new PagingMaker();
		pagingMaker.setCri(pCri);
		
		
		pagingMaker.setTotalData(productService.sellerListCountData(mvo));
		
//		System.out.println("총 게시글 갯수 : "+productService.listCountData(pCri)+"\n");
		
		model.addAttribute("pagingMaker", pagingMaker);
	}
	
	
	
	//상품 목록 페이지
	@RequestMapping(value="/productList", method=RequestMethod.GET)
	public void list(@ModelAttribute("fCri") FindCriteria fCri, Model model) throws Exception{
		logger.info("productList");
		
//		model.addAttribute("list", productService.listCriteria(fCri));//기존 list
		model.addAttribute("list", productService.listFind(fCri));
//		System.out.println(productService.listFind(fCri));
		//join해서 list불러오기 
		
		PagingMaker pagingMaker = new PagingMaker();
		pagingMaker.setCri(fCri);
		
//		pagingMaker.setTotalData(productService.listCountData(fCri));//기존 list
		pagingMaker.setTotalData(productService.findCountData(fCri));
//		System.out.println("fCri.getFindType()   ====>  "+fCri.getFindType());
	
		model.addAttribute("pagingMaker", pagingMaker);		
	}
	
	//상품 상세 페이지
		@RequestMapping(value="/productDetail", method = RequestMethod.GET)
		public void productDetail(@RequestParam("pd_idx") int pd_idx, @ModelAttribute("fCri") FindCriteria fCri, pd_reviewVO pd_reviewVO, PageCriteria pCri, Model model,HttpSession session,@ModelAttribute productQnaVO qvo)
			throws Exception{
//			productService.hitUp(pd_idx); //조회수 증가
			logger.info("productDetail  "+pd_idx+"번 상품");
			//url만들기***********
			PagingMaker pagingMaker = new PagingMaker();
			pagingMaker.setCri(fCri);
			model.addAttribute("pagingMaker", pagingMaker);	
			//product 정보 가져오기 
			model.addAttribute("productVO",productService.read(pd_idx));
			
			//리뷰테이블
			//리뷰 갯수 구하기
			pd_reviewVO.setProduct_pd_idx(pd_idx);
			int reviewListCountData = productReviewService.reviewListCountData(pd_reviewVO);
			model.addAttribute("reviewListCountData", reviewListCountData);
			//해당 상품 평점 평균
			if(reviewListCountData!=0) { 
				double starAVG = productReviewService.reviewStarAVG(pd_idx);
				model.addAttribute("starAVG", starAVG);
			}
			
		
			
			//상품문의
			PagingMaker pagingMaker3 = new PagingMaker();
			pagingMaker.setCri(fCri);
			model.addAttribute("pagingMaker", pagingMaker3);	
		
			model.addAttribute("productVO",productService.read(pd_idx));
			//qna service.list(pd_idx)
			//review service.list(pd_idx)
			
//			logger.info("PageCriteria :  "+pCri.toString());
			
			String mb_id = (String)session.getAttribute("mb_id");
			qvo.setMb_id(mb_id);
			
			
			Map map = new HashMap();
			map.put("mb_id",mb_id);
			map.put("startPage", pCri.getStartPage());
			map.put("numPerPage", pCri.getNumPerPage());
			map.put("pd_idx", pd_idx);

			model.addAttribute("qnalist", productqnaservice.list(map)); 
			PagingMaker pagingmaker = new PagingMaker();
			pagingmaker.setCri(pCri);
			
			pagingmaker.setTotalData(productqnaservice.countData(pCri));
			model.addAttribute("pagingMaker", pagingmaker);	
			
			model.addAttribute("qvo",qvo);
			logger.info("productDetail  "+pd_idx+" 번 상품"+qvo.toString());
			
		}
	 

	
	//상품 삭제하기
	@RequestMapping(value="/productSellerDelete", method = RequestMethod.GET)
	public String delPage(@RequestParam("pd_idx") int pd_idx, PageCriteria pCri,
			RedirectAttributes reAttr) throws Exception{
		logger.info("productSellerDelete  "+pd_idx+"번 상품");
		productService.remove(pd_idx);
			
		reAttr.addAttribute("page", pCri.getPage());
		reAttr.addAttribute("numPerPage", pCri.getNumPerPage());
		
		reAttr.addFlashAttribute("result", "Success");
		
		return "redirect:/product/productSellerList";
	}
	

	
	
	//상품 수정하기
	//판매자가 수정할 수 있다. 
	@RequestMapping(value="/productSellerModify", method=RequestMethod.GET)
	public void modifyGET(@RequestParam("pd_idx") int pd_idx,PageCriteria pCri, Model model) 
			throws Exception{
		logger.info("productSellerModify"+pd_idx+"번 상품");
		//url만들기***********
		PagingMaker pagingMaker = new PagingMaker();
		pagingMaker.setCri(pCri);
		model.addAttribute("pagingMaker", pagingMaker);		
		
		model.addAttribute("productVO",productService.read(pd_idx));
		
	}
	
	//판매자가 수정할 수 있다. 
	@RequestMapping(value="/productSellerModify", method=RequestMethod.POST)
	public String modifyPOST(productVO productVO, PageCriteria pCri, RedirectAttributes reAttr) throws Exception{
		logger.info("productSellerModify  POST");
//		logger.info(pCri.toString());
		
		productService.modify(productVO);
		
		reAttr.addAttribute("page", pCri.getPage());
		reAttr.addAttribute("numPerPage", pCri.getNumPerPage());
		
		reAttr.addFlashAttribute("result", "Success");
		
//		logger.info(reAttr.toString());
				
		return "redirect:/product/productSellerList";
	}
	
	
	
	
	//상품 등록하기
	@RequestMapping(value="/productWrite", method =RequestMethod.GET)
	public void writeGET(Model model) throws Exception{
		
		model.addAttribute("pd_idx", productService.listCountData()+1);		
		logger.info("productWrite");
		
	}
	
	@RequestMapping(value="/productWrite", method=RequestMethod.POST)
	public String writePOST(productVO productVO, RedirectAttributes reAttr, MultipartHttpServletRequest mRequest) throws Exception{
		logger.info("productWrite POST");
		// @RequestParam("pd_category") String pd_category 
		//파일 업로드하기 
		boolean isUpload = false;
		
		//!!!!!!경로 주의하기
//		String uploadPath = "D:\\food_market_my\\food_market_my\\src\\main\\webapp\\resources\\pd_img_upload\\"; //본인 폴더경로
		String uploadPath = mRequest.getSession().getServletContext().getRealPath("/resources/pd_img_upload/"+productVO.getPd_category()+"/"); //임시경로
//		System.out.println("파일 업로드 임시 경로 !!!!!     "+mRequest.getSession().getServletContext().getRealPath("/resources/pd_img_upload/"+productVO.getPd_category()+"/"));
		//임시경로 : D:\workspace_Spring\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\food_market_my\resources\pd_img_upload\
	
		//파일 이름이 들어있다.  
		Iterator<String> iterator = mRequest.getFileNames();
		while(iterator.hasNext()){
			String uploadFileName = iterator.next();
			
			MultipartFile mFile = mRequest.getFile(uploadFileName);
			String originFileName = mFile.getOriginalFilename();
			String saveFileName = originFileName;
			 
			if(saveFileName != null && !saveFileName.equals("")){
				
				//파일 이름 중복될 때 시간을 이름에 추가해서 이름이 중복되지 않도록 만든다.
				if(new File(uploadPath + saveFileName).exists()){
					
					//.기준으로 자르기 
					int baseIndex = saveFileName.lastIndexOf('.');
					//파일 이름 바꾸기
					String newSaveFileName = saveFileName.substring(0,baseIndex)+"_"+System.currentTimeMillis();
					//파일 형식
					String newFormat = saveFileName.substring(baseIndex+1);
					//새로운 이름 
					saveFileName = newSaveFileName+"."+newFormat;
					
				}//if()
				
				try {
					mFile.transferTo(new File(uploadPath+saveFileName));
//					System.out.println("mFile"+mFile.getOriginalFilename());
//					System.out.println("\n saveFileName : "+saveFileName);
					
					
					if(productVO.getPd_img_name_f()==null) {
						productVO.setPd_img_name_f(saveFileName);
					}else if(productVO.getPd_img_name_s()==null){
						productVO.setPd_img_name_s(saveFileName);
					}
					
					
					
					//file이름을 file vo 객체에 넣고 
					//vo.setFileName(saveFileName)
					//dao.write(pd_num, vo)
					//db에 넣기???
				
					isUpload = true;
					
//					pd_imgVO.setPd_img_name(saveFileName);
//					if(saveFileName != null)
//					pd_imgService.write(pd_imgVO); img_mapper 사용 안하고 product_mapper에 같이 insert하기 
					//img_mapper 지우기 
					
					//파일 이름이 2개 
					//파일을 처음 넣을 때 pd_img_name_f 에 넣기 -> setPd_img_name_f
					//pd_img_name_f 이 null이 아닐 때 값이 있을 때 
					//pd_img_name_s 에 set하기 
					//pd_img_name_f 와 pd_img_name_s이 null이 아닐 때  값이 있을 때 
					//pd_img_name_s2 에 set하기 
					//if else if else
					
					
				} catch (IllegalStateException e) {
					e.printStackTrace();
					isUpload = false;
				} catch (IOException e2){
					e2.printStackTrace();
					isUpload = false;
				}				
			}//if()
		}//while()
		
		if(isUpload){
			
			//여기서 vo객체로 insert하기 
			productService.write(productVO);
			reAttr.addFlashAttribute("result", "Success");	
			
		} else {
			reAttr.addFlashAttribute("result", "false");
		}
		
		return "redirect:/product/productSellerList";
	}
	//상품 등록하기
	
	
	
}
