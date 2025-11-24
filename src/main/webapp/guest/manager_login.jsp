<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value='/css/manager_style.css' />">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
.login-box {
  max-width: 260px;
  margin: 20px auto;
  padding: 24px;
  border: 1px solid #bbb;
  border-radius: 8px;
  background: #fafafa;
  box-shadow: 2px 2px 6px rgba(0,0,0,0.08);
  text-align: left;
}

.login-box input[type="text"],
.login-box input[type="password"] {
  width: 100%;
  padding: 6px;
  margin: 6px 0 12px;
  box-sizing: border-box;
}

.login-box input[type="submit"] {
  width: 100%;
  padding: 8px;
  font-weight: bold;
  cursor: pointer;
}
</style>
<title>管理者ログイン</title>
</head>
<body>
<main class="main-content">
      <h1>《管理者ログイン》</h1>

      <div class="login-box">
        <form action="/rental_room/guest/ManagerLogin.action" method="post">
          ログイン ID<br>
          <input type="text" name="login_id" required><br>
          パスワード<br>
          <input type="password" name="password" required><br>
          <input type="submit" value="ログイン">
        </form>
      </div>

</main>
</body>
</html>
