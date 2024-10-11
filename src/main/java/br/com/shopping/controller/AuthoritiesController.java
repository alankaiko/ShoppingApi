package br.com.shopping.controller;

import br.com.shopping.acore.controller.AbstractController;
import br.com.shopping.model.Authorities;
import br.com.shopping.model.dto.AuthoritiesDTO;
import br.com.shopping.service.AuthoritiesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = AuthoritiesController.PATH)
public class AuthoritiesController extends AbstractController<Authorities, AuthoritiesDTO> {
    static final String PATH = "authorities";
    private final AuthoritiesService authoritiesService;

    public AuthoritiesController(AuthoritiesService authoritiesService) {
        super(authoritiesService);
        this.authoritiesService = authoritiesService;
    }

    @GetMapping(params = "resumo")
    public Page<Authorities> resumir(AuthoritiesDTO authoritiesDTO, Pageable page) {
        return this.authoritiesService.filtering(authoritiesDTO, page);
    }


}
