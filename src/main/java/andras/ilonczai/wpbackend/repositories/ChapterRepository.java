package andras.ilonczai.wpbackend.repositories;

import andras.ilonczai.wpbackend.entities.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
}
