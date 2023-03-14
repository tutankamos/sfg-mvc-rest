package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CatorgoryListDTO;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    private final CustomerService customerService;
    public static final String BASE_URL = "/api/v1/customers";

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers(){
        return new ResponseEntity<CustomerListDTO>(
                new CustomerListDTO(
                        customerService.getAllCustomers()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id){
        return new ResponseEntity<CustomerDTO>(
                customerService.getCustomerById(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO){ // con @RequestBody chiediamo a spring di guardare il body di quello che ci arriva per estrarre i dati
        return new ResponseEntity<CustomerDTO>(
                customerService.createNewCustomer(customerDTO),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(
                customerService.saveCustomerByDTO(id, customerDTO),
                HttpStatus.OK
        );
    }
}
