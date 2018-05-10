package com.moodincode.wechat.core.message.receive;

import com.moodincode.wechat.core.message.WechatGenericMessage;

/**
 * 接收图片消息
 * 
 * @author yanfuqian
 * 
 */
public class RecWechatImageMessage extends WechatGenericMessage {

	/**
	 * 图片链接
	 */
	private String PicUrl;

	/**
	 * 消息id
	 */
	private Long MsgId;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public Long getMsgId() {
		return MsgId;
	}

	public void setMsgId(Long msgId) {
		MsgId = msgId;
	}
}
