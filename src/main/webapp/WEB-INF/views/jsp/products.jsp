<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <meta name="description" content="OneTech shop project">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/bootstrap4/bootstrap.min.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/fontawesome-free-5.0.1/css/fontawesome-all.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/contact_styles.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/contact_responsive.css" />">
    </head>
    <body>
       	<table>
				<tr style="border: 1px black solid">
					<td>${products.productId}</td>
					<td>${products.name}</td>
					<td>${products.chip}</td>
					<td>${products.ram}</td>
					<td>${products.vga}</td>
					<td>${products.display}</td>
					<td>${products.camera}</td>
					<td>${products.hardDisk}</td>
					<td>${products.keyboard}</td>
					<td>${products.port}</td>
					<td>${products.battery}</td>
					<td>${products.operaSystem}</td>
				</tr>
			</table>
    </body>
</html>
