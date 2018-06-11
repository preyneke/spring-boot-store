package co.pietza.springbootstore.Product;

public class Product {

    private Integer id;

    private String name;

    private String description;

    private Integer unitsInStock;

    public Product(){}


    public Product(Integer id, String name, String description, Integer unitsInStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unitsInStock = unitsInStock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(Integer unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unitsInStock=" + unitsInStock +
                '}';
    }
}
