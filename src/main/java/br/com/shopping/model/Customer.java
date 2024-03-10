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
public class Customer extends AbstractModel {
    private String firstName;

    private String lastName;

    private String customerPhone;

    @OneToOne
    @JoinColumn(name = "tbl_shipping_address_id", referencedColumnName = "id")
    private ShippingAddress shippingAddress;

    @OneToOne
    @JoinColumn(name = "tbl_billing_address_id", referencedColumnName = "id")
    private BillingAddress billingAddress;

    @OneToOne
    @JoinColumn(name = "tbl_users_id", referencedColumnName = "id")
    private UserSystem userSystem;

    @OneToOne
    @JoinColumn(name = "tbl_cart_id", referencedColumnName = "id")
    private Cart cart;
}
