package pl.teb.spring.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.teb.spring.domain.exception.AppExceptionCode;
import pl.teb.spring.infrastructure.entity.Category;
import pl.teb.spring.infrastructure.entity.Product;
import pl.teb.spring.infrastructure.repository.CategoryRepository;
import pl.teb.spring.infrastructure.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public Product createProduct(String name, int rating, double price, String categoryUuid, String comment) {
        return productRepository.save(new Product(UUID.randomUUID().
                toString(), name, rating, comment, price, getCategory(categoryUuid)));
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product updateProduct(String name, int rating, double price, String categoryUuid, String comment, String uuid) {
        Product product = productRepository.findByUuid(uuid)
                .orElseThrow(AppExceptionCode.NO_SUCH_PRODUCT::createException);
        Product toUpdate = new Product(product.getId(), product.getUuid(), name, rating, comment, price, getCategory(categoryUuid));
        return productRepository.save(toUpdate);
    }

    private Category getCategory(String categoryUuid) {
        return categoryRepository.findByUuid(categoryUuid)
                .orElseThrow(AppExceptionCode.NO_SUCH_CATEGORY::createException);
    }

}
