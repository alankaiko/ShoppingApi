package br.com.shopping.service;

import br.com.shopping.acore.service.AbstractService;
import br.com.shopping.model.Customer;
import br.com.shopping.model.dto.CustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService extends AbstractService<Customer, CustomerDTO> {
    Page<Customer> filtering(CustomerDTO customerDTO, Pageable pageable);
}
