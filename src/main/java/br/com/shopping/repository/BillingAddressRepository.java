package br.com.shopping.repository;

import br.com.shopping.acore.repository.AbstractRepository;
import br.com.shopping.model.BillingAddress;
import br.com.shopping.model.dto.BillingAddressDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BillingAddressRepository extends AbstractRepository<BillingAddress, BillingAddressDTO, Long> {
    Page<BillingAddress> filtering(BillingAddressDTO billingAddressDTO, Pageable pageable);
}
