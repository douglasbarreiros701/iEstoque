package com.iestoque.api.domain.product;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iestoque.api.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity (name = "products")
@Table (name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProductsJPA {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String productname;
    private String brand;
    private String location;
    private String batch;
    private String due_date;
    private String factory_date;
    private String product_type;

    @ManyToOne
    @JoinColumn(name = "myuser_id")
    @JsonIgnore
    private User user;

    public ProductsJPA(ProductsDTO product) {
        this.productname = product.productname();
        this.brand = product.brand();
        this.location = product.location();
        this.batch = product.batch();
        this.due_date = product.due_date();
        this.factory_date = product.factory_date();
        this.product_type = product.product_type();
    }


}
