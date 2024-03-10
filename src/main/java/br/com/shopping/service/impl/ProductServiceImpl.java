package br.com.shopping.service.impl;

import br.com.shopping.acore.service.impl.AbstractServiceImpl;
import br.com.shopping.model.Product;
import br.com.shopping.model.dto.ProductDTO;
import br.com.shopping.repository.ProductRepository;
import br.com.shopping.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl extends AbstractServiceImpl<Product, ProductDTO> implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> filtering(ProductDTO productDTO, Pageable pageable) {
        return this.productRepository.filtering(productDTO, pageable);
    }
}
