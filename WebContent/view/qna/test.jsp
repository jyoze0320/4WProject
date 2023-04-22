<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>
<html>
<head>
<title>qnalist</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<link rel="stylesheet" type="text/css" href="css/qna.css" >
</head>
<body>
<article>
<div id="doc" class="yui-t2">
  <div id="hd">
    <div id="header"><h1>제목입니다.</h1></div>
  </div>
  <div id="bd">
    <div id="yui-main">
      <div class="yui-b">
        <div class="content">
        
		<table id="qnalist">
			<tr>
				<th>처리번호</th>
				<th>제목</th>
				<th>등록일</th>
				<th>답변 여부</th>
			</tr>
			<!-- 모든 데이터를 리스트형식으로 표시 -->
			<c:forEach items="${qnaList}" var="qnaVO">
				<tr>
					<!-- 처리번호 -->
					<td>${qnaVO.qna_qseq}</td>

					<!-- 처리번호와 일치하는 제목 -->
					<td><a href="4w.do?command=qna_view&qna_qseq=${qnaVO.qna_qseq}">
							${qnaVO.qna_title}</a></td>
							
					<!-- 등록일 : 기존VO에서 DATE를 String형식으로 불러와 parsedate통해 date로 변경후 표기방법 변경 -->
					<td><fmt:parseDate value="${qnaVO.qna_indate}" var="dateValue"
							pattern="yyyy-MM-dd" /> <fmt:formatDate value="${dateValue}"
							pattern="yyyy-MM-dd" /> </td>
					
					<!-- 답변여부에 따른 상태표시 -->
					<td><c:choose>
							<c:when test="${qnaVO.repyn=='n'}"> NO </c:when>
							<c:when test="${qnaVO.repyn=='y'}"> YES </c:when>
		
						</c:choose></td>
						
				</tr>
			</c:forEach>
		
		</table>
		
		
		<div class="clear"></div>
		<div id="buttons" style="float: right">
			<input type="button" value="1:1 질문하기" class="submit"
				onclick="location.href='${pageContext.request.contextPath}/4w.do?command=qna_write_form'">
			<input type="button" value="쇼핑 계속하기" class="cancel"
				onclick="location.href='${pageContext.request.contextPath}/4w.do?command=index'">
		
        </div>
      </div>
    </div>
    </div>
    <div class="yui-b">
      <div id="secondary">
       <ul>    
      <li><a href="4w.do?command=qna_list">Q&amp;A 게시글 리스트</a></li>
      <li><a href="4w.do?command=qna_write_form">Q&amp;A 게시글 쓰기</a></li> 
   		 </ul>	  
</div>
    </div>
  </div>
  <div id="ft">
    <div id="footer">Footer</div>
  </div>
</div>
</article>
</body>
</html>
