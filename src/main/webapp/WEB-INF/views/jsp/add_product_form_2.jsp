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
    <title>ADD PRODUCT</title>
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
	<c:url value = "/admin/addProduct/${admin.id }" var="addProduct"/>
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
                        <h3 class="text-themecolor">ADD PRODUCT</h3>${product.type }
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
                                        <h4 style="padding:20px;" class="card-title m-b-0">Select Product Brand</h4>
                                    </div>
                                </div>	
                                <form:form action="${addProduct }" method = "POST" modelAttribute="product">
                                	<form:input path="type" value = "${product.type }" type = "hidden"/>
                                	<form:select path="brand" class="browser-default custom-select">
	                                	<c:forEach var="brand" items= "${brand }">
	                                		<form:option value = "${brand.brand }"/>
	                                	</c:forEach>
                                	</form:select>
                                	<form:button type="submit" class ="btn btn-primary">Next</form:button>
                                </form:form>
                            </div>
                            <div class = "card-body" ></div>
                            <div class = "card-body" ></div>
                            <div class = "card-body" ></div>
                            <div class = "card-body" ></div>
                            <div class = "card-body" ></div>
                            <div class = "card-body" ></div>
                            <div class = "card-body" ></div>
                            
                        </div>
                    </div>
                </div>
                </div>
                
                
                
    <jsp:include page="admin_footer.jsp"></jsp:include>
</body>

</html>