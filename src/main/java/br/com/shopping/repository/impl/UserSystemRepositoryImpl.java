package br.com.shopping.repository.impl;

import br.com.shopping.acore.repository.impl.AbstractRepositoryImpl;
import br.com.shopping.model.UserSystem;
import br.com.shopping.model.dto.UserSystemDTO;
import br.com.shopping.repository.UserSystemRepository;
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
public class UserSystemRepositoryImpl extends AbstractRepositoryImpl<UserSystem, UserSystemDTO, Long> implements UserSystemRepository {
    private final EntityManager entityManager;

    public UserSystemRepositoryImpl(EntityManager entityManager) {
        super(UserSystem.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<UserSystem> filtering(UserSystemDTO userSystemDTO, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<UserSystem> query = builder.createQuery(UserSystem.class);
        Root<UserSystem> root = query.from(UserSystem.class);

        query.orderBy(builder.asc(root.get("codigo")));
        Predicate[] predicato = this.addRestrictions(builder, userSystemDTO, root);
        query.where(predicato);

        TypedQuery<UserSystem> tiped = this.entityManager.createQuery(query);
        this.addPagination(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(userSystemDTO));
    }

    private Predicate[] addRestrictions(CriteriaBuilder builder, UserSystemDTO userSystemDTO, Root<UserSystem> root) {
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

    private Long total(UserSystemDTO userSystemDTO) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<UserSystem> root = query.from(UserSystem.class);

        Predicate[] predicato = this.addRestrictions(builder, userSystemDTO, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
