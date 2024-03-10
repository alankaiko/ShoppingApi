package br.com.shopping.repository.impl;

import br.com.shopping.acore.repository.impl.AbstractRepositoryImpl;
import br.com.shopping.model.CustomerOrder;
import br.com.shopping.model.dto.CustomerOrderDTO;
import br.com.shopping.repository.CustomerOrderRepository;
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
public class CustomerOrderRepositoryImpl extends AbstractRepositoryImpl<CustomerOrder, CustomerOrderDTO, Long> implements CustomerOrderRepository {
    private final EntityManager entityManager;

    public CustomerOrderRepositoryImpl(EntityManager entityManager) {
        super(CustomerOrder.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<CustomerOrder> filtering(CustomerOrderDTO customerOrderDTO, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerOrder> query = builder.createQuery(CustomerOrder.class);
        Root<CustomerOrder> root = query.from(CustomerOrder.class);

        query.orderBy(builder.asc(root.get("codigo")));
        Predicate[] predicato = this.addRestrictions(builder, customerOrderDTO, root);
        query.where(predicato);

        TypedQuery<CustomerOrder> tiped = this.entityManager.createQuery(query);
        this.addPagination(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(customerOrderDTO));
    }

    private Predicate[] addRestrictions(CriteriaBuilder builder, CustomerOrderDTO customerOrderDTO, Root<CustomerOrder> root) {
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

    private Long total(CustomerOrderDTO customerOrderDTO) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<CustomerOrder> root = query.from(CustomerOrder.class);

        Predicate[] predicato = this.addRestrictions(builder, customerOrderDTO, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
