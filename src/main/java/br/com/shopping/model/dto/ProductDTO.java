package br.com.shopping.model.dto;

import br.com.shopping.acore.model.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO extends AbstractDTO {
    private String category;

    private String name;
}
