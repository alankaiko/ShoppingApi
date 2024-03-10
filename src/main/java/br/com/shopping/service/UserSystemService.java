package br.com.shopping.service;

import br.com.shopping.acore.service.AbstractService;
import br.com.shopping.model.UserSystem;
import br.com.shopping.model.dto.UserSystemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserSystemService extends AbstractService<UserSystem, UserSystemDTO> {
    Page<UserSystem> filtering(UserSystemDTO userSystemDTO, Pageable pageable);
}
