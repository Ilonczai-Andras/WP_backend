package andras.ilonczai.wpbackend.entities.enums;

import lombok.Getter;

@Getter
public enum StoryStatusEnum {
    DRAFT("Draft"),
    PUBLISHED("Published"),
    ARCHIVED("Archived");

    private final String description;

    StoryStatusEnum(String description){
        this.description = description;
    }
}
