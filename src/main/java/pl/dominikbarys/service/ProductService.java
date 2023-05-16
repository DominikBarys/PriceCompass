package pl.dominikbarys.service;

import org.springframework.stereotype.Service;
import pl.dominikbarys.dto.ProductDTO;
import pl.dominikbarys.entity.Category;
import pl.dominikbarys.entity.Product;
import pl.dominikbarys.exception.CategoryNotFoundException;
import pl.dominikbarys.exception.ProductNotFoundException;
import pl.dominikbarys.repository.CategoryRepository;
import pl.dominikbarys.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductDTO> findAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(this::convertProductToDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO findProductById(Integer id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product by id " + id + " was not found"));

        return convertProductToDTO(product);
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProduct(Integer id){
        productRepository.findById(id)
                        .orElseThrow(() -> new ProductNotFoundException("Product with id " + id +" does not exist"));
        productRepository.deleteById(id);
    }

    public ProductDTO convertProductToDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        if(product.getCategory() != null)
            productDTO.setCategory(product.getCategory().getName());

        return productDTO;
    }

    public Product assignCategoryToProduct(Integer productId, Integer categoryId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product by id " + productId + " was not found"));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category by id " + categoryId + " was not found"));
        product.setCategory(category);
        return productRepository.save(product);
    }

}
