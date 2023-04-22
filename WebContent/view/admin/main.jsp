<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html> <head>
<meta charset="UTF-8">
<title>pj_4w Admin</title>
<link rel="stylesheet" href="view/admin/css/admin.css">

<script type="text/javascript">
function admin_check()
{
  if(document.frm.adminId.value==""){
      alert("아이디를 입력하세요.");
      return false;
  }else if(document.frm.adminPw.value==""){
     alert("비밀번호를 입력하세요.");
      return false;
    }
    return true;  
}
</script>
</head>
<body>
  <div>  
    <header>             
    </header>       
    <article>
      <div id="loginform">
      <form name="frm" method="post" 
		action="4w.do?command=admin_login">
      <table>
        <tr>
          <td> 아 이 디 </td>
          <td> <input type="text" name="adminId" size="10" value=""></td>
        </tr>
        <tr>
          <td> 비밀번호 </td>
          <td> 
            <input type="password" name="adminPw" size="10" value="">
          </td>
        </tr>
        <tr align="center" >
          <td  colspan="2">          
            <input class="btn" type="submit" value="관리자 로그인" 
				onclick="return admin_check()" style="width:150px">        
          </td>
         
  
  			<tr align="center">
           <td  colspan="2">          
            <input class="btn" type="button" value="메인 페이지" 
				onclick="location.href='4w.do?command=index'" style="width:150px"><br><br>          
          </td>
           </tr>
           
    
          
      </table>
       </form>
      </div>
      
      	
          
         
       
      
    </article>   
  </div>
</body></html>