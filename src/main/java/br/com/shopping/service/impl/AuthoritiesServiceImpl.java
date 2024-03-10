package br.com.shopping.service.impl;

import br.com.shopping.acore.service.impl.AbstractServiceImpl;
import br.com.shopping.model.Authorities;
import br.com.shopping.model.dto.AuthoritiesDTO;
import br.com.shopping.repository.AuthoritiesRepository;
import br.com.shopping.service.AuthoritiesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthoritiesServiceImpl extends AbstractServiceImpl<Authorities, AuthoritiesDTO> implements AuthoritiesService {
    private final AuthoritiesRepository authoritiesRepository;

    public AuthoritiesServiceImpl(AuthoritiesRepository authoritiesRepository) {
        super(authoritiesRepository);
        this.authoritiesRepository = authoritiesRepository;
    }

    @Override
    public Page<Authorities> filtering(AuthoritiesDTO authoritiesDTO, Pageable pageable) {
        return this.authoritiesRepository.filtering(authoritiesDTO, pageable);
    }
}
