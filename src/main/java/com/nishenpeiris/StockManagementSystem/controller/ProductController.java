package com.nishenpeiris.StockManagementSystem.controller;

import com.nishenpeiris.StockManagementSystem.Product;
import com.nishenpeiris.StockManagementSystem.ProductNameAlreadyInUseException;
import com.nishenpeiris.StockManagementSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping(path = "/product")
public class ProductController {
    ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(method = GET)
    public ResponseEntity<?> getProducts() {
        try {
            return new ResponseEntity<>(productRepository.query(null), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = POST)
    public ResponseEntity<?> create(@RequestBody Product product) {
        try {
            productRepository.add(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ProductNameAlreadyInUseException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = PUT)
    public ResponseEntity<?> update(@RequestBody Product product) {
        try {
            productRepository.update(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = DELETE)
    public ResponseEntity<?> delete(@RequestBody Product product) {
        try {
            productRepository.remove(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
