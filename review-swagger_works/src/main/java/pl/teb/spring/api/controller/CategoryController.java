package pl.teb.spring.api.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.teb.spring.api.dto.responce.CategoryResponce;
import pl.teb.spring.api.exception.AppExceptionHandler;
import pl.teb.spring.domain.service.CategoryService;
import pl.teb.spring.infrastructure.converter.DtoMapper;
import pl.teb.spring.infrastructure.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final DtoMapper dtoMapper;

    @GetMapping()
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class))),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class)))
            })
    public ResponseEntity<List<CategoryResponce>> getCategories(){
        List<CategoryResponce> categories = categoryService.getCategories().stream()
                .map(dtoMapper::mapToCategoryResponse)
                .collect(Collectors.toList());
        log.info("retried category list");
        return ResponseEntity.ok(categories);
    }
    @PostMapping()
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class))),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class)))
            })
    public ResponseEntity<CategoryResponce> createCategory(@RequestParam String categoryName) {
         Category category = categoryService.createCategory(categoryName);
         log.info("Created category");
         return ResponseEntity.ok(dtoMapper.mapToCategoryResponse(category));
    }
}





