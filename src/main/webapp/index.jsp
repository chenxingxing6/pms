<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>用户登陆</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%= basePath%>">
<link rel="Shortcut Icon" href="resources/img/favicon.png" />
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="chenstars" />
</head>
<body>
<form action="user/form">
	<table>
		<tr>
			<td>用户名：</td>
			<td><input type="text" name="username" placeholder="root"></td>
		</tr>
		<tr>
			<td>密码：</td>
			<td><input type="text" name="password" placeholder="123456"></td>
		</tr>
		<tr>
			<td>其他：</td>
			<td><input type="text" name="other"></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit"></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<a href="/api/qq/login">QQ登录</a>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<a href="/api/weixin/login">微信登录</a>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<a href="/api/baidu/login">Baidu登录</a>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<a href="/api/sina/login">新浪微博登录</a>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<a href="/api/github/login">Github登录</a>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<a href="/api/osc/login">开源中国登录</a>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<a href="/api/douban/login">豆瓣登录</a>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<a href="/api/renren/login">人人网登录</a>
			</td>
		</tr>
	</table>
</form>

</body>
</html>
