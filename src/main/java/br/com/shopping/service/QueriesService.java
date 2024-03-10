package br.com.shopping.service;

import br.com.shopping.acore.service.AbstractService;
import br.com.shopping.model.Queries;
import br.com.shopping.model.dto.QueriesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QueriesService extends AbstractService<Queries, QueriesDTO> {
    Page<Queries> filtering(QueriesDTO queriesDTO, Pageable pageable);
}
