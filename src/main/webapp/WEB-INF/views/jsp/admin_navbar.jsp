<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <!-- ============================================================== -->
        <!-- End Topbar header -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
        <aside class="left-sidebar">
            <!-- Sidebar scroll-->
            <div class="scroll-sidebar">
                <!-- Sidebar navigation-->
                <nav class="sidebar-nav">
                    <ul id="sidebarnav">
                        <li> 
                        	<a class="waves-effect waves-dark" href="/TechShopTeam4.com/admin/${admin.id }" aria-expanded="false">
                        		<i class="fa fa-tachometer"></i>
                        		<span class="hide-menu">Dashboard</span>
                        	</a>
                        </li>
                        <li> 
                        	<a class="waves-effect waves-dark" href="/TechShopTeam4.com/admin/products/${admin.id }" aria-expanded="false">
                        		<i class="fa fa-user-circle-o"></i>
                        		<span class="hide-menu">Products</span>
                        	</a>
                        </li>
                        <li> 
                        	<a class="waves-effect waves-dark" href="/TechShopTeam4.com/admin/users/${admin.id }" aria-expanded="false">
	                        	<i class="fa fa-table"></i>
	                        	<span class="hide-menu">Users</span>
	                        </a>
                        </li>
                        <li> 
                        	<a class="waves-effect waves-dark" href="/TechShopTeam4.com/admin/orders/${admin.id }" aria-expanded="false">
	                        	<i class="fa fa-table"></i>
	                        	<span class="hide-menu">Orders</span>
	                        </a>
                        </li>
                    </ul>    
                </nav>
                <!-- End Sidebar navigation -->
            </div>
            <!-- End Sidebar scroll-->
        </aside>
    </body>
</html>
