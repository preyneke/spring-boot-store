package co.pietza.springbootstore.Product;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class Product {

    private String id;
    @NotEmpty(message = "please enter product name")
    @Size(min=2, message = "Please enter product name with at least 2 characters")
    private String name;

    private String manufacturer;

    private String category;

    private String description;

    @Min(value = 1)
    @Digits(integer = 8, fraction = 2, message = "Please enter valid amount")
    private BigDecimal unitPrice;

    @NotNull(message = "Can not be blank")
    @Min(value = 1, message = "Must have one or more items in stock.")
    private Long unitsInStock;

    public Product(){}


    public Product(String id, String name, String manufacturer, String category, String description, BigDecimal unitPrice, Long unitsInStock) {
        this.id = id;

        this.name = name;
        this.manufacturer = manufacturer;
        this.category = category;
        this.description = description;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(Long unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", unitsInStock=" + unitsInStock +
                '}';
    }
}
