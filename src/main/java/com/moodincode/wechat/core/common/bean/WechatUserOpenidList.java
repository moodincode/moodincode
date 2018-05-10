package com.moodincode.wechat.core.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取关注者列表
 * @author leo
 *
 */
public class WechatUserOpenidList {
	/**
	 * 总人数
	 */
	private Integer total;
	/**
	 * 当前页数量
	 */
	private Integer count;
	private Data data;
	private String  next_openid;
	

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public String getNext_openid() {
		return next_openid;
	}

	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}

	@Override
	public String toString() {
		return "WechatUserOpenidList [total=" + total + ", count=" + count
				+ ", data=" + data + ", next_openid=" + next_openid + "]";
	}
	
	public static class Data implements Serializable{
		private static final long serialVersionUID = 1L;
		private List<String> openid = new ArrayList<String>();

		public List<String> getOpenid() {
			return openid;
		}

		public void setOpenid(List<String> openid) {
			this.openid = openid;
		}

		@Override
		public String toString() {
			return "[openid=" + openid + "]";
		}
	}
	
}




