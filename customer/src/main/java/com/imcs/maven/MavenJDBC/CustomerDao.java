package com.imcs.maven.MavenJDBC;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {
	public boolean create(Customer customer) throws SQLException;

	public Customer view(int id) throws SQLException;

	public boolean update(Customer customer) throws SQLException;

	public boolean remove(int id) throws SQLException;

	List<Customer> findByName(String name) throws SQLException;
	
	public List<Customer> sortByName() throws SQLException;
	
	public Customer highestPurchase() throws SQLException;

}
