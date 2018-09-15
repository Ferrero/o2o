package com.imooc.o2o.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.entity.LocalAuth;

/**
* @author xf
* @version 年9月12日 下午4:54:29
*/
public interface LocalAuthDao {

	/**
	 * 通过账号和密码查询对应信息，登录用
	 * @param userName
	 * @param password
	 * @return
	 */
	LocalAuth queryByNameAndPassword(@Param("userName") String userName, @Param("password") String password);
	/**
	 * 通过用户id查询对应localAuth
	 * @param userId
	 * @return
	 */
	LocalAuth queryByUserId(Long userId);
	
	/**
	 *添加平台账号
	 * @param localAuth
	 * @return
	 */
	int insertLocalAuth(LocalAuth localAuth);

	/**
	 * 通过userId，userName,password更改密码
	 * @param userId
	 * @param userName
	 * @param password
	 * @param newPassword
	 * @return
	 */
	int updateLocalAuth(@Param("userId") Long userId, @Param("userName") String userName, 
			@Param("password") String password, @Param("newPassword") String newPassword,
			@Param("lastEditTime") Date lastEditTime);
}


