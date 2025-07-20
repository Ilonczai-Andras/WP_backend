package andras.ilonczai.wpbackend.repositories;

import andras.ilonczai.wpbackend.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}
