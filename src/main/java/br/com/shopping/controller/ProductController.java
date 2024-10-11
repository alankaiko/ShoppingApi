package br.com.shopping.controller;

import br.com.shopping.acore.controller.AbstractController;
import br.com.shopping.model.Product;
import br.com.shopping.model.dto.ProductDTO;
import br.com.shopping.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = ProductController.PATH)
public class ProductController extends AbstractController<Product, ProductDTO> {
    static final String PATH = "product";
    private final ProductService productService;

    public ProductController(ProductService productService) {
        super(productService);
        this.productService = productService;
    }

    @GetMapping(params = "resumo")
    public Page<Product> resumir(ProductDTO productDTO, Pageable page) {
        return this.productService.filtering(productDTO, page);
    }


}
