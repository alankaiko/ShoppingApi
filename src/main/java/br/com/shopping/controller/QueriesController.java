package br.com.shopping.controller;

import br.com.shopping.acore.controller.AbstractController;
import br.com.shopping.model.Queries;
import br.com.shopping.model.dto.QueriesDTO;
import br.com.shopping.service.QueriesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = QueriesController.PATH)
public class QueriesController extends AbstractController<Queries, QueriesDTO> {
    static final String PATH = "queries";
    private final QueriesService queriesService;

    public QueriesController(QueriesService queriesService) {
        super(queriesService);
        this.queriesService = queriesService;
    }

    @GetMapping(params = "resumo")
    public Page<Queries> resumir(QueriesDTO queriesDTO, Pageable page) {
        return this.queriesService.filtering(queriesDTO, page);
    }


}
