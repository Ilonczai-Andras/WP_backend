package andras.ilonczai.wpbackend.entities.enums;

import lombok.Getter;

@Getter
public enum CopyrightLicenseEnum {
    ALL_RIGHTS_RESERVED("All Rights Reserved"),
    PUBLIC_DOMAIN("Public Domain"),
    CREATIVE_COMMONS_ATTRIBUTION("Creative Commons (CC) Attribution"),
    CREATIVE_COMMONS_ATTRIBUTION_NON_COMMERCIAL("Creative Commons (CC) Attrib. NonCommercial"),
    CREATIVE_COMMONS_ATTRIBUTION_NON_COMMERCIAL_NO_DERIVS("Creative Commons (CC) Attrib. NonComm. NoDerivs"),
    CREATIVE_COMMONS_ATTRIBUTION_NON_COMMERCIAL_SHARE_ALIKE("Creative Commons (CC) Attrib. NonComm. ShareAlike"),
    CREATIVE_COMMONS_ATTRIBUTION_SHARE_ALIKE("Creative Commons (CC) Attribution-ShareAlike"),
    CREATIVE_COMMONS_ATTRIBUTION_NO_DERIVS("Creative Commons (CC) Attribution-NoDerivs");

    private final String description;

    CopyrightLicenseEnum(String description) {
        this.description = description;
    }
}
