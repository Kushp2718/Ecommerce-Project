package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bean.Cart;
import com.bean.Wishlist;
import com.util.ProjectUtil;

public class CartDao {

	public static void addToCart(Cart c)
	{
		try {
			Connection conn=ProjectUtil.createConnection();
			String sql="insert into cart(uid,pid,product_qty,product_price,total_price) values(?,?,?,?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, c.getUid());
			pst.setInt(2, c.getPid());
			pst.setInt(3, c.getProduct_qty());
			pst.setInt(4, c.getProduct_price());
			pst.setInt(5, c.getTotal_price());
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void removeFromCart(Cart c)
	{
		try {
			Connection conn=ProjectUtil.createConnection();
			String sql="delete from cart where pid=? and uid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, c.getPid());
			pst.setInt(2, c.getUid());
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static List<Cart> getCartByUser(int uid)
	{
		String payment_status="pending";
		List<Cart> list=new ArrayList<Cart>();
		try {
			Connection conn=ProjectUtil.createConnection();
			String sql="select * from cart where uid=? and payment_status=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, uid);
			pst.setString(2, payment_status);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				Cart c=new Cart();
				c.setCid(rs.getInt("cid"));
				c.setUid(rs.getInt("uid"));
				c.setPid(rs.getInt("pid"));
				c.setProduct_qty(rs.getInt("product_qty"));
				c.setProduct_price(rs.getInt("product_price"));
				c.setTotal_price(rs.getInt("total_price"));
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static boolean checkCart(int uid,int pid)
	{
		boolean flag=false;
		try {
			Connection conn=ProjectUtil.createConnection();
			String sql="select * from cart where uid=? and pid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, uid);
			pst.setInt(2, pid);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	public static Cart getCartByCid(int cid)
	{
		Cart c=null;
		try {
			Connection conn=ProjectUtil.createConnection();
			String sql="select * from cart where cid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, cid);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				c=new Cart();
				c.setCid(rs.getInt("cid"));
				c.setUid(rs.getInt("uid"));
				c.setPid(rs.getInt("pid"));
				c.setProduct_qty(rs.getInt("product_qty"));
				c.setProduct_price(rs.getInt("product_price"));
				c.setTotal_price(rs.getInt("total_price"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	public static void updateCart(int product_qty,int total_price,int cid)
	{
		try {
			Connection conn=ProjectUtil.createConnection();
			String sql="update cart set product_qty=?,total_price=? where cid=? ";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, product_qty);
			pst.setInt(2, total_price);
			pst.setInt(3, cid);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void updateCartStatus(int cid)
	{
		String payment_status="paid";
		try {
			Connection conn=ProjectUtil.createConnection();
			String sql="update cart set payment_status=? where cid=? ";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, payment_status);
			pst.setInt(2, cid);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static List<Cart> getMyOrder(int uid)
	{
		String payment_status="paid";
		List<Cart> list=new ArrayList<Cart>();
		try {
			Connection conn=ProjectUtil.createConnection();
			String sql="select * from cart where uid=? and payment_status=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, uid);
			pst.setString(2, payment_status);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				Cart c=new Cart();
				c.setCid(rs.getInt("cid"));
				c.setUid(rs.getInt("uid"));
				c.setPid(rs.getInt("pid"));
				c.setProduct_qty(rs.getInt("product_qty"));
				c.setProduct_price(rs.getInt("product_price"));
				c.setTotal_price(rs.getInt("total_price"));
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
