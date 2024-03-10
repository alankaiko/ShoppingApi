package br.com.shopping.repository.impl;

import br.com.shopping.acore.repository.impl.AbstractRepositoryImpl;
import br.com.shopping.model.ShippingAddress;
import br.com.shopping.model.dto.ShippingAddressDTO;
import br.com.shopping.repository.ShippingAddressRepository;
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
public class ShippingAddressRepositoryImpl extends AbstractRepositoryImpl<ShippingAddress, ShippingAddressDTO, Long> implements ShippingAddressRepository {
    private final EntityManager entityManager;

    public ShippingAddressRepositoryImpl(EntityManager entityManager) {
        super(ShippingAddress.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<ShippingAddress> filtering(ShippingAddressDTO shippingAddressDTO, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<ShippingAddress> query = builder.createQuery(ShippingAddress.class);
        Root<ShippingAddress> root = query.from(ShippingAddress.class);

        query.orderBy(builder.asc(root.get("codigo")));
        Predicate[] predicato = this.addRestrictions(builder, shippingAddressDTO, root);
        query.where(predicato);

        TypedQuery<ShippingAddress> tiped = this.entityManager.createQuery(query);
        this.addPagination(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(shippingAddressDTO));
    }

    private Predicate[] addRestrictions(CriteriaBuilder builder, ShippingAddressDTO shippingAddressDTO, Root<ShippingAddress> root) {
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

    private Long total(ShippingAddressDTO shippingAddressDTO) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<ShippingAddress> root = query.from(ShippingAddress.class);

        Predicate[] predicato = this.addRestrictions(builder, shippingAddressDTO, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
