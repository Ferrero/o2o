package com.imooc.o2o.dto;
/**
* @author xf
* @version 2018年9月14日 下午3:37:04
*/

import com.imooc.o2o.entity.LocalAuth;
import com.imooc.o2o.enums.LocalAuthEnum;

public class LocalAuthExecution {
	private int state;
	private String stateInfo;
	private LocalAuth localAuth;
	
	public LocalAuth getLocalAuth() {
		return localAuth;
	}
	public void setLocalAuth(LocalAuth localAuth) {
		this.localAuth = localAuth;
	}
	public LocalAuthExecution(int state, String stateInfo) {
		super();
		this.state = state;
		this.stateInfo = stateInfo;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	public LocalAuthExecution(LocalAuthEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	public LocalAuthExecution(LocalAuthEnum stateEnum,LocalAuth localAuth) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.localAuth = localAuth;
	}
	
}
