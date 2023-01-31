package pl.teb.spring;


import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.teb.spring.infrastructure.entity.Category;
import pl.teb.spring.infrastructure.entity.Product;
import pl.teb.spring.infrastructure.entity.User;
import pl.teb.spring.infrastructure.repository.CategoryRepository;
import pl.teb.spring.infrastructure.repository.ProductRepository;
import pl.teb.spring.infrastructure.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CategoryRepository categoryRepository, UserRepository userRepository,
                                        ProductRepository productRepository) {
        return args -> {
            List<Category> categories = generateCategory();
            categoryRepository.saveAll(categories);
            userRepository.saveAll(generateUser());
            productRepository.saveAll(generateProducts(categories));

        };
    }

    List<Category> generateCategory() {
        Faker faker = new Faker();
        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            categories.add(new Category(UUID.randomUUID().toString(), faker.number().digit()));
        }
        return categories;
    }

    List<User> generateUser() {
        Faker faker = new Faker();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();

            users.add(new User(UUID.randomUUID().toString(), firstName, lastName, generateEmail(firstName, lastName)));
        }
        return users;
    }
    String generateEmail(String firstName, String lastName){
        return firstName + lastName + "@gmail.com";
    }
    List<Product> generateProducts(List<Category> categories){
        Faker faker = new Faker();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            products.add(new Product(UUID.randomUUID().toString(), faker.tea().type(),Integer.parseInt(faker.number().digits(1)),
                    faker.movie().quote(),Double.parseDouble(faker.number().digits(4)), categories.get(i)));
        }
        return products;
    }


}
