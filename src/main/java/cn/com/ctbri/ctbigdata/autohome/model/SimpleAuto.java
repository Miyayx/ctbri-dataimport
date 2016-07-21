package cn.com.ctbri.ctbigdata.autohome.model;

import java.io.Serializable;

public class SimpleAuto implements Serializable
{
	String pageId;
	String title;
	String url;
	double price;
	double score;
	
	public SimpleAuto() {
		// TODO Auto-generated constructor stub
	}
	
	public SimpleAuto(String pageString, String title, String url, double price, double score) {
		this.pageId = pageString;
		this.title = title;
		this.url = url;
		this.price = price;
		this.score = score;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public double getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
	
	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	@Override
	public String toString() {
		return "SimpleAuto [pageId=" + pageId + ", title=" + title + ", url=" + url + ", price=" + price
				+ ", score=" + score + "]";
	}
	
}
