package co.pietza.springbootstore.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class productResource {

    @Autowired
    private ProductDaoService service;




    // retrieveAll

    @GetMapping(path = "/products")
    public List<Product> retrieveAll(){
        return service.allProducts();
    }

    // findById
    @GetMapping(path = "/products/{id}")
    public Product findById(@PathVariable("id") int id){

        return service.findById(id);
    }
}
