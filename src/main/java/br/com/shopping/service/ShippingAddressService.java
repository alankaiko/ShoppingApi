package br.com.shopping.service;

import br.com.shopping.acore.service.AbstractService;
import br.com.shopping.model.ShippingAddress;
import br.com.shopping.model.dto.ShippingAddressDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShippingAddressService extends AbstractService<ShippingAddress, ShippingAddressDTO> {
    Page<ShippingAddress> filtering(ShippingAddressDTO shippingAddressDTO, Pageable pageable);
}
