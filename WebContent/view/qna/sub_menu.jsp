<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  <head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/qna.css" />
<style> @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap'); </style>
</head>
<style>
ul{
   list-style:none;
   padding-left:1px;
   }
</style>
          <ul id = "submenu">    
      <li><font size="4px"> <a href="4w.do?command=qna_list">▶ Q&amp;A LIST</a></font></li>
      <li><font size="4px"><a href="4w.do?command=qna_write_form">▶ Q&amp;A WRITE</a></font></li> 
     	 <li>
      <a href="mailto:jyoze0320@naver.com">
      <img class="email_dog" src="${pageContext.request.contextPath}/image/qna/help_dog.png" style="width: 180px; height:auto;"/>
      </a></li>
     
   		 </ul>	  
