
<%@page import="com.bean.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Bootshop online Shopping cart</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
<!--Less styles -->
   <!-- Other Less css file //different less files has different color scheam
	<link rel="stylesheet/less" type="text/css" href="themes/less/simplex.less">
	<link rel="stylesheet/less" type="text/css" href="themes/less/classified.less">
	<link rel="stylesheet/less" type="text/css" href="themes/less/amelia.less">  MOVE DOWN TO activate
	-->
	<!--<link rel="stylesheet/less" type="text/css" href="themes/less/bootshop.less">
	<script src="themes/js/less.js" type="text/javascript"></script> -->
	
<!-- Bootstrap style --> 
    <link id="callCss" rel="stylesheet" href="themes/bootshop/bootstrap.min.css" media="screen"/>
    <link href="themes/css/base.css" rel="stylesheet" media="screen"/>
<!-- Bootstrap style responsive -->	
	<link href="themes/css/bootstrap-responsive.min.css" rel="stylesheet"/>
	<link href="themes/css/font-awesome.css" rel="stylesheet" type="text/css">
<!-- Google-code-prettify -->	
	<link href="themes/js/google-code-prettify/prettify.css" rel="stylesheet"/>
<!-- fav and touch icons -->
    <link rel="shortcut icon" href="themes/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="themes/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="themes/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="themes/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="themes/images/ico/apple-touch-icon-57-precomposed.png">
	<style type="text/css" id="enject"></style>
  </head>
<body>
<%
	User u=null;
	if(session!=null)
	{
		if(session.getAttribute("u")!=null)
		{
			u=(User)session.getAttribute("u");
		}
	}
%>
<div id="header">
<div class="container">
<div id="welcomeLine" class="row">
	<div class="span6">Welcome!<strong> User</strong></div>
	<div class="span6">
	<div class="pull-right">
		<a href="cart.jsp"><span class="btn btn-mini btn-primary"><i class="icon-shopping-cart icon-white"></i> [ <%=session.getAttribute("cart_count") %> ] Itemes in your cart </span> </a> 
	</div>
	</div>
</div>
<!-- Navbar ================================================== -->
<div id="logoArea" class="navbar">
<a id="smallScreen" data-target="#topMenu" data-toggle="collapse" class="btn btn-navbar">
	<span class="icon-bar"></span>
	<span class="icon-bar"></span>
	<span class="icon-bar"></span>
</a>
  <div class="navbar-inner">
    <a class="brand" href="index.jsp"><img src="themes/images/logo.png" alt="Bootsshop"/></a>
		
    <ul id="topMenu" class="nav pull-right">
	 <li class=""><a href="special_offer.jsp">Specials Offer</a></li>
	 <li class=""><a href="normal.jsp">Delivery</a></li>
	 <li class=""><a href="contact.jsp">Contact</a></li>
	 <%
	 	if(u==null)
	 	{
	 %>
	 <li class=""><a href="login.jsp">Login</a></li>
	 <li class=""><a href="register.jsp">Sign Up</a></li>
	 <%
	 	}
	 	else
	 	{
	 %>
	 <li class=""><a href="logout.jsp">Logout(<%=u.getFname() %>)</a></li>
	 <li class=""><a href="change_password.jsp">Change Password</a></li>
	 <%
	 	int count=Integer.parseInt(session.getAttribute("wishlist_count").toString());
	 %>
	 <li class=""><a href="wishlist.jsp">Wishlist<%if(count>0){ %>(<%=session.getAttribute("wishlist_count") %>)<%} %></a></li>
	 <%} %>
	 <li class=""><a href="myorder.jsp">My Order</a></li>
    </ul>
  </div>
</div>
</div>
</div>
<!-- Header End====================================================================== -->

</body>
</html>