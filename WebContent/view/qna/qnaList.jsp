<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>qnalist</title>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap');
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/qna.css">
</head>
<%@ include file="../../header.jsp"%>

<body>
	<article>

		<div class="jb-box">
			<video muted autoplay loop>
				<source src="${pageContext.request.contextPath}/image/qna/bg.mp4"
					type="video/mp4">
			</video>
		</div>

 			<div id = "mainimg">
 			
			<img src="${pageContext.request.contextPath}/image/titlepic/4_qna.png" />
 			
 			</div>

		<div id="doc2" class="yui-t2">
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
										<td><a
											href="4w.do?command=qna_view&qna_qseq=${qnaVO.qna_qseq}">
												${qnaVO.qna_title}</a></td>

										<!-- 등록일 : 기존VO에서 DATE를 String형식으로 불러와 parsedate통해 date로 변경후 표기방법 변경 -->
										<td><fmt:parseDate value="${qnaVO.qna_indate}"
												var="dateValue" pattern="yyyy-MM-dd" /> <fmt:formatDate
												value="${dateValue}" pattern="yyyy-MM-dd" /></td>

										<!-- 답변여부에 따른 상태표시 -->
										<td><c:choose>
												<c:when test="${qnaVO.repyn=='n'}"> NO </c:when>
												<c:when test="${qnaVO.repyn=='y'}">
													<font color='red'> <b>YES</b>
													</font>
												</c:when>
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
						<%@ include file="sub_menu.jsp"%>
					</div>
				</div>
			</div>

		</div>
	</article>
</body>
</html>
<%@ include file="../../footer.jsp"%>
