package br.com.shopping.controller;

import br.com.shopping.acore.controller.AbstractController;
import br.com.shopping.model.ShippingAddress;
import br.com.shopping.model.dto.ShippingAddressDTO;
import br.com.shopping.service.ShippingAddressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = ShippingAddressController.PATH)
public class ShippingAddressController extends AbstractController<ShippingAddress, ShippingAddressDTO> {
    static final String PATH = "shippingaddress";
    private final ShippingAddressService shippingAddressService;

    public ShippingAddressController(ShippingAddressService shippingAddressService) {
        super(shippingAddressService);
        this.shippingAddressService = shippingAddressService;
    }

    @GetMapping(params = "resumo")
    public Page<ShippingAddress> Resumir(ShippingAddressDTO shippingAddressDTO, Pageable page) {
        return this.shippingAddressService.filtering(shippingAddressDTO, page);
    }


}
