package pl.teb.spring.api.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.teb.spring.api.dto.request.ProductRequest;
import pl.teb.spring.api.dto.responce.ProductResponce;
import pl.teb.spring.api.exception.AppExceptionHandler;
import pl.teb.spring.domain.service.ProductService;
import pl.teb.spring.infrastructure.converter.DtoMapper;
import pl.teb.spring.infrastructure.entity.Product;
import pl.teb.spring.infrastructure.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final DtoMapper dtoMapper;
    private final ProductService productService;
    @PostMapping("/save")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class))),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class)))
            })
    public ResponseEntity<ProductResponce> createProduct(@RequestBody ProductRequest productRequest) {
        Product product = productService.createProduct(
                productRequest.getName(), productRequest.getRating(),
                productRequest.getPrice(), productRequest.getCategoryUuid(), productRequest.getComment()
        );
        log.info("created product");
        return ResponseEntity.ok(dtoMapper.mapToProductResponse(product));
    }
    @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class))),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class)))
            })

    public ResponseEntity<List<ProductResponce>> getProducts(){
        List<ProductResponce> products =productService.getAll().stream()
                .map(dtoMapper::mapToProductResponse)
                .collect(Collectors.toList());
        log.info("retried product list");
        return ResponseEntity.ok(products);
    }
    @PutMapping("/{uuid}")
    public ResponseEntity<ProductResponce> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable String uuid){
        Product product = productService.updateProduct(productRequest.getName(), productRequest.getRating(),
                productRequest.getPrice(), productRequest.getCategoryUuid(), productRequest.getComment(), uuid);
        log.info("updated product '{}'", uuid);
        return ResponseEntity.ok(dtoMapper.mapToProductResponse(product));
    }
}
