<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<footer class="pokedex-footer">
  <a href="/rental_room/guest/top.jsp" >🏠 トップへ戻る</a>
  
   <c:if test="${sessionScope.account.role == '利用者'}">
	   <a href="/rental_room/user/member_cancel_confirm.jsp">退会</a>
   </c:if>
</footer>