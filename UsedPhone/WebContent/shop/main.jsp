<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 비회원일경우 세션값들 -->
<c:if test="${ empty sessionScope.memberID }">
	<% session.setAttribute("memberID", "비회원"); %>
</c:if>
<c:if test="${ empty sessionScope.membertype }">
	<% session.setAttribute("membertype", "비회원"); %>
</c:if>
<c:if test="${ empty sessionScope.wallet }">
	<% session.setAttribute("wallet", 0); %>
</c:if>

<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>사구팔구</title>
		<!-- 스타일시트 -->
		<link rel="stylesheet" type="text/css" href="css/css_main.css?var=<%=System.currentTimeMillis()%>">
		<!-- 웹폰트 -->
		<link href="http://fonts.googleapis.com/earlyaccess/notosanskr.css" rel="stylesheet" type="text/css">

		<script src="js/httpRequest.js"></script>
		<script>
		// 로그아웃
		function logout() {
			var memberID = "${ sessionScope.memberID }";
			
			if ( confirm(memberID + " 님 로그아웃 하시겠습니까?") ) {
				alert("로그아웃 되었습니다");
				location.href = "member_logout.do";
			}	
		}
		
		// 회원정보, 거래내역
		function mypage() {
			location.href = "member_mypage.do";
		}
		
		// 아이디, 비밀번호찾기
		function findpwd() {
			location.href = "member_search.do";
		}
		</script>
	</head>
	
	<body>
		<h1><a href="phonelist.do">사구팔구</a></h1>
		<c:choose>
		<c:when test="${ sessionScope.memberID == '비회원' }">
		<div class="dv1" style="float:left; width:99%;">
			<span style="float:left; width:30%;">
				<dl>
					<dd class="logdd">* 로그인 하시면 서비스를 이용하실 수 있습니다</dd>
					<dd class="logdd"><strong>${ sessionScope.memberID }</strong>님 접속중 입니다<br></dd>
					<dd class="logdd">
						<input class="btn" type="button" value="회원가입" onclick="location.href='member_join_typecheck.do'">
						<input class="btn" type="button" value="로그인" onclick="location.href='member_login.do'">
						<input class="btn" type="button" value="비밀번호찾기" onclick="findpwd()">
					</dd>
				</dl>
			</span>
			<span class="dv2" style="float:left; width:65%; margin-top: 10px;">
					<ul style="float:left;">
						<li><a class="category" href="phonelist.do?company=all">단말기리스트</a>
							<ul>
								<li><a class="category" href="phonelist.do?company=all">전체</a></li>
								<li><a class="category" href="phonelist.do?company=apple">APPLE</a></li>
								<li><a class="category" href="phonelist.do?company=samsung">SAMSUNG</a></li>
								<li><a class="category" href="phonelist.do?company=lg">LG</a></li>
								<li><a class="category" href="phonelist.do?company=other">기타</a>
							</ul>
						</li>
						<li><a class="category" href="free_board_main.do">자유게시판</a></li>
					</ul>
			</span>
		</div>
		</c:when>
		
		
		<c:when test="${ membertype == '사업자' or membertype == '일반' }">
			<div class="dv1" style="float:left; width:99%;">
				<span style="float:left; width:30%;">
					<dl>
						<dd class="logdd">[ ${ sessionScope.membertype }회원 ]</dd>
						<dd class="logdd"><strong>${ sessionScope.memberID }</strong>님 접속중 입니다<br></dd>
						<dd class="logdd">* 지갑 : <fmt:formatNumber value="${ sessionScope.wallet }"/>원<br></dd>
						<dd class="logdd">
							<input class="btn" type="button" value="회원정보/거래내역" onclick="mypage()">
							<input class="btn" type="button" value="로그아웃" onclick="logout()">
						</dd>
					</dl>
				</span>
				<span class="dv2" style="float:left; width:65%; margin-top: 10px;">
					<ul>
						<li><a class="category" href="phonelist.do?company=all">단말기리스트</a>
							<ul style="float:left;">
								<li><a class="category" href="phonelist.do?company=all">전체</a></li>								
								<li><a class="category" href="phonelist.do?company=apple">APPLE</a></li>
								<li><a class="category" href="phonelist.do?company=samsung">SAMSUNG</a></li>
								<li><a class="category" href="phonelist.do?company=lg">LG</a></li>
								<li><a class="category" href="phonelist.do?company=other">기타</a>
							</ul>
						</li>
						<li><a class="category" href="free_board_main.do">자유게시판</a></li>
						
						<c:if test="${ membertype == '일반' }">
							<li><a class="category" href="phone_regist.do">판매등록</a></li>
							<li><a class="category" href="phone_status.do">판매알림</a></li>
						</c:if>
						<c:if test="${ membertype == '사업자' }">
							<li><a class="category" href="phone_status.do">구매알림</a></li>
						</c:if>
					</ul>
				</span>
			</div>
		</c:when>
		<c:when test="${ membertype == 'master'}">
			<div id="grid">
				<dl>
					<dd class="logdd">[ 관리자 ]</dd>
					<dd class="logdd"><strong>[ 관리자로 로그인중입니다 ]<br></dd>
					<dd class="logdd">
						<div>
						<input class="btn" type="button" value="로그아웃" onclick="logout()">
						</div>
					</dd>
				</dl>
				<div id="article">
					<ul>
						<li><a class="category" href="member_list_action.do?master=1">일반회원목록</a></li>
						<li><a class="category" href="member_list_action.do?master=2">사업자회원목록</a></li>
						<li><a class="category" href="leaving_list_action.do">탈퇴회원목록</a></li>
						<li><a class="category" href="phone_list_action.do">모든거래목록</a></li>	
						<li><a class="category" href="board_list_action.do">게시글목록</a></li>
					</ul>
				</div>
			</div>
		</c:when>
		</c:choose>
	</body>
</html>