package pl.teb.spring.infrastructure.converter;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.teb.spring.api.dto.responce.CategoryResponce;
import pl.teb.spring.api.dto.responce.ProductResponce;
import pl.teb.spring.api.dto.responce.UserResponse;
import pl.teb.spring.infrastructure.entity.Category;
import pl.teb.spring.infrastructure.entity.Product;
import pl.teb.spring.infrastructure.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-30T22:32:55+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class DtoMapperImpl implements DtoMapper {

    @Override
    public ProductResponce mapToProductResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        String categoryUuid = null;
        String categoryName = null;
        String uuid = null;
        String name = null;
        int rating = 0;
        String comment = null;
        double price = 0.0d;

        categoryUuid = productCategoryUuid( product );
        categoryName = productCategoryName( product );
        uuid = product.getUuid();
        name = product.getName();
        rating = product.getRating();
        comment = product.getComment();
        price = product.getPrice();

        ProductResponce productResponce = new ProductResponce( uuid, name, rating, comment, price, categoryUuid, categoryName );

        return productResponce;
    }

    @Override
    public CategoryResponce mapToCategoryResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        String uuid = null;
        String name = null;

        uuid = category.getUuid();
        name = category.getName();

        CategoryResponce categoryResponce = new CategoryResponce( uuid, name );

        return categoryResponce;
    }

    @Override
    public UserResponse mapToUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        String uuid = null;
        String firstName = null;
        String lastName = null;
        String email = null;

        uuid = user.getUuid();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();

        UserResponse userResponse = new UserResponse( uuid, firstName, lastName, email );

        return userResponse;
    }

    private String productCategoryUuid(Product product) {
        if ( product == null ) {
            return null;
        }
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        String uuid = category.getUuid();
        if ( uuid == null ) {
            return null;
        }
        return uuid;
    }

    private String productCategoryName(Product product) {
        if ( product == null ) {
            return null;
        }
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        String name = category.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
