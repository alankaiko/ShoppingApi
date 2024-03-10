package br.com.shopping.repository;

import br.com.shopping.acore.repository.AbstractRepository;
import br.com.shopping.model.ShippingAddress;
import br.com.shopping.model.dto.ShippingAddressDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ShippingAddressRepository extends AbstractRepository<ShippingAddress, ShippingAddressDTO, Long> {
    Page<ShippingAddress> filtering(ShippingAddressDTO shippingAddressDTO, Pageable pageable);
}
