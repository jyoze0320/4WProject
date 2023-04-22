<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>pj_4W Shop</title>
		<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet">
		
		<style>
			@import
			url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap');
		</style>
	</head>

	<body>
		<div id="grand_header">
		<!--헤더파일 들어가는 곳 시작-->
			<header>
			<table id="grand_t_header">
			<tr>
				<td>
					<div id="logo">
						<a style="text-decoration:none;" href="4w.do?command=index">
						<img src="${pageContext.request.contextPath}/image/header/4WLOGO.png" alt="logo_1">
						<img src="${pageContext.request.contextPath}/image/header/4WLOGO2.png" alt="logo_2"></a>
					</div>
				</td>     
				<td>
					<div id="login_menu">
						<c:choose>
						<c:when test="${empty sessionScope.loginUser}">
							<a style="text-decoration:none;" href="4w.do?command=login">
							<img src="${pageContext.request.contextPath}/image/header/1_login.png" alt="login">
							</a>
						</c:when>
						<c:otherwise>
							<a style="text-decoration:none;" href="4w.do?command=logout">
							<img src="${pageContext.request.contextPath}/image/header/2_logout.png" alt="logout">
							</a>
							
							<a style="text-decoration:none;" href="4w.do?command=modmem">
							<img src="${pageContext.request.contextPath}/image/header/3_editmyinfo.png" alt="editmyinfo">
							</a>
							
							<a style="text-decoration:none;" href="4w.do?command=order">
							<img src="${pageContext.request.contextPath}/image/header/4_cart.png" alt="cart">
							</a>
						</c:otherwise>
						</c:choose>
						<a style="text-decoration:none;" href="4w.do?command=qna_list">
						<img src="${pageContext.request.contextPath}/image/header/5_qna.png" alt="qna">
						</a>
					</div>
				</td>
			</tr>
			</table>
			</header>
		</div>
<!--헤더파일 들어가는 곳 끝 -->
