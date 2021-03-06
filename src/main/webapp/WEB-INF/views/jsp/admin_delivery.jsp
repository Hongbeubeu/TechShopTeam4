<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="<c:url value="/resources/backend/images/favicon.png" />">
    <title>ORDERS</title>
    <!-- Bootstrap Core CSS -->
    <link href="<c:url value="/resources/backend/node_modules/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/backend/node_modules/perfect-scrollbar/css/perfect-scrollbar.css" />" rel="stylesheet">
    <!-- This page CSS -->
    <!-- chartist CSS -->
    <link href="<c:url value="/resources/backend/node_modules/node_modules/morrisjs/morris.css" />" rel="stylesheet">
    <!--c3 CSS -->
    <link href="<c:url value="/resources/backend/node_modules/node_modules/c3-master/c3.min.css" />" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="<c:url value="/resources/backend/css/style.css" />" rel="stylesheet">
    <!-- Dashboard 1 Page CSS -->
    <link href="<c:url value="/resources/css/pages/dashboard1.css" />" rel="stylesheet">
    <!-- You can change the theme colors from here -->
    <link href="<c:url value="/resources/css/colors/default.css" />" id="theme" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/responsive.css" />">
    
</head>

<body class="fix-header fix-sidebar card-no-border">
    <jsp:include page="admin_header.jsp"></jsp:include>
    <jsp:include page="admin_navbar.jsp"></jsp:include>
        <div class="page-wrapper">
            <!-- ============================================================== -->
            <!-- Container fluid  -->
            <!-- ============================================================== -->
            <div class="container-fluid">
                <!-- ============================================================== -->
                <!-- Bread crumb and right sidebar toggle -->
                <!-- ============================================================== -->
                <div class="row page-titles">
                    <div class="col-md-5 align-self-center">
                        <h3 class="text-themecolor">Delivery</h3>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- End Bread crumb and right sidebar toggle -->
                <!-- ============================================================== -->
                <!-- ============================================================== -->
                <!-- Sales Chart and browser state-->
                <!-- ============================================================== -->
                <div class="row">
                    <!-- Column -->
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="d-flex no-block">
                                    <div>
                                        <h4 class="card-title m-b-0">Delivery bill</h4>
                                       	<h5>${delivery.productName }</h5>
                                    </div>
                                </div>	
                               	<div class = "row">
                               		<div class = "col">
                               			<p>Order Id:</p>
                               			<p>Name: </p>
                               			<p>Phone Number:</p>
                               			<p>Address:</p>
                               			<p>Date:</p>
                               			<p>Total Price:</p>
                               		</div>
                               		<div class = "col">
                               			<p>${delivery.orderId }</p>
                               			<p>${delivery.lastName } ${delivery.firstName }</p>
                               			<p>${delivery.phoneNumber }</p>
                               			<p>${delivery.address }</p>
                               			<p>${delivery.date }</p>
                               			<p>${delivery.price }</p>
                               		</div>
                               	</div>
                               	<a href="/TechShopTeam4.com/admin/deliveryOrder/${admin.id }/${delivery.orderId }/${delivery.productId}"><button class = "btn btn-primary">delivery</button></a>
                            </div>
                        </div>
                    </div>
                </div>
    		</div>
    		<div class = "card-body" ></div>
            <div class = "card-body" ></div>
           
           
    <jsp:include page="admin_footer.jsp"></jsp:include>
</body>

</html>