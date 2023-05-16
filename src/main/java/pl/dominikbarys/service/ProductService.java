package pl.dominikbarys.service;

import org.springframework.stereotype.Service;
import pl.dominikbarys.entity.Product;
import pl.dominikbarys.exception.ProductNotFoundException;
import pl.dominikbarys.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Product findProductById(Integer id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Producy by id " + id + " was not found"));
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProduct(Integer id){
        productRepository.deleteById(id);
    }


}
