<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Purchased</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="OneTech shop project">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/bootstrap4/bootstrap.min.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/fontawesome-free-5.0.1/css/fontawesome-all.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/OwlCarousel2-2.2.1/owl.carousel.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/OwlCarousel2-2.2.1/owl.theme.default.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/OwlCarousel2-2.2.1/animate.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/slick-1.8.0/slick.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/main_styles.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/responsive.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/contact_styles.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/contact_responsive.css" />">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<br>
	<br>
	<c:if test="${not empty purchased}">
	<h1 align = "center" class ="contact_form">Purchased</h1>	
	<br>
	<br>
	<table class="table table-hover">
		<tr>
			<th>Order Id</th>
			<th>Product Image</th>
			<th>Product Name</th>
			<th>Quantity</th>
			<th>Price</th>
			<th>Status</th>
			<th>Order Date</th>
		</tr>
		<c:forEach var="purchased" items="${purchased}">
			<tr>
				<td>${purchased.orderId }</td>
				<td><img width="100px" height = "70px" src="<c:url value="/resources/product_images/${purchased.imgPath }" />" alt=""></td>
				<td><a style="padding: 20px" href="/TechShopTeam4.com/product/${user.id }/${purchased.productId}">${purchased.name}</a></td>
				<td>${purchased.quantity}</td>
				<td>${purchased.price}</td>
				<td>${purchased.status}</td>
				<td>${purchased.date}</td>
			</tr>
		</c:forEach>		
	</table>
	</c:if>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>