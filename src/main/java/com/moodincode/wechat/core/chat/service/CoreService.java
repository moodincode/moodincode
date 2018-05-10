package com.moodincode.wechat.core.chat.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;




import com.moodincode.wechat.core.aes.AesException;
import com.moodincode.wechat.core.aes.WXBizMsgCrypt;
import com.moodincode.wechat.core.chat.message.response.Article;
import com.moodincode.wechat.core.chat.message.response.KfAccesssMessage;
import com.moodincode.wechat.core.chat.message.response.NewsMessage;
import com.moodincode.wechat.core.chat.message.response.TextMessage;
import com.moodincode.wechat.core.chat.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 核心服务类
 * 
 *
 */
public class CoreService {
	private static Logger logger =  LoggerFactory.getLogger(CoreService.class);
	
	
	/**
	 * 处理微信发来的请求-明文模式
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		
		String respMessage = null;
		
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 客服接入消息,若需要转发给客服处理,则只需将此消息转换成xml格式字符串响应给服务器.
			KfAccesssMessage kfAccesssMessage = new KfAccesssMessage();
			kfAccesssMessage.setToUserName(fromUserName);
			kfAccesssMessage.setFromUserName(toUserName);
			kfAccesssMessage.setCreateTime(new Date().getTime());
			kfAccesssMessage.setMsgType(MessageUtil.TRANSFER_CUSTOMER_SERVICE);

			// 回复文本消息(默认消息回复方式)
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			WeixinMessageService wService=new WeixinMessageService();
			textMessage.setContent(respContent);
			/**
			 * 获取默认消息
			 */
			/*List<WeixinReplyMessage> replylist=wService.getDefaultMessge();
			if(replylist!=null&&replylist.size()>0){
				respContent = replylist.get(0).getContent()==null?"":replylist.get(0).getContent();
				textMessage.setContent(respContent);
				
			}*/
			
			respMessage = MessageUtil.textMessageToXml(textMessage); 
			
			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {

				// 获取文本消息内容
				String msgContent = requestMap.get("Content");
					if(!StringUtils.isEmpty(msgContent)){
						/*try {
							List<WeixinReplyMessage> replymsglist=wService.getKeyWordMessge(msgContent);
						 if(replymsglist!=null&&replymsglist.size()>0){
							 WeixinReplyMessage replymsg=replymsglist.get(0);
							 if(ReplyType.Text.equals(replymsg.getReplyType())||ReplyType.DefaultText.equals(replymsg.getReplyType())){
								 	respContent = replymsg.getContent()==null?"":replymsg.getContent();
									textMessage.setContent(respContent);
									respMessage = MessageUtil.textMessageToXml(textMessage); 
									
							 }else if(ReplyType.Article.equals(replymsg.getReplyType())){
								 	NewsMessage newsMessage = new NewsMessage();
									newsMessage.setToUserName(fromUserName);
									newsMessage.setFromUserName(toUserName);
									newsMessage.setCreateTime(new Date().getTime());
									newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
									newsMessage.setFuncFlag(1);

									// 向图文消息类添加item
									List<Article> items = new ArrayList<Article>();
									List<WeixinReplyArticle> weixinReplyArticles=new ArrayList<WeixinReplyArticle>(replymsg.getWeixinReplyArticle());
									for(int i=0;i<weixinReplyArticles.size();i++){
										WeixinReplyArticle wReplyArticle=weixinReplyArticles.get(i);
										Article article = new Article(wReplyArticle.getTitle(), wReplyArticle.getDescription(),
												wReplyArticle.getPicUrl(),wReplyArticle.getUrl());
										items.add(article);
									}
									newsMessage.setArticleCount(items.size());
									newsMessage.setArticles(items);
									respMessage = MessageUtil.newsMessageToXml(newsMessage);
							 }else{
								 //转客服聊天
								// 将消息处理转入到客服响应的xml字符串
								respMessage=MessageUtil.kfAccesssMessageToXml(kfAccesssMessage);
							 }
							 
							 
						 }
						} catch (Exception e) {
							logger.error("微信自动回复出错!原因:"+e);
						}*/
						respContent = "自动回复内容信息";
				  
				 }
					
		
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "已收到您发送的照片!";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
				 //转客服聊天
				// 将消息处理转入到客服响应的xml字符串
				 respMessage=MessageUtil.kfAccesssMessageToXml(kfAccesssMessage);
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
				 //转客服聊天
				// 将消息处理转入到客服响应的xml字符串
				 respMessage=MessageUtil.kfAccesssMessageToXml(kfAccesssMessage);
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
				 //转客服聊天
				// 将消息处理转入到客服响应的xml字符串
				 respMessage=MessageUtil.kfAccesssMessageToXml(kfAccesssMessage);
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
				 //转客服聊天
				// 将消息处理转入到客服响应的xml字符串
				 respMessage=MessageUtil.kfAccesssMessageToXml(kfAccesssMessage);
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅//或扫描带参数二维码事件
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					String eventKey = requestMap.get("EventKey");// 事件KEY值，qrscene_为前缀，后面为二维码的参数值
					String ticket = requestMap.get("Ticket");// 二维码的ticket，可用来换取二维码图片
					logger.info("二维码的EventKey(事件KEY值，qrscene_为前缀，后面为二维码的参数值):" + eventKey
							+ " ---  Ticket的值(二维码的ticket，可用来换取二维码图片):" + ticket);
					respContent = "谢谢您的关注！";
					
					//wService.updateQrCodeVisitCount(ticket, eventKey);
					//匹配关注二维码,增加访问人数
					if(!StringUtils.isEmpty(ticket)){
						wService.InsertQrCodeUserCount(ticket, fromUserName, eventKey);
					}
					
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
					//匹配取消关注人信息,移除二维码关注人数
					wService.RemoveQrCodeUserCount(fromUserName);
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {

					String eventKey = requestMap.get("EventKey");// 事件KEY值，与自定义菜单接口中KEY值对应
					logger.info("事件KEY值，与自定义菜单接口中KEY值对应:" + eventKey);
				}
				// 自定义菜单跳转URL事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_VIEW)) {

					String eventKey = requestMap.get("EventKey");// 事件KEY值，设置的跳转URL
					logger.info("事件KEY值，设置的跳转URL:" + eventKey);
				}
				// 扫描带参数二维码事件 SCAN(已关注)
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					// 扫描带参数二维码事件 subscribe(已关注)
					String eventKey = requestMap.get("EventKey");// 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
					String ticket = requestMap.get("Ticket");// 二维码的ticket，可用来换取二维码图片
					logger.info("二维码的EventKey(事件KEY值，二维码的参数值):" + eventKey + " ---  Ticket的值(二维码的ticket，可用来换取二维码图片):"
							+ ticket);
					//wService.updateQrCodeVisitCount(ticket, eventKey);
					if(!StringUtils.isEmpty(ticket)){
						wService.InsertQrCodeUserCount(ticket, fromUserName, eventKey);
					}
				}// 调用自定义菜单扫一扫推送时间
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCANCODE_PUSH)) {
					
					String eventKey = requestMap.get("EventKey");// 事件KEY值
					String ScanType = requestMap.get("ScanType");// 扫描类型，一般是qrcode
					String ScanResult = requestMap.get("ScanResult");// 扫描结果，即二维码对应的字符串信息
					
					logger.info("扫描事件的key为:" + eventKey + " ---  扫描类型:"
							+ ScanType+ " ---  扫描结果:"
							+ ScanResult);
					
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}

	/**
	 * 处理微信发来的请求-密文模式
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unused")
	public static String processRequestByAES(String token,HttpServletRequest request, String encodingAESKey, String appID) {
		String respMessage = null;

		try {
			// 默认返回的文本消息内容
			String respContent = "";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXmlByAes(token,request, encodingAESKey, appID);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			logger.info("接受的微信消息类型:"+msgType);
			// 客服接入消息,若需要转发给客服处理,则只需将此消息转换成xml格式字符串响应给服务器.
			KfAccesssMessage kfAccesssMessage = new KfAccesssMessage();
			kfAccesssMessage.setToUserName(fromUserName);
			kfAccesssMessage.setFromUserName(toUserName);
			kfAccesssMessage.setCreateTime(new Date().getTime());
			kfAccesssMessage.setMsgType(MessageUtil.TRANSFER_CUSTOMER_SERVICE);

			// 回复文本消息(默认消息回复方式)
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			WeixinMessageService wService=new WeixinMessageService();
			/**
			 * 获取默认消息
			 */
			respContent="欢迎光临";
		/*	List<WeixinReplyMessage> replylist=wService.getDefaultMessge();
			if(replylist!=null&&replylist.size()>0){
				respContent = replylist.get(0).getContent()==null?"":replylist.get(0).getContent();
				
			}*/

			textMessage.setContent(respContent);
			respMessage = MessageUtil.textMessageToXml(textMessage); 
			// 文本消息
						if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {

							// 获取文本消息内容
							String msgContent = requestMap.get("Content");
								if(!StringUtils.isEmpty(msgContent)){
									/*try {
										List<WeixinReplyMessage> replymsglist=wService.getKeyWordMessge(msgContent);
									 if(replymsglist!=null&&replymsglist.size()>0){
										 WeixinReplyMessage replymsg=replymsglist.get(0);
										 if(ReplyType.Text.equals(replymsg.getReplyType())||ReplyType.DefaultText.equals(replymsg.getReplyType())){
											 	respContent = replymsg.getContent()==null?"":replymsg.getContent();
												textMessage.setContent(respContent);
												respMessage = MessageUtil.textMessageToXml(textMessage); 
												
										 }else if(ReplyType.Article.equals(replymsg.getReplyType())){
											 	NewsMessage newsMessage = new NewsMessage();
												newsMessage.setToUserName(fromUserName);
												newsMessage.setFromUserName(toUserName);
												newsMessage.setCreateTime(new Date().getTime());
												newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
												newsMessage.setFuncFlag(1);

												// 向图文消息类添加item
												List<Article> items = new ArrayList<Article>();
												List<WeixinReplyArticle> weixinReplyArticles=new ArrayList<WeixinReplyArticle>(replymsg.getWeixinReplyArticle());
												for(int i=0;i<weixinReplyArticles.size();i++){
													WeixinReplyArticle wReplyArticle=weixinReplyArticles.get(i);
													Article article = new Article(wReplyArticle.getTitle(), wReplyArticle.getDescription(),
															wReplyArticle.getPicUrl(),wReplyArticle.getUrl());
													items.add(article);
												}
												newsMessage.setArticleCount(items.size());
												newsMessage.setArticles(items);
												respMessage = MessageUtil.newsMessageToXml(newsMessage);
										 }else{
											 //转客服聊天
											// 将消息处理转入到客服响应的xml字符串
											respMessage=MessageUtil.kfAccesssMessageToXml(kfAccesssMessage);
										 }
										 
										 
									 }else{
										 //转客服聊天
											// 将消息处理转入到客服响应的xml字符串
											respMessage=MessageUtil.kfAccesssMessageToXml(kfAccesssMessage); 
									 }
									} catch (Exception e) {
										logger.error("微信自动回复出错!原因:"+e);
									}*/
									
							  
							 }
								
					
						}
						// 图片消息
						else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
							respContent = "已收到您发送的照片!";
							textMessage.setContent(respContent);
							respMessage = MessageUtil.textMessageToXml(textMessage);
							 //转客服聊天
							// 将消息处理转入到客服响应的xml字符串
							 respMessage=MessageUtil.kfAccesssMessageToXml(kfAccesssMessage);
						}
						// 地理位置消息
						else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
							respContent = "您发送的是地理位置消息！";
							 //转客服聊天
							// 将消息处理转入到客服响应的xml字符串
							 respMessage=MessageUtil.kfAccesssMessageToXml(kfAccesssMessage);
						}
						// 链接消息
						else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
							respContent = "您发送的是链接消息！";
							 //转客服聊天
							// 将消息处理转入到客服响应的xml字符串
							 respMessage=MessageUtil.kfAccesssMessageToXml(kfAccesssMessage);
						}
						// 音频消息
						else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
							respContent = "您发送的是音频消息！";
							 //转客服聊天
							// 将消息处理转入到客服响应的xml字符串
							 respMessage=MessageUtil.kfAccesssMessageToXml(kfAccesssMessage);
						}
						// 事件推送
						else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
							// 事件类型
							String eventType = requestMap.get("Event");
							// 订阅//或扫描带参数二维码事件
							if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
								String eventKey = requestMap.get("EventKey");// 事件KEY值，qrscene_为前缀，后面为二维码的参数值
								String ticket = requestMap.get("Ticket");// 二维码的ticket，可用来换取二维码图片
								logger.info("二维码的EventKey(事件KEY值，qrscene_为前缀，后面为二维码的参数值):" + eventKey
										+ " ---  Ticket的值(二维码的ticket，可用来换取二维码图片):" + ticket);
								respContent = "谢谢您的关注！";
								
								//wService.updateQrCodeVisitCount(ticket, eventKey);
								//匹配关注二维码,增加访问人数
								if(!StringUtils.isEmpty(ticket)){
									wService.InsertQrCodeUserCount(ticket, fromUserName, eventKey);
								}
								//获取欢迎信息
								/*List<WeixinReplyMessage> welcomeReplylist=wService.getWelcomeMessge();
								if(welcomeReplylist!=null&&welcomeReplylist.size()>0){
									respContent = welcomeReplylist.get(0).getContent()==null?"":welcomeReplylist.get(0).getContent();
									textMessage.setContent(respContent);
									respMessage = MessageUtil.textMessageToXml(textMessage); 
								}*/

								respMessage ="感谢你的关注！";
								
							}
							// 取消订阅
							else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
								// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
								//匹配取消关注人信息,移除二维码关注人数
								wService.RemoveQrCodeUserCount(fromUserName);
							}
							// 自定义菜单点击事件
							else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {

								String eventKey = requestMap.get("EventKey");// 事件KEY值，与自定义菜单接口中KEY值对应
								logger.info("事件KEY值，与自定义菜单接口中KEY值对应:" + eventKey);
							}
							// 自定义菜单跳转URL事件
							else if (eventType.equals(MessageUtil.EVENT_TYPE_VIEW)) {

								String eventKey = requestMap.get("EventKey");// 事件KEY值，设置的跳转URL
								logger.info("事件KEY值，设置的跳转URL:" + eventKey);
							}
							// 扫描带参数二维码事件 SCAN(已关注)
							else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
								// 扫描带参数二维码事件 subscribe(已关注)
								String eventKey = requestMap.get("EventKey");// 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
								String ticket = requestMap.get("Ticket");// 二维码的ticket，可用来换取二维码图片
								logger.info("二维码的EventKey(事件KEY值，二维码的参数值):" + eventKey + " ---  Ticket的值(二维码的ticket，可用来换取二维码图片):"
										+ ticket);
								//wService.updateQrCodeVisitCount(ticket, eventKey);
								/*if(!StringUtils.isEmpty(ticket)){
									wService.InsertQrCodeUserCount(ticket, fromUserName, eventKey);
								}*/
								//获取欢迎信息
								/*List<WeixinReplyMessage> welcomeReplylist=wService.getWelcomeMessge();
								if(welcomeReplylist!=null&&welcomeReplylist.size()>0){
									respContent = welcomeReplylist.get(0).getContent()==null?"":welcomeReplylist.get(0).getContent();
									textMessage.setContent(respContent);
									respMessage = MessageUtil.textMessageToXml(textMessage); 
								}*/
								respMessage ="通过二维码关注";
							}// 调用自定义菜单扫一扫推送时间
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCANCODE_PUSH)) {
					
					String eventKey = requestMap.get("EventKey");// 事件KEY值
					String ScanType = requestMap.get("ScanType");// 扫描类型，一般是qrcode
					String ScanResult = requestMap.get("ScanResult");// 扫描结果，即二维码对应的字符串信息
					
					logger.info("扫描事件的key为:" + eventKey + " ---  扫描类型:"
							+ ScanType+ " ---  扫描结果:"
							+ ScanResult);
					
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 获取网站TOKEN

		// 微信加密签名
		String signature = request.getParameter("signature");

		// 时间戳
		String timestamp = request.getParameter("timestamp");

		// 随机数
		String nonce = request.getParameter("nonce");
		// 加密URLtoken,EncodingAESKey(消息加解密密钥),appID
		WXBizMsgCrypt pc;
		try {
			System.out.println("加密前的回复消息: " + respMessage);
			
			if (!StringUtils.isEmpty(respMessage)) {
				pc = new WXBizMsgCrypt(token, encodingAESKey, appID);
				respMessage = pc.encryptMsg(respMessage, timestamp, nonce);
			}

			System.out.println("加密后的回复消息: " + respMessage);
		} catch (AesException e) {
			// TODO Auto-generated catch block
			respMessage = null;
		}
     
		return respMessage;
	}
}
