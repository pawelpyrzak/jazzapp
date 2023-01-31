package pl.teb.spring.infrastructure.converter;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.teb.spring.api.dto.responce.CategoryResponce;
import pl.teb.spring.api.dto.responce.ProductResponce;
import pl.teb.spring.api.dto.responce.UserResponse;
import pl.teb.spring.infrastructure.entity.Category;
import pl.teb.spring.infrastructure.entity.Product;
import pl.teb.spring.infrastructure.entity.User;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface DtoMapper {

    @Mapping(target = "categoryUuid", source = "category.uuid")
    @Mapping(target = "categoryName", source = "category.name")
    ProductResponce mapToProductResponse(Product product);

    CategoryResponce mapToCategoryResponse(Category category);
    UserResponse mapToUserResponse(User user);
}