package com.moodincode.wechat.core.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户列表返回的数据
 * @author leo
 *
 */
public class Data implements Serializable{
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
		return "Data [openid=" + openid + "]";
	}
	
	
	
}