<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<th>Id</th>
			<th>Price</th>
			<th>Status</th>
		</tr>
		<c:forEach var="purchased" items="${purchased}">
			<tr>
				<td>${purchased.id }</td>
				<td>${purchased.totalPrice} VND</td>
				<td>${purchased.status}</td>
			</tr>
		</c:forEach>		
	</table>
	</c:if>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>