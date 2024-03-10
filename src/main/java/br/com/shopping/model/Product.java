package br.com.shopping.model;

import br.com.shopping.acore.model.AbstractModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Getter
@Setter
@Entity
@Table
public class Product extends AbstractModel {
    private String category;

    private String description;

    private String manufacturer;

    private String name;

    private double price;

    private String unitStock;

    @Transient
    private MultipartFile productImage;
}
