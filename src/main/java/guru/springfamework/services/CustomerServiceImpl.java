package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.controllers.v1.CustomerController;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    private String getCustomerUrl (Long id) {
        return CustomerController.BASE_URL + "/" + id;
    }

    @Override // per questo metodo mi manca tutta la parte della generazione dell'url che lui ha fatto
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()// apro lo stream e faccio cose
                .map( customer -> {
                    CustomerDTO cDTO = customerMapper.customerToCustomerDTO(customer);
                    cDTO.setUrl(getCustomerUrl(customer.getId()));
                    return cDTO;
                })
                .collect(Collectors.toList()); // per chiudere lo stream
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .map(customerDTO -> {
                    customerDTO.setUrl(getCustomerUrl(id));
                    return customerDTO;
                })
                .orElseThrow(RuntimeException::new); // todo implement better exception handling
    }


}
