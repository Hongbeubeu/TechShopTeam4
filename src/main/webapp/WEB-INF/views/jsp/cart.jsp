<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>Cart</title>
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
	<c:if test="${not empty cart}">
	<h1 align = "center" class ="contact_form">Cart</h1>
	<br>
	<br>
	<table class="table table-hover">
		<tr>
			<th>Image</th>
			<th>Product Name</th>
			<th>Quantity</th>
			<th>Price</th>
			<th>Delete</th>
		</tr>
		
			<c:forEach var="cart" items="${cart}">
				<tr>
					<td><img width="50%" src="<c:url value="/resources/product_images/${cart.imgPath }" />" alt=""></td>
					<td><a href="/TechShopTeam4.com/product/${user.id }/${cart.productId}">${cart.name}</a></td>
					<td>${cart.quantity}</td>
					<td>${cart.totalPrice} VND</td>
					<td> <a href="/TechShopTeam4.com/deleteCart/${user.id }/${cart.productId}">Delete</a></td>
				</tr>
			</c:forEach>
		
	</table>
	
	<div class = "row">
		<div class = "col"></div>
		<div class = "col"></div>
		<div class = "col">
		<form action="pay" method = "POST">
			<button class="btn btn-primary button" style="width:100%">Pay</button>
		</form>
		</div>
		<div class = "col">
		<p>Total Price </p>
		
		</div>
		<div class = "col">
		<p>${totalPrice } VND</p>
		
		</div>
	</div>
	</c:if>
	<c:if test="${empty cart}">
		<h1 align = "center" class ="contact_form">Empty Cart</h1>
	</c:if>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>