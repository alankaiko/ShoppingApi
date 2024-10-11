package br.com.shopping.controller;

import br.com.shopping.acore.controller.AbstractController;
import br.com.shopping.model.UserSystem;
import br.com.shopping.model.dto.UserSystemDTO;
import br.com.shopping.service.UserSystemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = UserSystemController.PATH)
public class UserSystemController extends AbstractController<UserSystem, UserSystemDTO> {
    static final String PATH = "usersystem";
    private final UserSystemService userSystemService;

    public UserSystemController(UserSystemService userSystemService) {
        super(userSystemService);
        this.userSystemService = userSystemService;
    }

    @GetMapping(params = "resumo")
    public Page<UserSystem> resumir(UserSystemDTO userSystemDTO, Pageable page) {
        return this.userSystemService.filtering(userSystemDTO, page);
    }


}
