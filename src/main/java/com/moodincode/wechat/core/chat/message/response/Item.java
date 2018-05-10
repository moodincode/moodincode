package com.moodincode.wechat.core.chat.message.response;

import java.util.Date;
import java.util.List;

public class Item {
	/**
	 * 永久素材Id
	 */
	private String media_id;
	/**
	 * 这篇图文消息素材的最后更新时间
	 */
	private Date update_time;
	/**
	 * 图文消息素材列表
	 */
	private List<News_item> news_item;
	
	//=========其他类型（图片、语音、视频）字段===========
	/**
	 * 文件名称
	 */
	private  String name;
	/**
	 * 图文页的URL，或者，当获取的列表是图片素材列表时，该字段是图片的URL
	 */
	private String url;
	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public List<News_item> getNews_item() {
		return news_item;
	}
	public void setNews_item(List<News_item> news_item) {
		this.news_item = news_item;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
