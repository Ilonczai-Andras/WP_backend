package andras.ilonczai.wpbackend.repositories;

import andras.ilonczai.wpbackend.entities.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    Optional<Chapter> findById(Long id);
    List<Chapter> findAllByStoryIdOrderByChapterOrderAsc(Long storyId);
}
