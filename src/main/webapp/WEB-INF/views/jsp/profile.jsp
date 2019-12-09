<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${user.email }</title>
        <meta name="description" content="OneTech shop project">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/bootstrap4/bootstrap.min.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/fontawesome-free-5.0.1/css/fontawesome-all.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/contact_styles.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/contact_responsive.css" />">
        <style>
        	.button{
        		padding: 10px;
        		width: 50%;
        	}
        </style>
    </head>
    <body>
    	<c:url value="/purchased/${user.id }" var="purchased"/>
    	<c:url value="/cart/${user.id }" var="cart"/>
    	<c:url value="/logout" var="logout"/>
        <jsp:include page="header.jsp"></jsp:include>
        <h1 align="center" class="contact_form_title">Profile</h1>
        <div class="row">
        <div class="col"></div>
        	<div class="col">
        		<p>Email: </p>
        		<p>Phone Number:</p>
        		<p>Address: </p>
        	</div>
        	<div class="col">
        		<p>${user.email }</p>
        		<p>${user.phoneNumber }</p>
        		<p>${user.address }</p>
        	</div>
        	<div class="col">
        		<form action="${purchased}" method="GET">
        			<button class="btn btn-primary button" type="submit">Purchased</button>
        		</form>
        		<form action="${cart }" method="GET">
        			<button class="btn btn-primary button" type="submit">Cart</button>
        		</form>
        		<form action="${logout }" method="GET">
        			<button class="btn btn-danger button" type="submit">Log Out</button>
        		</form>
        	</div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
