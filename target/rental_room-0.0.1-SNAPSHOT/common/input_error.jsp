<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/common/head.jsp" />
<title>入力エラー</title>
</head>
<body>

<jsp:include page="/common/header.jsp"/>
  <h2>《入力内容の確認のお願い》</h2>

  <!-- JSTLでエラー種別を切り替え -->

    <c:if test="${error_message == 'empty'}">
      <p>未入力の項目があります。全ての項目を入力してください。</p>
    </c:if>

    <c:if test="${error_message == 'duplicate'}">
      <p>IDが重複しています。別のIDを使用してください。</p>
    </c:if>

    <c:if test="${error_message == 'wrong'}">
      <p>入力に誤りがあります。もう一度確認してください。</p>
    </c:if>
    
    <c:if test="${error_message == 'withdrawal'}">
      <p>退会済みのアカウントです。新規登録をお願いします。</p>
    </c:if>
    
    
  <a href="${forward_page}">${button}戻る</a>

<jsp:include page="/common/footer.jsp"/>

</body>
</html>