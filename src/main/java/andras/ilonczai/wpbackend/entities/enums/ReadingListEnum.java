package andras.ilonczai.wpbackend.entities.enums;

import lombok.Getter;

@Getter
public enum ReadingListEnum {
    DEFAULT("Default"),
    LIKED("Liked"),
    CUSTOM("Custom");

    private final String code;

    ReadingListEnum(String code) {
        this.code = code;
    }

    public String getName() {
        return name().replace("_", " ");
    }

    public static ReadingListEnum fromCode(String code) {
        for (ReadingListEnum listItem : ReadingListEnum.values()) {
            if (listItem.getCode().equalsIgnoreCase(code)) {
                return listItem;
            }
        }
        throw new IllegalArgumentException("No enum constant with code " + code);
    }
}
