<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!-- ★ヘッダー部分 -->
<header class="pokedex-header">
  <div class="led soft-yellow"></div>
  <!-- 共通 -->
  <a href="/rental_room/guest/top.jsp">千葉市のアパート・マンション賃貸情報サイト	MyRental</a>

  <nav>
     <!-- ゲスト -->
    <c:if test="${empty sessionScope.account}">
      <p>
        <a href="/rental_room/guest/user_login.jsp">ログイン</a>
        <a href="/rental_room/guest/user_signup.jsp">利用者新規登録</a>
      </p>
    </c:if>

     <!-- 利用者 -->
    <c:if test="${sessionScope.account.role == '利用者'}">
      <p>
        <em>こんにちは <c:out value="${sessionScope.account.nickName}"/> さん</em>
        <a href="/rental_room/user/user_logout_confirm.jsp">ログアウト</a>
      </p>
    </c:if>

     <!-- 管理者 -->
    <c:if test="${sessionScope.account.role == '管理者'}">
      <p>
        <em>こんにちは <c:out value="${sessionScope.account.nickName}"/> さん</em>
        <a href="/rental_room/manager/property_register.jsp">物件登録</a>
        <a href="/rental_room/manager/manager_signup.jsp">管理者新規登録</a>
        <a href="/rental_room/manager/manager_logout_confirm.jsp">ログアウト</a>
      </p>
    </c:if>
  </nav>
</header>
