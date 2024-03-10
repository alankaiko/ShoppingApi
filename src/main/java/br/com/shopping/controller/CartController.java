package br.com.shopping.controller;

import br.com.shopping.acore.controller.AbstractController;
import br.com.shopping.model.Cart;
import br.com.shopping.model.dto.CartDTO;
import br.com.shopping.service.CartService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = CartController.PATH)
public class CartController extends AbstractController<Cart, CartDTO> {
    static final String PATH = "cart";
    private final CartService cartService;

    public CartController(CartService cartService) {
        super(cartService);
        this.cartService = cartService;
    }

    @GetMapping(params = "resumo")
    public Page<Cart> Resumir(CartDTO cartDTO, Pageable page) {
        return this.cartService.filtering(cartDTO, page);
    }


}
