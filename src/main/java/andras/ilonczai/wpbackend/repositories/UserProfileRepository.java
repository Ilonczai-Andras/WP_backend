package andras.ilonczai.wpbackend.repositories;

import andras.ilonczai.wpbackend.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
