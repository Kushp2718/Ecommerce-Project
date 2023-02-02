package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Cart;
import com.bean.User;
import com.bean.Wishlist;
import com.dao.CartDao;
import com.dao.UserDao;
import com.dao.WishlistDao;
import com.service.Services;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		
		if(action.equalsIgnoreCase("register"))
		{
			boolean flag=UserDao.checkEmail(request.getParameter("email"));
			if(flag==false)
			{
				User u=new User();
				u.setFname(request.getParameter("fname"));
				u.setLname(request.getParameter("lname"));
				u.setEmail(request.getParameter("email"));
				u.setMobile(request.getParameter("mobile"));
				u.setAddress(request.getParameter("address"));
				u.setPassword(request.getParameter("password"));
				u.setUsertype(request.getParameter("usertype"));
				UserDao.insertUser(u);
				response.sendRedirect("login.jsp");
			}
			else
			{
				request.setAttribute("msg", "Email Already Registered");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("login"))
		{
			User u=UserDao.login(request.getParameter("email"), request.getParameter("password"));
			if(u!=null)
			{
				if(u.getUsertype().equals("user"))
				{
					HttpSession session=request.getSession();
					session.setAttribute("u", u);
					List<Wishlist> list=WishlistDao.getWishlistByUser(u.getId());
					session.setAttribute("wishlist_count", list.size());
					List<Cart> list1=CartDao.getCartByUser(u.getId());
					session.setAttribute("cart_count", list1.size());
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				else
				{
					HttpSession session=request.getSession();
					session.setAttribute("u", u);
					request.getRequestDispatcher("seller_index.jsp").forward(request, response);
				}
			}
			else
			{
				request.setAttribute("msg", "Email Or Password Is Incorrect");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("change password"))
		{
			HttpSession session=request.getSession();
			User u=(User) session.getAttribute("u");
			if(u.getPassword().equals(request.getParameter("old_password")))
			{
				if(request.getParameter("new_password").equals(request.getParameter("cnew_password")))
				{
					UserDao.changePassword(u.getEmail(), request.getParameter("new_password"));
					response.sendRedirect("logout.jsp");
				}
				else
				{
					if(u.getUsertype().equals("user"))
					{
						request.setAttribute("msg", "New Password & Confrim New Password Does Not Matched");
						request.getRequestDispatcher("change_password.jsp").forward(request, response);
					}
					else
					{
						request.setAttribute("msg", "New Password & Confrim New Password Does Not Matched");
						request.getRequestDispatcher("seller_change_password.jsp").forward(request, response);
					}
				}
			}
			else
			{
				if(u.getUsertype().equals("user"))
				{
					request.setAttribute("msg", "Old Password Does Not Matched");
					request.getRequestDispatcher("change_password.jsp").forward(request, response);
				}
				else
				{
					request.setAttribute("msg", "Old Password Does Not Matched");
					request.getRequestDispatcher("seller_change_password.jsp").forward(request, response);
				}
			}
			
		}
		else if(action.equalsIgnoreCase("send OTP"))
		{
			String email=request.getParameter("email");
			System.out.println(email);
			boolean flag=UserDao.checkEmail(email);
			System.out.println(flag);
			if(flag==true)
			{
				int min=1000;
				int max=9999;
				int otp = (int)(Math.random()*(max-min+1)+min);
				Services.sendMail(email, otp);
				request.setAttribute("email", email);
				request.setAttribute("otp", otp);
				request.getRequestDispatcher("otp.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("msg", "Email Not Registered");
				request.getRequestDispatcher("forgot_password.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("verify otp")) 
		{
			String email=request.getParameter("email");
			String otp=request.getParameter("otp");
			String uotp=request.getParameter("uotp");
			
			if(otp.equals(uotp))
			{
				request.setAttribute("email", email);
				request.getRequestDispatcher("new_password.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("msg", "Invalid OTP");
				request.setAttribute("email", email);
				request.setAttribute("otp", otp);
				request.getRequestDispatcher("otp.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("update password"))
		{
			String email=request.getParameter("email");
			String np=request.getParameter("new_password");
			String cnp=request.getParameter("cnew_password");
			
			if(np.equals(cnp))
			{
				UserDao.changePassword(email, np);
				request.setAttribute("msg", "Password Updated Successfully");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("msg", "New Password & Confirm New Password Does Not Matched");
				request.setAttribute("email", email);
				request.getRequestDispatcher("new_password.jsp").forward(request, response);
			}
		}
	}

}
