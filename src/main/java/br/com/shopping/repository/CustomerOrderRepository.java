package br.com.shopping.repository;

import br.com.shopping.acore.repository.AbstractRepository;
import br.com.shopping.model.CustomerOrder;
import br.com.shopping.model.dto.CustomerOrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomerOrderRepository extends AbstractRepository<CustomerOrder, CustomerOrderDTO, Long> {
    Page<CustomerOrder> filtering(CustomerOrderDTO customerOrderDTO, Pageable pageable);
}
