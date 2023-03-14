package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

// questa interfaccia viene implementata da mapstruct e il codice lo mette lui
// si può far fare una compilazione a maven (maven / lifecycle / compile)
// per vedere poi all'interno della cartella target le classi che vengono generate da mapstruct

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);
}
