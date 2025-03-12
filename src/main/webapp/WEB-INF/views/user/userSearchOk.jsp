<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>.jsp</title>
<jsp:include page="/WEB-INF/views/include/bs5.jsp" />
</head>
<body>
<p><br/></p>
<div class="container">
  <table class="table talbe-bordered text-center">
  	<tr>
  		<td colspan="2"><font size="5">검색 결과</font></td>
  	</tr>
  	<tr>
  		<th>아이디</th>
  		<td>${vo.mid}</td>
  	</tr>
  	<tr>
  		<th>비밀번호</th>
  		<td>${vo.pwd}</td>
  	</tr>
  	<tr>
  		<th>성명</th>
  		<td>${vo.name}</td>
  	</tr>
  	<tr>
  		<th>나이</th>
  		<td>${vo.age}</td>
  	</tr>
  	<tr>
  		<th>성별</th>
  		<td>${vo.gender}</td>
  	</tr>
  	<tr>
  		<th>주소</th>
  		<td>${vo.address}</td>
  	</tr>
  	<tr>
	  	<td colspan="2">
	  	<input type="button" value="돌아가기" onclick="location.href='userSearch';"class="btn btn-primary me-2"/>
  	</tr>
  </table>
</div>
<p><br/></p>
</body>
</html>