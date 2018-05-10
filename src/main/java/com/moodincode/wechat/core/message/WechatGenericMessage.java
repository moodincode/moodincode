package com.moodincode.wechat.core.message;

/**
 * 微信消息接口基础类
 * 
 * @author yanfuqian
 * 
 */
public class WechatGenericMessage {

	/**
	 * 接收方帐号
	 */
	private String ToUserName;

	/**
	 * 发送方帐号
	 */
	private String FromUserName;

	/**
	 * 创建时间
	 */
	private Long CreateTime;

	/**
	 * 消息类型
	 */
	private String MsgType;
	

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	@Override
	public String toString() {
		return "WechatGenericMessage [ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime=" + CreateTime + ", MsgType=" + MsgType + "]";
	}

	
	
}
