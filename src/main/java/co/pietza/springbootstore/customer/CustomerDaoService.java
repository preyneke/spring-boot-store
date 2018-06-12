package co.pietza.springbootstore.customer;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CustomerDaoService {

    private static List<Customer> customers = new ArrayList<>();
    private int customerCount = 103;

    static{
        customers.add(new Customer("C101", "Francois","francois@gmail.com", "password","2 Skeen Blvd", "35 Manhatten", "BedfordView", "South Africa", 2008 ));
        customers.add(new Customer("C102", "Pieter","pieter@gmail.com", "password","2 Skeen Blvd", "35 Manhatten", "BedfordView", "South Africa", 2008 ));
        customers.add(new Customer("C103", "Rolene","rolene@gmail.com", "password","19 Plumer", "N/A", "Woodstock", "South Africa", 3000 ));

    }


    // show all Customers

    public List<Customer> allCustomers(){
        return customers;
    }

    public Customer saveCustomer(Customer customer){
        if(customer.getCust_id()==null){
            customer.setCust_id(String.format("C"+(++customerCount)));
        }
        customers.add(customer);
        return customer;
    }

    // find One customer

    public Customer findCustomerById(String id){
        for(Customer customer: customers){
            String targetCustomer = customer.getCust_id();
            if(targetCustomer.equals(id.toUpperCase())){
                return customer;
            }
        }
        return null;
    }

    // Delete Customer

    public Customer deleteCustomerById(String id){
        Iterator<Customer> iterator = customers.iterator();
        while(iterator.hasNext()){
            Customer customer = iterator.next();
            String targetCustomer = customer.getCust_id();
            if(targetCustomer.equals(id.toUpperCase())){
                iterator.remove();
                return customer;
            }
        }
        return null;
    }
}
