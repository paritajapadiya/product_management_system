package com.productmangement.api.model;

import com.productmangement.api.util.Constant;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Constant.COLLECTION_PRODUCTS)
@Getter
@Setter
@AllArgsConstructor
public class EntityProduct extends DateAudit {
    @Id
    private String id;

    @NotBlank(message = "product_name_not_blank")
    private String name;

    private String description;

    @NotNull(message = "product_price_notnull")
    @Min(value = 0, message = "product_price_min")
    private double price;

    @NotNull(message = "product_quantity_notnull")
    @Min(value = 0, message = "product_quantity_min")
    private int quantity;
}
