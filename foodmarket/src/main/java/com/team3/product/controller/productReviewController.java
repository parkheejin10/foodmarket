package com.team3.product.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team3.product.service.productReviewService;
import com.team3.product.vo.PageCriteria;
import com.team3.product.vo.PagingMaker;
import com.team3.product.vo.pd_reviewVO;
import com.team3.product.vo.pd_wishlistVO;
import com.team3.product.vo.productVO;


@Controller
@RequestMapping("/product/*")
public class productReviewController {

	private static final Logger logger = LoggerFactory.getLogger(productReviewController.class);

	@Inject
	private productReviewService productReviewService;
	
	//리스트 불러오기
	//page 처리하기 
	//@ResponseBody *****
		@RequestMapping(value = "/productReviewList2/{pd_idx}/{page}", method = RequestMethod.GET)
		public @ResponseBody  ResponseEntity<Map<String, Object>> reListPage( pd_reviewVO pd_reviewVO, PageCriteria pCri,
				@PathVariable("page") Integer page, @PathVariable("pd_idx") Integer pd_idx) {
			logger.info("productReviewList2/{"+pd_idx+"}/"+page+"}");
			ResponseEntity<Map<String, Object>> resEntity = null;

			try {
//				logger.info("PageCriteria :  "+pCri.toString());
				
				Map map = new HashMap();
				map.put("product_pd_idx",pd_idx); //파라미터값으로 받아오기 //임의로 지정하였다.
				map.put("startPage", pCri.getStartPage());
				map.put("numPerPage", pCri.getNumPerPage());
				//파라미터값 2개 페이지값, member값
				
				List<pd_reviewVO> list = productReviewService.reviewListCriteria(map);
				
				if(page == null || page == 0) {
					page = 1;
				}
				
				pCri.setPage(page);
				PagingMaker pagingMaker = new PagingMaker();
				pagingMaker.setCri(pCri);
				
				pd_reviewVO.setProduct_pd_idx(pd_idx); //파라미터값으로 받아오기 //임의로 지정하였다.
				pagingMaker.setTotalData(productReviewService.reviewListCountData(pd_reviewVO));
				
				System.out.println("\n"+pd_idx+"번 글의  리뷰 갯수 : "+productReviewService.reviewListCountData(pd_reviewVO));
				
				Map<String, Object> pd_reviewMap = new HashMap<String, Object>();

				pd_reviewMap.put("list", list);
				pd_reviewMap.put("pagingMaker", pagingMaker);
//				System.out.println(pd_idx+"번 글의 리뷰 list!!!!!!!!!!!!!!!!!!           "+list.toString()+"\n");
				resEntity = new ResponseEntity<Map<String, Object>>(pd_reviewMap, HttpStatus.OK);

			} catch (Exception e) {
				e.printStackTrace();
				resEntity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
			}
			
			return resEntity;
		}
		
	
	
	//리뷰 쓰기
	@RequestMapping(value="/productReviewWrite", method=RequestMethod.GET)
	public void productReviewWriteGet(@RequestParam("pd_idx") int pd_idx, @RequestParam("od_idx") int od_idx, Model model) throws Exception{
		logger.info("productReviewWriteGet()");
//@RequestParam("pd_idx") int pd_idx, @RequestParam("od_idx") int od_idx,   //파라미터값으로 받아오기  
		model.addAttribute("pd_idx",pd_idx); //받아온 값 jsp에 뿌려주기
		model.addAttribute("od_idx",od_idx); //받아온 값 jsp에 뿌려주기
		model.addAttribute("pd_re_idx",productReviewService.reviewCountData()+1);
		System.out.println("리뷰 쓰러가요~~~     od_idx : "+od_idx+"     pd_idx : "+pd_idx);
		
	}
	
	@RequestMapping(value="/productReviewWrite", method=RequestMethod.POST)
	public String productReviewWritePOST(pd_reviewVO pd_reviewvo, RedirectAttributes reAttr, MultipartHttpServletRequest mRequest) throws Exception{
		
		// @RequestParam("pd_category") String pd_category 
		logger.info("productReviewWritePOST()");
		//파일 업로드하기 
		boolean isUpload = false;
		
		//!!!!!!경로 주의하기
//		String uploadPath = "D:\\food_market_my\\food_market_my\\src\\main\\webapp\\resources\\pd_img_upload\\"; //본인 폴더경로
		String uploadPath = mRequest.getSession().getServletContext().getRealPath("/resources/pd_img_upload/productReviewImg/"); //임시경로
//		System.out.println("리뷰 파일 업로드 임시 경로 !!!!!     "+mRequest.getSession().getServletContext().getRealPath("/resources/pd_img_upload/productReviewImg/"));
		//임시경로 : D:\workspace_Spring\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\food_market_my\resources\pd_img_upload\
	
		//파일 이름이 들어있다. 
		Iterator<String> iterator = mRequest.getFileNames();
//		System.out.println("\niterator : "+iterator);
		while(iterator.hasNext()){
			String uploadFileName = iterator.next();
//			System.out.println("uploadFileName : "+uploadFileName);
			
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
					
					pd_reviewvo.setPd_re_img_name(saveFileName);
					
					isUpload = true;
					
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
			productReviewService.reviewWrite(pd_reviewvo);
			reAttr.addFlashAttribute("result", "Success");	
			
		} else {
			reAttr.addFlashAttribute("result", "false");
		}
		
		return "redirect:/orders/orderlist";
	}
	
	
	//구매자 마이페이지에서 불러오기 
	//기존 게시판 목록과 같은 형식
	@RequestMapping(value="/productReviewListMember", method=RequestMethod.GET)
	public void productWishListMember(productVO productVO , pd_reviewVO pd_reviewVO, PageCriteria pCri, Model model, HttpSession session) throws Exception{
		//세션값 가져오기
		String mb_id = session.getAttribute("mb_id").toString();
		pd_reviewVO.setMember_mb_id(mb_id);	
		//id값 기준으로 
		//List를 불러와서 
		//list의 pd_idx기준으로 상세 정보 불러오기 
		
		Map map = new HashMap();
		map.put("mb_id",mb_id);
		map.put("startPage", pCri.getStartPage());
		map.put("numPerPage", pCri.getNumPerPage());
		
		//join
		List<pd_reviewVO> ReviewListMember = productReviewService.reviewListMemberCriteria(map);
		
		model.addAttribute("ReviewListMember",ReviewListMember);
		
		PagingMaker pagingMaker = new PagingMaker();
		pagingMaker.setCri(pCri);		
		pagingMaker.setTotalData(productReviewService.reviewListMemberCountData(pd_reviewVO));
		model.addAttribute("pagingMaker", pagingMaker);
		
		
		System.out.println("ReviewListMember :     "+ReviewListMember);
		System.out.println(mb_id+"의 리뷰 갯수 :     "+productReviewService.reviewListMemberCountData(pd_reviewVO));
	}
	
	
	
	
//-----------------------------------------------------------------------------------------------------------	
//-----------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------	
	
	
	
	//기존 게시판 목록과 같은 형식
	@RequestMapping(value="/productReviewList", method=RequestMethod.GET)
	public void productReviewList( pd_reviewVO pd_reviewVO, PageCriteria pCri, Model model) throws Exception{
		//@RequestParam("pd_idx") int pd_idx,
		
		logger.info("productReviewList");
		
		Map map = new HashMap();
		map.put("member_mb_id",22); //파라미터값으로 받아오기 //임의로 지정하였다.
		map.put("startPage", pCri.getStartPage());
		map.put("numPerPage", pCri.getNumPerPage());
		//파라미터값 2개 페이지값, member값
		model.addAttribute("reviewList", productReviewService.reviewListCriteria(map));
		
		PagingMaker pagingMaker = new PagingMaker();
		pagingMaker.setCri(pCri);
		
		pd_reviewVO.setProduct_pd_idx(22); //파라미터값으로 받아오기 //임의로 지정하였다.
		pagingMaker.setTotalData(productReviewService.reviewListCountData(pd_reviewVO));
		
//		System.out.println("총 게시글 갯수 : "+productService.listCountData(pCri)+"\n");
		
		model.addAttribute("pagingMaker", pagingMaker);
	}
	
}
