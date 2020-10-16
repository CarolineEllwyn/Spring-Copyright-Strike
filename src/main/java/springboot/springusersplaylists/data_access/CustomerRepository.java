package springboot.springusersplaylists.data_access;


import springboot.springusersplaylists.models.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerRepository {
    private String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
    private Connection conn = null;



    public ArrayList<Customer> getAllCustomers(){
        ArrayList<Customer> customers = new ArrayList<>();

        try{
            conn = DriverManager.getConnection(URL);
            PreparedStatement prep = conn.prepareStatement( "SELECT CustomerId, FirstName, LastName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email FROM Customer");
            ResultSet set = prep.executeQuery();
            while(set.next()){
                customers.add( new Customer(
                        set.getInt("customerId"),
                        set.getString("firstName"),
                        set.getString("lastName"),
                        set.getString("company"),
                        set.getString("address"),
                        set.getString("city"),
                        set.getString("state"),
                        set.getString("country"),
                        set.getString("postalCode"),
                        set.getString("phone"),
                        set.getString("fax"),
                        set.getString("email")
                ));
            }
            System.out.println("Get all went well!");

        } catch (Exception exception) {
            System.out.println(exception.toString());
        }
        finally {
            try{
                conn.close();
            }catch (Exception exception){
                System.out.println(exception.toString());
            }
        }
        return customers;
    }
}
