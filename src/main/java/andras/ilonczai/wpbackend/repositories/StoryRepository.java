package andras.ilonczai.wpbackend.repositories;

import andras.ilonczai.wpbackend.entities.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
}
