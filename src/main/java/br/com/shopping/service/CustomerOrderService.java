package br.com.shopping.service;

import br.com.shopping.acore.service.AbstractService;
import br.com.shopping.model.CustomerOrder;
import br.com.shopping.model.dto.CustomerOrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerOrderService extends AbstractService<CustomerOrder, CustomerOrderDTO> {
    Page<CustomerOrder> filtering(CustomerOrderDTO customerOrderDTO, Pageable pageable);
}
