package br.com.shopping.service;

import br.com.shopping.acore.service.AbstractService;
import br.com.shopping.model.Cart;
import br.com.shopping.model.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartService extends AbstractService<Cart, CartDTO> {
    Page<Cart> filtering(CartDTO cartDTO, Pageable pageable);
}
