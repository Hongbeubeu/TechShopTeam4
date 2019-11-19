<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <meta name="description" content="OneTech shop project">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/bootstrap4/bootstrap.min.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/fontawesome-free-5.0.1/css/fontawesome-all.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/contact_styles.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/contact_responsive.css" />">
    </head>
    <body>
    	<c:url value="/doRegister" var="doRegister"/>
		<jsp:include page="header.jsp"></jsp:include>
        <!-- Contact Form -->
       	
        <div class="contact_form" align="center">
            <div class="container">
                <div class="row">
                    <div class="col-lg-10 offset-lg-1">  
                        <div class="contact_form_title">Register</div>
                        <form:form action="${doRegister }" id="contact_form" method="POST" modelAttribute="user">
                            <form:input path="email" type="text" id="contact_form_email" class="contact_form_email input_field" placeholder="Your email" required="required" data-error="Email is required."/><br/>
                            <form:input path="address" type="text" id="contact_form_email" class="contact_form_email input_field" placeholder="Your address"/><br/>
                            <form:input path="phoneNumber" type="text" id="contact_form_phone" class="contact_form_phone input_field" placeholder="Your phone number"/><br>
                            <form:input path="password" type="password" id="contact_form_name" class="contact_form_phone input_field" placeholder="Your password" required="required"/><br>                                 
                            <form:input path="confirmPassword" type="password" id="contact_form_name" class="contact_form_phone input_field" placeholder="Re-password" required="required"/>                                                                       
                            <div class="contact_form_button">
                                <button type="submit" class="button contact_submit_button">Register</button><br>
                                <a href="/TechShopTeam4.com/login">Login</a>
                            </div>
                        </form:form>
                    </div>   
                </div>
            </div>
            <div class="panel"></div>
        </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
