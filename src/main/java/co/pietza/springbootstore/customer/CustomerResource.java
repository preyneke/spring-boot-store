package co.pietza.springbootstore.customer;



import co.pietza.springbootstore.exceptions.CustomerNotFoundException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
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
    public MappingJacksonValue allCustomers(){

        List<Customer> allCustomers = service.allCustomers();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("cus_id","name", "email", "billingAddress");
        FilterProvider filters = new SimpleFilterProvider().addFilter("CustomerFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(allCustomers);

        mapping.setFilters(filters);



        return mapping;

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
