<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アパート・マンション賃貸システム</title>
<jsp:include page="/common/head.jsp" />
</head>

<body>
<jsp:include page="/common/header.jsp" />

    ログアウトしました。
    <form action="/rental_room/guest/user_login.jsp" method="post">
    	<input type="submit" value="ログイン">
    </form>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>