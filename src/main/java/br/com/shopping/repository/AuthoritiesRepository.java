package br.com.shopping.repository;

import br.com.shopping.acore.repository.AbstractRepository;
import br.com.shopping.model.Authorities;
import br.com.shopping.model.dto.AuthoritiesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AuthoritiesRepository extends AbstractRepository<Authorities, AuthoritiesDTO, Long> {
    Page<Authorities> filtering(AuthoritiesDTO authoritiesDTO, Pageable pageable);
}
