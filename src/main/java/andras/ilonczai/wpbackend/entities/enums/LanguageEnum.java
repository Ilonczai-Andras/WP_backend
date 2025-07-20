package andras.ilonczai.wpbackend.entities.enums;

import lombok.Getter;

@Getter
public enum LanguageEnum {
    POLSKI("pl"),
    ARABIC("ar"),
    CZECH("cs"),
    DEUTSCH("de"),
    GREEK("el"),
    ENGLISH("en"),
    SPANISH("es"),
    FARSI("fa"),
    FILIPINO("fil"),
    FRENCH("fr"),
    HINDI("hi"),
    BAHASA_INDONESIA("id"),
    ITALIANO("it"),
    HEBREW("he"),
    MAGYAR("hu"),
    BAHASA_MELAYU("ms"),
    NEDERLANDS("nl"),
    PORTUGUESE("pt"),
    ROMANIAN("ro"),
    RUSSIAN("ru"),
    SLOVAK("sk"),
    SERBIAN("sr"),
    TURKISH("tr"),
    UKRAINIAN("uk"),
    VIETNAMESE("vi"),
    OTHER("other");

    private final String code;

    LanguageEnum(String code) {
        this.code = code;
    }

    public String getName() {
        return name().replace("_", " "); // Optional formatting, e.g., BAHASA_INDONESIA -> Bahasa Indonesia
    }

    public static LanguageEnum fromCode(String code) {
        for (LanguageEnum lang : LanguageEnum.values()) {
            if (lang.getCode().equalsIgnoreCase(code)) {
                return lang;
            }
        }
        throw new IllegalArgumentException("No enum constant with code " + code);
    }
}
