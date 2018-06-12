package co.pietza.springbootstore.Product;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDaoService {

    private static List<Product> products = new ArrayList<>();

    private static int productCount = 3;

    static{
        products.add(new Product("P1","Apple iPhone", "Apple","Smartphone", "The new smartphone by Apple ",new BigDecimal("200.45"), 40L));
        products.add(new Product("P2","Lenovo ThinkPad", "Lenovo", "Laptop", "The new ThinkPad by Lenovo ",new BigDecimal("600.45"), 58L));
        products.add(new Product("P3","Beats by Dre","Apple","Audio", "Headphones by Dr Dre",new BigDecimal("300.55"), 5L));
    }

    // list allProducts

    public List<Product> allProducts(){
        return products;
    }

    // saveProduct(Product product)

    public Product saveProduct(Product product){
        if(product.getId()==null){
            product.setId(String.format("P"+(++productCount)));
        }
        products.add(product);
        return product;
    }


    // Find productById(int id)

    public Product findById(String id){
        for(Product product:products){
            String targetProduct = product.getId();

            if(targetProduct.equals(id.toUpperCase())){
                return product;
            }
        }
        return null;
    }





}
