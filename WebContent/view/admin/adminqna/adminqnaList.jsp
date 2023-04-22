<!-- 작성자 : 손일형 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/admin/header.jsp"%>
<%@ include file="/view/admin/admin_menu.jsp"%>

<script type="text/javascript">
	function go_view(qna_qseq) {
		var theForm = document.frm;
		theForm.qna_qseq.value = qna_qseq;
		theForm.action = "4w.do?command=admin_qna_detail";
		theForm.submit();
	}
</script>

<style>
header {
	height: 151px;
}
body {
	background-color: rgb(255, 255, 255);
	margin: 0;
	padding: 0;
	font-size: 0.75em;
	
	line-height: 1.2em;
	color: rgb(0, 0, 0);
}
.btn {
	
	position: relative;
    padding: 1px 30px;
    border-radius: 15px;
    font-family: "paybooc-Light", sans-serif;    
    text-decoration: none;
    font-weight: 600;
    transition: 1s;
	
}
nav#admin_menu {
	float: left;
	margin-left: 300px;
	
}
nav#admin_menu ul li {
	list-style-type: none;
	margin-top: 10px;
	
}

nav#admin_menu a {
	
	
	text-decoration: none;
	
	color: #666;
	
	display: block;
	width: 200px;
	height: 25px;
	padding: 5px;
}

nav#admin_menu a:hover {
	border-bottom: 1px solid black;
	width: 100px;
}

nav#admin_menu h1 {
	padding: 5px;
	
}

nav#admin_menu ul {
	margin-left: 0px;
}


div.reviewdiv {
	font-family: "paybooc-Light", sans-serif;
	
	font-size: 15px;
}
#adminqnalist td{ 
	text-align: center;
}
footer {
	
	background-position: 40px center;
	float: center;
	min-height: 145px;
	width: 952px;
	margin-left: 400px;
	margin-bottom: -500px;
}

footer hr {
	
	width: 70%; 
	float: center;
	
}

table#adminqnalist {
	text-align: center;
	width: 40%; 
	margin-left: 100px;
	margin-bottom: 20px;
}

th, td{ 
	padding: 4px 5px;
	
}

#adminqnalist td{ 
	padding-right: 10px;
	text-align: center;
}

#adminqnalist th{ 
	padding-right: 10px;
	text-align: center;
}
</style>

<article>
	<h1>Q&amp;A LIST</h1>

	<form name="frm" method="post">
		<input type="hidden" name="qna_qseq">

		<table id="adminqnalist">
			<tr>
				<th>처리번호</th>
				<th>답변여부</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			<c:forEach items="${qnaList}" var="qnaVO">
				<tr>
	
					<td>${qnaVO.qna_qseq}</td> 
					
				
					<td><c:choose>
						
							<c:when test='${qnaVO.repyn=="n"}'>(미처리)</c:when>
						
							<c:otherwise><b>(처리완료)</b></c:otherwise>
						</c:choose>
					</td>
					
					
					<td><a href="#"	onClick="javascript:go_view('${qnaVO.qna_qseq}')"> ${qnaVO.qna_title} </a></td>
					
					<!-- 작성자 -->
					<td>${qnaVO.mem_id}</td>
					
					<!-- 작성일 parseDate통해 String형에서 date로 변경후 출력형식 지정  -->
					<td><fmt:parseDate value="${qnaVO.qna_indate}" var="dateValue"
						pattern="yyyy-MM-dd" /> <fmt:formatDate value="${dateValue}"
						pattern="yyyy-MM-dd" /></td>
				</tr>
			</c:forEach>
		</table>

	</form>
</article>

<%@ include file="/view/admin/footer.jsp"%>