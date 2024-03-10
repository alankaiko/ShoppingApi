package br.com.shopping.repository.impl;

import br.com.shopping.acore.repository.impl.AbstractRepositoryImpl;
import br.com.shopping.model.Cart;
import br.com.shopping.model.dto.CartDTO;
import br.com.shopping.repository.CartRepository;
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
public class CartRepositoryImpl extends AbstractRepositoryImpl<Cart, CartDTO, Long> implements CartRepository {
    private final EntityManager entityManager;

    public CartRepositoryImpl(EntityManager entityManager) {
        super(Cart.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Cart> filtering(CartDTO cartDTO, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Cart> query = builder.createQuery(Cart.class);
        Root<Cart> root = query.from(Cart.class);

        query.orderBy(builder.asc(root.get("codigo")));
        Predicate[] predicato = this.addRestrictions(builder, cartDTO, root);
        query.where(predicato);

        TypedQuery<Cart> tiped = this.entityManager.createQuery(query);
        this.addPagination(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(cartDTO));
    }

    private Predicate[] addRestrictions(CriteriaBuilder builder, CartDTO cartDTO, Root<Cart> root) {
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

    private Long total(CartDTO cartDTO) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Cart> root = query.from(Cart.class);

        Predicate[] predicato = this.addRestrictions(builder, cartDTO, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
