package br.com.shopping.acore.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AbstractDTO implements Serializable {
    private int page;
    private int itensForPage;
}
