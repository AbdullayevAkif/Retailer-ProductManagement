package com.example.retailerproductmanagement.service;

import com.example.retailerproductmanagement.controller.expection.RetailerNotFoundException;
import com.example.retailerproductmanagement.entity.Product;
import com.example.retailerproductmanagement.entity.Retailer;
import com.example.retailerproductmanagement.repository.RetailerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RetailerService {

    private final  RetailerRepository retailerRepository;


    public RetailerService(RetailerRepository retailerRepository) {
        this.retailerRepository = retailerRepository;
    }


    public List<Retailer> findAll() {
        return retailerRepository.findAll();
    }

    public Retailer findById(Long id) {
        return retailerRepository.findById(id).orElseThrow(()->new RetailerNotFoundException("Retailer Not Found"));
    }

    public List<Product> findByRetailer(Long id) {
        Retailer retailer = retailerRepository.findById(id).get();

        return retailer.getProducts().stream().filter(product -> product.getRetailer().equals(retailer)).collect(Collectors.toList());
    }


    public Retailer createRetailer(Retailer retailer) {
        return retailerRepository.save(retailer);
    }

    public String update(Long id, String name, String email) {
        Optional<Retailer> lastRetailer = retailerRepository.findById(id);
        lastRetailer.orElseThrow(()->new RetailerNotFoundException("Retailer not found"));
        if (lastRetailer.isPresent()) {
            Retailer retailer = lastRetailer.get();
            retailer.setName(name);
            retailer.setEmail(email);
            retailerRepository.save(retailer);
            return "Update successful"+retailer.getName();
        }

        return "Update failed";
    }

    public String setOrderStatus(Long id, Product newProduct   ) {
        Optional<Retailer> lastRetailer = retailerRepository.findById(id);
        lastRetailer.orElseThrow(() -> new RetailerNotFoundException("Retailer not found"));
        if (lastRetailer.isPresent()) {
            Retailer retailer = lastRetailer.get();
            retailer.getProducts().forEach(product -> {
                if (product.equals(newProduct)) {
                    retailer.getProducts().remove(product);
                    retailer.getProducts().add(newProduct);
                    retailerRepository.save(retailer);
                }
               });
        }
        return "update successful";
    }


    public Retailer deleteRetailer(Long id) {
       Retailer retailer = retailerRepository.findById(id).orElseThrow(() -> new RetailerNotFoundException("Retailer not found"));
       retailerRepository.delete(retailer);
       return retailer;
    }
}
