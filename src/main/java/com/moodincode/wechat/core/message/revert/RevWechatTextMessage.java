package com.moodincode.wechat.core.message.revert;

import com.moodincode.wechat.core.message.WechatGenericMessage;

/**
 * 回复文本消息
 * 
 * @author yanfuqian
 * 
 */
public class RevWechatTextMessage extends WechatGenericMessage{

	/**
	 * 回复的消息内容,长度不超过2048字节
	 */
	private String Content;


	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
	
	public void build(String FromUserName,String ToUserName,Long CreateTime, String MsgType , String Content){
		this.setContent(Content);
		this.setCreateTime(CreateTime);
		this.setFromUserName(FromUserName);
		this.setMsgType(MsgType);
		this.setToUserName(ToUserName);
	}
}
