<!-- 작성자 : 손일형 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/admin/header.jsp"%>
<%@ include file="/view/admin/admin_menu.jsp"%>

<script type="text/javascript">
	function go_qna_list() {
		var theForm = document.frm;
		theForm.action = "4w.do?command=admin_qna_list";
		theForm.submit();
	}

	function go_repyn(qna_qseq) {
		var theForm = document.frm;
		theForm.qna_qseq.value = qna_qseq;
		theForm.action = "4w.do?command=admin_qna_update";
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

table {
	text-align: center;
}

th, td{ 
	padding: 4px 5px;
	
}

 td{ 
	padding-right: 10px;
	text-align: center;
}

 th{ 
	padding-right: 10px;
	text-align: center;
	width :"15%"
}


</style>

<article>
	<h1>Q&amp;A Answer</h1>

	<form name="frm" method="post">
		<input type="hidden" name="qna_qseq">

		<table id="adminqnadetail">
			<tr>
				<th>제목</th> <td>${qnaVO.qna_title}</td>
			</tr>
			<tr>
				<th>작성자</th> <td>${qnaVO.mem_id}</td>
			</tr>
			<tr>
				<th>등록일</th>	<td>${qnaVO.qna_indate}</td>
			</tr>
			<tr>
				<th>내용</th> <td>${qnaVO.qna_cnt}</td>
			</tr>
			
			
			<c:choose>
			<c:when test='${qnaVO.repyn=="n"}'>
					<th>
						<td>
						<textarea name="qna_rep" rows="10" cols="92"></textarea>
						<br>
						<div id="buttons" style="float: right">
						<input type="button" class="btn" value="저장" onClick="go_repyn('${qnaVO.qna_qseq}')">
						<input type="button" class="btn" value="돌아가기" onClick="go_qna_list()">
						</div>
						</td>
						
					
			</c:when>			
			<c:otherwise>		
			
			<tr>
				<th>답변</th> <td>${qnaVO.qna_rep}</td>
				<th><input type="button" style="float: right" class="btn" value="돌아가기" onClick="go_qna_list()"></th>
		
			</tr>	

			</c:otherwise>
		</c:choose>
		</table>

	</form>

</article>

<%@ include file="/view/admin/footer.jsp"%>
