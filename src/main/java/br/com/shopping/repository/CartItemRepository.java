package br.com.shopping.repository;

import br.com.shopping.acore.repository.AbstractRepository;
import br.com.shopping.model.CartItem;
import br.com.shopping.model.dto.CartItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CartItemRepository extends AbstractRepository<CartItem, CartItemDTO, Long> {
    Page<CartItem> filtering(CartItemDTO cartItemDTO, Pageable pageable);
}
