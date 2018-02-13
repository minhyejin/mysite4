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
					<input type="submit" value="찾기">
					 <%-- <input type = "hidden" name = "crtPage" value = "${bMap.crtPage }"> --%>
				</form>		
				<table class="tbl-ex">
					<tr>
						<th width ="10%">번호</th>
						<th width ="30%">제목</th>
						<th width ="15%">글쓴이</th>
						<th width ="10%">조회수</th>
						<th width ="25%">작성일</th>
						<th width ="10%">&nbsp;</th>
					</tr>
					<c:forEach items = "${bMap.boardList }" var ="boardVo"  >		
						<tr>
						<td>${boardVo.no }</td>
						<td><a href="${pageContext.request.contextPath }/board/view?no=${boardVo.no }&kwd=${bMap.kwd}&crtPage=${bMap.crtPage}">${boardVo.title }</a></td>
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
					<c:choose>
					<c:when test ="${bMap.kwd == ''}">
						<c:if test = "${bMap.prev }">
						<li><a href="${pageContext.request.contextPath }/board/list?crtPage=${bMap.startPageBtnNo-1}">◀</a></li>
						</c:if>
						<c:forEach begin = "${bMap.startPageBtnNo }" end= "${bMap.endPageBtnNo }" var = "idx">
						<li ><span style="font-weight:bold;"><a href="${pageContext.request.contextPath }/board/list?crtPage=${idx}">${idx }</a></span></li>
						</c:forEach>
		
						<c:if test = "${bMap.next }">
						<li><a href="${pageContext.request.contextPath }/board/list?crtPage=${bMap.endPageBtnNo+1}">▶</a></li>
						</c:if>

					</c:when >				
					<c:otherwise>
					
						<c:if test = "${bMap.prev }">
						<li><a href="${pageContext.request.contextPath }/board/list?kwd=${bMap.kwd }&crtPage=${bMap.startPageBtnNo-1}">◀</a></li>
						</c:if>
						<c:forEach begin = "${bMap.startPageBtnNo }" end= "${bMap.endPageBtnNo }" var = "idx">
						<li ><a href="${pageContext.request.contextPath }/board/list?kwd=${bMap.kwd }&crtPage=${idx}">${idx }</a></li>
						</c:forEach>
		
						<c:if test = "${bMap.next }">
						<li><a href="${pageContext.request.contextPath }/board/list?kwd=${bMap.kwd }&crtPage=${bMap.endPageBtnNo+1}">▶</a></li>
						</c:if>
					</c:otherwise>

					</c:choose>	
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