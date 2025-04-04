package com.example.retailerproductmanagement.controller;

import com.example.retailerproductmanagement.entity.Product;
import com.example.retailerproductmanagement.entity.Retailer;
import com.example.retailerproductmanagement.service.RetailerService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/retailers")
public class RetailerController {

    private final RetailerService retailerService;


    public RetailerController(RetailerService retailerService) {
        this.retailerService = retailerService;
    }

    @GetMapping("/getAll")
    public List<Retailer> getAllRetailers() {
        return retailerService.findAll();
    }


    @GetMapping("/getById")
    public Retailer retailerById( @RequestParam Long id){
        return retailerService.findById(id);
    }

    @GetMapping("/getProductsByRetailer")
    public List<Product> getProductsByRetailer(@RequestParam Long id) {
        return retailerService.findByRetailer(id);
    }


    @PostMapping("/createRetailer")
    public Retailer addRetailer(@RequestBody Retailer retailer) {
        return retailerService.createRetailer(retailer);
    }


    @PutMapping("/setRetailer")
    public String updateRetailer(@RequestParam Long id , @RequestParam String name ,@RequestParam String email ) {
         return retailerService.update(id, name, email);
    }

    @PutMapping("/setOrder")
    public String setOrder(@RequestParam Long id , @RequestBody Product product) {
        return retailerService.setOrderStatus(id, product);
    }


    @DeleteMapping("/removeRetailer")
    public Retailer removeRetailer(@RequestParam Long id ) {
        return retailerService.deleteRetailer(id);
    }
}
