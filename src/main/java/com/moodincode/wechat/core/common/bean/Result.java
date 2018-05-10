package com.moodincode.wechat.core.common.bean;

/**
 * WeChat API调用返回结果
 * 
 * @author yanfuqian
 * 
 */
public class Result {

	private Integer errcode;

	private Integer errmsg;

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public Integer getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(Integer errmsg) {
		this.errmsg = errmsg;
	}
}
