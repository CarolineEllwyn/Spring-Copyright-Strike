package springboot.springusersplaylists.viewController;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springboot.springusersplaylists.data_access.CustomerRepository;
import springboot.springusersplaylists.models.Customer;
import springboot.springusersplaylists.models.CustomerDTO;

import java.util.ArrayList;


@Controller
public class CustomerViewController {
    CustomerRepository customerRepository = new CustomerRepository();

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String listAllCustomer (Model model) {
        ArrayList<CustomerDTO> customers = customerRepository.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customersList";
    }
}




