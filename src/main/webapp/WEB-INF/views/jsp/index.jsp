<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>OneTech</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="OneTech shop project">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/bootstrap4/bootstrap.min.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/fontawesome-free-5.0.1/css/fontawesome-all.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/OwlCarousel2-2.2.1/owl.carousel.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/OwlCarousel2-2.2.1/owl.theme.default.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/OwlCarousel2-2.2.1/animate.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/slick-1.8.0/slick.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/main_styles.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/responsive.css" />">

    </head>
    <body>
        <div class="super_container">
        
            <jsp:include page="header.jsp"></jsp:include>
            <jsp:include page="banner.jsp"></jsp:include>
           
            <!-- Deals of the week -->

            <div class="deals_featured">
                <div class="container">
                    <div class="row">
                        <div class="col d-flex flex-lg-row flex-column align-items-center justify-content-start">

                            <!-- Deals -->

                            <div class="deals">
                                <div class="deals_title">Deals of the Week</div>
                                <div class="deals_slider_container">

                                    <!-- Deals Slider -->
                                    <div class="owl-carousel owl-theme deals_slider">

                                        <!-- Deals Item -->
                                        <div class="owl-item deals_item">
                                            <div class="deals_image"><img src="<c:url value="/resources/product_images/Dell XPS 15 7590_1.jpg" />" alt=""></div>
                                            <div class="deals_content">
                                                <div class="deals_info_line d-flex flex-row justify-content-start">
                                                    <div class="deals_item_category"><a href="#">Laptop</a></div>
                                                    <div class="deals_item_price_a ml-auto">$ 29999000</div>
                                                </div>
                                                <div class="deals_info_line d-flex flex-row justify-content-start">
                                                    <div class="deals_item_name">Dell XPS 15 7590</div>
                                                    <div class="deals_item_price ml-auto">$ 29599000</div>
                                                </div>
                                                <div class="available">
                                                    <div class="available_line d-flex flex-row justify-content-start">
                                                        <div class="available_title">Available: <span>6</span></div>
                                                        <div class="sold_title ml-auto">Already sold: <span>28</span></div>
                                                    </div>
                                                    <div class="available_bar"><span style="width:17%"></span></div>
                                                </div>
                                                <div class="deals_timer d-flex flex-row align-items-center justify-content-start">
                                                    <div class="deals_timer_title_container">
                                                        <div class="deals_timer_title">Hurry Up</div>
                                                        <div class="deals_timer_subtitle">Offer ends in:</div>
                                                    </div>
                                                    <div class="deals_timer_content ml-auto">
                                                        <div class="deals_timer_box clearfix" data-target-time="">
                                                            <div class="deals_timer_unit">
                                                                <div id="deals_timer1_hr" class="deals_timer_hr"></div>
                                                                <span>hours</span>
                                                            </div>
                                                            <div class="deals_timer_unit">
                                                                <div id="deals_timer1_min" class="deals_timer_min"></div>
                                                                <span>mins</span>
                                                            </div>
                                                            <div class="deals_timer_unit">
                                                                <div id="deals_timer1_sec" class="deals_timer_sec"></div>
                                                                <span>secs</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="deals_slider_nav_container">
                                    <div class="deals_slider_prev deals_slider_nav"><i class="fas fa-chevron-left ml-auto"></i></div>
                                    <div class="deals_slider_next deals_slider_nav"><i class="fas fa-chevron-right ml-auto"></i></div>
                                </div>
                            </div>

                            <!-- Featured -->
                            <div class="featured">
                                <div class="tabbed_container">
                                    <div class="tabs">
                                        <ul class="clearfix">
                                            <li class="active">Featured</li>                                        
                                        </ul>
                                        <div class="tabs_line"><span></span></div>
                                    </div>

                                    <!-- Product Panel -->
                                    <div class="product_panel panel active">
                                        <div class="featured_slider slider">

                                            <!-- Slider Item -->
                                            <c:forEach var="p" items="${products}">
                                            <div class="featured_slider_item">
                                                <div class="border_active"></div>
                                                <div class="product_item discount d-flex flex-column align-items-center justify-content-center text-center">
                                                    <div class="product_image d-flex flex-column align-items-center justify-content-center"><img src="<c:url value="/resources/product_images/${p.imgPath }" />" alt=""></div>
                                                    <div class="product_content">
                                                        <div class="product_price discount">$ ${p.price }</div>
                                                        <div class="product_name"><div><a href="#">${p.name}</a></div></div>
                                                        <div class="product_extras">
                                                            <button class="product_cart_button">Add to Cart</button>
                                                        </div>
                                                    </div>
                                                    <div class="product_fav"><i class="fas fa-heart"></i></div>
                                                </div>
                                            </div>
                                            </c:forEach>
                                        </div>
                                        <div class="featured_slider_dots_cover"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>