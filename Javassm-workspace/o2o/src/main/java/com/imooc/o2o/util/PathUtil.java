package com.imooc.o2o.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathUtil {
	private static final Logger logger = LoggerFactory.getLogger(PathUtil.class);
	//依据执行环境（操作系统）的不同，选择不同的根目录,返回项目图片根路径
	//获取当前系统文件的分隔符
	//！！错误：private static String seperator = System.getProperty("file.seperator");
	//file.separator !! 单词拼错，导致imagePath调用replace方法后直接抛出异常，不过经试验在本机上"\"和"/"两种分隔符可以串联，即分隔符不影响上传和保存图片。
	private static String seperator = System.getProperty("file.separator");
	 /**
     * 
     * 
     * @Title: getImgBasePath
     * 
     * @Description: 根据不同的操作系统,获取图片的存放路径。
     * 
     *               一般情况下都是将图片存放到专门的图片服务器或者主机上的其他目录。
     *               而存放的目录路径，都是以配置项的形式存放在数据库配置表中或者配置文件中。
     * 
     *               这里为了简单起见，我们直接将路径硬编码在代码中。
     * 
     *               
     * @return		basePath  图片存储的根路径
     * 
     */
	public static String getImgbasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";
		if(os.toLowerCase().startsWith("win")) {
			basePath = "c:/porjectdev/image";
		}else {
			basePath = "/home/rong/image/";
		}
		//basePath = basePath.replace("/",seperator);
		return basePath;
	}
	//获取各个店铺下的图片的子路径
	/**
     * 
     * 
     * @Title: getShopImagePath
     * 
     * @Description: 获取特定商铺的图片的路径,根据shopId来区分。理应配置到数据库配置表中或者配置文件中。
     * 
     *               同样的这里为了简化操作,硬编码
     * 
     *               约定每个店铺下的图片分别存放在对应的店铺id下，即为子路径[upload/item/shop/1/]
     * 
     *               图片最终存储的位置,即绝对路径，需要加上getImgBasePath方法返回的basePath（根路径）[c:/porjectdev/image/ + upload/item/shop/1/2018082310101962804.jpg]
     * 
     *               数据库tb_shop中的shop_img字段存储的是相对路径[upload/item/shop/1/2018082310101962804.jpg]
     * 
     * @param shopId
     * @return
     * 
     * @return: String
     */
	public static String getShopImagePath(long shopId) {
		//店铺图片子路径（upload/item/shop/1/）
		String imagePath = "/upload/item/shop/" + shopId + "/";
		logger.debug("shopImgPath: {}", imagePath);
		//imagePath = imagePath.replace("/",seperator);
		//System.out.println("替换分隔符之后的imagePath = " + imagePath);
		return imagePath;
	}
		
	
}