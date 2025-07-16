package andras.ilonczai.wpbackend.repositories;

import andras.ilonczai.wpbackend.dtos.UserDto;
import andras.ilonczai.wpbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
    List<User> findByUserNameContainingIgnoreCase(String userName);
}
