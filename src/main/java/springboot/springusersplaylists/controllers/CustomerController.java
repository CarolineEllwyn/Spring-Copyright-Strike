package springboot.springusersplaylists.controllers;

import org.springframework.web.bind.annotation.*;
import springboot.springusersplaylists.data_access.CustomerRepository;
import springboot.springusersplaylists.models.*;

import java.util.ArrayList;

//This is a controller that specifies the endpoints of each method

@RestController
public class CustomerController {
    CustomerRepository customerRepository = new CustomerRepository();

    @RequestMapping(value = "/api/customers", method = RequestMethod.GET)
    public ArrayList<CustomerDTO> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @RequestMapping(value = "/api/addNewCustomer", method = RequestMethod.POST)
    public Boolean addNewCustomer(@RequestBody Customer customer) {
        return customerRepository.addNewCustomer(customer);
    }

    @RequestMapping(value = "/api/customer/update", method = RequestMethod.PUT)
    public Boolean updateCustomer(@RequestBody Customer customer) {
        return customerRepository.updateCustomer(customer);
    }

    @GetMapping(value = "/api/customer/customersByCountry")
    public ArrayList<CustomersByCountry> getCustomersByCountry() {
        ArrayList<CustomersByCountry> customersByCountry = customerRepository.getCustomersByCountry();
        return customersByCountry;
    }

    @GetMapping (value = "/api/customer/customersHighestSpenders")
    public ArrayList<CustomersHighestSpenders> getCustomersHighestSpenders(){
        ArrayList<CustomersHighestSpenders> mostWastefulCustomers = customerRepository.getCustomersHighestSpenders();
        return mostWastefulCustomers;
    }

    @GetMapping (value = "/api/customer/customersMostPopularGenre/{id}")
    public ArrayList<CustomersMostPopularGenre> getCustomersMostPopularGenre(@PathVariable String id){
        ArrayList<CustomersMostPopularGenre> mostPopularGenres = customerRepository.getCustomersMostPopularGenre(Integer.parseInt(id));
        return mostPopularGenres;
    }


}