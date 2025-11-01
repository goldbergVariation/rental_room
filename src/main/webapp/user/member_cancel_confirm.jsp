<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/common/head.jsp" />
</head>

<body>
<jsp:include page="/common/header.jsp"/>

<p>利用者退会手続きを行います。よろしいですか？</p>

<p>
    <a href="/rental_room/user/CancelUser.action">はい</a>
    &nbsp;|&nbsp;
    <a href="/rental_room/guest/top.jsp">いいえ</a>
       
</p>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>