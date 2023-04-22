<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 작성자 : 김준영 -->
<!-- 홈페이지의 네비게이션 메뉴 -->
<div class="frame">
	<!-- 각 메뉴의 쿼리스트링에 카테고리 정보 보냄(kind) -->
	<a href="${contextPath}/4w.do?command=categorylist&kind=1">
    <button class="custom-btn btn-5"><span>Human</span></button></a>
    <a href="${contextPath}/4w.do?command=categorylist&kind=2">
    <button class="custom-btn btn-5"><span>Environment</span></button></a>
    <a href="${contextPath}/4w.do?command=categorylist&kind=3">
    <button class="custom-btn btn-5"><span>Architecture</span></button></a>
    <a href="${contextPath}/4w.do?command=categorylist&kind=4">
    <button class="custom-btn btn-5"><span>Illustration</span></button></a>
    <a href="${contextPath}/4w.do?command=categorylist&kind=5">
    <button class="custom-btn btn-5"><span>Pattern</span></button></a>
</div>