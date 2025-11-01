<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="jakarta.tags.core" %>

<html>
<head>
<jsp:include page="/common/head.jsp" />
</head>

<body>
<jsp:include page="/common/header.jsp"/>
    
    利用者新規登録を完了しました。
    
    <form action="user_login.jsp" method="post">
    <input type="submit" value="ログイン画面へ">
    </form>
<jsp:include page="/common/footer.jsp"/>
</body>
</html>