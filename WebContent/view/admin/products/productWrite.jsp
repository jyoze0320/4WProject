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
	padding: 8px 5px;
	
}

#productList td{ 
	padding-right: 40px;
	text-align: right;
}
</style>

<article>
	<h1>새상품 등록</h1>  
	
	<form name="newfrm" method="post" enctype="multipart/form-data">
		<table id="list">
			<tr>
  				<th>상품종류</th>
  				<td colspan="5">
  				<select name="pd_kind">
    				<c:forEach items="${kindList}" var="kind" varStatus="status">
      					<option value="${status.count}">${kind}</option>
   					</c:forEach>
  				</select>      
			<tr>
  				<th>상품이름</th>
  				<td width="343" colspan="5">
       		<input type="text" name="pd_name" size="47" maxlength="100" >
  				</td>
			</tr>
			
			<tr>
  				<th>원가</th>
 				<td width="70">
  			  		<input type="text" name="cost_price" size="11">
  				</td>
 				<th>판매가</th>
  				<td width="70">
     				<input type="text" name="sell_price" size="11">
  				</td>
 				
 			</tr>    
  			<tr>
    			<th>설명</th>
    			<td colspan="5">
      			<textarea name="pd_content" rows="8" cols="70"></textarea>
    			</td>
  			</tr>
 			 <tr>
   				 <th>이미지</th>
   				 <td width="343" colspan="5">
				
   				   <input type="file" name="pd_image">
   				</td>
  			</tr>    
	</table>

			<input class="btn" type="button" value="등록" onClick="go_save()"style="margin-left: 600px;">      
			<script>
			function go_save() 
			{
				var theForm = document.newfrm;
				
				if (theForm.pd_kind.value == '') {
					alert('상품분류를 선택하세요.');
					theForm.kind.focus();
				} else if (theForm.pd_name.value == '') {
					alert('상품명을 입력하세요.');
					theForm.pd_name.focus();
				} else if (theForm.cost_price.value == '') {
					alert('원가를 입력하세요.');
					theForm.cost_price.focus();
				} else if (theForm.sell_price.value == '') {
					alert('판매가를 입력하세요.');
					theForm.sell_price.focus();
				} else if (theForm.pd_content.value == '') {
					alert('상품상세를 입력하세요.');
					theForm.pd_content.focus();
				} else if (theForm.pd_image.value == '') {
					alert('제품 이미지를 입력하세요.');
					theForm.pd_image.focus();
				}
				else {				
					theForm.action = "4w.do?command=admin_product_write";
					theForm.submit();
			}	
			}
			</script>     
			
			
			<input class="btn" type="button" value="취소" onClick="go_mov()"style="margin-right: 600px;">
			<script>
			function go_mov()
			{
				var theForm = document.newfrm;
				theForm.action = "4w.do?command=admin_product_list";
				theForm.submit();
			}
			</script>   
			
</form> 
</article>

<%@ include file="/view/admin/footer.jsp"%>
