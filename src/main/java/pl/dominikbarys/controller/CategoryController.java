package pl.dominikbarys.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dominikbarys.dto.category.UpdateCategoryDTO;
import pl.dominikbarys.entity.Category;
import pl.dominikbarys.entity.Product;
import pl.dominikbarys.service.CategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.findAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Integer id){
        Category category = categoryService.findCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Category newCategory = categoryService.addCategory(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable("id") Integer id){
        Category updatedCategory = categoryService.updateCategory(category, id);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @PatchMapping("/addProducts/{id}")
    public ResponseEntity<Category> addProducts(@RequestBody List<Product> products, @PathVariable("id") Integer id){
        Category updatedCategory = categoryService.addProducts(products, id);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @PatchMapping("/removeProducts/{id}")
    public ResponseEntity<Category> removeProducts(@RequestBody List<Product> products, @PathVariable("id") Integer id){
        Category updatedCategory = categoryService.removeProducts(products, id);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Integer id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
