<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//====================== JS最常用三种弹出对话框 ========================  

	//弹出对话框并输出一段提示信息  
	function ale() {
		//弹出一个对话框  
		alert("提示信息！");

	}

	//弹出一个询问框，有确定和取消按钮  
	function firm() {
		//利用对话框返回的值 （true 或者 false）  
		if (confirm("你确定提交吗？")) {
			alert("点击了确定");
		} else {
			alert("点击了取消");
		}

	}

	//弹出一个输入框，输入一段文字，可以提交  
	function prom() {
		var name = prompt("请输入您的名字", ""); //将输入的内容赋给变量 name ，  

		//这里需要注意的是，prompt有两个参数，前面是提示的话，后面是当对话框出来后，在对话框里的默认值  
		if (name)//如果返回的有内容  
		{
			alert("欢迎您：" + name)
		}

	}
</script>
</head>
<body>
	<script language="javascript">
		window.alert = function(title, content) {
			execScript('MsgBox   "' + content + '",0,"' + title + '"',
					'VBScript');
		}
		alert("标题", "内容");
	</script>
	<!-----------按钮提示框---------->
	<input type="button" name="btn2" id="btn2" value="删除"
		onclick="return confirm('Yes/No');" />

	<!-----------按钮提示框---------->
	<input type="button" name="btn2" id="btn2" value="提示"
		onclick="javaScript:alert('您确定要删除吗？');" />

	<!-----------提交按钮---------->
	<input type="button" value="提交"
		onclick="javaScript:window.location.href='http://www.baidu.com';" />

	<!-----------关闭按钮---------->
	<input type="button" value="关闭" onclick="javaScript:window.close();">

	<!-----------返回并关闭连接---------->
	<a href="#"
		onclick="javascript:;window.opener.location.reload();window.close()">返回</a>
	<!-- javaScript:window.location.reload();//返回当前页并刷新 -->

	<!-----------返回上一级页面---------->
	<input type="button" name="button" value="< 返回"
		onclick="javascript:history.go(-1)" />
</body>
</html>