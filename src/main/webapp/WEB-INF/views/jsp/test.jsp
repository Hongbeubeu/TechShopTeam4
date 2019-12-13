<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>test</title>
        <meta name="description" content="OneTech shop project">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/bootstrap4/bootstrap.min.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/fontawesome-free-5.0.1/css/fontawesome-all.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/contact_styles.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/contact_responsive.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/product_styles.css" />">
    </head>
	<body>
	<form:form method="POST" action="uploadFile" enctype="multipart/form-data" modelAttribute="myFile">
    	File: <input type="file" name="multipartFile" /> <br /> <br/>
    	Description: <form:input path="description"/> <br />
    	<input type="submit" value="Submit" />
  	</form:form> 
        <c:if test="${not empty message }">${message }</c:if>
        <c:if test="${not empty error }">${error }</c:if>
    </body>
</html>