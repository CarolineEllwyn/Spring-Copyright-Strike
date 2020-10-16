package springboot.springusersplaylists.controllers;

import org.springframework.web.bind.annotation.*;
import springboot.springusersplaylists.data_access.CustomerRepository;
import springboot.springusersplaylists.models.Customer;

import java.util.ArrayList;




@RestController
public class CustomerController {
    CustomerRepository customerRepository = new CustomerRepository();

    @RequestMapping(value = "/api/customers", method = RequestMethod.GET)
    public ArrayList<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @RequestMapping(value = "/api/addNewCustomer", method = RequestMethod.POST)
    public Boolean addNewCustomer(@RequestBody Customer customer) {
        return customerRepository.addNewCustomer(customer);
    }

/*    @RequestMapping(value = "/api/customers", method = RequestMethod.PUT)
    public Boolean uppdateCustomer(@RequestBody Customer customer) {
        return customerRepository.updateCustomer(customer);
    }*/
}


