<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>

<!-- 작성자 : 김준영 -->
<!-- html의 header를 추가함 -->
<%@ include file="../header.jsp" %> 
<!-- path 선언, http://localhost:8080/project_4w-->
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<!-- CSS 링크 -->
<link rel="stylesheet" type="text/css" href="css/shopping.css">

<!-- 홈페이지 첫화면의 상단 이미지 -->
<div id = "mainimg">
	<img src="${contextPath}/image/titlepic/6_index.png" />
</div>
<!-- 홈페이지의 네비게이션 메뉴 -->
<%@ include file="view/shopping/categoryMenu.jsp" %> 

<!-- 전체 상품 목록 테이블 -->
<table>
	<tr>
		<td>
			<!-- JSTL의 forEach로 반복(for) 수행, 모든 상품 목록 출력 -->
			<c:forEach items="${productsMain}"  var="productsVO" varStatus="status">
				<div style="display:inline;  text-align: center" >
					<%--상품 이지지 클릭시 해당 상품 정보 URL를 요청함 --%>
					<a href="${contextPath}/4w.do?command=pddetail&pdnum=${productsVO.pd_num}">
					<img src="${contextPath}/image/products/${productsVO.pd_image}" height="250"/></a>	
				</div>
			</c:forEach><!-- end for -->
		</td>
	</tr>
</table>
<!-- html의 footer를 추가함 -->
<%@ include file="../footer.jsp" %>    