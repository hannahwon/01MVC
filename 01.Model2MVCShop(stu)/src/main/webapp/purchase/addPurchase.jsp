<%@ page contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8" %>

<%@ page import="com.model2.mvc.service.product.vo.*"  %>
<%@ page import="com.model2.mvc.service.purchase.vo.*" %>

<%
	PurchaseVO purchaseVO = (PurchaseVO)request.getAttribute("purchaseVO");
	ProductVO productVO = (ProductVO)request.getAttribute("productVO");
%>

<html>
<head>
<title>구매</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=0" method="post">

다음과 같이 구매가 되었습니다.

<table border=1>
	<tr>
		<td>물품번호</td>
		<td><%=productVO.getProdNo() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자아이디</td>
		<td><%=purchaseVO.getBuyer() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매방법</td>
		<td><%=purchaseVO.getPaymentOption() %>
		
			<select name="paymentOption">
			<option value="cash">현금결제</option>
			<option value="card">카드결제</option>
			</select>
		
		</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td><%=purchaseVO.getReceiverName() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td><%=purchaseVO.getReceiverPhone() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td><%=purchaseVO.getDivyAddr()%></td>
		<td></td>
	</tr>
		<tr>
		<td>구매요청사항</td>
		<td>빠른 배송 부탁</td>
		<td></td>
	</tr>
	<tr>
		<td>배송희망일자</td>
		<td><%=purchaseVO.getTranCode() %></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>