<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:choose>
	<c:when test="${not empty sessionScope.account && sessionScope.account.role == '管理者'}">
		<link rel="stylesheet" href="<c:url value='/css/manager_style.css' />">
		<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
	</c:when>
	<c:otherwise>
		<link rel="stylesheet" href="<c:url value='/css/style.css' />">
		<link href="https://fonts.googleapis.com/css2?family=M+PLUS+Rounded+1c:wght@500&display=swap" rel="stylesheet">
	</c:otherwise>
</c:choose>
