package com.team3.product.vo;

import java.sql.Date;

import com.team3.member.vo.memberVO;

public class pdReviewVO {
//	CREATE TABLE `pd_review` (
//			`pd_re_idx` INT(11) NOT NULL AUTO_INCREMENT,
//			`pd_re_subject` VARCHAR(100) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
//			`pd_re_img_name` VARCHAR(100) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
//			`pd_re_star` INT(11) NULL DEFAULT NULL,
//			`pd_re_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
//			`member_mb_id` VARCHAR(45) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
//			`product_pd_idx` INT(11) NULL DEFAULT NULL,
//			PRIMARY KEY (`pd_re_idx`) USING BTREE,
//			INDEX `FK_pd_review_member` (`member_mb_id`) USING BTREE,
//			INDEX `FK_pd_review_product` (`product_pd_idx`) USING BTREE,
//			CONSTRAINT `FK_pd_review_member` FOREIGN KEY (`member_mb_id`) REFERENCES `projecttest`.`member` (`mb_id`) ON UPDATE CASCADE ON DELETE CASCADE,
//			CONSTRAINT `FK_pd_review_product` FOREIGN KEY (`product_pd_idx`) REFERENCES `projecttest`.`product` (`pd_idx`) ON UPDATE CASCADE ON DELETE CASCADE
//		)
	
	private int pd_re_idx;
	private String pd_re_subject;
	private String pd_re_img_name;
	private int pd_re_star;
	private Date pd_re_date;
	//-------------------------------
	private String member_mb_id; //외래키
	private int product_pd_idx; //외래키
	private int order_od_idx; //외래키 주문 번호가 있을 때 리뷰 작성하기 
	
	//productVO 객체
	private productVO productVO;
	public productVO getProductVO() {
		return productVO;
	}
	public void setProductVO(productVO productVO) {
		this.productVO = productVO;
	}

	@Override
	public String toString() {
		return "pd_reviewVO [pd_re_idx=" + pd_re_idx + ", pd_re_subject=" + pd_re_subject + ", pd_re_img_name="
				+ pd_re_img_name + ", pd_re_star=" + pd_re_star + ", pd_re_date=" + pd_re_date + ", member_mb_id="
				+ member_mb_id + ", product_pd_idx=" + product_pd_idx + ", order_od_idx=" + order_od_idx
				+ ", productVO=" + productVO + "]";
	}
	
	
	
	public pdReviewVO() {}
	
	public pdReviewVO(int pd_re_idx, String pd_re_subject, String pd_re_img_name, int pd_re_star, Date pd_re_date,
			String member_mb_id, int product_pd_idx, int order_od_idx, com.team3.product.vo.productVO productVO) {
		super();
		this.pd_re_idx = pd_re_idx;
		this.pd_re_subject = pd_re_subject;
		this.pd_re_img_name = pd_re_img_name;
		this.pd_re_star = pd_re_star;
		this.pd_re_date = pd_re_date;
		this.member_mb_id = member_mb_id;
		this.product_pd_idx = product_pd_idx;
		this.order_od_idx = order_od_idx;
		this.productVO = productVO;
	}
	
	public int getPd_re_idx() {
		return pd_re_idx;
	}
	public void setPd_re_idx(int pd_re_idx) {
		this.pd_re_idx = pd_re_idx;
	}
	public String getPd_re_subject() {
		return pd_re_subject;
	}
	public void setPd_re_subject(String pd_re_subject) {
		this.pd_re_subject = pd_re_subject;
	}
	public String getPd_re_img_name() {
		return pd_re_img_name;
	}
	public void setPd_re_img_name(String pd_re_img_name) {
		this.pd_re_img_name = pd_re_img_name;
	}
	public int getPd_re_star() {
		return pd_re_star;
	}
	public void setPd_re_star(int pd_re_star) {
		this.pd_re_star = pd_re_star;
	}
	public Date getPd_re_date() {
		return pd_re_date;
	}
	public void setPd_re_date(Date pd_re_date) {
		this.pd_re_date = pd_re_date;
	}
	public String getMember_mb_id() {
		return member_mb_id;
	}
	public void setMember_mb_id(String member_mb_id) {
		this.member_mb_id = member_mb_id;
	}
	public int getProduct_pd_idx() {
		return product_pd_idx;
	}
	public void setProduct_pd_idx(int product_pd_idx) {
		this.product_pd_idx = product_pd_idx;
	}
	public int getOrder_od_idx() {
		return order_od_idx;
	}
	public void setOrder_od_idx(int order_od_idx) {
		this.order_od_idx = order_od_idx;
	}
	
}
