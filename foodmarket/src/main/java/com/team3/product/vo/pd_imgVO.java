package com.team3.product.vo;

import java.util.Date;

public class pd_imgVO {
	
//	pd_img_idx int  not null AUTO_INproductCREMENT PRIMARY key,
//	pd_img_name VARCHAR(100),
//	pd_date timestamp not null default now(),
//	product_pd_idx int 
	
	private int pd_img_idx;
	private String pd_img_name;
	private Date regdate;
	private int pd_idx; //product 외래키 
	

	

	public pd_imgVO() {
		
	}

	@Override
	public String toString() {
		return "pd_imgVO [pd_img_idx=" + pd_img_idx + ", pd_img_name=" + pd_img_name + ", regdate=" + regdate
				+ ", product_pd_idx=" + pd_idx + "]";
	}
	
	public int getPd_img_idx() {
		return pd_img_idx;
	}
	public void setPd_img_idx(int pd_img_idx) {
		this.pd_img_idx = pd_img_idx;
	}
	public String getPd_img_name() {
		return pd_img_name;
	}
	public void setPd_img_name(String pd_img_name) {
		this.pd_img_name = pd_img_name;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getPd_idx() {
		return pd_idx;
	}

	public void setPd_idx(int pd_idx) {
		this.pd_idx = pd_idx;
	}
	
}
