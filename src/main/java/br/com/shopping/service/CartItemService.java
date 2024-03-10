package br.com.shopping.service;

import br.com.shopping.acore.service.AbstractService;
import br.com.shopping.model.CartItem;
import br.com.shopping.model.dto.CartItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartItemService extends AbstractService<CartItem, CartItemDTO> {
    Page<CartItem> filtering(CartItemDTO cartItemDTO, Pageable pageable);
}
