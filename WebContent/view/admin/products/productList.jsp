<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/admin/header.jsp"%>
<%@ include file="/view/admin/admin_menu.jsp"%>
<script type="text/javascript" src="view/admin/products/product.js"></script>


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
#productList td{ 
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

table#productList {
	
	width: 40%; 
	margin-left: 100px;
	margin-bottom: 20px;
}

th, td{ 
	padding: 4px 5px;
	
}

#productList td, #productList th{ 
	padding-right: 10px;
	text-align: center;
}
</style>

<article>
<h1>상품리스트</h1>	
<form name="frm" method="post">
<table>
  <tr>
  <td width="650">
      상품이름 
     <input type="text" name="key">
     <input class="btn" type="button" name="btn_search" value="검색" onClick="go_search()">
     <script>
     function go_search() {
    		var theForm = document.frm;
    		theForm.action =  "4w.do?command=admin_product_list";
    		theForm.submit();
    	}
     </script>
     <input class="btn" type="button" name="btn_total" value="전체보기 " onClick="go_total()">
     <script>
     function go_total() {
    		var theForm = document.frm;
    		theForm.key.value = "";
    		theForm.action =  "4w.do?command=admin_product_list";
    		theForm.submit();
    	}
     </script>
     <input class="btn" type="button" name="btn_write" value="상품등록" onClick="go_wrt()">
      <script>
      function go_wrt() {
    		var theForm = document.frm;
    		theForm.action = "4w.do?command=admin_product_write_form";
    		theForm.submit();
    	}
     </script>
  </td>
  </tr>
</table>

<table id="productList">
    <tr>
        <th>상품번호</th><th>상품이름</th><th>원가</th><th>판매가</th><th>등록일</th><th>판매유무</th>
    </tr>
    <c:choose>
 	<c:when test="${productListSize<=0}">
 	   <tr>
  		    <td width="100%" colspan="7" align="center" height="23">
  			      등록된 상품이 없습니다.
 		     </td>      
 	   </tr>
    </c:when>    
	<c:otherwise>
		<c:forEach items="${productList}" var="productsVO">
    	<tr>
      		<td height="23" align="center" >${productsVO.pd_num}</td>
     		 <td>${productsVO.pd_name}</td>
     		 <td>${productsVO.cost_price}</td>
     		 <td>${productsVO.sell_price}</td>
     		 <td><fmt:parseDate value="${productsVO.regdate}" var="dateValue"
				 pattern="yyyy-MM-dd" /> <fmt:formatDate value="${dateValue}"
				 pattern="yyyy-MM-dd" /></td>
     		 <td>
      			<c:choose>
   	 				<c:when test='${productsVO.sellyn=="N"}'>미판매</c:when>
   	 				<c:otherwise>판매</c:otherwise>   	 		
   	 			</c:choose>	 
   	 		</td> 
   		 </tr>
   		 </c:forEach>
   
   		   <tr><td id="page_num" colspan="6"> ${paging} </td></tr> 
	</c:otherwise>	
</c:choose>  
</table>
</form> 
</article>

<%@ include file="/view/admin/footer.jsp"%>
