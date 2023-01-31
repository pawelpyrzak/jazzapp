package pl.teb.spring.api.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.teb.spring.api.dto.request.UserRequest;
import pl.teb.spring.api.dto.responce.UserResponse;
import pl.teb.spring.api.exception.AppExceptionHandler;
import pl.teb.spring.domain.service.UserService;
import pl.teb.spring.infrastructure.converter.DtoMapper;
import pl.teb.spring.infrastructure.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
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

    public ResponseEntity<List<UserResponse>> getUser(){
        List<UserResponse> users = userService.getUsers().stream()
                .map(dtoMapper::mapToUserResponse)
                .collect(Collectors.toList());
        log.info("retried users list");
        return ResponseEntity.ok(users);
}
    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class))),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class)))
            })
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){

         UserResponse user = dtoMapper.mapToUserResponse(userService.createUser(userRequest.getFirstName(),
                userRequest.getLastName(), userRequest.getEmail()));
        log.info("Created user");
        return ResponseEntity.ok(user);
    }
}
