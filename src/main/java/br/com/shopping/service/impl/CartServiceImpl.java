package br.com.shopping.service.impl;

import br.com.shopping.acore.service.impl.AbstractServiceImpl;
import br.com.shopping.model.Cart;
import br.com.shopping.model.dto.CartDTO;
import br.com.shopping.repository.CartRepository;
import br.com.shopping.service.CartService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartServiceImpl extends AbstractServiceImpl<Cart, CartDTO> implements CartService {
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        super(cartRepository);
        this.cartRepository = cartRepository;
    }

    @Override
    public Page<Cart> filtering(CartDTO cartDTO, Pageable pageable) {
        return this.cartRepository.filtering(cartDTO, pageable);
    }
}
