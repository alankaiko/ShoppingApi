package br.com.shopping.controller;

import br.com.shopping.acore.controller.AbstractController;
import br.com.shopping.model.Customer;
import br.com.shopping.model.dto.CustomerDTO;
import br.com.shopping.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = CustomerController.PATH)
public class CustomerController extends AbstractController<Customer, CustomerDTO> {
    static final String PATH = "customer";
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        super(customerService);
        this.customerService = customerService;
    }

    @GetMapping(params = "resumo")
    public Page<Customer> Resumir(CustomerDTO customerDTO, Pageable page) {
        return this.customerService.filtering(customerDTO, page);
    }


}
