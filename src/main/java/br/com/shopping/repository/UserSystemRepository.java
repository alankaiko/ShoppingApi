package br.com.shopping.repository;

import br.com.shopping.acore.repository.AbstractRepository;
import br.com.shopping.model.UserSystem;
import br.com.shopping.model.dto.UserSystemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserSystemRepository extends AbstractRepository<UserSystem, UserSystemDTO, Long> {
    Page<UserSystem> filtering(UserSystemDTO userSystemDTO, Pageable pageable);
}
