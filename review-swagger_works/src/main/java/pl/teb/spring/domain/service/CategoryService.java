package pl.teb.spring.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.teb.spring.infrastructure.entity.Category;
import pl.teb.spring.infrastructure.repository.CategoryRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public Category createCategory(String name){
        return categoryRepository.save(new Category(UUID.randomUUID().toString(), name));
    }
    public List<Category> getCategories(){

        return categoryRepository.findAll();
    }
}
