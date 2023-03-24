package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.controllers.v1.CustomerController;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import net.bytebuddy.implementation.bytecode.Throw;
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

    @Override // per questo metodo mi manca tutta la parte della generazione dell'url che lui ha fatto
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()// apro lo stream e faccio cose
                .map(customer -> {
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
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);

        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> { // ha poco senso scrivere qui un map penso
            if(customerDTO.getFirstname() != null){
                customer.setFirstname(customerDTO.getFirstname());
            }
            if(customerDTO.getLastname() != null){
                customer.setLastname(customerDTO.getLastname());
            }
            return saveAndReturnDTO(customer);
        }).orElseThrow(ResourceNotFoundException::new);
    }


//    // provo a non usare .map
//    @Override // vado in casino se tolgo il map perche' non so gestire bene l'opitonal
//    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
//
//        Optional<Customer> customer = customerRepository.findById(id);
//        if (customer != null){
//            if(customerDTO.getFirstname() != null){
//                customer.setFirstname(customerDTO.getFirstname());
//            }
//            if(customerDTO.getLastname() != null){
//                customer.setLastname(customerDTO.getLastname());
//            }
//            return saveAndReturnDTO(customer);
//        }.orElseThrow(RuntimeException::new);
//    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id); // tecnicamente dovrei metterci qualcosa che geestisce la possibilita' che non venga trovato l'id
    }

    private String getCustomerUrl(Long id) {
        return CustomerController.BASE_URL + "/" + id;
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnCDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        returnCDTO.setUrl(getCustomerUrl(savedCustomer.getId()));
        return returnCDTO;
    }


}
