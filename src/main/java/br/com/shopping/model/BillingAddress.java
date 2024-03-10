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
public class BillingAddress extends AbstractModel {
    private String address;

    private String city;

    private String state;

    private String zipcode;

    private String country;

    @OneToOne
    @JoinColumn(name = "tbl_customer_id", referencedColumnName = "id")
    private Customer customer;
}
