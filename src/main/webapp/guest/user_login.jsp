<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/common/head.jsp" />
<title>ログイン</title>
</head>
<body>

<jsp:include page="/common/header.jsp"/>


      <h1>ログイン</h1>
      <form action="/rental_room/guest/UserLogin.action" method="post">
        ログイン ID<br>
        <input type="text" name="login_id" minlength="4" maxlength="20"  required><br>
        パスワード<br>
        <input type="password" name="password" required><br>
        <input type="submit" value="ログイン">
      </form>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>