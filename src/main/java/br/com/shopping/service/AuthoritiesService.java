package br.com.shopping.service;

import br.com.shopping.acore.service.AbstractService;
import br.com.shopping.model.Authorities;
import br.com.shopping.model.dto.AuthoritiesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthoritiesService extends AbstractService<Authorities, AuthoritiesDTO> {
    Page<Authorities> filtering(AuthoritiesDTO authoritiesDTO, Pageable pageable);
}
