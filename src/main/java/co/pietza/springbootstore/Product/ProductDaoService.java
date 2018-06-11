package co.pietza.springbootstore.Product;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDaoService {

    private static List<Product> products = new ArrayList<>();

    private static int productCount = 3;

    static{
        products.add(new Product(1,"Apple iPhone", "The new smartphone by Apple ", 40));
        products.add(new Product(2,"Lenovo ThinkPad", "The new ThinkPad by Lenovo ", 58));
        products.add(new Product(3,"Beats by Dre", "Headphones by Dr Dre", 5));
    }

    // list allProducts

    public List<Product> allProducts(){
        return products;
    }

    // saveProduct(Product product)

    public Product saveProduct(Product product){
        if(product.getId()==null){
            product.setId(++productCount);
        }
        products.add(product);
        return product;
    }


    // Find productById(int id)

    public Product findById(int id){
        for(Product product:products){
            if(product.getId()==id){
                return product;
            }
        }
        return null;
    }





}
