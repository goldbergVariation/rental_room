<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>アパート・マンション賃貸システム</title>
<jsp:include page="/common/head.jsp" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<style>
.layout-group {
	margin: 10px 0;
}

.layout-group label {
	margin-right: 15px;
}
.city-grid {
  display: grid;
  grid-template-columns: repeat(3, auto);
  justify-content: center;
  gap: 8px 20px;
}

.city-grid label {
  text-align: left;
}
.bold {
	font-weight: bold;
}
</style>
</head>

<body>
<jsp:include page="/common/header.jsp" />

<main class="main-content">
		<h1>
			物件検索
		</h1>

		<form action="/rental_room/guest/PropertySearch.action" method="post">
			<p class="search-section">
			<span class="bold">賃料(共益費込)</span><br> <label><input type="radio" name="priceNum" value="1"> 0〜50,000円</label><br> 
				<label><input type="radio" name="priceNum" value="2"> 50,001〜80,000円</label><br> 
				<label><input type="radio" name="priceNum" value="3"> 80,001〜100,000円</label><br> 
				<label><input type="radio" name="priceNum" value="4"> 100,001円以上</label><br>
			</p>

			<p>
			<span class="bold">間取り（複数選択可）</span><br> 
				<label><input type="checkbox" name="layout" value="1R"> 1R</label> 
				<label><input type="checkbox" name="layout" value="1K"> 1K</label> 
				<label><input type="checkbox" name="layout" value="1DK"> 1DK</label> 
				<label><input type="checkbox" name="layout" value="1LDK"> 1LDK</label><br> 
				<label><input type="checkbox" name="layout" value="2R"> 2R</label> 
				<label><input type="checkbox" name="layout" value="2K"> 2K</label> 
				<label><input type="checkbox" name="layout" value="2DK"> 2DK</label> 
				<label><input type="checkbox" name="layout" value="2LDK"> 2LDK</label><br> 
				<label><input type="checkbox" name="layout" value="3R"> 3R</label> 
				<label><input type="checkbox" name="layout" value="3K"> 3K</label> 
				<label><input type="checkbox" name="layout" value="3DK"> 3DK</label> 
				<label><input type="checkbox" name="layout" value="3LDK"> 3LDK</label><br> 
				<label><input type="checkbox" name="layout" value="4R以上"> 4R以上</label><br> 
			</p>
			<div class="search-block">
			<span class="bold">地区</span><br>
			<table  class="city-grid">
				<tr>
					<td><label><input type="radio" name="cityNum" value="1">中央区</label></td>
					<td><label><input type="radio" name="cityNum" value="2">花見川区</label></td>
					<td><label><input type="radio" name="cityNum" value="3">稲毛区</label></td>
				</tr>
				<tr>
					<td><label><input type="radio" name="cityNum" value="4">若葉区</label></td>
					<td><label><input type="radio" name="cityNum" value="5">緑区</label></td>
					<td><label><input type="radio" name="cityNum" value="6">美浜区</label></td>
				</tr>
			</table>
			</div><br>

			<button type="submit">検索</button>
			<input type="reset" value="リセット">
		</form>
</main>

<jsp:include page="/common/footer.jsp" />
</body>
</html>