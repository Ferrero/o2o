package com.imooc.o2o.dto;
/**
* @author xf
* @version 2018年8月11日 下午5:14:47
*/
/**
 *封装json对象，所有返回结果都使用它
 *<T>泛型
 */
public class Result<T> {
	private boolean success;//是否成功标志
	
	private T data ; //成功时返回的数据
	
	private String errorMsg;//错误信息
	
	private int errorCode;//状态码
	
	public Result() {
		
	}
	
	//成功时的构造器
	public Result(boolean success, T data) {
		this.success = success;
		this.data = data;
	}
	
	//错误时的构造器
	public Result(boolean success, String errorMsg, int errorCode) {
		this.success = success;
		this.errorMsg = errorMsg;
		this.errorCode = errorCode;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
}
