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
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script>
//$(document).ready(function(){}); 와 같음
$(function() {
	//basketimg(장바구니 이동 아이콘) 클릭 시 작동
	$("#basketimg").click(function(){
		//세션에 로그인이 되어있는지 확인
		if(${not empty sessionScope.loginUser}){
			$.ajax({
				type:"post",	//보낼 방식
				async:false,	//비동기 여부
				url:"${contextPath}/4w.do?command=basketin",	//요청될 URL
				data:{pdnum:${productsDetail.pd_num}},			//상품에 대한 고유번호 보냄
				success: function(chk) {						//통신이 성공했으면 return값 받음
					//장바구니 등록되있으면 3, 결제되어 있으면 1, 둘다 아니면 2
					if (chk==2){			
						//confirm에서 확인은 true, 아니요는 false return
						if (confirm("장바구니 담기 완료\n결제로 이동하시겠습니까?")) {		
							location.href="${contextPath}/4w.do?command=order"; //URL 요청
						}//end if 
					} else if (chk==3) {
						alert('이미 해당 상품을 장바구니에 추가하셨습니다');  	     	  
					} else if (chk==1) {
						alert('이미 해당 상품을 구입하셨습니다');		  	     	  
					}//end if
				}//end success
			});//end ajax
		} else {
			if (confirm("로그인이 필요합니다\로그인으로 이동하시겠습니까?")) {
	    		location.href="${contextPath}/4w.do?command=login";
 	     	}
		}//end if
	});
});
</script>

<!-- 홈페이지의 네비게이션 메뉴 --> 
<%@ include file="categoryMenu.jsp" %> 

<div>
	<table>
		<tr>
			<!-- 해당 상품 이지지 -->
			<td colspan="2" align="center">
				<img src="image/products/${productsDetail.pd_image}" width="600"/>                                      
			</td>
		</tr>
		<tr>
			<!-- 해당 상품 정보 -->
			<td>
				<!-- 뒤로가기 아이콘 -->
				<a href="javascript:history.back()"><img src="${contextPath}/image/productsdatail/fwfwee.png" height="40"/></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<!-- 장바구니 아이콘 -->
				<img id="basketimg" src="${contextPath}/image/productsdatail/fqq3fw3f.png" style="cursor:pointer;" height="40"/>
				<br><b>판매량 : ${productsDetail.pd_totalsell}</b>
				<br>
				<br>이미지 번호 : ${productsDetail.pd_num}
				<br>이미지 제목 : ${productsDetail.pd_name}
				<!-- fmt(JSTL)이용, 천단위 쉼표 -->
				<br>판매 가격 : <b><fmt:formatNumber value="${productsDetail.sell_price}" />원</b>
				<br>이미지 설명	: ${productsDetail.pd_content}   
			</td> 
		</tr>
	</table>
</div>

<!-- html의 footer를 추가함 -->
<%@ include file="../../footer.jsp"%>  
