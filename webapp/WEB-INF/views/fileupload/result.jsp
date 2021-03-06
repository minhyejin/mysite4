<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<title>Mysite4</title>
</head>
<body>
	<div id="container">
		
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navi.jsp"></c:import>
		
		<div id="content">
			<div style="margin:50px auto; width:500px;">
				<h1 style="margin-bottom:20px">Upload completed</h1>
				<div class="result-images">
					<img src="${pageContext.request.contextPath }/${url }" style="width:150px"><br>
				</div>
				<p>
					<a href="${pageContext.request.contextPath }/fileupload/form"> 다시 업로드 하기 </a>
				</p>
			</div>
		</div>


		<c:import url="/WEB-INF/views/includes/foot.jsp"></c:import>
		
	</div><!-- /container -->
</body>
</html>		
		
