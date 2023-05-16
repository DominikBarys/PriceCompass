package pl.dominikbarys.service;

import org.springframework.stereotype.Service;
import pl.dominikbarys.entity.Category;
import pl.dominikbarys.exception.CategoryNotFoundException;
import pl.dominikbarys.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategories(){
        return categoryRepository.findAll();
    }

    public Category findCategoryById(Integer id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category by id " + id + " was not found"));
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category){
        return categoryRepository.save(category);
    }

    public void deleteCategory(Integer id){
        categoryRepository.findById(id)
                        .orElseThrow(() ->
                                new CategoryNotFoundException("Category with id " + id + " does not exist"));
        categoryRepository.deleteById(id);
    }

    public Optional<Category> findCategoryByName(Category category){
        return categoryRepository.findByName(category.getName());
    }

}
