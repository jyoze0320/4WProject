<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 작성자 : 김준영 -->
<!-- html의 header를 추가함 -->
<%@ include file="../../header.jsp" %>
<!-- EL코어 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!-- path 선언, http://localhost:8080/project_4w-->
<c:set var="contextPath" value="${pageContext.request.contextPath}"  /> 
<!-- CSS 링크 -->
<link rel="stylesheet" type="text/css" href="css/shopping.css?after">
<!-- jquery 다운 -->
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<!-- 홈페이지 카테고리화면의 상단 이미지 -->
<div id = "mainimg">			
	<img src="${pageContext.request.contextPath}/image/titlepic/cate_${kind}.png" />
</div>
<!-- 홈페이지의 네비게이션 메뉴 --> 
<%@ include file="categoryMenu.jsp" %> 

<!-- 결제 내역 테이블 -->
<table>
	<tr>
		<td>
			<!-- JSTL의 for -->
			<c:forEach items="${productsView}"  var="productsView" varStatus="status">
				<div style="display:inline;  text-align: center" >
					<a href="${contextPath}/4w.do?command=pddetail&pdnum=${productsView.pd_num}">
					<img src="${contextPath}/image/products/${productsView.pd_image}" height="250"/></a>
				</div>
			</c:forEach><!-- end for -->
		</td>
	</tr>
</table>

<!-- html의 footer를 추가함 -->
<%@ include file="../../footer.jsp" %>  













