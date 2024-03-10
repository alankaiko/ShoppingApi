package br.com.shopping.repository.impl;

import br.com.shopping.acore.repository.impl.AbstractRepositoryImpl;
import br.com.shopping.model.CartItem;
import br.com.shopping.model.dto.CartItemDTO;
import br.com.shopping.repository.CartItemRepository;
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
public class CartItemRepositoryImpl extends AbstractRepositoryImpl<CartItem, CartItemDTO, Long> implements CartItemRepository {
    private final EntityManager entityManager;

    public CartItemRepositoryImpl(EntityManager entityManager) {
        super(CartItem.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<CartItem> filtering(CartItemDTO cartItemDTO, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<CartItem> query = builder.createQuery(CartItem.class);
        Root<CartItem> root = query.from(CartItem.class);

        query.orderBy(builder.asc(root.get("codigo")));
        Predicate[] predicato = this.addRestrictions(builder, cartItemDTO, root);
        query.where(predicato);

        TypedQuery<CartItem> tiped = this.entityManager.createQuery(query);
        this.addPagination(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(cartItemDTO));
    }

    private Predicate[] addRestrictions(CriteriaBuilder builder, CartItemDTO cartItemDTO, Root<CartItem> root) {
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

    private Long total(CartItemDTO cartDTO) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<CartItem> root = query.from(CartItem.class);

        Predicate[] predicato = this.addRestrictions(builder, cartDTO, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
