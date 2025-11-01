<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="jakarta.tags.core" %>

<html>
<head>
<title>利用者新規登録の確認画面</title>
<jsp:include page="/common/head.jsp" />

</head>

<body>
<jsp:include page="/common/header.jsp"/>


	<tr>
	<td>ログインID：<%=(String) session.getAttribute("loginId") %></td><br>
	<td>ニックネーム：<%=(String) session.getAttribute("nickName") %></td><br>
	<td>パスワード：<%=(String) session.getAttribute("hidePass") %> </td><br>
	
	</tr>


<p>この内容で登録しますか？</p>


<form action="UserSignup.action" method="post"><!--postリクエスト-->
 <!-- 値を再送する（hiddenで送る） -->
    <input type="hidden" name="loginid" value="<%= (String) session.getAttribute("loginId") %>">
    <input type="hidden" name="nickname" value="<%= (String) session.getAttribute("nickName") %>">
    <input type="hidden" name="password" value="<%= (String) session.getAttribute("password") %>">

   
    <input type="submit" value="はい"> 
</form>

<form action="user_signup.jsp" method="get">
    <input type="submit" value="いいえ">
</form>

<jsp:include page="/common/footer.jsp"/>

</body>
</html>