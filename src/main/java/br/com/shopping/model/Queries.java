package br.com.shopping.model;

import br.com.shopping.acore.model.AbstractModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
public class Queries extends AbstractModel {

    private String email;

    private String subject;

    private String message;
}
