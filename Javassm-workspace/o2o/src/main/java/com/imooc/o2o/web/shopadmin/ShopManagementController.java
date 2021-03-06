package com.imooc.o2o.web.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.service.AreaService;
import com.imooc.o2o.service.ShopCategoryService;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.CodeUtil;
import com.imooc.o2o.util.HttpServletRequestUtil;

import exceptions.ShopOperationException;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;

	
	@RequestMapping(value = "/getshopmanagementinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopManagementInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId <= 0) {
			Object currentShopObj = request.getSession().getAttribute("currentShop");
			if (currentShopObj == null) {
				modelMap.put("redirect", true);
				modelMap.put("url", "/o2o/shopadmin/shoplist");
			} else {
				Shop currentShop = (Shop) currentShopObj;
				modelMap.put("redirect", false);
				modelMap.put("shopId", currentShop.getShopId());
			}
		} else {
			Shop currentShop = new Shop();
			currentShop.setShopId(shopId);
			request.getSession().setAttribute("currentShop", currentShop);
			modelMap.put("redirect", false);
		}
		return modelMap;
	}

	@RequestMapping(value = "/getshoplist", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		PersonInfo user = new PersonInfo();
		user.setUserId(1L);
		user.setName("test");
		request.getSession().setAttribute("user", user);
		user = (PersonInfo) request.getSession().getAttribute("user");
		// long employeeId = user.getUserId();
		// List<Shop> shopList = new ArrayList<Shop>();
		try {
			Shop shopCondition = new Shop();
			shopCondition.setOwner(user);
			ShopExecution se = shopService.getShopList(shopCondition, 0, 99);
			modelMap.put("shopList", se.getShopList());
			modelMap.put("user", user);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value = "/getshopid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId > -1) {
			try {
				Shop shop = shopService.getByShopId(shopId);
				List<Area> areaList = areaService.getAreaList();
				modelMap.put("shop", shop);
				modelMap.put("areaList", areaList);
				modelMap.put("success", true);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty shopId");
		}
		return modelMap;
	}

	@RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		// 1.接受并转化相应的参数，包括店铺信息以及图片信息
		// 1.1 shop信息
		// (shopStr 是和前端约定好的参数值，后端从request中获取request这个值来获取shop的信息)
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		// 使用jackson-databind 将json转换为pojo
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			// 将json转换为pojo
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			// 将错误信息返回给前台
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		// 1.2 图片信息 基于Apache Commons FileUpload的文件上传
		// Spring MVC中的 图片存在CommonsMultipartFile中
		CommonsMultipartFile shopImg = null;
		// 从request的本次会话中的上下文中获取图片的相关内容
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 使用commonsMultipartResolver判断request是否有上传的文件流
		if (commonsMultipartResolver.isMultipart(request)) {
			// 如果有的话，需要对request进行转化，将其转化成MultipartHttpServletRequest对象
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			// 从文件中提取出相应的文件流，shopImg是和前端约定好的变量名
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		}
		// 2.修改店铺信息
		if (shop != null && shop.getShopId() != null) {
			ShopExecution se;
			try {
				if (shopImg == null) {

					se = shopService.modifyShop(shop, null);
				} else {
					ImageHolder imageHolder = new ImageHolder(shopImg.getOriginalFilename(), shopImg.getInputStream());
					se = shopService.modifyShop(shop, imageHolder);
				}
				if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (ShopOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺Id");
			return modelMap;
		}

	}

	@RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopInitInfo() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		List<Area> areaList = new ArrayList<Area>();
		try {
			shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
			areaList = areaService.getAreaList();
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value = "/registershop", method = RequestMethod.POST)
	@ResponseBody
	/**
	 * @Title: registerShop
	 * @Description:1. 接收并转换相应的参数，包括shop信息和图片信息 2. 注册店铺 3. 返回结果给前台
	 * @param: request
	 *             因为是接收前端的请求，而前端的信息都封装在HttpServletRequest中，
	 *             所以需要解析HttpServletRequest，获取必要的参数
	 * @return: Map<String,Object>
	 */

	private Map<String, Object> registerShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}

		// 1.接受并转化相应的参数，包括店铺信息以及图片信息
		// 1.1 shop信息
		// (shopStr 是和前端约定好的参数值，后端从request中获取request这个值来获取shop的信息)
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		// 使用jackson-databind 将json转换为pojo
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			// 将json转换为pojo
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			// 将错误信息返回给前台
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		// 1.2 图片信息 基于Apache Commons FileUpload的文件上传
		// Spring MVC中的 图片存在CommonsMultipartFile中
		CommonsMultipartFile shopImg = null;
		// 从request的本次会话中的上下文中获取图片的相关内容
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 使用commonsMultipartResolver判断request是否有上传的文件流
		if (commonsMultipartResolver.isMultipart(request)) {
			// 如果有的话，需要对request进行转化，将其转化成MultipartHttpServletRequest对象
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			// 从文件中提取出相应的文件流，shopImg是和前端约定好的变量名
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		} else {
			// 将错误信息返回给前台
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}
		// 2.注册店铺
		/* shopExecution se = shopService.addShop(shop, shopImg); 改造前的调用方式
		 这个时候，我们从前端获取到的shopImg是CommonsMultipartFile类型的，如何将CommonsMultipartFile转换为file.
		 网上也有将CommonsMultipartFile转换为File的方法，并通过maxInMemorySize的设置尽量不产生临时文件
		 这里我们换个思路,因为CommonsMultipartFile可以获取InputStream,Thumbnailator又可以直接处理输入流
		 因为InputStream中我们无法得到文件的名称，而thumbnail中需要根据文件名来获取扩展名，
		 所以还要再加一个参数String类型的fileName
		 既然第二个和第三个参数都是通过shopImg获取的，为什么不直接传入一个shopImg呢？
		 主要是为了service层单元测测试的方便，因为service层很难实例化出一个CommonsMultipartFile类型的实例*/
		if (shop != null && shopImg != null) {
			// 店主persionInfo的信息，肯定要登录才能注册店铺。
			// 所以这部分信息我们从session中获取，尽量不依赖前端,这里暂时时不具备条件，后续改造，先硬编码，方便单元测试
			// PersonInfo owner = new PersonInfo();
			// Session TODO
			PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
			shop.setOwner(owner);
			// File shopImgFile = new File(PathUtil.getImgbasePath() +
			// ImageUtil.getRandomFileName());
			/*
			 * try{ shopImgFile.createNewFile(); }catch(IOException e){
			 * modelMap.put("success", false); modelMap.put("errMsg", e.getMessage());
			 * return modelMap; } try { inputStreamToFile(shopImg.getInputStream(),
			 * shopImgFile); }catch(IOException e) { modelMap.put("success", false);
			 * modelMap.put("errMsg", e.getMessage()); return modelMap; }
			 */
			ShopExecution se;
			try {
				ImageHolder imageHolder = new ImageHolder(shopImg.getOriginalFilename(), shopImg.getInputStream());
				se = shopService.addShop(shop, imageHolder);
				if (se.getState() == ShopStateEnum.CHECK.getState()) {
					modelMap.put("success", true);
					// 该用户可以操作的店铺列表
					List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
					if (shopList == null || shopList.size() == 0) {
						shopList = new ArrayList<Shop>();
						/*
						 * shopList.add(se.getShop()); request.getSession().setAttribute("shopList",
						 * shopList); }else {
						 */
						shopList.add(se.getShop());
						request.getSession().setAttribute("shopList", shopList);
					}
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (ShopOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}

	}
	// 3.返回结果

	/*
	 * private static void inputStreamToFile(InputStream ins, File file) {
	 * FileOutputStream os = null; try { os = new FileOutputStream(file); int
	 * bytesRead = 0; //定义缓冲区的大小,buffer数组长度为1024 byte[] buffer = new byte[1024];
	 * //如果不等于-1，就继续读取并复制文件。直到-1说明已读完，已达文件末尾，退出循环。 while ((bytesRead =
	 * ins.read(buffer)) != -1) { os.write(buffer,0,bytesRead); } }catch(Exception
	 * e) { throw new RuntimeException("调用inputStreamToFile产生异常" + e.getMessage());
	 * }finally { try { if(os != null) { os.close(); } if(ins != null) {
	 * ins.close(); } }catch(IOException e) { throw new
	 * RuntimeException("inputStreamToFile关闭io产生异常" + e.getMessage()); } } }
	 */
}
