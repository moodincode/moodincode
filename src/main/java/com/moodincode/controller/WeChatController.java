package com.moodincode.controller;

import com.moodincode.wechat.core.chat.service.CoreService;

import com.moodincode.wechat.core.chat.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

/**
 * Created by leshang on 2018/5/9.
 */
@Controller
@RequestMapping("wechat")
public class WeChatController {
    public  static Logger logger= LoggerFactory.getLogger(WeChatController.class);
    @ResponseBody
    @RequestMapping(value="exchange/{token}",method = RequestMethod.POST)
    public String exchangdataPost(@PathVariable String token, HttpServletRequest request, HttpServletResponse response){
// 设置请求,响应编码
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");

        logger.info("【处理微信服务器发来的消息】token:" + token);
        // 接受消息并转化
        //WechatGenericMessage message = MessageUtil.handler(request.getInputStream());
        // 微信加密签名
        String signature = request.getParameter("signature");

        // 时间戳
        String timestamp = request.getParameter("timestamp");

        // 随机数
        String nonce = request.getParameter("nonce");

        logger.info(" nonce:" + nonce + " signature:" + signature+" timestamp"+timestamp);
        //logger.info("【处理微信服务器发来的消息】safeMode:" + encrypt_type);

				/*org.springframework.core.io.Resource resource = new ClassPathResource(CommonAttributes.CONFIGURE_PROPERTIES_PATH);
				Properties properties = PropertiesLoaderUtils.loadProperties(resource);
				String APP_ID = properties.getProperty("APP_ID");
				String EncodingAESKey = properties.getProperty("EncodingAESKey");*/
        String APP_ID="wx444c40b933d68540";
        String EncodingAESKey ="CilCqRiuxKx7yF3gP0RQmVjhLqBHJ4Gv5qSk2qrGiWN";
        /**
         * =====加密模式处理====
         */

        String respMessage = CoreService.processRequestByAES(token,request,EncodingAESKey,APP_ID);
        /**
         * =====明文模式处理====
         */

        //String respMessage = CoreService.processRequest(request);


        // 响应消息
        /*try {
            PrintWriter out = response.getWriter();
            out.print(respMessage);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        return respMessage;
        //logger.info("【处理微信服务器发来的消息】message:" +  MessageUtil.inputStream2String(request.getInputStream()));
       //return "Post,token:"+token+" IP:"+request.getRemoteAddr();
    }
    @ResponseBody
    @RequestMapping(value="exchange/{token}",method = RequestMethod.GET)
    public String exchangdataGet(@PathVariable String token, HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        // 微信加密签名
        String signature = request.getParameter("signature");

        // 时间戳
        String timestamp = request.getParameter("timestamp");

        // 随机数
        String nonce = request.getParameter("nonce");

        // 随机字符串
        String echostr = request.getParameter("echostr");

        logger.info("echostr：" + echostr + " nonce:" + nonce + " signature:" + signature);

        try {
            //通过检验signature对请求进行校验，若校验成功则原样返回echostr,表示接入成功，否则接入失败
            if (MessageUtil.checkSignature(token, signature, timestamp, nonce)) {
                logger.info("验证成功");
               //response.getWriter().print(echostr);
                return echostr;
              // return ;
            } else {
                logger.info("验证失败");
            }

        } catch (Exception e) {
            logger.error("验证失败,数据不完整");
        }
       // return "GET,token:"+token+" IP:"+request.getRemoteAddr();
     /*   try {
            response.getWriter().print("验证失败");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return "验证失败";
    }


}
