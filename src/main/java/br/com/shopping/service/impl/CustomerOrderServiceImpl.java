package br.com.shopping.service.impl;

import br.com.shopping.acore.service.impl.AbstractServiceImpl;
import br.com.shopping.model.CustomerOrder;
import br.com.shopping.model.dto.CustomerOrderDTO;
import br.com.shopping.repository.CustomerOrderRepository;
import br.com.shopping.service.CustomerOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerOrderServiceImpl extends AbstractServiceImpl<CustomerOrder, CustomerOrderDTO> implements CustomerOrderService {
    private final CustomerOrderRepository customerOrderRepository;

    public CustomerOrderServiceImpl(CustomerOrderRepository customerOrderRepository) {
        super(customerOrderRepository);
        this.customerOrderRepository = customerOrderRepository;
    }

    @Override
    public Page<CustomerOrder> filtering(CustomerOrderDTO customerOrderDTO, Pageable pageable) {
        return this.customerOrderRepository.filtering(customerOrderDTO, pageable);
    }
}
