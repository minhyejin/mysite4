<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="navigation">
			<ul>
				<li><a href="${pageContext.request.contextPath }/main">민혜진</a></li>
				<li><a href="${pageContext.request.contextPath }/guestbook/list">방명록</a></li>
				<li><a href="${pageContext.request.contextPath }/guestbook/listajax">방명록(ajax)</a></li>
				<li><a href="${pageContext.request.contextPath }/board/list">게시판</a></li>
				<li><a href="${pageContext.request.contextPath }/fileupload/form">Gallary</a></li>
			</ul>
		</div>
</body>
</html>