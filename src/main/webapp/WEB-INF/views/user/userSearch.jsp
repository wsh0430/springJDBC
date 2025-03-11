<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userSearch.jsp</title>
<jsp:include page="/WEB-INF/views/include/bs5.jsp" />
</head>
<body>
<p><br/></p>
	<div class="container">
		<form name="searchform" method="post">
			<table class="table talbe-bordered text-center">	
				<tr>
	  			<td colspan="2"><font size="5">회원 검색</font></td>
	  		</tr>
				<tr>
	  			<th>아이디</th>
	  			<td><input type="text" name="mid" id="mid" placeholder="아이디를 입력하세요" autofocus required class="form-control"></td>
	  		</tr> 
	  		<tr>
	  			<td colspan="2">
	  				<input type="submit" value="검색" class="btn btn-success me-2"/>
	  				<input type="reset" value="다시입력" class="btn btn-warning me-2"/>
	  				<input type="button" value="돌아가기" onclick="location.href='${ctp}/';"class="btn btn-primary me-2"/>
  			</tr>
  		</table>
		</form>
	</div>
	<p><br/></p>
</body>
</html>