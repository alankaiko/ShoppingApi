package br.com.shopping.acore.service;


import br.com.shopping.acore.model.AbstractDTO;
import br.com.shopping.acore.model.AbstractModel;

import java.util.List;

public interface AbstractService<T extends AbstractModel, D extends AbstractDTO> {
    T salvar(T entidade);

    void deletar(Long codigo);

    T buscarId(Long codigo);

    List<T> listar();
}
