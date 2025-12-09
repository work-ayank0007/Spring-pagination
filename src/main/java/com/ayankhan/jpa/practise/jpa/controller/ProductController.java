package com.ayankhan.jpa.practise.jpa.controller;

import com.ayankhan.jpa.practise.jpa.entity.ProductEntity;
import com.ayankhan.jpa.practise.jpa.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/product")
@AllArgsConstructor
public class ProductController {

    private final ProductRepository repository;

    @GetMapping
    public String getProducts(@RequestParam(required = false,name = "sortBy") String sortBy){
        return sortBy;
    }
    @GetMapping(path = "/data")
    public Page<ProductEntity> getAllProduct(
            @RequestParam(required = false,name = "sortBy" , defaultValue = "id") String sortBy,
            @RequestParam(required = false,name = "pageNo" ,defaultValue = "0") int pageNo,
            @RequestParam(required = false,name = "pageSize",defaultValue = "5") int pageSize,
            @RequestParam(required = false,name = "sortOrder",defaultValue = "asc") String order){
        Pageable page = PageRequest.of(pageNo,pageSize,Sort.by(Sort.Direction.fromString(order),sortBy));
        return repository.findAll(page);
    }
}
