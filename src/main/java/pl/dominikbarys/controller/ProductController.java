package pl.dominikbarys.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dominikbarys.dto.product.ProductDTO;
import pl.dominikbarys.dto.product.UpdateProductDTO;
import pl.dominikbarys.entity.Product;
import pl.dominikbarys.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable("id") Integer id){
        ProductDTO product = productService.findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product newProduct = productService.addProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody UpdateProductDTO product, @PathVariable("id") Integer id){
        Product updatedProduct = productService.updateProduct(product, id);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{productId}/category/{categoryId}")
    public ResponseEntity<Product> assignCategoryToProduct(
            @PathVariable("productId") Integer productId,
            @PathVariable("categoryId") Integer categoryId
    ){
        Product updatedCategoryProduct = productService.assignCategoryToProduct(productId, categoryId);
        return new ResponseEntity<>(updatedCategoryProduct, HttpStatus.OK);
    }

}
