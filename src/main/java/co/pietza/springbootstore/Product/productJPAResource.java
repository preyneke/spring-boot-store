package co.pietza.springbootstore.Product;

import co.pietza.springbootstore.exceptions.ProductNotFoundException;
import co.pietza.springbootstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class productJPAResource {



    @Autowired
    private ProductRepository productRepository;



    //Created
    //output return CREATED and return Product

    @PostMapping("/jpa/products")
    public ResponseEntity<Object> createProduct(@Valid @RequestBody Product product){
        Product savedProduct = productRepository.save(product);

        // Created

        // map loaction of new product

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    // retrieveAll

    @GetMapping(path = "/jpa/products")
    public List<Product> retrieveAll(){
        return productRepository.findAll();
    }

    // findById
    @GetMapping(path = "/jpa/products/{id}")
    public Resource<Product> findById(@PathVariable("id") String id){

        Optional<Product> product= productRepository.findById(id);

        if(!product.isPresent()) {
            throw new ProductNotFoundException("id: "+id);
        } else{
            Product theProduct = product.get();
            Resource<Product> resource = new Resource<Product>(theProduct);
            ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAll());
            resource.add(linkTo.withRel("all-products"));

            return resource;
        }

    }

    @DeleteMapping(path = "/jpa/products/{id}")
    public void deleteProductById(@PathVariable("id") String id){

         productRepository.deleteById(id);


    }
}
