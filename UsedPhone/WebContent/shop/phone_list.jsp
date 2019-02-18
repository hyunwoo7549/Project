<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>사구팔구</title>
		<style>
		fieldset {
			margin: 0 auto;
			text-align : center;
			width : 97%;
			margin-top:30px;
		}
		
		fieldset .tb {
			border : 2px solid darkgrey;
			border-collapse : collapse;
			width : 99%;
    		height: 99%;
		}
		
		fieldset .tb tr, fieldset .tb th, fieldset .tb td {
			border : 1px solid darkgrey;
		}
		</style>
		<script>
		function details ( p_idx ) {
			location.href = "phone_view.do?p_idx=" + p_idx;
		}
		</script>
	</head>
	<body>
		<jsp:include page="../shop/main.jsp"/>
		
		<fieldset>
			<table class="tb">
				<tr style="height: 40px;">
					<th>등록자</th>
					<th>제품사진</th>
					<th>제조회사</th>
					<th>모델명</th>
					<th>고객희망매입가</th>
					<th>등록일</th>
					<th>상태</th>
					<th>매입</th>
				</tr>
				
				<!-- 제품이 없을경우 -->
				<c:if test="${ empty list }">
					<tr>
						<td colspan="8" align="center">
							상품이 없습니다.
						</td>
					</tr>
				</c:if>
				
				<c:forEach var="vo" items="${ list }">
					<tr align="center">
						<td>
							${ vo.g_id }
						</td>
						
						<td>
							<a href="phone_view.do?p_idx=${ vo.p_idx }">
							<img src="phoneimages/${ vo.p_image_s }" width="100" height="90">
							</a>
						</td>
					
						<td>
							${ vo.p_company }
						</td>
					
						<td>
							<a href="phone_view.do?p_idx=${ vo.p_idx }">
							${ vo.p_name }
							</a>
						</td>
					
						<td>
							<fmt:formatNumber value="${ vo.p_price }"/>원<br>
						</td>
					
						<td>
							${ vo.regidate }
						</td>
						
						<td>
							<c:if test="${ vo.status == '완료'}">
								<span style="color:red">거래완료</span>
							</c:if>
							<c:if test="${ vo.status == '대기'}">
								<span style="color:blue">매입가능</span>
							</c:if>
							<c:if test="${ vo.status == '거래중'}">
								<span style="color:orange">거래중</span>
							</c:if>
						</td>
						
						<td>
							<input class="btn" type="button" value="상세보기" onclick="details(${ vo.p_idx })">
						</td>
					</tr>
				</c:forEach>
			</table>
		</fieldset>
	</body>
</html>










