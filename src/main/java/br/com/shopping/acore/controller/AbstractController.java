package br.com.shopping.acore.controller;

import br.com.shopping.acore.model.AbstractDTO;
import br.com.shopping.acore.model.AbstractModel;
import br.com.shopping.acore.service.AbstractService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public abstract class AbstractController<T extends AbstractModel, D extends AbstractDTO> {
    private final AbstractService<T, D> service;

    public AbstractController(AbstractService<T, D> service) {
        this.service = service;
    }

    @PostMapping
    public T salvar(@Valid @RequestBody T entidade) {
        return this.service.salvar(entidade);
    }

    @DeleteMapping
    public void deletar(@PathVariable Long codigo) {
        this.service.deletar(codigo);
    }

    @GetMapping({"{codigo}"})
    public T buscarId(@PathVariable Long codigo) {
        return this.service.buscarId(codigo);
    }

    @GetMapping
    public List<T> listar() {
        return this.service.listar();
    }
}
