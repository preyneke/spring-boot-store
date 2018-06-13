package co.pietza.springbootstore.customer;



import co.pietza.springbootstore.exceptions.CustomerNotFoundException;
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
public class CustomerResource {

    @Autowired
    private CustomerDaoService service;


    // Create Customer
    @PostMapping("/customers")
    public ResponseEntity<Object> saveCustomer(@Valid @RequestBody Customer customer){

        Customer savedCustomer = service.saveCustomer(customer);

        // CREATED

        // map loacation of new Customer

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{custId}")
                .buildAndExpand(savedCustomer.getCust_id()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/customers")
    public List<Customer> allCustomers(){

        return service.allCustomers();
    }

    // Get customer by Id
    @GetMapping("/customers/{custId}")
    public Resource<Customer> findCustomerById(@PathVariable("custId") String custId){
        Customer customer = service.findCustomerById(custId);

        if(customer==null) throw new CustomerNotFoundException("id: "+ custId);

        Resource<Customer> resource = new Resource<Customer>(customer);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).allCustomers());
        resource.add(linkTo.withRel("all-customers"));

        return resource;
    }

    // Delete Customer
    @DeleteMapping("/customers/{custId}")
    public void deleteCustomerById(@PathVariable("custId") String custId){
        Customer customer = service.deleteCustomerById(custId);

        if(customer==null) throw new CustomerNotFoundException("id: "+ custId);


    }


}
