<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>利用者ログイン</title>

<jsp:include page="/common/head.jsp" />  

<style>
.layout-group {
	margin: 10px 0;
}

.layout-group label {
	margin-right: 15px;
}
.city-grid {
  display: grid;
  grid-template-columns: repeat(3, auto);
  justify-content: center;
  gap: 8px 20px;
}

.city-grid label {
  text-align: left;
}

</style>
</head>


<body>
<jsp:include page="/common/header.jsp" />
<main class="main-content">	


      <h1>《 ログイン 》</h1>
      <form action="/rental_room/guest/UserLogin.action" method="post">
        ログイン ID<br>
        <input type="text" name="login_id" required><br>
        パスワード<br>
        <input type="password" name="password" required><br><br><br>
        <input type="submit" value="ログイン">
      </form>
 </main>

<jsp:include page="/common/footer.jsp" />

</body>
</html>