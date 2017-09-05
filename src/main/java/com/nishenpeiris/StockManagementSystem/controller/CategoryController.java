package com.nishenpeiris.StockManagementSystem.controller;

import com.nishenpeiris.StockManagementSystem.Category;
import com.nishenpeiris.StockManagementSystem.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping(path = "/category")
public class CategoryController {
    CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping(value = "/add", method = POST)
    public ResponseEntity<?> addCategory(Category category) {
        try {
            categoryRepository.add(category);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/delete", method = DELETE)
    public ResponseEntity<?> removeCategory(Category category) {
        try {
            categoryRepository.remove(category);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/update", method = POST)
    public ResponseEntity<?> updateCategory(Category category) {
        try {
            categoryRepository.update(category);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/get", method = GET)
    public ResponseEntity<?> getCategories() {
        return null;
    }
}
