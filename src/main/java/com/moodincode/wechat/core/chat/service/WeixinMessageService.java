package com.moodincode.wechat.core.chat.service;

import java.util.List;

public  class  WeixinMessageService {

	/*public WeixinReplyMessageService weixinReplyMessageService=null;
	
	public  WeixinQrcodeService weixinQrcodeService=null;
	
	public WeixinUserQrcodeService weixinUserQrcodeService=null;*/
	
	
/**
 * 获取默认回复
 * @return
 */
	/*public  List<WeixinReplyMessage> getDefaultMessge(){
	
		weixinReplyMessageService = (WeixinReplyMessageService) SpringUtils.getBean("weixinReplyMessageServiceImpl");
		List<WeixinReplyMessage> replylist=null;
		try {
			 replylist=weixinReplyMessageService.getList("replyType", ReplyType.DefaultText);
		} catch (Exception e) {
			replylist=null;
		}
		
		return replylist;
	}*/
	/**
	 * 获取欢迎信息回复
	 * @return
	 */
		/*public  List<WeixinReplyMessage> getWelcomeMessge(){
		
			weixinReplyMessageService = (WeixinReplyMessageService) SpringUtils.getBean("weixinReplyMessageServiceImpl");
			List<WeixinReplyMessage> replylist=null;
			try {
				 replylist=weixinReplyMessageService.getList("replyType", ReplyType.WelcomeText);
			} catch (Exception e) {
				replylist=null;
			}
			
			return replylist;
		}*/
	/**
	 * 匹配自动回复
	 * @param keyword
	 * @return
	 */
	/*public List<WeixinReplyMessage> getKeyWordMessge(String keyword){
		weixinReplyMessageService = (WeixinReplyMessageService) SpringUtils.getBean("weixinReplyMessageServiceImpl");
		List<WeixinReplyMessage> replylist=null;
		try {
			replylist=weixinReplyMessageService.getListByKeyword(keyword);
		} catch (Exception e) {
			replylist=null;
		}
		return replylist;
	}*/
	/**
	 * 更新带参数二维码的访问数
	 * @param ticket
	 * @param strID
	 * @return
	 */
	public Boolean updateQrCodeVisitCount(String ticket,String strID){
		/*weixinQrcodeService = (WeixinQrcodeService) SpringUtils.getBean("weixinQrcodeServiceImpl");
		WeixinQrcode qrcode=null;
		try {
			qrcode =weixinQrcodeService.get("ticket", ticket);
			if(qrcode!=null){
				Long count=qrcode.getVisitCount()==null?0L:qrcode.getVisitCount();
				qrcode.setVisitCount(count+1L);
				weixinQrcodeService.update(qrcode);
			}
		} catch (Exception e) {
			return false;
		}*/
		return true;
	}
	/**
	 * 新增带参数二维码的关注人数,空值时不作处理
	 * @param ticket
	 * @param strID
	 * @return
	 */
	public Boolean InsertQrCodeUserCount(String ticket,String openID,String strID){
	/*
		weixinQrcodeService = (WeixinQrcodeService) SpringUtils.getBean("weixinQrcodeServiceImpl");
		WeixinQrcode qrcode=null;
		try {
			qrcode =weixinQrcodeService.get("ticket", ticket);
			if(qrcode!=null){
				weixinUserQrcodeService = (WeixinUserQrcodeService) SpringUtils.getBean("weixinUserQrcodeServiceImpl");
				WeixinUserQrcode wxuqc=null;
				wxuqc=weixinUserQrcodeService.get("openID", openID);
				if(wxuqc==null){
					wxuqc=new WeixinUserQrcode();
					wxuqc.setOpenID(openID);
					wxuqc.setWeixinQrcode(qrcode);
					wxuqc.setRemark(strID);
					weixinUserQrcodeService.save(wxuqc);
				}
				
			}
		} catch (Exception e) {
			return false;
		}*/
		return true;
	}
	
	/**
	 * 取消关注时,删除二维码关注人.

	 * @return
	 */
	public Boolean RemoveQrCodeUserCount(String openID){
		
		
		/*try {
			
				weixinUserQrcodeService = (WeixinUserQrcodeService) SpringUtils.getBean("weixinUserQrcodeServiceImpl");
				WeixinUserQrcode wxuqc=null;
				wxuqc=weixinUserQrcodeService.get("openID", openID);
				if(wxuqc!=null){
					weixinUserQrcodeService.delete(wxuqc.getId());
					
				}
				
			
		} catch (Exception e) {
			return false;
		}*/
		return true;
	}
}
