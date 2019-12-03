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
       	<c:forEach var="p" items="${products}">
				<tr style="border: 1px black solid">
					
					<td>${p.name}</td>
					<td>${p.chip}</td>
					<td>${p.ram}</td>
					<td>${p.vga}</td>
					<td>${p.display}</td>
					<td>${p.camera}</td>
					<td>${p.hardDisk}</td>
					<td>${p.keyboard}</td>
					<td>${p.port}</td>
					<td>${p.battery}</td>
					<td>${p.operaSystem}</td>
				</tr>
			</c:forEach>
			</table>
    </body>
</html>
