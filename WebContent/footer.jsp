<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<hr>
		<footer>
			<div align="center">
			<br><br>
			Whatever you Want, Whenever you Want, 4W<br>
			All contents Copyright 2022 
			<c:choose>
			<c:when test="${empty sessionScope.loginUser}">
				<a href="4w.do?command=admin_login_form" id="adminlog">PJ_4W </a>
			</c:when>
			<c:otherwise>
				PJ_4W 	
			</c:otherwise>
			</c:choose>
			Inc. all rights reserved
			<br><br><br><br>			
			</div>
		</footer>
	</body>
</html>