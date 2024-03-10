package br.com.shopping.service;

import br.com.shopping.acore.service.AbstractService;
import br.com.shopping.model.BillingAddress;
import br.com.shopping.model.dto.BillingAddressDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BillingAddressService extends AbstractService<BillingAddress, BillingAddressDTO> {
    Page<BillingAddress> filtering(BillingAddressDTO billingAddressDTO, Pageable pageable);
}
