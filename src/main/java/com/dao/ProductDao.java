package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bean.Product;
import com.util.ProjectUtil;

public class ProductDao {

	public static void addProduct(Product p)
	{
		try {
			Connection conn=ProjectUtil.createConnection();
			String sql="insert into product(product_category,product_name,product_price,product_desc,product_image,uid) values(?,?,?,?,?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, p.getPcid());
			pst.setString(2, p.getProduct_name());
			pst.setInt(3, p.getProduct_price());
			pst.setString(4, p.getProduct_desc());
			pst.setString(5, p.getProduct_image());
			pst.setInt(6, p.getUid());
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static List<Product> getProductsBySeller(int id)
	{
		List<Product> list=new ArrayList<Product>();
		try {
			Connection conn=ProjectUtil.createConnection();
			String sql="select * from product where uid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				Product p=new Product();
				p.setId(rs.getInt("pid"));
				p.setPcid(rs.getInt("product_category"));
				p.setProduct_desc(rs.getString("product_desc"));
				p.setProduct_image(rs.getString("product_image"));
				p.setProduct_name(rs.getString("product_name"));
				p.setProduct_price(rs.getInt("product_price"));
				p.setUid(rs.getInt("uid"));
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static Product getProductPid(int pid)
	{
		Product p=null;
		try {
			Connection conn=ProjectUtil.createConnection();
			String sql="select * from product where pid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, pid);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				p=new Product();
				p.setId(rs.getInt("pid"));
				p.setPcid(rs.getInt("product_category"));
				p.setProduct_desc(rs.getString("product_desc"));
				p.setProduct_image(rs.getString("product_image"));
				p.setProduct_name(rs.getString("product_name"));
				p.setProduct_price(rs.getInt("product_price"));
				p.setUid(rs.getInt("uid"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	public static List<Product> getAllProducts()
	{
		List<Product> list=new ArrayList<Product>();
		try {
			Connection conn=ProjectUtil.createConnection();
			String sql="select * from product";
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				Product p=new Product();
				p.setId(rs.getInt("pid"));
				p.setPcid(rs.getInt("product_category"));
				p.setProduct_desc(rs.getString("product_desc"));
				p.setProduct_image(rs.getString("product_image"));
				p.setProduct_name(rs.getString("product_name"));
				p.setProduct_price(rs.getInt("product_price"));
				p.setUid(rs.getInt("uid"));
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
