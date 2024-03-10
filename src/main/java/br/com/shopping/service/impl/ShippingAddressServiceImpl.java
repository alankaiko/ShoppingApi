package br.com.shopping.service.impl;

import br.com.shopping.acore.service.impl.AbstractServiceImpl;
import br.com.shopping.model.ShippingAddress;
import br.com.shopping.model.dto.ShippingAddressDTO;
import br.com.shopping.repository.ShippingAddressRepository;
import br.com.shopping.service.ShippingAddressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShippingAddressServiceImpl extends AbstractServiceImpl<ShippingAddress, ShippingAddressDTO> implements ShippingAddressService {
    private final ShippingAddressRepository shippingAddressRepository;

    public ShippingAddressServiceImpl(ShippingAddressRepository shippingAddressRepository) {
        super(shippingAddressRepository);
        this.shippingAddressRepository = shippingAddressRepository;
    }

    @Override
    public Page<ShippingAddress> filtering(ShippingAddressDTO shippingAddressDTO, Pageable pageable) {
        return this.shippingAddressRepository.filtering(shippingAddressDTO, pageable);
    }
}
