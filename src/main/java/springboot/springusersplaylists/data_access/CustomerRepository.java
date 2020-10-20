package springboot.springusersplaylists.data_access;

import springboot.springusersplaylists.models.*;

import java.sql.*;
import java.util.ArrayList;

public class CustomerRepository {
    private String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";     // This is the connection to the database.
    private Connection conn = null;

    /*
     Setup methods to manipulate database, using conn = DriverManager.getConnection(URL);
     and prepared statements.
    */

    public ArrayList<CustomerDTO> getAllCustomers() {   //This method prints all the customers that are registered in the database.
        ArrayList<CustomerDTO> customers = new ArrayList<>();   //This is a placeholder for all customers.

        try {
            conn = DriverManager.getConnection(URL);       //connecting to database
            PreparedStatement prep = conn.prepareStatement("SELECT customerId, firstName, lastName, country, postalCode, phone FROM Customer"); // This is an SQL-query that gives us the values we want from all customers.
            ResultSet set = prep.executeQuery();
            while (set.next()) {
                customers.add(new CustomerDTO(      //Adding all customer to the arrayList customer

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
                conn.close();       // closing the connection to the database
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
        return customers;
    }

    public Boolean addNewCustomer(Customer customer) {      //This method add a new customer to the database
        Boolean success = false;
        try {
            conn = DriverManager.getConnection(URL);      //connecting to database
            PreparedStatement prep = conn.prepareStatement(
                    "INSERT INTO customer( customerId, firstName, lastName, company, address, city, state, country, postalCode, phone, fax, email )" +
                            " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");        // This is an SQL-query that create a new customer in the database.
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
                conn.close();       //close connection to database
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
        return success;
    }

    public Boolean updateCustomer(Customer customer) {      //This method updates a customer values.
        Boolean success = false;
        try {

            conn = DriverManager.getConnection(URL);        //connecting to database
            PreparedStatement prep =
                    conn.prepareStatement("UPDATE customer SET firstName=?, lastName=?, country=?, postalCode=?, phone=?, email=?" +
                            " WHERE customerId=?");              // This is an SQL-query that updates a already existing customer with given values.

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
                conn.close();       //closing database connection
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
        return success;
    }

    public ArrayList<CustomersByCountry> getCustomersByCountry() {      //This method list all customers by country by descending order.
        ArrayList<CustomersByCountry> numberOfCustomers = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL);     //connecting to database
            PreparedStatement prep =
                    conn.prepareStatement("SELECT Country, COUNT (*) as Quantity FROM Customer GROUP BY Country ORDER BY COUNT(*) DESC;"); // This is an SQL-query that count all customers grouped by country.
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
                conn.close();           //closing database connection
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
        return numberOfCustomers;
    }

    public ArrayList<CustomersHighestSpenders> getCustomersHighestSpenders() {      //Method that list the highest spending customer in an descending order.
        ArrayList<CustomersHighestSpenders> mostWastefulCustomers = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL);        //connecting to database
            PreparedStatement prep =
                    conn.prepareStatement("SELECT Customer.FirstName, Customer.LastName, Invoice.Total\n" +
                            "FROM Customer\n" +
                            "    JOIN Invoice ON Customer.CustomerId = Invoice.CustomerId ORDER BY total DESC;"); // This is an SQL-query that takes out the total spent by each customer and list them in a descending order..
            ResultSet set = prep.executeQuery();
            while (set.next()) {
                mostWastefulCustomers.add(new CustomersHighestSpenders(         // Adding all the customers to arraylist mostWastefulCustomers.

                        set.getString(1),
                        set.getString(2),
                        set.getDouble(3)));

                System.out.println("Get highest spent customer went well!");
            }
        } catch (Exception exception) {
            System.out.println(exception.toString());
        } finally {
            try {
                conn.close();           //closing database connection
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
        return mostWastefulCustomers;
    }


    public ArrayList<CustomersMostPopularGenre> getCustomersMostPopularGenre(int customerId) {      //Method that shows customers most popular genre(s).

        ArrayList<CustomersMostPopularGenre> mostPopularGenre = new ArrayList<>();
        var filteredPopularGenres = new ArrayList<CustomersMostPopularGenre>();
        try {

            conn = DriverManager.getConnection(URL);        //connecting to database
            PreparedStatement prep =
                    conn.prepareStatement("    SELECT Customer.FirstName, Customer.LastName, Genre.Name, COUNT (*) AS Total FROM InvoiceLine\n" +
                            "JOIN Track ON InvoiceLine.TrackId = Track.TrackId\n" +
                            "JOIN Genre ON Track.GenreId = Genre.GenreId\n" +
                            "JOIN Invoice ON InvoiceLine.InvoiceId = Invoice.InvoiceId\n" +
                            "JOIN Customer ON Invoice.CustomerId = Customer.CustomerId\n" +
                            "WHERE Customer.CustomerId = ?\n" +
                            "GROUP BY Genre.Name\n" +
                            "ORDER BY Total DESC LIMIT 2;");    // This is an SQL-query that takes out the two most popular genre based on purchased tracks.

            prep.setInt(1, customerId);
            ResultSet set = prep.executeQuery();

            while (set.next()) {
                mostPopularGenre.add(new CustomersMostPopularGenre(

                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getInt(4)
                ));


                System.out.println("Get the most popular genre went well!");
            }
            var max = 0;

            max = Math.max(max, mostPopularGenre.get(0).getMostPopularGenre());
            ;
            for (var genre : mostPopularGenre                   //this loop looks for the highest value of the most popular genre.
            ) {                                                 //if there are two genre of the same value, the both are printed.
                if (max == genre.getMostPopularGenre()) {
                    filteredPopularGenres.add(genre);
                }
            }

        } catch (Exception exception) {
            System.out.println(exception.toString());
        } finally {
            try {
                conn.close();           //closing database connection
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
        return filteredPopularGenres;
    }
}






