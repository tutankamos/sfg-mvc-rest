package guru.springfamework.repositories;

import guru.springfamework.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Optional<Customer> findById(Long id); // funziona anche se lo commentiamo perchè esiste già in jpa
    // la find all dovrei gia' averla
}
