<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image⁄x-icon" href="pd_img_upload/food market_LOGO.png"><title>FoodMarket</title>
</head>
<body>
<jsp:include page="../inc/header.jsp"/>
<div class="breadcrumb-area pt-205 pb-210" style="background-image: url(../assets/img/cs/cs.jpg)">
    <div class="container">
        <div class="breadcrumb-content">
            <h2 style="color: #333;">Contact Us</h2>
            <ul>
                <li><a href="#" style="color: #555;">home</a></li>
                <li style="color: #333;"> 고객문의 </li>
            </ul>
        </div>
    </div>
</div>
<div class="cart-main-area pt-95 pb-100">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	<h2>CS 글쓰기</h2>
	<form action="" method="post" enctype="multipart/form-data">
<!-- 	<table> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				<select id="cs_type" name="cs_type"> -->
<!-- 					<option value="0">배송문의</option> -->
<!-- 					<option value="1">주문결제</option> -->
<!-- 					<option value="2">반품/교환/환불</option> -->
<!-- 					<option value="8">기타</option> -->
<!-- 				</select> -->
<!-- 			</td> -->
<!-- 		<td>제목</td><td><input type="text" name="cs_subject" id="cs_subject" required></td></tr> -->
<!-- 		<tr><td>첨부파일</td><td colspan="2"><input type="file" name="file" id="file"></td></tr> -->
<!-- 		<tr><td>내용</td><td colspan="2"><textarea cols="30" rows="10" name="cs_content" id="cs_content" required></textarea></td></tr> -->
<!-- 		<tr><td colspan="3"> -->
<!-- 		<input type="submit" value="등록"></td></tr> -->
<!-- 	</table> -->
	<div class="form-group">
		<label for="cs_type">문의항목</label>
		<select id="cs_type" name="cs_type" class="form-control">
					<option value="0">배송문의</option>
					<option value="1">주문결제</option>
					<option value="2">반품/교환/환불</option>
					<option value="8">기타</option>
				</select>
	</div>
	<div class="form-group">
		<label for="cs_subject">제목</label> <input type="text"
			class="form-control" id="cs_subject" name="cs_subject" required>
	</div>
	<div class="form-group">
		<label for="file">첨부파일</label> <input type="file"
			class="form-control" id="file" name="file">
	</div>
	<div class="form-group">
		<label for="cs_content">내용</label> <textarea cols="30" rows="10" class="form-control" name="cs_content" id="cs_content" style="resize: none;" required></textarea>
	</div>
	<div class="form-group text-center">
		<button type="submit" class="btn btn-primary">작성하기</button>
	</div>
	</form>
	 </div>
 </div>
 </div>
</div>
 <jsp:include page="../inc/footer.jsp"/>
</body>
</html>