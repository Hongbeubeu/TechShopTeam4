<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
   
    <!-- ============================================================== -->
    <!-- Main wrapper - style you can find in pages.scss -->
    <!-- ============================================================== -->
    <div id="main-wrapper">
        <!-- ============================================================== -->
        <!-- Topbar header - style you can find in pages.scss -->
        <!-- ============================================================== -->
        <header class="topbar">
            <nav class="navbar top-navbar navbar-expand-md navbar-light">
                <!-- ============================================================== -->
                <!-- Logo -->
                <!-- ============================================================== -->
                <div class="navbar-header">
                    <a class="navbar-brand" href="/TechShopTeam4.com/admin/${admin.id }">
                        <!-- Logo icon -->
                        <b>
                            <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
                            <!-- Dark Logo icon -->
                            <img src="<c:url value="/resources/backend/images/logo-icon.png" />" alt="homepage" class="dark-logo" />
                            <!-- Light Logo icon -->
                            <img src="<c:url value="/resources/backend/images/logo-light-icon.png" />" alt="homepage" class="light-logo" />
                        </b>
                        <!--End Logo icon -->
                        <!-- Logo text -->
                        <span>
                        <!-- dark Logo text -->
                        <img src="<c:url value="/resources/backend/images/logo-text.png" />" alt="homepage" class="dark-logo" />
                        <!-- Light Logo text -->    
                        <img src="<c:url value="/resources/backend/images/logo-light-text.png" />" class="light-logo" alt="homepage" />
                        </span>
                	</a>
                </div>
                <!-- ============================================================== -->
                <!-- End Logo -->
                <!-- ============================================================== -->
                <div class="navbar-collapse">
                    <!-- ============================================================== -->
                    <!-- toggle and nav items -->
                    <!-- ============================================================== -->
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item"> 
	                        <a class="nav-link nav-toggler hidden-md-up waves-effect waves-dark" href="">
	                        	<i class="fa fa-bars"></i>
                        	</a> 
                        </li>
                        <!-- ============================================================== -->
                        <!-- Search -->
                        <!-- ============================================================== -->
                        
                    </ul>
                    <!-- ============================================================== -->
                    <!-- User profile and search -->
                    <!-- ============================================================== -->
                    <ul class="navbar-nav my-lg-0">
                        <!-- ============================================================== -->
                        <!-- Profile -->
                        <!-- ============================================================== -->
                        <li class="nav-item dropdown u-pro">
                        	<a href = "/TechShopTeam4.com/admin/logout"><button class = "btn btn-danger">Logout</button></a>
                            <a class="nav-link dropdown-toggle waves-effect waves-dark profile-pic" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                            <img src="<c:url value="/resources/backend/images/users/5.jpg" />" alt="user" class="" /> 
	                            <span class="hidden-md-down">${admin.email } &nbsp;</span> 
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
    </body>
</html>
