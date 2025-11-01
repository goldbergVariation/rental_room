<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<title>退会完了</title>
<jsp:include page="/common/head.jsp" />
</head>

<body>
<jsp:include page="/common/header.jsp"/>

    <main>
        <h2><c:out value="${requestScope.message}" /></h2>
    </main>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>