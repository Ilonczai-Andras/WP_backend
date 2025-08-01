package andras.ilonczai.wpbackend.entities.enums;

import lombok.Getter;

@Getter
public enum TargetAudienceEnum {
    YOUNG_ADULT("Young Adult (13-18 years of age)"),
    NEW_ADULT("New Adult (18-25 years of age)"),
    ADULT("Adult (25+ years of age)");

    private final String description;

    TargetAudienceEnum(String description) {
        this.description = description;
    }

}
