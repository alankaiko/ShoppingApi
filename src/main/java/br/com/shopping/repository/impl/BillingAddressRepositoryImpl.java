package br.com.shopping.repository.impl;

import br.com.shopping.acore.repository.impl.AbstractRepositoryImpl;
import br.com.shopping.model.BillingAddress;
import br.com.shopping.model.dto.BillingAddressDTO;
import br.com.shopping.repository.BillingAddressRepository;
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
public class BillingAddressRepositoryImpl extends AbstractRepositoryImpl<BillingAddress, BillingAddressDTO, Long> implements BillingAddressRepository {
    private final EntityManager entityManager;

    public BillingAddressRepositoryImpl(EntityManager entityManager) {
        super(BillingAddress.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<BillingAddress> filtering(BillingAddressDTO billingAddressDTO, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<BillingAddress> query = builder.createQuery(BillingAddress.class);
        Root<BillingAddress> root = query.from(BillingAddress.class);

        query.orderBy(builder.asc(root.get("codigo")));
        Predicate[] predicato = this.addRestrictions(builder, billingAddressDTO, root);
        query.where(predicato);

        TypedQuery<BillingAddress> tiped = this.entityManager.createQuery(query);
        this.addPagination(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(billingAddressDTO));
    }

    private Predicate[] addRestrictions(CriteriaBuilder builder, BillingAddressDTO billingAddressDTO, Root<BillingAddress> root) {
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

    private Long total(BillingAddressDTO billingAddressDTO) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<BillingAddress> root = query.from(BillingAddress.class);

        Predicate[] predicato = this.addRestrictions(builder, billingAddressDTO, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
