package co.pietza.springbootstore.customer;


import co.pietza.springbootstore.exceptions.CustomerNotFoundException;
import co.pietza.springbootstore.repository.CustomerRepository;
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
public class CustomerJPAResource {



    @Autowired
    private CustomerRepository customerRepository;


    // Create Customer
    @PostMapping("/jpa/customers")
    public ResponseEntity<Object> saveCustomer(@Valid @RequestBody Customer customer){

        Customer savedCustomer = customerRepository.save(customer);

        // CREATED

        // map loacation of new Customer

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{custId}")
                .buildAndExpand(savedCustomer.getCust_id()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/jpa/customers")
    public List<Customer> allCustomers(){

        List<Customer> allCustomers = customerRepository.findAll();





        return allCustomers;

    }

    // Get customer by Id
    @GetMapping("/jpa/customers/{custId}")
    public Resource<Customer> findCustomerById(@PathVariable("custId") String custId){
        Optional<Customer> customer = customerRepository.findById(custId.toUpperCase());

        if(!customer.isPresent()) {
            throw new CustomerNotFoundException("id: "+ custId);
        } else{
            Customer theCustomer = customer.get();
            Resource<Customer> resource = new Resource<Customer>(theCustomer);
            ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).allCustomers());
            resource.add(linkTo.withRel("all-customers"));

            return resource;

        }


    }

    // Delete Customer
    @DeleteMapping("/jpa/customers/{custId}")
    public void deleteCustomerById(@PathVariable("custId") String custId){
        customerRepository.deleteById(custId.toUpperCase());




    }


}
