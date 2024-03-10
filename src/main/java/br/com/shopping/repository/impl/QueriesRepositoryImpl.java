package br.com.shopping.repository.impl;

import br.com.shopping.acore.repository.impl.AbstractRepositoryImpl;
import br.com.shopping.model.Queries;
import br.com.shopping.model.dto.QueriesDTO;
import br.com.shopping.repository.QueriesRepository;
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
public class QueriesRepositoryImpl extends AbstractRepositoryImpl<Queries, QueriesDTO, Long> implements QueriesRepository {
    private final EntityManager entityManager;

    public QueriesRepositoryImpl(EntityManager entityManager) {
        super(Queries.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Queries> filtering(QueriesDTO queriesDTO, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Queries> query = builder.createQuery(Queries.class);
        Root<Queries> root = query.from(Queries.class);

        query.orderBy(builder.asc(root.get("codigo")));
        Predicate[] predicato = this.addRestrictions(builder, queriesDTO, root);
        query.where(predicato);

        TypedQuery<Queries> tiped = this.entityManager.createQuery(query);
        this.addPagination(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(queriesDTO));
    }

    private Predicate[] addRestrictions(CriteriaBuilder builder, QueriesDTO queriesDTO, Root<Queries> root) {
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

    private Long total(QueriesDTO queriesDTO) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Queries> root = query.from(Queries.class);

        Predicate[] predicato = this.addRestrictions(builder, queriesDTO, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
