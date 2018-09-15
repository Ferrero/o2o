/**
 * shopoperation.js功能：
 * 1.从后台获取到店铺分类以及区域等信息，将信息填充至前台HTML控件中去
 * 2.将表单的信息获取到转发到后台，进行注册店铺
 */
$(function(){
	 // 从URL里获取shopId参数的值
	var shopId = getQueryString('shopId');
	// 由于店铺注册和编辑使用的是同一个页面，
    // 该标识符用来标明本次是添加还是编辑操作
	var isEdit = shopId?true:false;
	// 用于店铺注册时候的店铺类别以及区域列表的初始化的URL
	var initUrl = '/o2o/shopadmin/getshopinitinfo';
	// 注册店铺的URL
	var registerShopUrl = '/o2o/shopadmin/registershop';
	// 编辑店铺前需要获取店铺信息，这里为获取当前店铺信息的URL
	var shopInfoUrl = "/o2o/shopadmin/getshopid?shopId="+shopId;
	// 编辑店铺信息的URL
	var editShopUrl = "/o2o/shopadmin/modifyshop";
	// 判断是编辑操作还是注册操作
	if(!isEdit){
		getShopInitInfo();
	}else{
		getShopInfo(shopId);
	}
	//alert(initUrl);
	
	function getShopInfo(shopId){
		$.getJSON(shopInfoUrl, function(data){
			if(data.success){
				console.log(data.shop);
				var shop = data.shop;
				$('#shop-name').val(shop.shopName);
				$('#shop-addr').val(shop.shopAddr);
				$('#shop-phone').val(shop.phone);
				$('#shop-desc').val(shop.shopDesc);
				var shopCategory= '<option data-id="'+ shop.shopCategory.shopCategoryId + '">'
				+ shop.shopCategory.shopCategoryName + '</option>';
				var tempAreaHtml = '';
				data.areaList.map(function(item,index){
					console.log(item,123);
					tempAreaHtml += '<option data-id="' + item.areaId +'">'
					+ item.areaName + '</option>';
					//console.log(tempAreaHtml,"地域");
					
				});
				$('#shop-category').html(shopCategory);
				$('#shop-category').attr('disabled', 'disabled');
				$('#area-category').html(tempAreaHtml);//注意自己的标签id名与老师的不一样
				$("#area-category option[data-id='"+shop.area.areaId+"']").attr('selected','selected');
//				console.log(shop.area.areaId,10000);
			}
		});
	}
	//submit点击事件原本放在getShopInitInfo方法下，导致点击事件无效.
	//原因在于传入shopId,执行编辑方法(getShopInfo),而不去执行getShopInitInfo方法，所以自然不会执行点击事件。
//	getShopInitInfo();
	$('#submit').click(function(){
		console.log("SDG");
		var shop = {};
		if(isEdit){
			shop.shopId = shopId;
		}
		shop.shopName = $('#shop-name').val();
		shop.shopAddr = $('#shop-addr').val();
		shop.phone = $('#shop-phone').val();
		shop.shopDesc = $('#shop-desc').val();
		shop.shopCategory = {
				shopCategoryId:$('#shop-category').find('option').not(function(){
					return !this.selected;
				}).data('id')
		};
		shop.area = {
				areaId:$('#area-category').find('option').not(function(){
					return !this.selected;
				}).data('id')
		};
		var shopImg = $('#shop-img')[0].files[0];
		//FormData对象，可以把所有表单元素的name与value组成一个queryString,提交到后台(简单多图+其它参数 用FormData 上传)
		var formData = new FormData();
		formData.append('shopImg',shopImg);
		formData.append('shopStr',JSON.stringify(shop));
		var verifyCodeActual = $('#j_captcha').val();
		if(!verifyCodeActual){
			$.toast("请输入验证码");
			return;
		}
		formData.append('verifyCodeActual',verifyCodeActual);
		$.ajax({
			url:isEdit?editShopUrl:registerShopUrl,
			type:'POST',
			data:formData,
			contentType:false,
			processData:false,
			cashe:false,
			success:function(data){
				console.log(data,9898);
				if(data.success){
					$.toast('提交成功！');
					console.log(1234)
				}else{
					$.toast('提交失败！' + data.errMsg);
					console.log(5678);
				}
				$("#captcha_ima").click();
			},
			error:function(data){
				console.log(data,errMsg);
			}
		});
	});
	
	function getShopInitInfo(){
		console.log("fags",22222);
		$.getJSON(initUrl,function(data){
			console.log(data);
			if(data.success){
				var tempHtml = '';
				var tempAreaHtml = '';
				data.shopCategoryList.map(function(item,index){
					tempHtml += '<option data-id="'+ item.shopCategoryId + '">'
					+ item.shopCategoryName + '</option>';
				});
				data.areaList.map(function(item,index){
					
					tempAreaHtml += '<option data-id="' + item.areaId +'">'
					+ item.areaName + '</option>';
					console.log(tempAreaHtml,1111111111);
				});
				$('#shop-category').html(tempHtml);
				$('#area-category').html(tempAreaHtml);
			}else{
				console.log(3333333);
			}
		});
	
	}
})