package com.imooc.o2o.service;

import com.imooc.o2o.dto.LocalAuthExecution;
import com.imooc.o2o.entity.LocalAuth;

import exceptions.LocalAuthException;

/**
* @author xf
* @version 2018年9月13日 下午2:10:23
*/
public interface LocalAuthService {
	/**
	 * 通过账号和密码获取平台账号信息
	 * @param userName
	 * @param password
	 * @return
	 */
	LocalAuth getLocalAuthByUserNameAndpwd(String userName, String password);
	
	/**
	 * 通过userId获取平台账号信息
	 * @param userId
	 * @return
	 */
	LocalAuth getLocalAuthByUserId(Long userId);
	/**
	 * 生成平台专属的账号
	 * @param localAuth
	 * @return
	 */
	LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthException;
	
	LocalAuthExecution updateLocalAuth(Long userId, String userName, String password, String newPassword)
	throws LocalAuthException;
	
}
