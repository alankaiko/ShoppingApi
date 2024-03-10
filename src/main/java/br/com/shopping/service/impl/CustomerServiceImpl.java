package br.com.shopping.service.impl;

import br.com.shopping.acore.service.impl.AbstractServiceImpl;
import br.com.shopping.model.Customer;
import br.com.shopping.model.dto.CustomerDTO;
import br.com.shopping.repository.CustomerRepository;
import br.com.shopping.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerServiceImpl extends AbstractServiceImpl<Customer, CustomerDTO> implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        super(customerRepository);
        this.customerRepository = customerRepository;
    }

    @Override
    public Page<Customer> filtering(CustomerDTO customerDTO, Pageable pageable) {
        return this.customerRepository.filtering(customerDTO, pageable);
    }
}
