package com.moodincode.wechat.core.message.receive;

import com.moodincode.wechat.core.message.WechatGenericMessage;

/**
 * 接收位置消息
 * 
 * @author yanfuqian
 * 
 */
public class RecWechatLocationMessage extends WechatGenericMessage {
	/**
	 * 地理位置纬度
	 */
	private String Location_X;

	/**
	 * 地理位置经度
	 */
	private String Location_Y;

	/**
	 * 地图缩放大小
	 */
	private Long Scale;

	/**
	 * 地理位置信息
	 */
	private String Label;

	/**
	 * 消息id
	 */
	private Long MsgId;

	public String getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(String locationX) {
		Location_X = locationX;
	}

	public String getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(String locationY) {
		Location_Y = locationY;
	}

	public Long getScale() {
		return Scale;
	}

	public void setScale(Long scale) {
		Scale = scale;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}

	public Long getMsgId() {
		return MsgId;
	}

	public void setMsgId(Long msgId) {
		MsgId = msgId;
	}

}
