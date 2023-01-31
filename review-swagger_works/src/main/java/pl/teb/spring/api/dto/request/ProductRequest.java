package pl.teb.spring.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.teb.spring.infrastructure.entity.Category;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {

    @NotNull
    @NotBlank
    @NotEmpty
    @Max(value =200 )
    private String name;
    private String image;
    private int rating;
    @NotNull
    @NotBlank
    @NotEmpty
    @Max(value =1000)
    private String comment;
    private double price;
    private String categoryUuid;


}
