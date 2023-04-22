<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
		<meta http-equiv="refresh" content="1; url=${requestScope.forwardURL}" >	<!-- 1초 후 해당 URL로 redirect 시키도록 설정 -->
		<title>${requestScope.title}</title>
	</head>
	<body>
		<input id="alert_msg" type="hidden" value="${requestScope.message}">
		<script>alert(document.querySelector('#alert_msg').value);</script>
	</body>
</html>