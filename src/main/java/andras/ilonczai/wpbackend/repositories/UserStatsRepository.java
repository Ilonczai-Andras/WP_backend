package andras.ilonczai.wpbackend.repositories;

import andras.ilonczai.wpbackend.entities.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatsRepository extends JpaRepository<UserStats, Long> {
}
