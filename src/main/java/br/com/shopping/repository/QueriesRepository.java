package br.com.shopping.repository;

import br.com.shopping.acore.repository.AbstractRepository;
import br.com.shopping.model.Queries;
import br.com.shopping.model.dto.QueriesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface QueriesRepository extends AbstractRepository<Queries, QueriesDTO, Long> {
    Page<Queries> filtering(QueriesDTO queriesDTO, Pageable pageable);
}
