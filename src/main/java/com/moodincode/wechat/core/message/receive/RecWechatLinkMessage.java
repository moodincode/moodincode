package com.moodincode.wechat.core.message.receive;

import com.moodincode.wechat.core.message.WechatGenericMessage;

/**
 * 接收链接消息
 * 
 * @author yanfuqian
 * 
 */
public class RecWechatLinkMessage extends WechatGenericMessage {
	/**
	 * 消息标题
	 */
	private String Title;

	/**
	 * 消息描述
	 */
	private String Description;

	/**
	 * 消息链接
	 */
	private String Url;

	/**
	 * 消息id
	 */
	private Long MsgId;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public Long getMsgId() {
		return MsgId;
	}

	public void setMsgId(Long msgId) {
		MsgId = msgId;
	}
}
