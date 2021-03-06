package co.pietza.springbootstore.Product;

import co.pietza.springbootstore.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class productResource {

    @Autowired
    private ProductDaoService service;



    //Created
    //output return CREATED and return Product

    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@Valid @RequestBody Product product){
        Product savedProduct = service.saveProduct(product);

        // Created

        // map loaction of new product

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    // retrieveAll

    @GetMapping(path = "/products")
    public List<Product> retrieveAll(){
        return service.allProducts();
    }

    // findById
    @GetMapping(path = "/products/{id}")
    public Resource<Product> findById(@PathVariable("id") String id){

        Product product= service.findById(id);

        if(product==null) throw new ProductNotFoundException("id: "+id);
        Resource<Product> resource = new Resource<Product>(product);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAll());
        resource.add(linkTo.withRel("all-products"));

        return resource;
    }

    @DeleteMapping(path = "/products/{id}")
    public void deleteProductById(@PathVariable("id") String id){

        Product product= service.deleteById(id);
        if(product==null) throw new ProductNotFoundException("id: "+id);

    }
}
