<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="jakarta.tags.core" %>

<html>
<head>
<title>管理者新規登録の確認画面</title>
<jsp:include page="/common/head.jsp" />
</head>

<body>
<jsp:include page="/common/header.jsp"/>

<h1>《管理者の新規登録》</h1>
	ログインID：<%=(String) session.getAttribute("loginId") %><br>
	使用者名：<%=(String) session.getAttribute("nickName") %><br>
	パスワード：<%=(String) session.getAttribute("hidePass") %><br>
	
<p>この内容で登録しますか？</p>


<form action="ManagerSignup.action" method="post"><!--postリクエスト-->
 <!-- 値を再送する（hiddenで送る） -->
    <input type="hidden" name="loginId" value="<%= (String) session.getAttribute("loginId") %>">
    <input type="hidden" name="nickName" value="<%= (String) session.getAttribute("nickName") %>">
    <input type="hidden" name="password" value="<%= (String) session.getAttribute("password") %>">

    <input type="submit" value="はい"> 
</form>

<form action="manager_signup.jsp" method="get">
    <input type="submit" value="いいえ">
</form>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>