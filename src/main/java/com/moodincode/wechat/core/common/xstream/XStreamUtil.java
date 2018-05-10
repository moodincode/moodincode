package com.moodincode.wechat.core.common.xstream;

import java.io.Writer;

import com.moodincode.wechat.core.message.revert.RevWechatMusicMessage;
import com.moodincode.wechat.core.message.revert.RevWechatNewsMessage;
import com.moodincode.wechat.core.message.revert.RevWechatTextMessage;
import com.moodincode.wechat.core.message.revert.RevWechatNewsMessage.Articles;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;


/**
 * XStream 类型转换
 * 
 * @author yanfuqian
 * 
 */
public class XStreamUtil {
	/**
	 * 文本消息对象转换成xml
	 * 
	 * @param message
	 *            文本消息对象
	 * @return xml
	 */
	public static String textMessageToXml(RevWechatTextMessage message) {
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);
	}

	/**
	 * 音乐消息对象转换成xml
	 * 
	 * @param message
	 *            音乐消息对象
	 * @return xml
	 */
	public static String musicMessageToXml(RevWechatMusicMessage message) {
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);
	}

	/**
	 * 图文消息对象转换成xml
	 * 
	 * @param message
	 *            图文消息对象
	 * @return xml
	 */
	public static String newsMessageToXml(RevWechatNewsMessage message) {
		xstream.alias("xml", message.getClass());
		xstream.alias("item", Articles.class);
		return xstream.toXML(message);
	}
	
	
	public static Object fromXML(String xml , Class<?> clazz){
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("xml", clazz);
		return xstream.fromXML(xml);
	}

	/**
	 * 扩展xstream，使其支持CDATA块
	 * 
	 * @date 2013-05-19
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				boolean cdata = true;
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
	
}
