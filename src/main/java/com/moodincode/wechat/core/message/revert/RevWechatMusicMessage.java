package com.moodincode.wechat.core.message.revert;

import com.moodincode.wechat.core.message.WechatGenericMessage;

/**
 * 回复音乐消息
 * 
 * @author yanfuqian
 * 
 */
public class RevWechatMusicMessage extends WechatGenericMessage {

	/**
	 * 音乐链接
	 */
	private String MusicUrl;

	/**
	 * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	 */
	private String HQMusicUrl;

	public String getMusicUrl() {
		return MusicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}

	public String getHQMusicUrl() {
		return HQMusicUrl;
	}

	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}
}
