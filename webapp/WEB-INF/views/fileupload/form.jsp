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
		
		<div id="wrapper">
			<div id="content">
				<div id="guestbook">
					
					<form method="post" action="${pageContext.request.contextPath }/fileupload/upload" enctype = "multipart/form-data">
						<table>
							<tr>
								<td>첨부파일</td>
								<td><input type="file" name="file"></td>
								<td><input type="submit" value="파일업로드"></td>
							</tr>
						</table>
					</form>
					<table>
							
						<c:forEach items = "${fList}" var = "fileuploadVo">
						<tr>
							<td><img src="${pageContext.request.contextPath }/${url }${fileuploadVo.savename}" style="width:150px"></td>
							<td><a href = "${pageContext.request.contextPath }/fileupload/delete?no=${fileuploadVo.no}">삭제</a></td>
						</tr>
						</c:forEach> 
							
					</table>
					
				</div><!-- /guestbook -->
						
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<c:import url="/WEB-INF/views/includes/foot.jsp"></c:import>
		
	</div><!-- /container -->
	
	
	
</body>
</html>		
		
