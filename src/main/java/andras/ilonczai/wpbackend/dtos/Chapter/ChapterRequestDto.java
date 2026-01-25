package andras.ilonczai.wpbackend.dtos.Chapter;

import andras.ilonczai.wpbackend.entities.enums.StoryStatusEnum;

import java.time.LocalDateTime;

public record ChapterRequestDto(String title,
                                String content,

                                StoryStatusEnum status,
                                LocalDateTime publishDate,

                                String authorNotes,
                                String mediaUrl) {
}
