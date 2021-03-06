package com.moodincode.wechat.core.message.receive;

import com.moodincode.wechat.core.message.WechatGenericMessage;

/**
 * 接收事件消息
 * @author yanfuqian
 *
 */
public class RecWechatEventMessage extends WechatGenericMessage {

	/**
	 * 事件类型，subscribe(订阅)、unsubscribe(取消订阅)、CLICK(自定义菜单点击事件)
	 */
	private String Event;

	/**
	 * 事件KEY值，与自定义菜单接口中KEY值对应
	 */
	private String EventKey;

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
}
