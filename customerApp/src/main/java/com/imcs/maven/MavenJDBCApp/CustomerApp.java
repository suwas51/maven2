
package com.imcs.maven.MavenJDBCApp;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.imcs.maven.MavenJDBC.Customer;
import com.imcs.maven.MavenJDBC.CustomerDao;
import com.imcs.maven.MavenJDBC.CustomerDaoImpl;

public class CustomerApp {
	static CustomerDao dao = new CustomerDaoImpl();

	public static void main(String[] args) throws SQLException {

		Scanner scanner = new Scanner(System.in);
		// List<Customer> customer = new Arraylist<>();

		int option;
		do {
			System.out.println("Option: \n1. Create \n2. View \n3. Update \n4. Delete \n5. FindByName \n6. SortByName \n7. HighestPurchaseCustomer \n8. Exit");
			System.out.println("Your option:");
			option = scanner.nextInt();

			switch (option) {
			case 1:
				try {

					System.out.println("Enter id:");
					int id = scanner.nextInt();

					System.out.println("Enter name:");
					String name = scanner.next();
					System.out.println("Enter  age:");
					int age = scanner.nextInt();

					System.out.println("Enter address");
					String address = scanner.next();
					System.out.println("Enter purchase:");
					float purchase = scanner.nextFloat();

					Customer customer = new Customer(id, name, age, address, purchase);
					boolean flag = dao.create(customer);
					if (flag)
						System.out.println("Succesfully added");
					else
						System.out.println("Not added");
				} catch (Exception e) {
					e.printStackTrace();
				}

				break;

			case 2:
				try {
					System.out.println("Enter customer id:");
					int id = scanner.nextInt();

					Customer customer = dao.view(id);
					System.out.println(customer);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					System.out.println("Enter id:");
					int id = scanner.nextInt();

					System.out.println("Enter name:");
					String name = scanner.next();
					System.out.println("Enter  age:");
					int age = scanner.nextInt();

					System.out.println("Enter address");
					String address = scanner.next();
					System.out.println("Enter purchase:");
					float purchase = scanner.nextFloat();

					Customer customer = new Customer(id, name, age, address, purchase);
					boolean flag = dao.update(customer);
					if (flag)
						System.out.println("Updated sucessfully");
					else
						System.out.println("Not updated");
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 4:
				try {
					System.out.println("Enter customer id:");
					int id = scanner.nextInt();

					boolean flag = dao.remove(id);
					if (flag)
						System.out.println("Delete successful");
					else
						System.out.println("Not delete");

				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case 5:
				try {
					System.out.println("Enter customer name:");
					String name = scanner.next();
					List<Customer> customer = dao.findByName(name);

					for (Customer cust : customer) {
						System.out.println(cust);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case 6:
				getSortingCust();
				break;
				
			case 7:
				getHighestPurchase();
				break;

			case 8:
				System.exit(0);

				break;

			default:
				System.out.println("invalid");
			}

		} while (option != 8);
	}

	private static void getHighestPurchase() throws SQLException {
		Customer customer = dao.highestPurchase();
		System.out.println(customer);
	}

	private static void getSortingCust() throws SQLException {
		List<Customer> customer = dao.sortByName();
		Collections.sort(customer, new Comparator<Customer>() {

			public int compare(Customer o1, Customer o2) {
				return (o1.getName().compareTo(o2.getName()));
			}
			
		});
		
		for (Customer cust : customer) {
			System.out.println(cust);
		}
		
		
	}

}