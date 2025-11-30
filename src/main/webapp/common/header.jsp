<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!-- ヘッダー部分 -->
<header class="pokedex-header">

  <!-- 共通 -->
  <a href="/rental_room/guest/top.jsp">千葉市のアパート・マンション賃貸情報サイト	MyRental</a>

  <!-- ナビゲーション（右寄せしたい部分） -->
  <nav class="header-nav">
  
    <!-- ゲスト -->
    <c:if test="${empty sessionScope.account}">
      <p>
        <a href="/rental_room/guest/user_login.jsp" class="header-btn login">ログイン</a>
        <a href="/rental_room/guest/user_signup.jsp" class="header-btn register">利用者新規登録</a>
      </p>
    </c:if>

    <!-- 利用者 -->
    <c:if test="${sessionScope.account != null and sessionScope.account.role == '利用者'}">
      <p>
        <strong>こんにちは <c:out value="${sessionScope.account.nickName}"/> さん</strong>
        <a href="/rental_room/user/user_logout_confirm.jsp" class="header-btn">ログアウト</a>
      </p>
    </c:if>

    <!-- 管理者 -->
    <c:if test="${sessionScope.account != null and sessionScope.account.role == '管理者'}">
      <p>
        <strong>使用者： <c:out value="${sessionScope.account.nickName}"/> </strong><br>
      </p>
      <p>
        <a href="/rental_room/manager/property_register.jsp" class="header-btn">物件登録</a>
        <a href="/rental_room/manager/manager_signup.jsp" class="header-btn">管理者新規登録</a>
        <a href="/rental_room/manager/manager_logout_confirm.jsp" class="header-btn">ログアウト</a>
      </p>
    </c:if>
  </nav>

</header>