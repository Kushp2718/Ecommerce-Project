<%@page import="com.dao.CartDao"%>
<%@page import="com.bean.Cart"%>
<%
	int cid=Integer.parseInt(request.getParameter("cid"));
	int product_qty=Integer.parseInt(request.getParameter("product_qty"));
	Cart c=CartDao.getCartByCid(cid);
	int product_price=c.getProduct_price();
	int total_price=product_qty*product_price;
	CartDao.updateCart(product_qty, total_price, cid);
	response.sendRedirect("cart.jsp");

%>