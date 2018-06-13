package co.pietza.springbootstore.Product;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigDecimal;
@Entity
public class Product {

    @Id
    @GenericGenerator(name="product_id", strategy = "co.pietza.springbootstore.generator.ProductIdGenerator")
    @GeneratedValue(generator = "product_id")
    @Column(name = "product_id")
    private String id;
    @Column(name = "name")
    @NotEmpty(message = "please enter product name")
    @Size(min=2, message = "Please enter product name with at least 2 characters")
    private String name;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "category")
    private String category;

    @Column(name="description")
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

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", unitsInStock=" + unitsInStock +
                '}';
    }
}
