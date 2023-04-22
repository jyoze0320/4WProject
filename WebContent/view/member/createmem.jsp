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
						<div class="Cardlayout" id="createmem_card">
							<header class="CardLayout_header">
								<h1 class="Header_title">회원가입</h1>
								<p class="Header_ask">이미 계정이 있으신가요?
									<a class="Hyperlink" href="4w.do?command=login" id="link_login" rel="nofollow noopener noreferrer">로그인</a>
								</p>
							</header>
							<section class="CardLayout_inputform">
								<form id="form_createmem" class="MemberForm" method="post" action="4w.do?command=insert_newmem">
									<div>
										<div class="Input_field">
											<label class="Input_name">아이디</label>
											<input name="id" id="id_input" class="TxtInput" type="text" size="15" autofocus="autofocus" oninput="id_input_change()" required> 
											<input id="overlapchk_btn" type="button" value="중복확인" onclick="overlapchk()"> 
											<label class="ChkLabel" id="id_chk"></label>
											<input id="chk1" type="hidden" value="false">
										</div>
										<div class="Input_field"> 
											<label class="Input_name">비밀번호</label>
											<input name="pw" id="pw_input" class="TxtInput" type="password" size="15" oninput="pw_input_change()" required>
										</div>
										<div class="Input_field">
											<label class="Input_name">비밀번호 확인</label>
											<input name="pwchk" id="pwchk_input" class="TxtInput" type="password" size="15" oninput="pw_input_chk()" required>
											<label class="ChkLabel" id="pw_eqchk"></label>
											<input id="chk2" type="hidden" value="false">
										</div>
										<div class="Input_field">
											<label class="Input_name">이름</label>
											<input name="name" class="TxtInput" type="text" size="15" required>
										</div>
										<div class="Input_field">
											<label class="Input_name">이메일</label>
											<input name="email1" class="TxtInput" type="text" size="10" required> @ 
											<input name="email2" id="email2_input" class="TxtInput" type="text" size="10" required> 
											<select id="email_select" onchange="set_email_input()">
												<option value="0">직접입력</option>
												<option value="naver.com">naver.com</option>
												<option value="gmail.com">gmail.com</option>
											</select>
										</div>
										<div class="Input_field">
											<label class="Input_name">주소</label>
											<input name="address" class="TxtInput" type="text" size="45" required>
										</div>
										<div class="Input_field">
											<label class="Input_name">전화번호</label>
											<input name="phone1" class="TxtInput_num" type="text" maxlength="3" size="4" pattern="\d{2,3}" required> - 
											<input name="phone2" class="TxtInput_num" type="text" maxlength="4" size="4" pattern="\d{3,4}" required> - 
											<input name="phone3" class="TxtInput_num" type="text" maxlength="4" size="4" pattern="\d{4}" required>
										</div>
									</div>
									<div>
										<input class="SubBtn" type="submit" value="가입하기" onclick="createmem_btn_action()">
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