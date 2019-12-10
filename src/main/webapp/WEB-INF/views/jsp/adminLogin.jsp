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
    	<c:url value="/doAdminLogin" var="doLogin"/>
        <jsp:include page="header.jsp"></jsp:include>
         <!-- Contact Form -->
            <div class="contact_form" align="center">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-10 offset-lg-1">
                            <div class="contact_form_container">
                                <div class="contact_form_title">Admin Login</div>
                                <form:form action="${doLogin }" id="contact_form" method="POST" modelAttribute="user">                                                                          
                                    <form:input path="email" type="text" id="contact_form_email" class="contact_form_email input_field" placeholder="Your email" required="required" data-error="Email is required."/><br/>
                                    <form:input path="password" type="password" id="contact_form_name" class="contact_form_phone input_field" placeholder="Your password"/>
                                    <c:if test = "${not empty error }">
                            			<p style = "color: red">${error }</p>
                            		</c:if>
                                    <div class="contact_form_button">
                                        <button type="submit" class="button contact_submit_button">Login</button><br>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
         <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
