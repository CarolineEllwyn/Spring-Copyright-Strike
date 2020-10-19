package springboot.springusersplaylists.controllers;

import org.springframework.web.bind.annotation.*;
import springboot.springusersplaylists.data_access.CustomerRepository;
import springboot.springusersplaylists.models.Customer;
import springboot.springusersplaylists.models.CustomerDTO;
import springboot.springusersplaylists.models.CustomersByCountry;
import springboot.springusersplaylists.models.CustomersHighestSpenders;

import java.util.ArrayList;


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


}