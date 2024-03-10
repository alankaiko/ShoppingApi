package br.com.shopping.service.impl;

import br.com.shopping.acore.service.impl.AbstractServiceImpl;
import br.com.shopping.model.CartItem;
import br.com.shopping.model.dto.CartItemDTO;
import br.com.shopping.repository.CartItemRepository;
import br.com.shopping.service.CartItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartItemServiceImpl extends AbstractServiceImpl<CartItem, CartItemDTO> implements CartItemService {
    private final CartItemRepository cartItemRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        super(cartItemRepository);
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Page<CartItem> filtering(CartItemDTO cartItemDTO, Pageable pageable) {
        return this.cartItemRepository.filtering(cartItemDTO, pageable);
    }
}
