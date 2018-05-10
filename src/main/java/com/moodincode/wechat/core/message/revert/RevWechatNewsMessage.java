package com.moodincode.wechat.core.message.revert;

import java.util.List;

import com.moodincode.wechat.core.message.WechatGenericMessage;

/**
 * 回复图文消息
 */
public class RevWechatNewsMessage extends WechatGenericMessage {

	/**
	 * 图文消息个数，限制为10条以内
	 */
	private Integer ArticleCount;

	/**
	 * 多条图文消息信息，默认第一个item为大图
	 */
	private List<Articles> Articles;

	/**
	 * 图文消息标题
	 */
	private String Title;

	/**
	 * 图文消息描述
	 */
	private String Description;

	/**
	 * 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80。
	 */
	private String PicUrl;

	/**
	 * 点击图文消息跳转链接
	 */
	private String Url;

	public Integer getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(Integer articleCount) {
		ArticleCount = articleCount;
	}

	public List<Articles> getArticles() {
		return Articles;
	}

	public void setArticles(List<Articles> articles) {
		Articles = articles;
	}

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

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}
	
	
	

	public RevWechatNewsMessage() {
	}

	public void build(String FromUserName,String ToUserName,Long CreateTime, String MsgType){
		this.setCreateTime(CreateTime);
		this.setFromUserName(FromUserName);
		this.setMsgType(MsgType);
		this.setToUserName(ToUserName);
	}
	
	public void buildNewsMessage(Integer articleCount, List<Articles> articles,
			String title, String description, String picUrl, String url) {
		this.setArticleCount(articleCount);
		this.setArticles(articles);
		this.setTitle(title);
		this.setDescription(description);
		this.setPicUrl(picUrl);
		this.setUrl(url);
	}

	
	public class Articles {

		/**
		 * 图文消息标题
		 */
		private String Title;

		/**
		 * 图文消息描述
		 */
		private String Description;

		/**
		 * 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80。
		 */
		private String PicUrl;

		/**
		 * Url 点击图文消息跳转链接
		 */
		private String Url;
		
		
		
		

		public Articles(String title, String description, String picUrl,
				String url) {
			Title = title;
			Description = description;
			PicUrl = picUrl;
			Url = url;
		}

		public Articles() {
		}

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

		public String getPicUrl() {
			return PicUrl;
		}

		public void setPicUrl(String picUrl) {
			PicUrl = picUrl;
		}

		public String getUrl() {
			return Url;
		}

		public void setUrl(String url) {
			Url = url;
		}
		
	}
}
