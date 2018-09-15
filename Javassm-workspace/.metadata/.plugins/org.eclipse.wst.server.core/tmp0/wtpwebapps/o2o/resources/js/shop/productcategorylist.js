$(function() {
	getproductcategorylist();
	function getproductcategorylist() {
		$.ajax({
			url : "/o2o/shopadmin/getproductcategorylist",
			type : "get",
			dataType : "json",
			success : function(data) {
				if (data.success) {
					handleList(data.data);// data.data,
											// 获取后台传过来的商品列表数据。之前controller层使用
					// Map<String,Object>方式查询数据，传给前台的数据名为productcategorylist
				} // 之后采用result<T>,却忽略了更改传值的名称，以至于浏览器检查时一直报map错误。
			},
		});
	}
	
	
	
	function handleList(data) {
		var html = '';
		$.map(data,function(item) {
			html += '<div class="row row-product-category now"><div class="col-40">'
					+ item.productCategoryName
					+ '</div><div class = "col-40">'
					+ item.priority
					+ '</div><div class="col-20"><a href="#" class="button delete" data-id="'
					+ item.productCategoryId
					+ '">删除</a></div>' + '</div>';
		});
		$('.category-wrap').html(html);// . 表示类标签
	}

		
		
	$("#new").on("click",function(){
		var tempHtml = '<div class="row row-product-category temp">'
			+ '<div class="col-40"><input class="category-input category" type="text" placeholder="商店类别"/>'
			+ '</div><div class = "col-40"><input class="category-input priority" type="number" placeholder="优先级"/>'
			+ '</div><div class="col-20"><a href="#" class="button delete">删除</a></div>'
			+ '</div>';
		$('.category-wrap').append(tempHtml);
	});
		

		$("#submit").click(function() {
			// 通过temp 获取新增的行
			var tempArr = $('.temp');
			// 定义数组接收新增的数据
			var productCategoryList = [];
			tempArr.map(function(index, item) {
				var tempObj = {};
				tempObj.productCategoryName = $(item).find('.category').val();
				tempObj.priority = $(item).find('.priority').val();
				// 商品类别和优先级都需要输入
				if (tempObj.productCategoryName && tempObj.priority) {
					productCategoryList.push(tempObj);
				}
			});
			$.ajax({
				url : "/o2o/shopadmin/addproductcategorys",
				type : "post",
				// 后端通过 @RequestBody直接接收
				//需要通过JSON.stringify(productCategoryList)把页面中获取的参数变成json字符串,，此时需要加上contentType: "application/json",
				data : JSON.stringify(productCategoryList),
				contentType : "application/json",//发送内容类型为json
				success : function(data) {
					if (data.success) {
						$.toast("提交成功")
						getproductcategorylist();
					} else {
						// 当前端页面上没有输入商品类别就点击提交的话，后台返回errMsg:"请至少输入一个商品类别"给前端
						$.toast(data.errMsg);
					}
				},
				
			})
		});

		$(".category-wrap").on("click", ".row-product-category.temp .delete",//子级标签加空格
		function(e) {
			console.log($(this).parent().parent());//parent()父级标签
			$(this).parent().parent().remove();
		});

	$(".category-wrap").on("click", ".row-product-category.now .delete",
		function(e) {
			console.log("进入点击事件");
			var target = e.currentTarget;
			console.log(e.currentTarget);
			$.confirm('确定吗？', function() {
				$.ajax({
					url : "/o2o/shopadmin/removeproductcategory",
					type : 'POST',
					data : {
						productCategoryId : target.dataset.id
					},
					dataType : 'json',
					//不使用contentType: “application/json”则data可以是对象,
					//使用contentType: “application/json”则data只能是json字符串
					//dataType:预期服务器返回的数据类型。如果不指定，jQuery 将自动根据 HTTP 包 MIME 信息来智能判断，
					success : function(data) {
						if (data.success) {
							$.toast("删除成功！");
							getproductcategorylist();
						} else {
							$.toast("删除失败！");
						}
					}
				})
			})
		})
});0
