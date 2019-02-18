<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 배열 사이즈 구하기위해서 사용 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>상세보기</title>
		
		<style>
		fieldset {
			text-align : center;
			position : absolute;
			top : 65%;
			left : 50%;
			width : 1200px;
			height : 650px;
			overflow : hidden;
		    transform : translate(-50%, -50%);
		}
		
		fieldset .tb {
			border : 2px solid darkgrey;
			border-collapse : collapse;
			width : 1200px;
    		height: 130px;
		}
		
		fieldset .tb tr, fieldset .tb th, fieldset .tb td {
			border : 1px solid darkgrey;
		}
		
		</style>
		
		<script>
			function parchase ( f ){
				var membertype = '${ sessionScope.membertype }'; // 로그인한 회원타입
				var wallet = ${ sessionScope.wallet };		// 현재로그인한 사업자회원이 보유한 money
				var p_price = ${ vo.p_price };					// 고객희망매입가
				var doNotBuyAgain = Boolean(${ requestScope.doNotBuyAgain });
				
				var hopeprice = f.hopeprice.value.trim();   	// 입력한 매입가격
				var g_id = f.g_id.value.trim();			    	// 상품등록자 아이디
				var p_idx = f.p_idx.value.trim();		    	// 상품번호
				
				if ( membertype == '비회원' || membertype == '일반'){
					alert("사업자회원만 매입신청이 가능합니다. 비회원일경우 회원가입 후 이용해주세요.");
					return;
				}

				if ( hopeprice == ''){
					alert("금액을 입력해주세요");
					return;
				}	
				
				// 2번 매입신청 못하게 막음
				if ( doNotBuyAgain == true ) {
					alert("매입신청은 한번만 가능합니다");
					return;
				}
				
				if ( hopeprice > p_price * 2 ) {
					alert("희망매입가보다 2배이상의 금액은 입력할 수 없습니다.");
					return;
				}
				
				
				if ( wallet < p_price || wallet == null ) {
					alert("잔액이 부족합니다.")
					return;
				}
				
				
				if (confirm(hopeprice + "원에 매입신청 하시겠습니까?")){
					alert("등록되었습니다.");
				    f.submit();
				}
			}
		</script>
	</head>
	<body>
		<jsp:include page="main.jsp"/>
		<c:choose>
			<c:when test="${ vo.status eq '대기' }">
				<fieldset>
				<legend><h3>단말기상세</h3></legend>
				<form method="get" action="apply_purchase.do">
					<table class="tb">
						<tr style="height: 50px;">
							<th>등록자</th>
							<th style="width:100px">제조회사</th>
							<th>제품명</th>
							<th style="width:150px">고객희망매입가</th>
							<th colspan="2">등록일</th>
						</tr>
												
						<tr style="height: 40px;">
							<td>
								${ vo.g_id }			
							</td>
							
							<td>
								${ vo.p_company }
							</td>
							
							<td>
								${ vo.p_name }
							</td>
							
							<td>
								<fmt:formatNumber value="${ vo.p_price }"/>원
							</td>
							
							<td colspan="2">
								${ vo.regidate }
							</td>
						</tr>
						
						<c:if test="${ !empty list[0].b_id }">
							<tr>
								<th rowspan="4" style>상세설명</th>
								
								<td colspan="2" rowspan="4">
									<textarea style="border:none; resize:none;"cols="60" rows="10" readonly>${ vo.p_text }</textarea>
								</td>
								
								<th rowspan="4">희망매입가<br> TOP3</th>
								
								<th style="height: 50px;">
									아이디
								</th>
								
								<th>
									희망매입가
								</th>
							
							</tr>
							
							<tr style="height: 35px;">
								<td>${ list[0].b_id }</td>
								<td><fmt:formatNumber value="${ list[0].hopeprice }"/></td>
							</tr>
							
							<tr style="height: 35px;">
								<td>${ list[1].b_id }</td>
								<td><fmt:formatNumber value="${ list[1].hopeprice }"/></td>
							</tr>
							
							<tr style="height: 35px;">
								<td>${ list[2].b_id }</td>
								<td><fmt:formatNumber value="${ list[2].hopeprice }"/></td>
							</tr>
						</c:if>
						
						<c:if test="${ empty list[0].b_id }">
							<tr>
							<th rowspan="4" style>상세설명</th>
							<td colspan="2" rowspan="4"><textarea style="border:none; resize:none;"cols="60" rows="10" readonly>${ vo.p_text }</textarea></td>
							<th rowspan="4">현재매입가격 TOP3</th>
							<th colspan="2" rowspan="4" style="height: 50px">아직 매입자가 없습니다</th>
							</tr>
							<tr></tr>
							<tr></tr>
							<tr></tr>
						</c:if>
					
						<tr style="height: 280px;">
							<th colspan="1">상세사진</th>
							<th colspan="5">
								<img src="phoneimages/${ vo.p_image_1 }" width="260">
								<img src="phoneimages/${ vo.p_image_2 }" width="260">
								<c:if test="${ vo.p_image_3 ne 'no_file' }">
									<img src="phoneimages/${ vo.p_image_3 }" width="260">
								</c:if>
								<c:if test="${ vo.p_image_4 ne 'no_file' }">
									<img src="phoneimages/${ vo.p_image_4 }" width="260">
								</c:if>
							</th>
						</tr>
					</table>
					<br>
					<input style="width: 150px; height: 25px;" type="text" name="hopeprice" placeholder="숫자만입력">
					<input style="border: 2px groove grey; background:none; width: 120px; height: 31px;" type="button" value="원에 매입신청" onclick="parchase(this.form)">
					<input style="border: 2px groove grey; background:none; width: 50px; height: 31px;" type="button" value="이전" onclick="javascript:history.go(-1)">
					
					<!-- 서블릿으로 념겨줄 hidden 목록  -->
					
					<!-- 제품 고유번호 hidden -->
					<input type="hidden" name="p_idx" value="${ vo.p_idx }"/>
					
					<!-- 상품 등록자 아이디 hidden -->
					<input type="hidden" name="g_id" value="${ vo.g_id }"/>
						
					<!-- 매입신청자 아이디 hidden -->
					<input type="hidden" name="b_id" value="${ sessionScope.memberID }"/>
					
					<!-- 상품 미리보기이미지 hidden -->
					<input type="hidden" name="p_image_s" value="${ vo.p_image_s }"/>
					
					<!-- 상품명 hidden -->
					<input type="hidden" name="p_name" value="${ vo.p_name }"/>
				</form>
				</fieldset>
			</c:when>
		
			<c:when test="${ vo.status eq '완료' }">
				<fieldset>
				<legend><h3>단말기상세</h3></legend>
				<form method="get" action="apply_purchase.do">
					<table class="tb">
						<tr style="height: 50px;">
							<th>등록자</th>
							<th style="width:100px">제조회사</th>
							<th>제품명</th>
							<th style="width:150px">고객희망매입가</th>
							<th colspan="2">등록일</th>
						</tr>
												
						<tr style="height: 40px;">
							<td>
								${ vo.g_id }			
							</td>
							
							<td>
								${ vo.p_company }
							</td>
							
							<td>
								${ vo.p_name }
							</td>
							
							<td>
								<fmt:formatNumber value="${ vo.p_price }"/>원
							</td>
							
							<td colspan="2">
								${ vo.regidate }
							</td>
						</tr>	
						
						<tr>
							<th rowspan="4" >상세설명</th>
							
							<td colspan="2" rowspan="4">
								<textarea style="border:none; resize:none;"cols="60" rows="10" readonly>${ vo.p_text }</textarea>
							</td>
							
							<th style="color:red; border-right: 2px solid darkgrey;" colspan="2" rowspan="4">이미 판매된<br>상품입니다 </th>
						</tr>
						
						<tr></tr>
						<tr></tr>
						<tr></tr>
					
						<tr style="height: 280px;">
							<th colspan="1">상세사진</th>
							<th colspan="5">
								<img src="phoneimages/${ vo.p_image_1 }" width="260">
								<img src="phoneimages/${ vo.p_image_2 }" width="260">
								<c:if test="${ vo.p_image_3 ne 'no_file' }">
									<img src="phoneimages/${ vo.p_image_3 }" width="260">
								</c:if>
								<c:if test="${ vo.p_image_4 ne 'no_file' }">
									<img src="phoneimages/${ vo.p_image_4 }" width="260">
								</c:if>
							</th>
						</tr>
					</table>
				</form>
				</fieldset>
			</c:when>
			
			<c:when test="${ vo.status eq '거래중' }">
				<fieldset>
				<legend><h3>단말기상세</h3></legend>
				<form method="get" action="apply_purchase.do">
					<table class="tb">
						<tr style="height: 50px;">
							<th>등록자</th>
							<th style="width:100px">제조회사</th>
							<th>제품명</th>
							<th style="width:150px">고객희망매입가</th>
							<th colspan="2">등록일</th>
						</tr>
												
						<tr style="height: 40px;">
							<td>
								${ vo.g_id }			
							</td>
							
							<td>
								${ vo.p_company }
							</td>
							
							<td>
								${ vo.p_name }
							</td>
							
							<td>
								<fmt:formatNumber value="${ vo.p_price }"/>원
							</td>
							
							<td colspan="2">
								${ vo.regidate }
							</td>
						</tr>	
						
						<tr>
							<th rowspan="4" >상세설명</th>
							
							<td colspan="2" rowspan="4">
								<textarea style="border:none; resize:none;"cols="60" rows="10" readonly>${ vo.p_text }</textarea>
							</td>
							
							<th style="color:orange; border-right: 2px solid darkgrey;" colspan="2" rowspan="4">거래중인<br>상품입니다 </th>
						</tr>
						
						<tr></tr>
						<tr></tr>
						<tr></tr>
					
						<tr style="height: 280px;">
							<th colspan="1">상세사진</th>
							<th colspan="5">
								<img src="phoneimages/${ vo.p_image_1 }" width="260">
								<img src="phoneimages/${ vo.p_image_2 }" width="260">
								<c:if test="${ vo.p_image_3 ne 'no_file' }">
									<img src="phoneimages/${ vo.p_image_3 }" width="260">
								</c:if>
								<c:if test="${ vo.p_image_4 ne 'no_file' }">
									<img src="phoneimages/${ vo.p_image_4 }" width="260">
								</c:if>
							</th>
						</tr>
					</table>
				</form>
				</fieldset>
			</c:when>
			
			
			
			
			
		</c:choose>
	</body>
</html>











