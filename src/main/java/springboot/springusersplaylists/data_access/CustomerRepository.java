package springboot.springusersplaylists.data_access;


import springboot.springusersplaylists.models.*;

import java.sql.*;
import java.util.ArrayList;

public class CustomerRepository {
    private String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
    private Connection conn = null;


    public ArrayList<CustomerDTO> getAllCustomers() {
        ArrayList<CustomerDTO> customers = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement prep = conn.prepareStatement("SELECT customerId, firstName, lastName, country, postalCode, phone FROM Customer");
            ResultSet set = prep.executeQuery();
            while (set.next()) {
                customers.add(new CustomerDTO(

                        set.getInt("customerId"),
                        set.getString("firstName"),
                        set.getString("lastName"),
                        set.getString("country"),
                        set.getString("postalCode"),
                        set.getString("phone")
                ));
            }
            System.out.println("Get all customers went well!");


        } catch (Exception exception) {
            System.out.println(exception.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
        return customers;
    }

    public Boolean addNewCustomer(Customer customer) {
        Boolean success = false;
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement prep = conn.prepareStatement(
                    "INSERT INTO customer( customerId, firstName, lastName, company, address, city, state, country, postalCode, phone, fax, email )" +
                            " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            prep.setInt(1, customer.getCustomerId());
            prep.setString(2, customer.getFirstName());
            prep.setString(3, customer.getLastName());
            prep.setString(4, customer.getCompany());
            prep.setString(5, customer.getAddress());
            prep.setString(6, customer.getCity());
            prep.setString(7, customer.getState());
            prep.setString(8, customer.getCountry());
            prep.setString(9, customer.getPostalCode());
            prep.setString(10, customer.getPhone());
            prep.setString(11, customer.getFax());
            prep.setString(12, customer.getEmail());

            int result = prep.executeUpdate();
            success = (result != 0); // if res = 1; true


            System.out.println("Customer added!!");


        } catch (Exception exception) {
            System.out.println(exception.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
        return success;
    }

    public Boolean updateCustomer(Customer customer) {
        Boolean success = false;
        try {

            conn = DriverManager.getConnection(URL);
            PreparedStatement prep =
                    conn.prepareStatement("UPDATE customer SET firstName=?, lastName=?, country=?, postalCode=?, phone=?, email=?" +
                            " WHERE customerId=?");

            prep.setString(1, customer.getFirstName());
            prep.setString(2, customer.getLastName());
            prep.setString(3, customer.getCountry());
            prep.setString(4, customer.getPostalCode());
            prep.setString(5, customer.getPhone());
            prep.setString(6, customer.getEmail());
            prep.setInt(7, customer.getCustomerId());

            int result = prep.executeUpdate();
            success = (result != 0); // if res = 1; true

            System.out.println("Update went well!");

        } catch (Exception exception) {
            System.out.println(exception.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
        return success;
    }

    public ArrayList<CustomersByCountry> getCustomersByCountry() {
        ArrayList<CustomersByCountry> numberOfCustomers = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement prep =
                    conn.prepareStatement("SELECT Country, COUNT (*) as Quantity FROM Customer GROUP BY Country ORDER BY COUNT(*) DESC;");
            ResultSet set = prep.executeQuery();
            while (set.next()) {
                numberOfCustomers.add(new CustomersByCountry(
                        set.getString("Country"),
                        set.getInt("Quantity")
                ));

                System.out.println("Get customer by country went well!");
            }
        } catch (Exception exception) {
            System.out.println(exception.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
        return numberOfCustomers;
    }

    public ArrayList<CustomersHighestSpenders> getCustomersHighestSpenders() {
        ArrayList<CustomersHighestSpenders> mostWastefulCustomers = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement prep =
                    conn.prepareStatement("SELECT Customer.FirstName, Customer.LastName, Invoice.Total\n" +
                            "FROM Customer\n" +
                            "    JOIN Invoice ON Customer.CustomerId = Invoice.CustomerId ORDER BY total DESC;");
            ResultSet set = prep.executeQuery();
            while (set.next()) {
                mostWastefulCustomers.add(new CustomersHighestSpenders(

                        set.getString(1),
                        set.getString(2),
                        set.getDouble(3)));

                System.out.println("Get highest spent customer went well!");
            }
        } catch (Exception exception) {
            System.out.println(exception.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
        return mostWastefulCustomers;
    }


    public ArrayList<CustomersMostPopularGenre> getCustomersMostPopularGenre(int customerId) {

        ArrayList<CustomersMostPopularGenre> mostPopularGenre = new ArrayList<>();
        var filteredPopularGenres = new ArrayList<CustomersMostPopularGenre>();
        try {

            conn = DriverManager.getConnection(URL);
            PreparedStatement prep =
                    conn.prepareStatement("    SELECT Customer.FirstName, Customer.LastName, Genre.Name, COUNT (*) AS Total FROM InvoiceLine\n" +
                            "JOIN Track ON InvoiceLine.TrackId = Track.TrackId\n" +
                            "JOIN Genre ON Track.GenreId = Genre.GenreId\n" +
                            "JOIN Invoice ON InvoiceLine.InvoiceId = Invoice.InvoiceId\n" +
                            "JOIN Customer ON Invoice.CustomerId = Customer.CustomerId\n" +
                            "WHERE Customer.CustomerId = ?\n" +
                            "GROUP BY Genre.Name\n" +
                            "ORDER BY Total DESC LIMIT 2;");

            prep.setInt(1, customerId);
            ResultSet set = prep.executeQuery();

            while (set.next()) {
                mostPopularGenre.add(new CustomersMostPopularGenre(

                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getInt(4)
                ));


                System.out.println("Get highest spent customer went well!");
            }
            var max = 0;

            max = Math.max(max, mostPopularGenre.get(0).getMostPopularGenre());
            ;
            for (var genre : mostPopularGenre
            ) {
                if (max == genre.getMostPopularGenre()) {
                    filteredPopularGenres.add(genre);
                }
            }

        } catch (Exception exception) {
            System.out.println(exception.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
        return filteredPopularGenres;
    }
}






