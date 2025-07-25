package andras.ilonczai.wpbackend.repositories;

import andras.ilonczai.wpbackend.entities.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
    Optional<Story> findById(Long id);
    List<Story> findAllByAuthorId(Long authorId);

}
