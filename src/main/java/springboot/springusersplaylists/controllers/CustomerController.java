package springboot.springusersplaylists.controllers;

import org.springframework.web.bind.annotation.*;
import springboot.springusersplaylists.data_access.CustomerRepository;
import springboot.springusersplaylists.models.Customer;

import java.util.ArrayList;

@RestController
public class CustomerController {
    CustomerRepository customerRepository = new CustomerRepository();

    @RequestMapping(value = "/api/customers", method = RequestMethod.GET)
    public ArrayList<Customer> getAllCustomers() { return customerRepository.getAllCustomers(); }

/*    @GetMapping("/")
    public String index(){
        return "Helloooooo!!";
    }

    @RequestMapping(value = "/greet", method = RequestMethod.GET)
    public String greet(@RequestParam("name") String name){
       return "Howdy " + name;
    }

    @RequestMapping(value = "/{firstName}/{lastName}", method = RequestMethod.GET)
    public String getCustomer(@PathVariable String firstName, @PathVariable String lastName) {
        return firstName + " " + lastName;
    }*/
}
