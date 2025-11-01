<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/common/head.jsp" />
<title>アパート・マンション賃貸システム-管理者ログイン-</title>
</head>
<body>

      <h1>《管理者ログイン》</h1>
      <form action="/rental_room/guest/ManagerLogin.action" method="post">
        ログイン ID<br>
        <input type="text" name="login_id" required><br>
        パスワード<br>
        <input type="password" name="password" required><br>
        <input type="submit" value="ログイン">
      </form>

</body>
</html>