package br.com.shopping.service.impl;

import br.com.shopping.acore.service.impl.AbstractServiceImpl;
import br.com.shopping.model.Queries;
import br.com.shopping.model.dto.QueriesDTO;
import br.com.shopping.repository.QueriesRepository;
import br.com.shopping.service.QueriesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QueriesServiceImpl extends AbstractServiceImpl<Queries, QueriesDTO> implements QueriesService {
    private final QueriesRepository queriesRepository;

    public QueriesServiceImpl(QueriesRepository queriesRepository) {
        super(queriesRepository);
        this.queriesRepository = queriesRepository;
    }

    @Override
    public Page<Queries> filtering(QueriesDTO queriesDTO, Pageable pageable) {
        return this.queriesRepository.filtering(queriesDTO, pageable);
    }
}
