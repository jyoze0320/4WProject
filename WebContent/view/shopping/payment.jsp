<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8" %>

<!-- 작성자 : 김준영 -->
<!-- html의 header를 추가함 -->
<%@ include file="../../header.jsp" %>
<!-- EL코어 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- path 선언, http://localhost:8080/project_4w-->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!-- CSS 링크 -->
<link rel="stylesheet" type="text/css" href="css/shopping.css?after"> 

<!-- 홈페이지 결제화면의 상단 이미지 -->
<div id = "mainimg">
	<img src="${pageContext.request.contextPath}/image/titlepic/5_payment.png" />
</div>
<!-- 홈페이지의 네비게이션 메뉴 -->
<%@ include file="categoryMenu.jsp" %> 

<section>
	<!-- 결제 내역 테이블 제목 -->
	<div class="tbl-header">
		<table>
			<thead>
				<tr>
					<td width="100">상품명</td>
					<td width="100">이미지</td>
					<td>내용</td>
					<td width="100">가격</td>
					<td width="100">결제방법</td>
					<td width="100">결제일</td>
					<td width="100"></td>
				</tr>
			</thead>
		</table>
	</div>
	<!-- 결제 내역 테이블 목록 -->
	<div class="tbl-content">
		<table>
			<!-- JSTL의 if -->
			<c:choose>
				<%-- if의 조건, empty는 null이면 ture,  EL태그로 값을 전달 받음--%>
				<c:when test="${!empty orderList}">
					<tbody>
						<%--JSTL의 for --%>
						<c:forEach items="${orderList}" var="orderList">
							<tr align="center">
								<td width="100"><h5>${orderList.pd_name}</h5></td>
								<td width="100"><a href="${contextPath}/4w.do?command=pddetail&pdnum=${orderList.pd_num}">
								<img src="image/products/${orderList.pd_image}" width="70" /></a></td>
								<td><h5>${orderList.pd_content}</h5></td>
								<td width="100"><h5><b><fmt:formatNumber value="${orderList.sell_price}" />원</b></h5></td>
								<td width="100"><h5>${orderList.payment}</h5></td>
								<td width="100"><h5>${orderList.order_date}</h5></td>
								<td width="100"><a href="image/products/${orderList.pd_image}" download="${orderList.pd_image}"><img src="image/productsdatail/wefwefg.png" width="30" /></a></td>
							</tr>
						</c:forEach><%--end for --%>
					</tbody>
				</c:when>
				<%-- if의 else--%>
				<c:otherwise>
					<tfoot>
						<tr><td colspan="7" align="center"><h5>결재 내역이 없습니다</h5></td></tr>
					</tfoot>
				</c:otherwise>
			</c:choose><%-- end if --%>
		</table>
	</div>
	<!-- 결제 목록의 총 합계 -->
	<table>
		<tr style="text-align: left;">
			<td>
				<label id="tatal">총 합계 : <fmt:formatNumber value="${ordertotal}" />원</label>
			</td>
		</tr>
	</table>
</section>

<!-- html의 footer를 추가함 -->
<%@ include file="../../footer.jsp"%>  