package pl.teb.spring.api.dto.responce;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserResponse {
    private String uuid;
    private String firstName;
    private String lastName;
    private String email;
}
