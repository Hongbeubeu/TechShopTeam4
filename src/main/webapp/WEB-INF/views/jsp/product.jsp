<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${product.name }</title>
        <meta name="description" content="OneTech shop project">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/bootstrap4/bootstrap.min.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/fontawesome-free-5.0.1/css/fontawesome-all.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/contact_styles.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/contact_responsive.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/product_styles.css" />">
    </head>
	<body>
	<c:url value="/addToCart" var="addToCart"/>	
        <jsp:include page="header.jsp"></jsp:include>
        
        	<h1 class= "product_name">${product.name }</h1><br>
        	<div class ="row">
	        	<c:forEach var="image" items="${images }">
	        		<div class ="col" >
	        			<img style = "width: 600px; height: 400px; " src="<c:url value="/resources/product_images/${image.imgPath }" />" alt="">
	        		</div>
	        	</c:forEach>
        	</div>
        	<div class="col">
	        	<h2>Description</h2><br>
	        	<div class="row">
		        	<div class="col">
			        	<p>Chip:</p> <br>
			        	<p>Ram:</p> <br>
			        	<p>VGA:</p> <br>
			        	<p>Display:</p> <br>
			        	<p>Hard Disk:</p> <br>
			        	<p>Key Board:</p> <br>
			        	<p>Port:</p> <br>
			        	<p>Battery:</p> <br>
			        	<p>OS:</p> <br>
		        	</div>
		        	<div class="col">
		        		<p>${product.chip }</p><br>
		        		<p>${product.ram }</p><br>
		        		<p>${product.vga }</p><br>
		        		<p>${product.display }</p><br>
		        		<p>${product.keyboard }</p><br>
		        		<p>${product.hardDisk }</p><br>
		        		<p>${product.port }</p><br>
		        		<p>${product.battery }</p><br>
		        		<p>${product.operaSystem }</p><br>
		        	</div>
	        	</div>
	        </div>
	        <c:if test = "${not empty user }">
		        <div class = "row">
		        <div class = "col">
		        	<p>Quantity: </p>
		        </div>
			    <div class = "col">
				   <form action="${addToCart}" method="POST">
				   		<input type="number" class = "form-control" name = "quantity">
				   		<input type="hidden" value="${user.id }" name = "userId">
				   		<input type="hidden" value="${product.intPrice }" name = "price">
				   		<input type="hidden" value="${product.productId }" name = "productId">
		           		<button style ="margin-top: 10px" class="btn btn-primary">Add to Cart</button>
		           </form>
			    </div>
		    	</div> 
		    </c:if>
		<jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>