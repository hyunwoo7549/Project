<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style>
		dl {
			text-align : left;
    		padding-left : 10%;	
		}
		
		dd {
			margin : 8px;
		}
		
		label {
			font-weight : 600;
		}
		
		fieldset {
			text-align : center;
			position : absolute;
			top : 55%;
			left : 50%;
			width : 400px;
			height : 270px;
			overflow : hidden;
		    transform : translate(-50%, -50%);
		}
		</style>
		
		<script>
		function pwdupdate ( f ) {
			var sessionPwd = '${ sessionScope.memberpwd }'; // 세션에 저장되어있는 내 비밀번호
			var  pwd = f.pwd.value; // 변경할 비밀번호
			var pwd2 = f.pwd2.value; // 변경할 비밀번호 확인
			if ( f.originpwd.value != sessionPwd ) {
				alert("현재 비밀번호를 확인해주세요");
				return;
			}
			
			// 비밀번호 유효성검사
			if ( pwd.length < 8 || pwd.length > 20 ) {
				alert("비밀번호는 8 ~ 20자로 입력하세요.");
				return
			} 
			
			if ( pwd !=  pwd2 ) {
				alert("비밀번호가 일치하지 않습니다.");
				return;
			}
			
			if ( f.originpwd.value == pwd ) {
				alert("변경할 비밀번호는 현재 비밀번호와 다르게 설정해주세요.");
				return;
			}
			
			if ( !(/^.*(?=.{8,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/.test( pwd )) ) {
				alert("비밀번호는 숫자와 영문을 혼용하여야합니다");
				return;
			}
			
			alert("비밀번호가 변경되었습니다.");
			location.href = "member_update_pwd_result.do?inputpwd=" + pwd;
			
		}
		</script>
	</head>
	<body>
		<jsp:include page="../shop/main.jsp"/>
		
		<form method="POST"> 
			<fieldset>
			<legend><h3>비밀번호변경</h3></legend>
			<div style="display:;">
				<dl>
					<dd>
						<label for="id">아이디 </label>
						<input style="border:none; background-color: #e2e2e2;" name="id" id="id" type="hidden" value="${ memberID }">${ memberID }
					</dd>
					<dd>
						<label for="pwd">현재 비밀번호 </label>
						<input style="border:none; background-color: #e2e2e2;" name="originpwd" id="originpwd" type="password">
					</dd>
					<dd>
						<label for="pwd">변경할 비밀번호 </label>
						<input style="border:none; background-color: #e2e2e2;" name="pwd" id="pwd" type="password" placeholder="8자 이상 ~ 20자 이하">
					</dd>
					<dd>
						<label for="pwd2">변경할 비밀번호확인 </label>
						<input style="border:none; background-color: #e2e2e2;" name="pwd2" id="pwd2" type="password" placeholder="8자 이상 ~ 20자 이하">
					</dd>
				</dl>
				<div>
					<input class="btn" type="button" value="비밀번호변경" onclick="pwdupdate(this.form)">
					<input class="btn" type="button" value="이전" onclick="javascript:history.go(-1)">
				</div>
			</div>
			</fieldset>
		</form>
	</body>
</html>