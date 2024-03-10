package br.com.shopping.model;

import br.com.shopping.acore.model.AbstractModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
public class UserSystem extends AbstractModel {
    private String emailId;

    private String password;

    private boolean enabled;

    @OneToOne
    @JoinColumn(name = "tbl_customer_id", referencedColumnName = "id")
    private Customer customer;
}
