<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="/view/admin/header.jsp"%>
<%@ include file="/view/admin/admin_menu.jsp"%>

<script type="text/javascript">
  function go_search()
  {
     document.frm.action="4w.do?command=admin_member_list";
     document.frm.submit();
  }
</script>

<style>
header {
	height: 151px;
}
body {
	background-color: rgb(255, 255, 255);
	margin: 0;
	padding: 0;
	font-size: 0.75em;
	
	line-height: 1.2em;
	color: rgb(0, 0, 0);
}
.btn {
	
	position: relative;
    padding: 1px 30px;
    border-radius: 15px;
    font-family: "paybooc-Light", sans-serif;    
    text-decoration: none;
    font-weight: 600;
    transition: 1s;
	
}
nav#admin_menu {
	float: left;
	margin-left: 300px;
	
}
nav#admin_menu ul li {
	list-style-type: none;
	margin-top: 10px;
	
}

nav#admin_menu a {
	
	
	text-decoration: none;
	
	color: #666;
	
	display: block;
	width: 200px;
	height: 25px;
	padding: 5px;
}

nav#admin_menu a:hover {
	border-bottom: 1px solid black;
	width: 100px;
}

nav#admin_menu h1 {
	padding: 5px;
	
}

nav#admin_menu ul {
	margin-left: 0px;
}


div.reviewdiv {
	font-family: "paybooc-Light", sans-serif;
	
	font-size: 15px;
}
#memberList td{ 
	padding-right: 40px;
	text-align: right;
}
footer {
	
	
	background-position: 40px center;
	float: center;
	min-height: 145px;
	width: 952px;
	margin-left: 400px;
	margin-bottom: -500px;
}

footer hr {
	
	width: 70%; 
	float: center;
	
}

table#memberList {
	
	width: 40%; 
	margin-left: 100px;
	margin-bottom: 20px;
}

th, td{ 
	padding: 8px 5px;
	
}

#memberList td, #memberList td{ 
	padding-right: 10px;
	text-align: center;
}
</style>


<article>
<h1>회원리스트</h1>  
<form name="frm" method="post">

<table style="float:left; ">
  <tr>
 	 <td width="650">   회원 이름
  		  <input type="text" name="key">
		  <input class="btn" type="button" value="검색" onclick="go_searchmem()" >
		  <script>
		  function go_searchmem() {				
				var theForm = document.frm;
				theForm.action =  "4w.do?command=admin_member_list";
				theForm.submit();
			}
		  </script>
		  <input class="btn" type="button" value="전체보기 " onclick="go_totalmem()">
		  <script>
		  function go_totalmem() {				
				var theForm = document.frm;
				theForm.key.value = "";
				theForm.action =  "4w.do?command=admin_member_list";
				theForm.submit();
			}
		  </script>
 	 </td>
  </tr>
</table>  
<br>

<table id="memberList">
  <tr>
    <th> ID </th>    <th> Name </th>
    <th> Email </th> <th> Address </th>  
    <th> Phone </th> <th> InitDate </th>
  </tr>
  
  <c:choose>
  <c:when test="${memberListSize<=0}">
 	   <tr>
  		    <td width="100%" colspan="7" align="center" height="23">
  			      등록된 상품이 없습니다.
 		     </td>      
 	   </tr>
    </c:when>   
  <c:otherwise>
  
  <c:forEach items="${list}" var="memberVO">  
  <tr>
    <td> ${memberVO.mem_id} </td>
    <td> ${memberVO.mem_name} </td>
    <td> ${memberVO.email} </td> 

    <td> ${memberVO.address} </td>
    <td> ${memberVO.phone} </td> 
    <td> <fmt:parseDate value="${memberVO.initDate}" var="dateValue"
		 pattern="yyyy-MM-dd" /> <fmt:formatDate value="${dateValue}"
		 pattern="yyyy-MM-dd" /> </td> 
  </tr>
  </c:forEach>
   <tr><td id="page_num" colspan="6"> ${paging} </td></tr> 
   </c:otherwise>	
</c:choose>  
</table>
</form>
</article>

<%@ include file="/view/admin/footer.jsp"%>
