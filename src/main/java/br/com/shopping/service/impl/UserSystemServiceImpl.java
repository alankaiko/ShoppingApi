package br.com.shopping.service.impl;

import br.com.shopping.acore.service.impl.AbstractServiceImpl;
import br.com.shopping.model.UserSystem;
import br.com.shopping.model.dto.UserSystemDTO;
import br.com.shopping.repository.UserSystemRepository;
import br.com.shopping.service.UserSystemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserSystemServiceImpl extends AbstractServiceImpl<UserSystem, UserSystemDTO> implements UserSystemService {
    private final UserSystemRepository userSystemRepository;

    public UserSystemServiceImpl(UserSystemRepository userSystemRepository) {
        super(userSystemRepository);
        this.userSystemRepository = userSystemRepository;
    }

    @Override
    public Page<UserSystem> filtering(UserSystemDTO userSystemDTO, Pageable pageable) {
        return this.userSystemRepository.filtering(userSystemDTO, pageable);
    }
}
