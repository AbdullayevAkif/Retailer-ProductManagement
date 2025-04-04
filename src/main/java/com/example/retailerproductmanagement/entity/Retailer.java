package com.example.retailerproductmanagement.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "retailer")
@Entity
public class Retailer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
       Long id;

    @NotBlank(message = "can not be empty")
    String name;

    @Email(message = "Invalid Format")
    String email;

    @OneToMany( fetch = FetchType.LAZY , cascade = CascadeType.MERGE)
    @JsonIgnore
    List<Product> products;



}
