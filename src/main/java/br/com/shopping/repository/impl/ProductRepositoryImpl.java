package br.com.shopping.repository.impl;

import br.com.shopping.acore.repository.impl.AbstractRepositoryImpl;
import br.com.shopping.model.Product;
import br.com.shopping.model.dto.ProductDTO;
import br.com.shopping.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ProductRepositoryImpl extends AbstractRepositoryImpl<Product, ProductDTO, Long> implements ProductRepository {
    private final EntityManager entityManager;

    public ProductRepositoryImpl(EntityManager entityManager) {
        super(Product.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Product> filtering(ProductDTO productDTO, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);

        query.orderBy(builder.asc(root.get("codigo")));
        Predicate[] predicato = this.addRestrictions(builder, productDTO, root);
        query.where(predicato);

        TypedQuery<Product> tiped = this.entityManager.createQuery(query);
        this.addPagination(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(productDTO));
    }

    private Predicate[] addRestrictions(CriteriaBuilder builder, ProductDTO productDTO, Root<Product> root) {
        List<Predicate> lista = new ArrayList<>();

        return lista.toArray(new Predicate[lista.size()]);
    }

    private void addPagination(TypedQuery<?> tiped, Pageable page) {
        int paginaatual = page.getPageNumber();
        int totalporpagina = page.getPageSize();
        int primeiroRegistroDaPagina = paginaatual * totalporpagina;

        tiped.setFirstResult(primeiroRegistroDaPagina);
        tiped.setMaxResults(totalporpagina);
    }

    private Long total(ProductDTO productDTO) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Product> root = query.from(Product.class);

        Predicate[] predicato = this.addRestrictions(builder, productDTO, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
