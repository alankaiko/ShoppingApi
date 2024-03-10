package br.com.shopping.controller;

import br.com.shopping.acore.controller.AbstractController;
import br.com.shopping.model.CustomerOrder;
import br.com.shopping.model.dto.CustomerOrderDTO;
import br.com.shopping.service.CustomerOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = CustomerOrderController.PATH)
public class CustomerOrderController extends AbstractController<CustomerOrder, CustomerOrderDTO> {
    static final String PATH = "customerorder";
    private final CustomerOrderService customerOrderService;

    public CustomerOrderController(CustomerOrderService customerOrderService) {
        super(customerOrderService);
        this.customerOrderService = customerOrderService;
    }

    @GetMapping(params = "resumo")
    public Page<CustomerOrder> Resumir(CustomerOrderDTO customerOrderDTO, Pageable page) {
        return this.customerOrderService.filtering(customerOrderDTO, page);
    }


}
