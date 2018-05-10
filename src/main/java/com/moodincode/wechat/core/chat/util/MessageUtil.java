package com.moodincode.wechat.core.chat.util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.moodincode.wechat.core.chat.message.response.KfAccesssMessage;
import com.moodincode.wechat.core.chat.message.response.TextMessage;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.moodincode.wechat.core.aes.WXBizMsgCrypt;
import com.moodincode.wechat.core.chat.message.response.*;

import com.thoughtworks.xstream.XStream;  
import com.thoughtworks.xstream.core.util.QuickWriter;  
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;  
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;  
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息工具类
 * @author 
 * @date 
 */
public class MessageUtil {
	private static Logger logger =  LoggerFactory.getLogger(MessageUtil.class);
	/**
	 * 返回消息类型：文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 返回消息类型：音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

	/**
	 * 返回消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 请求消息类型：链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	/**
	 * 请求消息类型：音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	/**
	 * 事件类型：subscribe(订阅)/事件类型：扫描带参数二维码事件  subscribe(未关注)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";
	
	/**
	 * 事件类型：扫描带参数二维码事件  SCAN(已关注)
	 */
	public static final String EVENT_TYPE_SCAN = "SCAN";
	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_VIEW = "VIEW";
	/**
	 * 事件类型：扫码推事件(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_SCANCODE_PUSH = "scancode_push";
	/**
	 * 事件类型：扫码推事件且弹出“消息接收中”提示框的事件(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_SCANCODE_WAITMSG = "scancode_waitmsg";
	/**
	 * 客服类型：客服类型接入参数 transfer_customer_service
	 */
	public static final String TRANSFER_CUSTOMER_SERVICE = "transfer_customer_service";
	
	

	/**
	 * 解析微信发来的请求（XML）
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();

		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();
		
		
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		//转换为字符串,密文
				
				
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		logger.info("===================微信明文消息解析开始================");
		// 遍历所有子节点
		for (Element e : elementList){
			map.put(e.getName(),  e.getText());
			//处理二级节点扫描二维码信息内容
			if("ScanCodeInfo".equals(e.getName())){
				
				map.put(e.element("ScanType").getName(),  e.element("ScanType").getText());
				map.put(e.element("ScanResult").getName(),  e.element("ScanResult").getText());
				logger.info("[二级节点消息键值对]"+e.getName()+":"+e.getData());
			}
			
			logger.info("[消息键值对]"+e.getName()+":"+e.getData());
		}
		logger.info("===================微信明文消息解析结束================");
		

		// 释放资源
		inputStream.close();
		inputStream = null;

		return map;
	}
	
	/**
	 * 解析微信发来的请求（XML）
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXmlByAes(String token,HttpServletRequest request,String encodingAESKey,String appID) throws Exception {

		// 微信加密签名
		String signature = request.getParameter("signature");

				// 时间戳
		String timestamp = request.getParameter("timestamp");

					// 随机数
		String nonce = request.getParameter("nonce");

		String msg_signature= request.getParameter("msg_signature");
		logger.info("msg_signature:"+msg_signature);
	
					// 随机字符串
		//String echostr = request.getParameter("echostr");
		logger.info("encodingAESKey:"+encodingAESKey+",appID:"+appID);
		//加密URLtoken,EncodingAESKey(消息加解密密钥),appID
		WXBizMsgCrypt pc = new WXBizMsgCrypt(token,encodingAESKey,appID);
		
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();

		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();
		//转换为字符串,密文
		String xmlstring=inputStream2String(inputStream);
		logger.info("===微信接收的密文"+xmlstring);
		//解析为明文
		String mingwen = pc.decryptMsg(msg_signature,timestamp,nonce,xmlstring);
		logger.info("===解密后的明文"+mingwen);
		// 读取输入流
		//SAXReader reader = new SAXReader();
		
		Document document = DocumentHelper.parseText(mingwen);
	
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		for (Element e : elementList){
			map.put(e.getName(),  e.getText());
			//处理二级节点扫描二维码信息内容
			if("ScanCodeInfo".equals(e.getName())){
				
				map.put(e.element("ScanType").getName(),  e.element("ScanType").getText());
				map.put(e.element("ScanResult").getName(),  e.element("ScanResult").getText());
				logger.info("[二级节点消息键值对]"+e.getName()+":"+e.getData());
			}
			
			logger.info("[消息键值对]"+e.getName()+":"+e.getData());
		}

		// 释放资源
		inputStream.close();
		inputStream = null;

		return map;
	}


	/**
	 * 文本消息对象转换成xml
	 * 
	 * @param textMessage 文本消息对象
	 * @return xml
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}
	/**
	 * 客服接入消息转换成xml
	 * 
	 * @param   kfAccesssMessage 文本消息对象
	 * @return xml
	 */
	public static String kfAccesssMessageToXml(KfAccesssMessage kfAccesssMessage) {
		xstream.alias("xml", kfAccesssMessage.getClass());
		return xstream.toXML(kfAccesssMessage);
	}

	/**
	 * 音乐消息对象转换成xml
	 * 
	 * @param musicMessage 音乐消息对象
	 * @return xml
	 */
	public static String musicMessageToXml(MusicMessage musicMessage) {
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}

	/**
	 * 图文消息对象转换成xml
	 * 
	 * @param newsMessage 图文消息对象
	 * @return xml
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}

	/**
	 * 扩展xstream，使其支持CDATA块
	 * 
	 * @date 2013-05-19
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				
				@SuppressWarnings("unused")
				public void startNode(String name, Class clazz) {
					super.startNode(name);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});
	/**
	 * 将inputStreamz转为String
	 * @param is
	 * @return 输出字符串
	 */
	public static String inputStream2String(InputStream   is)   throws   IOException{ 
        ByteArrayOutputStream   baos   =   new   ByteArrayOutputStream(); 
        int   i=-1; 
        while((i=is.read())!=-1){ 
        baos.write(i); 
        } 
       return   baos.toString(); 
	}
	/**
	 * 验签
	 * @param token
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static final boolean checkSignature(String token,String signature,String timestamp,String nonce){
		List<String> params = new ArrayList<String>();
		params.add(token);
		params.add(timestamp);
		params.add(nonce);
		Collections.sort(params,new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		String temp = params.get(0)+params.get(1)+params.get(2);
		return SHA1.encode(temp).equals(signature.trim());
	}
}
