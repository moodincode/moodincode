package com.moodincode.wechat.core.chat.message.response;

import java.util.List;

/**
 * 获取永久素材响应信息
 */
public class Material {
	/**
	 * 该类型的素材的总数
	 */
	private Integer total_count;
	/**
	 * 本次调用获取的素材的数量
	 */
	private Integer item_count;
	/**
	 * 素材包含的项
	 */
	private List<Item> item;
	public Integer getTotal_count() {
		return total_count;
	}
	public void setTotal_count(Integer total_count) {
		this.total_count = total_count;
	}
	public Integer getItem_count() {
		return item_count;
	}
	public void setItem_count(Integer item_count) {
		this.item_count = item_count;
	}
	public List<Item> getItem() {
		return item;
	}
	public void setItem(List<Item> item) {
		this.item = item;
	}
	
	

}
