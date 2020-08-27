<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html class="no-js" lang="">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <link rel="shortcut icon" type="image⁄x-icon" href="pd_img_upload/food market_LOGO.png"><title>FoodMarket</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!-- all css here -->
        <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="../assets/css/magnific-popup.css">
        <link rel="stylesheet" href="../assets/css/animate.css">
        <link rel="stylesheet" href="../assets/css/owl.carousel.min.css">
        <link rel="stylesheet" href="../assets/css/slinky.min.css">
        <link rel="stylesheet" href="../assets/css/meanmenu.min.css">
        <link rel="stylesheet" href="../assets/css/slick.css">
        <link rel="stylesheet" href="../assets/css/ionicons.min.css">
        <link rel="stylesheet" href="../assets/css/bundle.css">
        <link rel="stylesheet" href="../assets/css/style.css">
        <link rel="stylesheet" href="../assets/css/responsive.css">
        <script src="../assets/js/vendor/modernizr-2.8.3.min.js"></script>
         <style type="text/css">
.scale {
  -webkit-transform: scale(1);
  -moz-transform: scale(1);
  -ms-transform: scale(1);
  -o-transform: scale(1);
  transform: scale(1);

  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  transition: all 0.3s ease-in-out;
}

.scale:hover {
  -webkit-transform: scale(6);
  -moz-transform: scale(6);
  -ms-transform: scale(6);
  -o-transform: scale(6);
  transform: scale(6);
}

.scale img {width:50px; height:50px; overflow:hidden } 

</style>
    </head>
    <body>
    

 <jsp:include page="../inc/header.jsp"></jsp:include>

            <div class="breadcrumb-area pt-205 pb-210 bg-img" style= "background-image: url(../pd_img_upload/전체상품.jpg)">
                <div class="container">
                    <div class="breadcrumb-content">
                        <h2>구매후기</h2>
                        <ul>
                            <li><a href="/food_market/">home</a></li>
                            <li> 구매후기 </li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- shopping-cart-area start -->
            <div class="cart-main-area pt-95 pb-100">
	            <div style="float: left; padding:25px 30px 30px 200px;border-right:1ex solid #E3DEDD;">		
					<ul>
						<c:choose>
							<c:when test="${seller == 1 }">
								<li style="padding-top: 20px;"><a
									href="/food_market/product/productSellerList">판매자 상품 관리</a></li>
								<li style="padding-top: 20px;"><a
									href="/food_market/member/sellerOrder.do">배송 관리</a></li>
							</c:when>
							<c:otherwise>
								<li style="padding-top: 20px;"><a
									href="/food_market/cart/list">장바구니</a></li>
								<li style="padding-top: 20px;"><a
									href="/food_market/orders/orderlist">구매내역</a></li>
									
									
								<!-- 추가!!!!!!!!!!!! -->
								<li style="padding-top: 20px;"><a
									href="/food_market/product/productReviewListMember">구매후기</a></li>
								<li style="padding-top: 20px;"><a
									href="/food_market/product/productQnaListMember">상품Q&A</a></li>
								<!-- 추가!!!!!!!!!!!! -->
								
								
							</c:otherwise>
						</c:choose>
						<li style="padding-top: 20px;"><a
							href="/food_market/member/info">회원정보 수정</a></li>
						<li style="padding-top: 20px;"><a
							href="/food_market/member/modifyPw">비밀번호 번경</a></li>
						<li style="padding-top: 20px;"><a
							href="/food_market/member/delete">회원 탈퇴</a></li>
					</ul>
				</div>
                <div class="container">
                    <div class="row">
                    
                     <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <h1 class="cart-heading">Review</h1>
                                <hr>
                                	<c:if test="${empty ReviewListMember}"> 
									<script type="text/javascript">
									alert("리뷰리스트가 비었습니다!");
									location.href="${path }/food_market/member/myPage";
									</script>
									</c:if>
                                	<c:if test="${!empty ReviewListMember}"> 
                                <div class="table-content table-responsive">
                                    <table>
                                        <thead>
                                            <tr>
                                                <th class="product-thumbnail">상품사진</th>
                                                <th class="product-name" style ="width: 30em;">상품명</th>
                                                <th class="product-name">별점</th>
                                                <th class="product-price" style ="width: 100em;">상품평</th>
                                                <th class="product-price">리뷰사진</th>
                                                <th class="product-price" style ="width: 30em;">작성일</th>
                                                <th class="product-price" style ="width: 30em;">주문번호</th>
                                                
                                            </tr>
                                        </thead>
										<tbody>
				
											<!-- foreach문 돌리기 -->
											<c:forEach items="${ReviewListMember}" var="pd_reviewVO">
				
												<tr>
												<!-- 사진 -->
													<td class="product-thumbnail">
													<a
														href="productDetail${pagingMaker.makeURI(pagingMaker.cri.page)}&pd_idx=${pd_reviewVO.product_pd_idx }">
															<img src="../pd_img_upload/${pd_reviewVO.productVO.pd_category }/${pd_reviewVO.productVO.pd_img_name_f }"
															width="100" height="100" alt="${pd_reviewVO.productVO.pd_img_name_f }">
													</a>
													</td>
													<td class="product-name">
													<a
														href="productDetail${pagingMaker.makeURI(pagingMaker.cri.page)}&pd_idx=${pd_reviewVO.product_pd_idx }">
													${pd_reviewVO.productVO.pd_name }</a>
													</td>
													<!--  별점	-->	
													<td class="product-name"><img src="../pd_img_upload/${pd_reviewVO.pd_re_star }.jpg" height='10'></td>															
													<!-- 상품평 -->
													<td class="product-price" >${pd_reviewVO.pd_re_subject }</td>
													<!-- 리뷰사진 -->
													<td class="product-price">
													<c:if test="${pd_reviewVO.pd_re_img_name != null}">
													<img src="../pd_img_upload/productReviewImg/${pd_reviewVO.pd_re_img_name }" alt='' height='50' width='50' class='scale'>
													</c:if>
													</td >
													<td class="product-price">${pd_reviewVO.pd_re_date }</td>
													<td class="product-price">${pd_reviewVO.order_od_idx }</td>
												</tr>
											</c:forEach>
											<!-- foreach문 돌리기 -->
										</tbody>
									</table>
								</div>

								<hr>
								<div style="margin-left: 150px;">
									<c:if test="${pagingMaker.prev}">
										<a  class='button btn-hover'
											onclick="location.href='productReviewListMember${pagingMaker.makeURI(pagingMaker.startPage-1)}'">◀</a>
									</c:if>
									<c:forEach begin="${pagingMaker.startPage}"
										end="${pagingMaker.endPage}" var="pNum">
			
										<a  class='button btn-hover'
											onclick="location.href='productReviewListMember${pagingMaker.makeURI(pNum)}'">${pNum}</a>
			
									</c:forEach>
									<c:if test="${pagingMaker.next&&pagingMaker.endPage > 0}">
										<a  class='button btn-hover'
											onclick="location.href='productReviewListMember${pagingMaker.makeURI(pagingMaker.endPage+1)}'">▶</a>
									</c:if>
								</div>
							</c:if>
                        </div>
                      </div>
                  </div>
            </div>

 <jsp:include page="../inc/footer.jsp"></jsp:include>
		
		
		
		
		
		
		
		
		<!-- all js here -->
        <script src="assets/js/vendor/jquery-1.12.0.min.js"></script>
        <script src="assets/js/popper.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.magnific-popup.min.js"></script>
        <script src="assets/js/isotope.pkgd.min.js"></script>
        <script src="assets/js/imagesloaded.pkgd.min.js"></script>
        <script src="assets/js/jquery.counterup.min.js"></script>
        <script src="assets/js/waypoints.min.js"></script>
        <script src="assets/js/slinky.min.js"></script>
        <script src="assets/js/ajax-mail.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/js/plugins.js"></script>
        <script type="text/javascript">
            // grab an element
            var myElement = document.querySelector(".intelligent-header");
            // construct an instance of Headroom, passing the element
            var headroom  = new Headroom(myElement);
            // initialise
            headroom.init(); 
        </script>

        <script src="assets/js/main.js"></script>
    </body>
</html>
