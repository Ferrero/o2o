package com.imooc.o2o.entity;

import java.util.Date;
/**
 * ΢���˺�ʵ����
 * @author xf
 *@version 2018-07-21
 */
public class WeChatAuth {
	//΢���˺�id
	private Long WeChatAuthId;
	//��Ϊ΢���˺��빫�ںŰ󶨵�Ψһ��ʶ
	private String openId;
	//����ʱ��
	private Date createTime;
	//�û�id  ���  �� ���û���Ϣʵ������û�id�໥������
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