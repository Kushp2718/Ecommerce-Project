<%@page import="com.dao.CartDao"%>
<%@page import="com.bean.Cart"%>
<%@page import="java.util.List"%>

<%
	
	int pid=Integer.parseInt(request.getParameter("pid"));
	int uid=Integer.parseInt(request.getParameter("uid"));
	Cart c=new Cart();
	c.setPid(pid);
	c.setUid(uid);
	CartDao.removeFromCart(c);
	List<Cart> list=CartDao.getCartByUser(uid);
	session.setAttribute("cart_count", list.size());
	response.sendRedirect("cart.jsp");
%>