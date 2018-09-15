package com.imooc.o2o.entity;

import java.util.Date;
/**
 * 微信账号实体类
 * @author xf
 *@version 2018-07-21
 */
public class WeChatAuth {
	//微信账号id
	private Long WeChatAuthId;
	//作为微信账号与公众号绑定的唯一标识
	private String openId;
	//创建时间
	private Date createTime;
	//用户id  外键  （ 与用户信息实体类的用户id相互关联）
	private PersonInfo personInfo;
	
	public Long getWeChatAuthId() {
		return WeChatAuthId;
	}
	public void setWeChatAuthId(Long weChatAuthId) {
		WeChatAuthId = weChatAuthId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public PersonInfo getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
	
}
