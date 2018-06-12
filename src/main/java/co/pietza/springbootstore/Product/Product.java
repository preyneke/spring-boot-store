package co.pietza.springbootstore.Product;

import java.math.BigDecimal;

public class Product {

    private String id;

    private String name;

    private String manufacturer;

    private String catagory;

    private String description;

    private BigDecimal unitPrice;

    private Long unitsInStock;

    public Product(){}


    public Product(String id, String name, String manufacturer, String catagory, String description, BigDecimal unitPrice, Long unitsInStock) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.catagory = catagory;
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

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
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
