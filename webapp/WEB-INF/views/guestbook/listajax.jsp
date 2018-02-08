<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css"rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>

	<div id="container">

		<!-- /header -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navi.jsp"></c:import>
		<!-- /navigation -->

		<div id="wrapper">
			<div id="content">
				<div id="guestbook">
						<table >
							<tr>
								<td>이름</td>
								<td><input type="text" id = "name" name="name" /></td>
								<td>비밀번호</td>
								<td><input type="password"  id = "password" name="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content"  placeholder = "내용을 입력해주세요" class = "article" id="content"></textarea></td>			
							</tr>
							<tr>
								<td colspan=4 align=right><input id=insert type="submit" VALUE=" 확인 " /></td>

							</tr>
						</table>
						<input id="btnDel" type="button" value="삭제예제버튼" />	
					<ul id="listarea">
					</ul>
					<input id="btnNext" type="button" VALUE=" 더보기 " />
				</div>
				<!-- /guestbook -->
			</div>
			<!-- /content -->
		</div>
		<!-- /wrapper -->
		<c:import url="/WEB-INF/views/includes/foot.jsp"></c:import>
		<!-- /footer -->
	</div>
	<!-- /container -->
	<!-- 삭제팝업(모달)창 -->
	<div class="modal fade" id="del-pop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">방명록삭제</h4>
				</div>
				<div class="modal-body">
					<label>비밀번호</label>
					<input type="password" name="modalPassword" id="modalPassword"><br>
					<input type="text" name="modalNo" value="" id="modalNo"> <br>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger" id="btn_del">삭제</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</body>

<script type="text/javascript">
	
	$("#btn_del").on("click", function(){
		var guestbookVo = {
				no : $("#modalNo").val(),
				password : $("#modalPassword").val()
		}// no와 password를 guestbookVo에 저장
		console.log(guestbookVo)
		$.ajax({
			//보낼 때 데이터 타입
			url : "${pageContext.request.contextPath }/guestbook/api/delete",
			type : "post",
			contentType : "application/json",
			data :JSON.stringify(guestbookVo),//guestbookVo를 apicontroller로 보냄
			//받을 때 데이터 타입
			dataType : "json",
			success : function(result) {
				if(result){
					$("#del-pop").modal("hide");
					/* $("[id="+no+"]").remove(); */
				     location.reload();//새로고침1번 
				}else {
					console.log("제거 실패");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});	
	});	
	$("ul").on("click", ".deletebutton", function(){		
		var no = $(this).data("no");
		$("#del-pop").modal();
		$("#modalNo").val(no);//no값을 modalNo에 저장 
		$("#modalPassword").val("");//입력받는 password를 modalPassword에 저장 
	});	
	$("#insert").on("click",function(){
		var name =$("#name").val();
		var password = $("#password").val();
		var content = $(".article").val();
		var guestbookVo={
			
			name : name,
			password : password,
			content : content
		};	
		$.ajax({
			//보낼 때 데이터 타입
			url : "${pageContext.request.contextPath }/guestbook/api/selectinsert",
			type : "post",
			contentType : "application/json",
			data :JSON.stringify(guestbookVo),
			//받을 때 데이터 타입
			dataType : "json",
			success : function(guestbookVo) {
				console.log(guestbookVo);
				render(guestbookVo , "up");
				$('table').val('Default Value');
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});	
	});
	var page = 1; /* 현재페이지 */
	$(document).ready(function() {
		fetchList();
	});
	$("#btnNext").on("click", function() {

		page = page + 1;
		fetchList();
	});
	function fetchList() {
		$.ajax({
			//보낼 때 데이터 타입
			url : "${pageContext.request.contextPath }/guestbook/api/listajax",
			type : "post",
			/* 			contentType : "application/json",*/
			data : {
				page : page
			},
			//받을 때 데이터 타입
			dataType : "json",
			success : function(guestbookList) {
				console.log(guestbookList);
				for (var i = 0; i < guestbookList.length; i++) {
					render(guestbookList[i], "down");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	function render(guestbookVo, updown) {

		var str = "";
		str += "<li>";
		str += "	<table> ";
		str += "	  <tr>";
		str += "		<td>[" + guestbookVo.no + "]</td>";
		str += "		<td>" + guestbookVo.name + "</td>";
		str += "		<td>" + guestbookVo.regDate + "</td>";
		str += "		<td><input class = 'deletebutton' data-no = '"+ guestbookVo.no +"' type = 'button' value = '삭제' ></a></td>";
		str += "	  </tr>";
		str += "	  <tr>";
		str += "		<td colspan=4>" + guestbookVo.content + "</td>";

		str += "	  </tr>";
		str += "	 </table>";
		str += "  <br>";
		str += "</li>";
		if (updown == "up") {
			$("#listarea").prepend(str);

		} else if (updown == "down") {
			$("#listarea").append(str);
		} else {
			console.log("updown 오류");
		}
	}
</script>
</html>