package br.com.shopping.service;

import br.com.shopping.acore.service.AbstractService;
import br.com.shopping.model.Product;
import br.com.shopping.model.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService extends AbstractService<Product, ProductDTO> {
    Page<Product> filtering(ProductDTO productDTO, Pageable pageable);
}
