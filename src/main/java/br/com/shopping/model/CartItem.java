package br.com.shopping.model;

import br.com.shopping.acore.model.AbstractModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
public class CartItem extends AbstractModel {
    private int quality;

    private double price;

    @ManyToOne
    @JoinColumn(name = "tbl_product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "tbl_cart_id", referencedColumnName = "id")
    private Cart cart;
}
