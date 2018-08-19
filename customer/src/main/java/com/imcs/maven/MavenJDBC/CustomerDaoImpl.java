package com.imcs.maven.MavenJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

	public static void forName() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Create connection
	public static Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "root");
		return con;
	}

	// create customer from resultSet
	public Customer prepareCustomer(ResultSet rs) throws SQLException {
		int id = rs.getInt(1);
		String name = rs.getString(2);
		int age = rs.getInt(3);
		String address = rs.getString(4);
		float purchase = rs.getFloat(5);

		Customer cust = new Customer(id, name, age, address, purchase);
		return cust;
	}

	@Override
	public boolean create(Customer customer) throws SQLException {
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						"insert into Customer(id , name, age, address, purchase) values(?,?,?,?,?)")) {
			ps.setInt(1, customer.getId());
			ps.setString(2, customer.getName());
			ps.setInt(3, customer.getAge());
			ps.setString(4, customer.getAddress());
			ps.setFloat(5, customer.getPurchase());

			int noOfrowsAdded = ps.executeUpdate();
			return noOfrowsAdded > 0 ? true : false;
		}
	}

	@Override
	public Customer view(int id) throws SQLException {
		try (Connection con = getConnection();
				PreparedStatement ps = con
						.prepareStatement("select id , name, age, address, purchase from Customer where id = ?")) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return prepareCustomer(rs);
		}
		return null;
	}

	@Override
	public boolean update(Customer customer) throws SQLException {
		try (Connection con = getConnection();
				PreparedStatement ps = con
						.prepareStatement("update Customer set name=?, age=?, address=?, purchase=? where id =?")) {
			ps.setString(1, customer.getName());
			ps.setInt(2, customer.getAge());
			ps.setString(3, customer.getAddress());
			ps.setFloat(4, customer.getPurchase());
			ps.setInt(5, customer.getId());

			int noOfrowsAdded = ps.executeUpdate();
			return noOfrowsAdded > 0 ? true : false;

		}
	}

	@Override
	public boolean remove(int id) throws SQLException {
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("delete from Customer where id = ?")) {

			ps.setInt(1, id);
			int noOfrowsAdded = ps.executeUpdate();
			return noOfrowsAdded > 0 ? true : false;
		}

	}

	@Override
	public List<Customer> findByName(String name) throws SQLException {
		try (Connection con = getConnection();
				PreparedStatement ps = con
						.prepareStatement("select id, name, age, address, purchase from Customer where name =?")) {
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			List<Customer> customer = new ArrayList<>();
			while (rs.next()) {
				customer.add(prepareCustomer(rs));
			}

			return customer;

		}
	}

	@Override
	public List<Customer> sortByName() throws SQLException {
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("select * from customer")){
			ResultSet rs = ps.executeQuery();

			List<Customer> customer = new ArrayList<>();
			while (rs.next()) {
				customer.add(prepareCustomer(rs));
			}

			return customer;
		}
			
	}

	@Override
	public Customer highestPurchase() throws SQLException {
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("select * from customer order by purchase desc limit 1")){

			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return prepareCustomer(rs);
		}
		return null;
	}

}

