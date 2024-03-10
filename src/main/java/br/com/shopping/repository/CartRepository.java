package br.com.shopping.repository;

import br.com.shopping.acore.repository.AbstractRepository;
import br.com.shopping.model.Cart;
import br.com.shopping.model.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CartRepository extends AbstractRepository<Cart, CartDTO, Long> {
    Page<Cart> filtering(CartDTO cartDTO, Pageable pageable);
}
