package br.com.shopping.repository;

import br.com.shopping.acore.repository.AbstractRepository;
import br.com.shopping.model.Customer;
import br.com.shopping.model.dto.CustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomerRepository extends AbstractRepository<Customer, CustomerDTO, Long> {
    Page<Customer> filtering(CustomerDTO customerDTO, Pageable pageable);
}
