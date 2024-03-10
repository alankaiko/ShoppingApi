package br.com.shopping.controller;

import br.com.shopping.acore.controller.AbstractController;
import br.com.shopping.model.BillingAddress;
import br.com.shopping.model.dto.BillingAddressDTO;
import br.com.shopping.service.BillingAddressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = BillingAddressController.PATH)
public class BillingAddressController extends AbstractController<BillingAddress, BillingAddressDTO> {
    static final String PATH = "billingaddress";
    private final BillingAddressService billingAddressService;

    public BillingAddressController(BillingAddressService billingAddressService) {
        super(billingAddressService);
        this.billingAddressService = billingAddressService;
    }

    @GetMapping(params = "resumo")
    public Page<BillingAddress> Resumir(BillingAddressDTO billingAddressDTO, Pageable page) {
        return this.billingAddressService.filtering(billingAddressDTO, page);
    }


}
