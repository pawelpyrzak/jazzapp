package pl.teb.spring.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.teb.spring.infrastructure.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
