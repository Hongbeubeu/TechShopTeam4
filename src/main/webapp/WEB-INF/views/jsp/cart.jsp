<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="cart" items="${cart }">
	${cart.userId }<br>
	${cart.name }<br>
	<img src="<c:url value="/resources/product_images/${cart.imgPath }" />" alt="">
	${cart.productId }<br>
	${cart.quantity }<br>
	${cart.totalPrice }<br>
</c:forEach>
</body>
</html>