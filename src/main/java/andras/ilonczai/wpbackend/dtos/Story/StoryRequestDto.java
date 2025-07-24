package andras.ilonczai.wpbackend.dtos.Story;

import andras.ilonczai.wpbackend.entities.enums.CategoryEnum;
import andras.ilonczai.wpbackend.entities.enums.CopyrightLicenseEnum;
import andras.ilonczai.wpbackend.entities.enums.LanguageEnum;
import andras.ilonczai.wpbackend.entities.enums.TargetAudienceEnum;

import java.util.List;

public record StoryRequestDto(String title,
                              String description,
                              List<String> mainCharacters,
                              CategoryEnum category,
                              List<String> tags,
                              TargetAudienceEnum targetAudience,
                              LanguageEnum language,
                              CopyrightLicenseEnum copyright,
                              boolean mature,
                              String coverImageUrl) {
}
