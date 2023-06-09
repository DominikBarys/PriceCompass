package pl.dominikbarys.service;

import org.springframework.stereotype.Service;
import pl.dominikbarys.dto.product.ProductDTO;
import pl.dominikbarys.dto.product.UpdateProductDTO;
import pl.dominikbarys.entity.Category;
import pl.dominikbarys.entity.Product;
import pl.dominikbarys.exception.NotFoundException;
import pl.dominikbarys.exception.NotUniqueException;
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
                .map(this::convertProductToProductDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO findProductById(Integer id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product by id " + id + " was not found"));

        return convertProductToProductDTO(product);
    }

    public Product addProduct(Product product){
        if(!productRepository.existsByName(product.getName())){
            return productRepository.save(product);
        }else{
            throw new NotUniqueException("Product with name " + product.getName() +" already exists");
        }
    }

    public Product updateProduct(UpdateProductDTO newProduct, Integer id){

            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Product with id " + id +" does not exist"));

            product.setName(newProduct.getName());

            if(newProduct.getCategory() != null){
                Category category = categoryRepository.findByName(newProduct.getCategory())
                        .orElseThrow(()-> new NotFoundException(
                                "Category with name " + newProduct.getCategory() + " does not exist"
                        ));
                product.setCategory(category);
            }

            return productRepository.save(product);
    }

    public void deleteProduct(Integer id){
        productRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Product with id " + id +" does not exist"));
        productRepository.deleteById(id);
    }

    public ProductDTO convertProductToProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        if(product.getCategory() != null)
            productDTO.setCategory(product.getCategory().getName());

        return productDTO;
    }

    public Product assignCategoryToProduct(Integer productId, Integer categoryId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product by id " + productId + " was not found"));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category by id " + categoryId + " was not found"));
        product.setCategory(category);
        return productRepository.save(product);
    }

}
