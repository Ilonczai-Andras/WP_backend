package andras.ilonczai.wpbackend.repositories;

import andras.ilonczai.wpbackend.entities.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    Optional<Chapter> findById(Long id);
    List<Chapter> findAllByStoryIdOrderByChapterOrderAsc(Long storyId);

    @Query("SELECT COALESCE(MAX(c.chapterOrder), 0) FROM Chapter c WHERE c.story.id = :storyId")
    int findMaxOrderByStoryId(@Param("storyId") Long storyId);
}
