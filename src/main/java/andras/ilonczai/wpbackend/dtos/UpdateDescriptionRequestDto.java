package andras.ilonczai.wpbackend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateDescriptionRequestDto(@NotBlank @Size(max = 1000) String description) {
}
