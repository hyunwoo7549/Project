<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style>
		dd {
			margin:0;
			display:inline;
		}
		
		fieldset {
			text-align : center;
			position : absolute;
			top : 50%;
			left : 50%;
			width : 700px;
			height : 100px;
		    transform : translate(-50%, -50%);
		}
		</style>
	</head>
	
	<body>
		<jsp:include page="../shop/main.jsp"/>
		
		<form method="POST"> 
			<fieldset>
			<legend><h3 style="margin:0 auto">원하는 서비스를 클릭하세요</h3></legend>
			<div style="display:;">
				<dl>
					<dd><input class="btn" type="button" value="회원정보수정" onclick="location.href='member_update.do'"></dd>
					<dd><input class="btn" type="button" value="비밀번호변경" onclick="location.href='member_update_pwd.do'"></dd>
					<dd><input class="btn" type="button" value="내 게시글보기" onclick=""></dd>
					<dd><input class="btn" type="button" value="내 거래내역보기" onclick="location.href='dealhistory.do'"></dd>
					<dd><input class="btn" type="button" value="회원탈퇴" onclick="location.href='member_leaving.do'"></dd>
				</dl>
			</div>
			</fieldset>
		</form>
	</body>
</html>