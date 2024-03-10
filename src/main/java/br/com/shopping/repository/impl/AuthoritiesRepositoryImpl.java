package br.com.shopping.repository.impl;

import br.com.shopping.acore.repository.impl.AbstractRepositoryImpl;
import br.com.shopping.model.Authorities;
import br.com.shopping.model.dto.AuthoritiesDTO;
import br.com.shopping.repository.AuthoritiesRepository;
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
public class AuthoritiesRepositoryImpl extends AbstractRepositoryImpl<Authorities, AuthoritiesDTO, Long> implements AuthoritiesRepository {
    private final EntityManager entityManager;

    public AuthoritiesRepositoryImpl(EntityManager entityManager) {
        super(Authorities.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Authorities> filtering(AuthoritiesDTO authoritiesDTO, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Authorities> query = builder.createQuery(Authorities.class);
        Root<Authorities> root = query.from(Authorities.class);

        query.orderBy(builder.asc(root.get("codigo")));
        Predicate[] predicato = this.addRestrictions(builder, authoritiesDTO, root);
        query.where(predicato);

        TypedQuery<Authorities> tiped = this.entityManager.createQuery(query);
        this.addPagination(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(authoritiesDTO));
    }

    private Predicate[] addRestrictions(CriteriaBuilder builder, AuthoritiesDTO authoritiesDTO, Root<Authorities> root) {
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

    private Long total(AuthoritiesDTO authoritiesDTO) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Authorities> root = query.from(Authorities.class);

        Predicate[] predicato = this.addRestrictions(builder, authoritiesDTO, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
