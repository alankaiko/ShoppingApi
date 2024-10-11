package br.com.shopping.repository.impl;

import br.com.shopping.acore.repository.impl.AbstractRepositoryImpl;
import br.com.shopping.model.Customer;
import br.com.shopping.model.Customer_;
import br.com.shopping.model.dto.CustomerDTO;
import br.com.shopping.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
public class CustomerRepositoryImpl extends AbstractRepositoryImpl<Customer, CustomerDTO, Long> implements CustomerRepository {
    private final EntityManager entityManager;

    public CustomerRepositoryImpl(EntityManager entityManager) {
        super(Customer.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Customer> filtering(CustomerDTO customerDTO, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
        Root<Customer> root = query.from(Customer.class);

        query.orderBy(builder.asc(root.get("codigo")));
        Predicate[] predicato = this.addRestrictions(builder, customerDTO, root);
        query.where(predicato);

        TypedQuery<Customer> tiped = this.entityManager.createQuery(query);
        this.addPagination(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(customerDTO));
    }

    private Predicate[] addRestrictions(CriteriaBuilder builder, CustomerDTO customerDTO, Root<Customer> root) {
        List<Predicate> lista = new ArrayList<>();

        if (!StringUtils.isEmpty(customerDTO.getFirstName()))
            lista.add(builder.like(builder.lower(root.get(Customer_.FIRST_NAME)), "%" + customerDTO.getFirstName().toLowerCase() + "%"));

        return lista.toArray(new Predicate[lista.size()]);
    }

    private void addPagination(TypedQuery<?> tiped, Pageable page) {
        int paginaatual = page.getPageNumber();
        int totalporpagina = page.getPageSize();
        int primeiroRegistroDaPagina = paginaatual * totalporpagina;

        tiped.setFirstResult(primeiroRegistroDaPagina);
        tiped.setMaxResults(totalporpagina);
    }

    private Long total(CustomerDTO customerDTO) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Customer> root = query.from(Customer.class);

        Predicate[] predicato = this.addRestrictions(builder, customerDTO, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
