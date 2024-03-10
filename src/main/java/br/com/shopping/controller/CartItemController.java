package br.com.shopping.controller;

import br.com.shopping.acore.controller.AbstractController;
import br.com.shopping.model.CartItem;
import br.com.shopping.model.dto.CartItemDTO;
import br.com.shopping.service.CartItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = CartItemController.PATH)
public class CartItemController extends AbstractController<CartItem, CartItemDTO> {
    static final String PATH = "cartitem";
    private final CartItemService cartService;

    public CartItemController(CartItemService cartService) {
        super(cartService);
        this.cartService = cartService;
    }

    @GetMapping(params = "resumo")
    public Page<CartItem> Resumir(CartItemDTO cartItemDTO, Pageable page) {
        return this.cartService.filtering(cartItemDTO, page);
    }


}
