package andras.ilonczai.wpbackend.services;

import andras.ilonczai.wpbackend.entities.Language;
import andras.ilonczai.wpbackend.entities.enums.LanguageEnum;
import andras.ilonczai.wpbackend.repositories.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepository languageRepository;

    public void populateLanguages() {
        if (languageRepository.count() > 0) {
            return;
        }

        for (LanguageEnum lang : LanguageEnum.values()) {
            Language languageEntity = Language.builder()
                    .name(lang.name())
                    .code(lang.getCode())
                    .build();

            languageRepository.save(languageEntity);
        }
    }
}
