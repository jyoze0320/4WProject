<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="member_header.jsp" %>
	</head>
	<body>
		<div class="Canvas_background">
			<video muted autoplay loop>
				<source src="${pageContext.request.contextPath}/image/qna/bg.mp4"
					type="video/mp4">
			</video>
			<div class="Side_context"></div>
			<div class="Context">
				<div class="Context_header">
					<div class="Context_logo">
						<div>
							<a href="4w.do?command=index">
							<img class="mem_logo1" src="${pageContext.request.contextPath}/image/header/4WLOGO.png" alt="logo_1">
							<img class="mem_logo2" src="${pageContext.request.contextPath}/image/header/4WLOGO2.png" alt="logo_2"></a>
						</div>
						<h3>로그인 또는 회원가입</h3>
					</div>
				</div>
			</div>
			<div class="Side_context"></div>
			<div class="Cardlayout_container">
				<div class="Cardlayout_space"></div>
				<div class="Cardlayout_mainspace">
					<div class="Cardlayout_main">
						<div class="Cardlayout" id="login_card">
							<header class="CardLayout_header">
								<h1 class="Header_title">로그인</h1>
								<p class="Header_ask">계정이 없으신가요?
									<a class="Hyperlink" href="4w.do?command=createmem" id="link_createmem" rel="nofollow noopener noreferrer">계정 만들기</a>
								</p>
							</header>
							<section class="CardLayout_inputform">
								<form id="form_login" class="MemberForm" method="post" action="4w.do?command=loginchk">
									<div>
										<div class="Input_field">
											<label class="Input_name">아이디</label>
											<input name="userID" class="TxtInput" type="text" autofocus="autofocus" required>
										</div>
										<div class="Input_field">
											<label class="Input_name">비밀번호</label>
											<input name="userPW" class="TxtInput" type="password" required>
										</div>
									</div>
									<div>
										<input class="SubBtn" type="submit" value="로그인">
									</div>
								</form>
							</section>
						</div>
					</div>
				</div>
				<div class="Cardlayout_space"></div>
			</div>
		</div>
	</body>
</html>