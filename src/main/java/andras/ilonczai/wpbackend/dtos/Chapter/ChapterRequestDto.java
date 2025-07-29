package andras.ilonczai.wpbackend.dtos.Chapter;

import java.time.LocalDateTime;

public record ChapterRequestDto(String title,
                                String content,

                                boolean isPublished,
                                LocalDateTime publishDate,

                                String authorNotes) {
}
