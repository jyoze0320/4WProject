<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>

<html>
<head>
<title>qnaView</title>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap')
	;
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/qna.css">
</head>
<body>
	<script type="text/javascript">
		// 버튼이벤트 function
		function delete_event() {
			// 버튼을 누르면 "삭제하시겠습니까" 라고 고객에게 확인하는 알람창 열림
			if (confirm("정말 삭제하시겠습니까?") == true) {
				// 확인을 누를경우 qna를 삭제하는 커맨드 실행
				document.formm.submit();
				location.href = '${pageContext.request.contextPath}/4w.do?command=qna_delete&qna_qseq=${qnaVO.qna_qseq}'
			} else {
				// 취소하면 기존화면으로 돌아옴
				return;
			}
		}
	</script>

	<article>
		<div class="jb-box">
			<video muted autoplay loop>
				<source src="${pageContext.request.contextPath}/image/qna/bg.mp4"
					type="video/mp4">
			</video>
			 <div id = "mainimg">
 			
			<img src="${pageContext.request.contextPath}/image/titlepic/4_qna.png" />
 			
 			</div>
		</div>
		<div id="doc2" class="yui-t2">

			<div id="bd">
				<div id="yui-main">
					<div class="yui-b">
						<div class="content">
							<form name="formm" method="post">
								<table id="qnaview">
									<tr>
										<th>제목</th>
										<td>${qnaVO.qna_title}</td>
									</tr>
									<tr>
										<th>처리 번호</th>
										<td>${qnaVO.qna_qseq}</td>
									</tr>
									<tr>
										<th>등록일</th>
										<td><fmt:parseDate value="${qnaVO.qna_indate}"
												var="dateValue" pattern="yyyy-MM-dd" /> <fmt:formatDate
												value="${dateValue}" pattern="yyyy-MM-dd" /></td>
									<tr>
										<th>질문내용</th>
										<td>${qnaVO.qna_cnt}
									</tr>
									<tr>
										<th>답변 내용</th>
										<td>${qnaVO.qna_rep}
									</tr>
								</table>
							</form>
							<div class="clear"></div>
							<div id="buttons" style="float: right">




								<!-- JS function 사용 -->
								<button type="button" onclick="delete_event();">삭제하기</button>

								<!-- 수정 : 버튼누를시 현재 글의 시퀀스를 modform으로 보냄 -->
								<input type="button" value="수정하기" class="submit"
									onclick="location.href='${pageContext.request.contextPath}/4w.do?command=qna_modform&qna_qseq=${qnaVO.qna_qseq}'">

								<input type="button" value="목록보기" class="submit"
									onclick="location.href='${pageContext.request.contextPath}/4w.do?command=qna_list'">

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
