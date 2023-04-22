<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>

<html>
<head>
<title>qnaWrite</title>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap')
	;
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/qna.css">
</head>
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
							<form name="form" method="post" action="4w.do?command=qna_write">
								<fieldset>
									<legend>Q&amp;A WRITE</legend>

									<label>Title</label><br>
									<br>
									<input type="text" name="qna_title" size="63"><br>
									<br> <label>Content</label><br>
									<br>
									<textarea rows="15" cols="65" name="qna_cnt"></textarea>
									<br>

								</fieldset>

								<div class="clear"></div>
								<div id="buttons" style="float: right">
									<input type="submit" value="글쓰기" class="submit"> <input
										type="reset" value="취소" class="cancel"
										onclick="location.href='${pageContext.request.contextPath}/4w.do?command=qna_list'">

									<input type="button" value="쇼핑 계속하기" class="submit"
										onclick="location.href='${pageContext.request.contextPath}/4w.do?command=index'">
								</div>
							</form>

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
