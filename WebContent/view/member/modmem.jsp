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
						<h3>계정 정보 및 회원정보 수정</h3>
					</div>
				</div>
			</div>
			<div class="Side_context"></div>
			<div class="Cardlayout_container">
				<div class="Cardlayout_space"></div>
				<div class="Cardlayout_mainspace">
					<div class="Cardlayout_main">
						<div class="Cardlayout" id="modmem_card">
							<header class="CardLayout_header">
								<h1 class="Header_title">계정 정보</h1>
							</header>
							<section class="CardLayout_inputform">
								<form id="form_modemem" class="MemberForm" method="post" action="4w.do?command=update_mem">
									<div>
										<div class="Input_field">
											<label class="Input_name">아이디</label>
											<input name="id" id="id_input" class="TxtInput" type="text" value="${requestScope.userInfo.mem_id}" size="15" disabled>
										</div>
										<div class="Input_field" hidden="true"> 
											<label class="Input_name">비밀번호</label>
											<input name="pw" id="pw_input" class="TxtInput" type="password" size="15" oninput="pw_input_change()">
										</div>
										<div class="Input_field" hidden="true">
											<label class="Input_name">비밀번호 확인</label>
											<input name="pwchk" id="pwchk_input" class="TxtInput" type="password" size="15" oninput="pw_input_chk()">
											<label class="ChkLabel" id="pw_eqchk"></label>
											<input id="chk2" type="hidden" value="false">
										</div>
										<div class="Input_field">
											<label class="Input_name">이름</label>
											<input name="name" class="TxtInput" type="text" value="${requestScope.userInfo.mem_name}" size="15" disabled onblur="blankchk(this)">
											<input name="origin_name" type="hidden">
										</div>
										<div class="Input_field">
											<label class="Input_name">이메일</label>
											<input name="email1" class="TxtInput" type="text" value="${fn:split(requestScope.userInfo.email,'@')[0]}" size="10" disabled onblur="blankchk(this)"> @ 
											<input name="origin_email1" type="hidden">
											<input name="email2" id="email2_input" class="TxtInput" type="text" value="${fn:split(requestScope.userInfo.email,'@')[1]}" size="10" disabled onblur="blankchk(this)"> 
											<input name="origin_email2" type="hidden">
											<select id="email_select" hidden="true" onchange="set_email_input()">
												<option value="0">직접입력</option>
												<option value="naver.com">naver.com</option>
												<option value="gmail.com">gmail.com</option>
											</select>
										</div>
										<div class="Input_field">
											<label class="Input_name">주소</label>
											<input name="address" class="TxtInput" type="text" value="${requestScope.userInfo.address}" size="45" disabled onblur="blankchk(this)">
											<input name="origin_address" type="hidden">
										</div>
										<div class="Input_field">
											<label class="Input_name">전화번호</label>
											<input name="phone1" class="TxtInput_num" type="text" maxlength="3" value="${fn:split(requestScope.userInfo.phone,'-')[0]}" size="4" pattern="\d{2,3}" disabled onblur="blankchk(this)"> - 
											<input name="origin_phone1" type="hidden">
											<input name="phone2" class="TxtInput_num" type="text" maxlength="4" value="${fn:split(requestScope.userInfo.phone,'-')[1]}" size="4" pattern="\d{3,4}" disabled onblur="blankchk(this)"> - 
											<input name="origin_phone2" type="hidden">
											<input name="phone3" class="TxtInput_num" type="text" maxlength="4" value="${fn:split(requestScope.userInfo.phone,'-')[2]}" size="4" pattern="\d{4}" disabled onblur="blankchk(this)">
											<input name="origin_phone3" type="hidden">
										</div>
									</div>
									<div id="btn_layout">
										<input class="SubBtn" type="button" value="정보수정" onclick="modmem_btn_action()">
										<input class="SubBtn" type="button" value="결제내역" onclick="location.href='${pageContext.request.contextPath}/4w.do?command=payment'">
										<input class="SubBtn" type="button" value="메인으로" onclick="location.href='${pageContext.request.contextPath}/4w.do?command=index'">
									</div>
									<div hidden="true">
										<input class="SubBtn" type="submit" value="수정하기" onclick="updatemem_action()">
										<input class="SubBtn" type="button" value="취소" onclick="location.href='${pageContext.request.contextPath}/4w.do?command=modmem'">
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