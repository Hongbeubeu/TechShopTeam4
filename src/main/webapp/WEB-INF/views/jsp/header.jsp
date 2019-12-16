<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
	    <!-- Header -->
	
	    <header class="header">
	
	        <!-- Top Bar -->
	
	        <div class="top_bar">
	            <div class="container">
	                <div class="row">
	                    <div class="col d-flex flex-row">
	                        <div class="top_bar_contact_item"><div class="top_bar_icon"><img src="<c:url value="/resources/images/phone.png" />" alt=""></div>+84 972 511 617</div>
	                        <div class="top_bar_contact_item"><div class="top_bar_icon"><img src="<c:url value="/resources/images/mail.png" />" alt=""></div><a href="mailto:hongbeubeu@gmail.com">hongbeubeu@gmail.com</a></div>
	                        <div class="top_bar_content ml-auto">
	                            <div class="top_bar_user">
	                                <c:if test="${empty user || user.id == 0}">
		                                <div><a href="/TechShopTeam4.com/register">Register &nbsp;</a></div>
		                                <div><a href="/TechShopTeam4.com/login">Sign in &nbsp;</a></div>
	                                </c:if>
	                                <c:if test="${user.id > 0 }">
	                                <div><a href="/TechShopTeam4.com/register/${user.id }">Register &nbsp;</a></div>
		                                <div><a href="/TechShopTeam4.com/login/${user.id }">Sign in &nbsp;</a></div>
	                                	<div class="user_icon"><img src="<c:url value="/resources/images/user.svg" />" alt=""></div>
	                                	<div><a href="/TechShopTeam4.com/profile/${user.id }">${user.email }</a></div>
	                                </c:if>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>		
	        </div>
	
	        <!-- Header Main -->
	
	        <div class="header_main">
	            <div class="container">
	                <div class="row">
	
	                    <!-- Logo -->
	                    <div class="col-lg-2 col-sm-3 col-3 order-1">
	                        <div class="logo_container">
	                        	<c:if test = "${user.id > 0}">
	                            	<div class="logo"><a href="/TechShopTeam4.com/${user.id }">OneTech</a></div>
	                            </c:if>
	                            <c:if test = "${empty user || user.id == 0 }">
	                            	<div class="logo"><a href="/TechShopTeam4.com/">OneTech</a></div>
	                            </c:if>
	                        </div>
	                    </div>
	
	                    <!-- Search -->
	                    <div class="col-lg-6 col-12 order-lg-2 order-3 text-lg-left text-right">
	                        <div class="header_search">
	                            <div class="header_search_content">
	                                <div class="header_search_form_container">
	                                <c:if test="${user.id > 0 }">
	                                    <form action="/TechShopTeam4.com/${user.id }" class="header_search_form clearfix">
	                                    	<input type ="hidden" name = "userId" value = "${user.id }">
	                                        <input type="search" name = "productName" required="required" class="header_search_input" placeholder="Search for products...">
	                                        <div class="custom_dropdown">
	                                            <div class="custom_dropdown_list">
	                                                <span class="custom_dropdown_placeholder clc">All Categories</span>
	                                                <i class="fas fa-chevron-down"></i>
	                                                <ul class="custom_list clc">
	                                                </ul>
	                                            </div>
	                                        </div>
	                                        <button type="submit" class="header_search_button trans_300" value="Submit"><img src="<c:url value="/resources/images/search.png" />" alt=""></button>
	                                    </form>
	                                </c:if>
	                                <c:if test="${empty user || user.id == 0 }">
	                                    <form action="/TechShopTeam4.com/" class="header_search_form clearfix">
	                                        <input type="search" name = "productName" required="required" class="header_search_input" placeholder="Search for products...">
	                                        <div class="custom_dropdown">
	                                            <div class="custom_dropdown_list">
	                                                <span class="custom_dropdown_placeholder clc">All Categories</span>
	                                                <i class="fas fa-chevron-down"></i>
	                                                <ul class="custom_list clc">
	                                                </ul>
	                                            </div>
	                                        </div>
	                                        <button type="submit" class="header_search_button trans_300" value="Submit"><img src="<c:url value="/resources/images/search.png" />" alt=""></button>
	                                    </form>
	                                </c:if>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	
	                 	<!-- Cart -->
	                    <div class="col-lg-4 col-9 order-lg-3 order-2 text-lg-left text-right">
	                        <div class="wishlist_cart d-flex flex-row align-items-center justify-content-end">
	                            <div class="cart">
	                                <div class="cart_container d-flex flex-row align-items-center justify-content-end">
	                                    <div class="cart_icon">
	                                        <img src="<c:url value="/resources/images/cart.png" />" alt="">
	                                        <div class="cart_count"><span>0</span></div>
	                                    </div>
	                                    <div class="cart_content">
	                                    	<c:if test ="${user.id > 0 }">
	                                        	<div class="cart_text"><a href="/TechShopTeam4.com/cart/${user.id }">Cart</a></div>
											</c:if>	 
											<c:if test ="${empty user || user.id == 0}">
	                                        	<div class="cart_text"><a href="#">Cart</a></div>
											</c:if>	                                  
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	
	        <!-- Main Navigation -->
	
	        <nav class="main_nav">
	            <div class="container">
	                <div class="row">
	                    <div class="col">
	
	                        <div class="main_nav_content d-flex flex-row">
	
	                            <!-- Categories Menu -->
	
	                            <div class="cat_menu_container">
	                                <div class="cat_menu_title d-flex flex-row align-items-center justify-content-start">
	                                    <div class="cat_burger"><span></span><span></span><span></span></div>
	                                    <div class="cat_menu_text">categories</div>
	                                </div>
	
	                                <ul class="cat_menu">
	                                    <li><a href="#">Computers & Laptops <i class="fas fa-chevron-right ml-auto"></i></a></li>
	                                    <li><a href="#">Cameras & Photos<i class="fas fa-chevron-right"></i></a></li>
	                                    <li><a href="#">Smart Phone<i class="fas fa-chevron-right"></i></a></li>
	                                </ul>
	                            </div>
	
	                            <!-- Main Nav Menu -->
	
	                            <div class="main_nav_menu ml-auto">
	                                <ul class="standard_dropdown main_nav_dropdown">
	                                	<c:if test ="${user.id > 0 }">
	                                    	<li><a href="/TechShopTeam4.com/${user.id }">Home<i class="fas fa-chevron-down"></i></a></li>
	                                	</c:if>
	                                	<c:if test ="${empty user || user.id == 0 }">
	                                    	<li><a href="/TechShopTeam4.com/">Home<i class="fas fa-chevron-down"></i></a></li>
	                                	</c:if>
	                                </ul>
	                            </div>
	
	                            <!-- Menu Trigger -->
	
	                            <div class="menu_trigger_container ml-auto">
	                                <div class="menu_trigger d-flex flex-row align-items-center justify-content-end">
	                                    <div class="menu_burger">
	                                        <div class="menu_trigger_text">menu</div>
	                                        <div class="cat_burger menu_burger_inner"><span></span><span></span><span></span></div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </nav>
	    </header>
    </body>
</html>