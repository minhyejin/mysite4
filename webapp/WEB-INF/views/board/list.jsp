<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>mysite4</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navi.jsp"></c:import>
		
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/board/list" method="get">
					
					<input type="text" id="kwd" name="kwd" value="">
					<input type = "hidden" name = "a" value = "list">
					<input type="submit" value="찾기">
				</form>
			
				<table class="tbl-ex">
					<tr>
						<th width ="10%">번호</th>
						<th width ="20%">제목</th>
						<th width ="20%">글쓴이</th>
						<th width ="15%">조회수</th>
						<th width ="25%">작성일</th>
						<th width ="10%">&nbsp;</th>
					</tr>
					<c:forEach items = "${bList }" var ="boardVo" >		
						<tr>
						<td>${boardVo.no }</td>
						<td><a href="${pageContext.request.contextPath }/board/view?no=${boardVo.no }">${boardVo.title }</a></td>
						<td>${boardVo.writer}</td>
						<td>${boardVo.hit }</td>
						<td>${boardVo.regDate }</td>
						<td>
						<c:if test="${boardVo.userNo == authUser.no}">
						<p align="center"><a href="${pageContext.request.contextPath }/board/delete?no=${boardVo.no}&userNo=${boardVo.userNo}" class="del">삭제</a></p>
						</c:if></td>
						</tr>
				</c:forEach>
				</table>
				<div class="pager">
				
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li><a href="">2</a></li>
						<li class="selected">3</li>
						<li><a href="">4</a></li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
					
				</div>				
				<div class="bottom">
					<c:if test="${not empty authUser}">
					<a href="${pageContext.request.contextPath }/board/writeform" id="new-book">글쓰기</a>
					</c:if>	
				</div>				
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/foot.jsp"></c:import> <!-- /footer -->
		
	</div>
</body>
</html>