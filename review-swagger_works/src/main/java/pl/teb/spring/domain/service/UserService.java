package pl.teb.spring.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.teb.spring.infrastructure.entity.Category;
import pl.teb.spring.infrastructure.entity.User;
import pl.teb.spring.infrastructure.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User createUser(String firstName, String lastName, String email){
        return userRepository.save(new User(UUID.randomUUID().toString(), firstName, lastName, email));
    }
    public List<User> getUsers(){

        return userRepository.findAll();
    }
}
