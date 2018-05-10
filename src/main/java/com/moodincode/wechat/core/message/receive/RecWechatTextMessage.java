package com.moodincode.wechat.core.message.receive;

import com.moodincode.wechat.core.message.WechatGenericMessage;

/**
 * 接收文本消息
 * 
 * @author yanfuqian
 * 
 */
public class RecWechatTextMessage extends WechatGenericMessage {
	/**
	 * 内容
	 */
	private String Content;

	/**
	 * 消息id
	 */
	private Long MsgId;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public Long getMsgId() {
		return MsgId;
	}

	public void setMsgId(Long msgId) {
		MsgId = msgId;
	}
}
