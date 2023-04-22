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
<!-- jquery 다운 -->
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<script>
//$(function(){ }); 와 같음
$(document).ready(function() {
	
	//전체 체크박스 클릭 시 
	$("#chkAll").click(function() {
		//전체 체크박스가 체크 상태이면 전체 개별 체크박스에 체크 속성 부여
		if($("#chkAll").is(":checked")) $("input[name=chkOdNum]").prop("checked", true);
		//체크 상태가 아니면 체크 속성 삭제
		else $("input[name=chkOdNum]").prop("checked", false);
	});
	//전체 개별 체크박스가 클릭 시
	$("input[name=chkOdNum]").click(function() {
		//전체 개별 체크박스의 개수
		var total = $("input[name=chkOdNum]").length;
		//전체 개별 체크박스의 체크된 박스의 개수
		var checked = $("input[name=chkOdNum]:checked").length;
		//같다면 전체 체크박스에 체크 속성 부여
		if(total != checked) $("#chkAll").prop("checked", false);
		//다르다면 전체 체크박스에 체크 속석 삭제
		else $("#chkAll").prop("checked", true); 
	});	
		
	//체크된 행 삭제 버튼 클릭 시(ajax 방식)
	$("#chkDelete").click(function(){
		//체크된 행들의 주문 번호 배열 선언
		var arrValue = new Array;
		//체크된 주문 번호 담음(push)
		$('input[name="chkOdNum"]:checked').each(function(){         
			arrValue.push($(this).val());
        });
		//체크된 박스가 있다면
		if(arrValue.length != 0){
			//ajax으로 데이터 전송
			$.ajax({
			      type:"post",					//전송 방식
			      url:"${contextPath}/4w.do?command=basketdelete",	//호출 URL
			      data:{arrValue : arrValue},	//주문 번호 배열 보냄
			      traditional: true,			//배열 넘길때 필요
			      success: function(data) {			//데이터 전송 성공시 실행
			    	  var json = JSON.parse(data);	//json을 파싱함
			    	  var total = 0;
			    	  $("#ordertbody").empty();		//기존 테이블 요소 삭제
			    	  if(json.length != 0)						//장바구니 데이터가 있다면 테이블 출력
					  for (var i = 0; i < json.length; i++) {
						  $("#ordertbody").append(				//셀렉터 안에 요소를 추가, 장바구니 테이블 
								"<tr>"+
									"<td width=\"100\"><a href=\"${contextPath}/4w.do?command=pddetail&pdnum="+json[i].pd_num+"\">"+
									"<img src=\"image/products/"+json[i].pd_image+"\" width=\"70\" /></a></td>"+
									"<td width=\"100\"><h5>"+json[i].pd_name+"</h5></td>"+
									"<td><h5>"+json[i].pd_content+"</h5></td>"+
									"<td width=\"100\"><h5><b>"+json[i].sell_price+"원</b></h5></td>"+
									"<td width=\"10\"><input type=\"checkbox\" name=\"chkOdNum\" class=\"chkOdNum\" value="+json[i].order_num+"></td>"+
								"</tr>"
						  );
						  total += json[i].sell_price;	//장바구니의 총합 금액 계산
					  } else {
						  $("#ordertbody").append(		//장바구니 데이터가 없다면 실행
								"<tr>"+
									"<td colspan=\"5\" align=\"center\"><h5>장바구니가 비었습니다</h5></td>"+
								"</tr>"
						  );
					  }//end for
					  $("#total").empty();								//기존 라벨 요소 삭제
					  $("#total").append("<b>총 합계 : "+total+"원</b>");	//요소 추가, 총합 금액
			      }//end success
			 });//end ajax
		} else {
			alert("상품을 선택해 주세요");//없다면 alert창 띄움
		}//end if
	});
	
	//결제 아이콘 클릭 시(form-submit 방식)
	$("#payment").click(function() {
		//체크된 박스의 개수
		var checked = $("input[name=chkOdNum]:checked").length;
		//체크된 박스가 있다면
		if(checked != 0){
			if (confirm("선택 항목을 결제 하시겠습니까?")) {
				//해당 아이디 form을 전송(submit)
				$("#paymentAction").submit();
		    }
		} else {
			alert("상품을 선택해 주세요");
		}//end if
    });
});
</script>


<!-- 홈페이지 장바구니의 상단 이미지 -->
<div id = "mainimg">
 	<img src="${contextPath}/image/titlepic/8_cart.png" />
</div>
<!-- 홈페이지의 네비게이션 메뉴 -->
<%@ include file="categoryMenu.jsp" %> 

<!-- post방식으로 URL(action) 호출 -->
<form id ="paymentAction" method="post" action="${contextPath}/4w.do?command=paymentIn">
	<section>
		<!-- 장바구니 테이블 제목 -->
		<div class="tbl-header">
			<table>
				<thead>
					<tr>
						<td width="100">이미지</td>	
						<td width="100">상품명</td>
						<td>내용</td>
						<td width="100">가격</td>
						<td width="10"><input type="checkbox"  id="chkAll"></td>
					</tr>
				</thead>
			</table>
		</div>
		<!-- 장바구니 테이블 목록 -->
		<div class="tbl-content">
			<table>
				<!-- JSTL의 if -->
				<c:choose>
					<%--if의 조건, empty는 null이면 ture,  EL태그로 값을 전달 받음 --%>
					<c:when test="${!empty basketList}">
						<tbody id="ordertbody">
							<%-- JSTL의 for--%>
							<c:forEach items="${basketList}" var="basketList">
								<tr>
									<td width="100"><a href="${contextPath}/4w.do?command=pddetail&pdnum=${basketList.pd_num}">
									<img src="image/products/${basketList.pd_image}" width="70" /></a></td>
									<td width="100"><h5>${basketList.pd_name}</h5></td>
									<td><h5>${basketList.pd_content}</h5></td>
									<td width="100"><h5><b>${basketList.sell_price}원</b></h5></td>
									<td width="10"><input type="checkbox" name="chkOdNum" class="chkOdNum" value="${basketList.order_num}"></td>
								</tr>
							</c:forEach><%--end for --%>
						</tbody>
					</c:when>
					<%--if의 else --%>
					<c:otherwise>
						<tbody>
							<tr><td colspan="5" align="center"><h5>장바구니가 비었습니다</h5></td></tr>
						</tbody>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
		<!-- 장바구니의 하단 -->
		<table>
			<tr>
				<!-- 결제 아이콘 -->
				<td>
					<img id="payment" src="${contextPath}/image/productsdatail/8039820.png" style="cursor:pointer;" height="60"/>
				</td>
				<!-- 결제 방식(submit으로 전송함) -->
				<td style="text-align: left;">
					<ul>
						<li><label>결제 수단</label></li>
						<!-- radio 버튼 -->
						<li><input type="radio" name="chkRd" value="Credit" checked>신용카드
							<input type="radio" name="chkRd" value="account">계좌이체
							<input type="radio" name="chkRd" value="phone">핸드폰<br></li>
						<li><label id="tatal">결제 금액 : ${baskettotal}원</label><br></li>
					</ul>
				</td>
				<!-- 장바구니의 총 합계 -->
				<td>
					<label id="total"><b>총 합계 : ${baskettotal}원</b></label><br><br>
					<label style="cursor:pointer" id="chkDelete">선택 삭제</label>
				</td>
			</tr>
		</table>
	</section>
</form>

<!-- html의 footer를 추가함 -->
<%@ include file="../../footer.jsp" %>