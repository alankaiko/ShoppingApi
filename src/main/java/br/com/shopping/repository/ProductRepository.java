package br.com.shopping.repository;

import br.com.shopping.acore.repository.AbstractRepository;
import br.com.shopping.model.Product;
import br.com.shopping.model.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ProductRepository extends AbstractRepository<Product, ProductDTO, Long> {
    Page<Product> filtering(ProductDTO productDTO, Pageable pageable);
}
