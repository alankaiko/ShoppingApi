package br.com.shopping.service.impl;

import br.com.shopping.acore.service.impl.AbstractServiceImpl;
import br.com.shopping.model.BillingAddress;
import br.com.shopping.model.dto.BillingAddressDTO;
import br.com.shopping.repository.BillingAddressRepository;
import br.com.shopping.service.BillingAddressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BillingAddressServiceImpl extends AbstractServiceImpl<BillingAddress, BillingAddressDTO> implements BillingAddressService {
    private final BillingAddressRepository billingAddressRepository;

    public BillingAddressServiceImpl(BillingAddressRepository billingAddressRepository) {
        super(billingAddressRepository);
        this.billingAddressRepository = billingAddressRepository;
    }

    @Override
    public Page<BillingAddress> filtering(BillingAddressDTO billingAddressDTO, Pageable pageable) {
        return this.billingAddressRepository.filtering(billingAddressDTO, pageable);
    }
}
