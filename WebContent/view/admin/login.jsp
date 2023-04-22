<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="/view/admin/header.jsp"%>
 <%@ include file="/view/admin/admin_menu.jsp"%>
 
<article>
<h1>관리자 로그인 성공</h1>	
${adminId}
<h4 style="color:red">${message}</h4>
</article>

<%@ include file="/view/admin/footer.jsp"%>
</body>
</html>