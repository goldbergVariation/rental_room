<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<% // 管理者・利用者・ゲストでフッタを変更する %>
<footer class="pokedex-footer">
	<c:choose>
		<c:when test="${sessionScope.account != null and sessionScope.account.role == '管理者'}">
		  <a href="/rental_room/guest/top.jsp" class="back-to-top">トップへ戻る</a>
		</c:when>
		<c:otherwise>
		  <a href="/rental_room/guest/top.jsp" class="back-to-top">🏠 トップへ戻る</a>
		</c:otherwise>
	</c:choose>

  <c:if test="${sessionScope.account != null and sessionScope.account.role == '利用者'}">
    <form action="/rental_room/user/member_cancel_confirm.jsp" method="get">
      <button type="submit" class="cancel-link">退会</button>
    </form>
  </c:if>
</footer>

