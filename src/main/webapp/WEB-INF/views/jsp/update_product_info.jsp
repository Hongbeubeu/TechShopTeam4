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
    <title>UPDATE PRODUCT</title>
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
    <c:url value = "/admin/doUpdate/${product.productId }" var="update"/>
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
                        <h3 class="text-themecolor">UPDATE PRODUCT</h3><p>${product.type } ${product.brand }</p>
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
                                        <h4 style="padding:20px;" class="card-title m-b-0">Add Product Description</h4>
                                    </div>
                                </div>	
                                <form:form action="${update }" method = "POST" modelAttribute="product">
                                	<form:input type = "hidden" path="productId" value="${product.productId }"/>
                                	<p>Name</p>
                                	<form:input style="padding:15px; margin: 15px" path="name" class ="form-control" value = "${product.name }" placeholder="Product Name" required="required"/><br>
                                	<p>Chip</p>
                                	<form:input style="padding:15px; margin: 15px" path="chip" class ="form-control" value = "${product.chip }" placeholder="Chip" required="required"/><br>
                                	<p>Ram</p>
                                	<form:input style="padding:15px; margin: 15px" path="ram" class ="form-control" value = "${product.ram }" placeholder="Ram" required="required"/><br>
                                	<p>Vga</p>
                                	<form:input style="padding:15px; margin: 15px" path="vga" class ="form-control" value = "${product.vga }" placeholder="Vga" required="required"/><br>
                                	<p>Display</p>
                                	<form:input style="padding:15px; margin: 15px" path="display" class ="form-control" value = "${product.display }" placeholder="Display" required="required"/><br>
                                	<p>Camera</p>
                                	<form:input style="padding:15px; margin: 15px" path="camera" class ="form-control" value = "${product.camera }" placeholder="Camera" required="required"/><br>
                                	<p>Hard Disk</p>
                                	<form:input style="padding:15px; margin: 15px" path="hardDisk" class ="form-control" value = "${product.hardDisk }" placeholder="Hard Disk" required="required"/><br>
                                	<p>Key Board</p>
                                	<form:input style="padding:15px; margin: 15px" path="keyboard" class ="form-control" value = "${product.keyboard }" placeholder="Key Board" required="required"/><br>
                                	<p>Port</p>
                                	<form:input style="padding:15px; margin: 15px" path="port" class ="form-control" value = "${product.port }" placeholder="Port" required="required"/><br>
                                	<p>Battery</p>
                                	<form:input style="padding:15px; margin: 15px" path="battery" class ="form-control" value = "${product.battery }" placeholder="Battery" required="required"/><br>
                                	<p>Opera System</p>
                                	<form:input style="padding:15px; margin: 15px" path="operaSystem" class ="form-control" value = "${product.operaSystem }" placeholder="Opera System" required="required"/><br>
                                	<p>Quantity</p>
                                	<form:input style="padding:15px; margin: 15px" path="quantity" class ="form-control" value = "${product.quantity }" placeholder="Quantity" required="required"/><br>
                                	<p>Price</p>
                                	<form:input style="padding:15px; margin: 15px" path="price" class ="form-control" value = "${product.price }" placeholder="Price" required="required"/><br>
                                	<form:button style="padding:15px; margin: 15px; width: 100%" type="submit" class ="btn btn-primary">UPDATE</form:button>
                                </form:form>
                            </div>  
                        </div>
                    </div>
                </div>
                </div>     
    <jsp:include page="admin_footer.jsp"></jsp:include>
</body>

</html>