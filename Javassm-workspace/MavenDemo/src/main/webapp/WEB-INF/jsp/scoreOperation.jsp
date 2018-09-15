<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>首页</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		var str = "";
		var str1 = "";
		var on = true;
		var url = "/MavenDemo/score/scoreInfoList";
		query();
		function query() {
					$.ajax({
						type : "get",
						url : url,
						dataType : "json",
						contentType : "application/json",
						/* data:{
							//studentId : $("#inputId").val()
						}, */
						success : function(data) {
							console.log(data.scoreInfoList);
							
						
							/* if (data.success) {
								$.toast("提交成功")
							} else {
								$.toast("提交失败！" + data.errMsg)
							} */
							//jquery之从ajax获取json数据以表格的形式显示在页面上(拼接)
							
							str += "<table border='1' cellpadding='0' cellspacing='0'><thead><tr><th></th><th>学号</th><th>姓名</th><th>语文成绩</th><th>数学成绩</th><th>英语成绩</th></tr></thead><tbody>";
							$.each(data.scoreInfoList,
									function(index, element) {
										str += 
											"<tr><td><input type='checkbox'/></td><td>"
												+ element['studentId']
												+ "</td><td>" + element['name']
												+ "</td><td>"
												+ element['chineseScore']
												+ "</td><td>"
												+ element['mathScore']
												+ "</td><td>"
												+ element['englishScore']
												+ "</td></tr>";
									})
							//遍历完成之后
							str += "</tbody></table>";
							//将表格添加到body下table子标签中
							$('#table').append(str);

						},
						error : function(err) {
							console.log("失敗" + err);
						}
					});
		}
		
		
		
		$("#query").click(function() {
			$.ajax({
				type : "get",
				url : "/MavenDemo/score/scoreInfoList",
				dataType : "json",
				contentType : "application/json",
				/* data: JSON.stringify({
					studentId : $("#inputId").val()
				}), */
				data: {studentId : $("#inputId").val()},
				success : function(data) {
					console.log(data.scoreInfoList);
					str1 += "<table border='1' cellpadding='0' cellspacing='0'><thead><tr><th></th><th>学号</th><th>姓名</th><th>语文成绩</th><th>数学成绩</th><th>英语成绩</th></tr></thead><tbody>";
					$.each(data.scoreInfoList,
							function(index, element) {
								str1 += 
									"<tr><td><input type='checkbox'/></td><td>"
										+ element['studentId']
										+ "</td><td>" + element['name']
										+ "</td><td>"
										+ element['chineseScore']
										+ "</td><td>"
										+ element['mathScore']
										+ "</td><td>"
										+ element['englishScore']
										+ "</td></tr>";
							})
					//遍历完成之后
					str1 += "</tbody></table>";
					//将表格添加到body下table子标签中
					//使用html()替换掉table里拼接的数据
					$('#table').html("");
				},
				error : function(err) {
					console.log("失敗" + err);
				},
				complete:function(){
					$('#table').html(str1);
				}
			});
		});
		
		/* $("#insert").click(function(){
			$.ajax({
				
			});
		}); */
	});
</script>
<style>
#container {
	width: 600px;
	min-height: 400px;
	margin: 100px auto;
}

#container header {
	width: 600px;
	height: 100px;
}

#container header input {
	width: 300px;
	height: 40px;
	border: 1px solid #ccc;
	border-radius: 10px;
	text-indent: 10px;
}

#container header button {
	background: #999;
	width: 80px;
	height: 30px;
	border: none;
	color: #fff;
	cursor: pointer;
}

#container header button:hover {
	color: #ff0;
	background: #f0f;
}

#container article {
	width: 600px;
	height: 300px;
}

#container article table tr td {
	width: 100px;
	min-height: 40px;
	text-align: center;
}
</style>
</head>
<body>
		<div id="container">
		<header>
			<input type="text" placeholder="请输入学号查询" id="inputId" autofocus>
			<button id="query">查询</button>
			<button id="insert">新增</button>
			<button id="delete">删除</button>
		</header>
		<article id="table">
			<!-- <table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td></td>
					<td>学号</td>
					<td>姓名</td>
					<td>语文成绩</td>
					<td>数学成绩</td>
					<td>英语成绩</td>
				</tr> -->

	<%-- 	<c:forEach items="${scoreInfoList}" var="score">
					<tr>
					<td><input type="checkbox"></td>
					<td>${score.studentId}</td>
					<td>${score.name}</td>
					<td>${score.chineseScore}</td>
					<td>${score.mathScore}</td>
					<td>${score.englishScore}</td>
					</tr>
					</c:forEach>
		 --%>




	<!-- </table> -->
	</article>
	</div>
</body>
</html>
