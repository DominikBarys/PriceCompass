package pl.dominikbarys.service;

import org.springframework.stereotype.Service;
import pl.dominikbarys.entity.Category;
import pl.dominikbarys.entity.Product;
import pl.dominikbarys.exception.NotFoundException;
import pl.dominikbarys.exception.NotUniqueException;
import pl.dominikbarys.repository.CategoryRepository;
import pl.dominikbarys.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<Category> findAllCategories(){
        return categoryRepository.findAll();
    }

    public Category findCategoryById(Integer id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category by id " + id + " was not found"));
    }

    public Category addCategory(Category category){

        if(!categoryRepository.existsByName(category.getName())){
            List<Product> products = category.getProducts().stream()
                    .filter(product -> !productRepository.existsByName(product.getName()))
                    .toList();

            category.setProducts(products);

            return categoryRepository.save(category);
        }else{
            throw new NotUniqueException("Category with name " + category.getName() +" already exists");
        }

    }

    public Category updateCategory(Category newCategory, Integer id){

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category with id " + id +" does not exist"));


        category.setName(newCategory.getName());

        List<Product> newProducts = newCategory.getProducts().stream()
                .filter(product -> !productRepository.existsByName(product.getName()))
                .collect(Collectors.toList());

        category.setProducts(newProducts);

        return categoryRepository.save(category);

    }

    public Category addProducts(List<Product> products, Integer id){

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category with id " + id + " was not found"));

        List<Product> newProducts = products.stream()
                .filter(product -> !productRepository.existsByName(product.getName()))
                .collect(Collectors.toList());

        category.getProducts().addAll(newProducts);
        return categoryRepository.save(category);
    }

    public Category removeProducts(List<Product> products, Integer id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category with id " + id + " was not found"));

        List<String> productNames = products.stream()
                        .map(Product::getName)
                        .collect(Collectors.toList());

        category.getProducts().removeIf(product -> productNames.contains(product.getName()));

        return categoryRepository.save(category);
    }

    public void deleteCategory(Integer id){
        categoryRepository.findById(id)
                        .orElseThrow(() ->
                                new NotFoundException("Category with id " + id + " does not exist"));
        categoryRepository.deleteById(id);
    }

}
