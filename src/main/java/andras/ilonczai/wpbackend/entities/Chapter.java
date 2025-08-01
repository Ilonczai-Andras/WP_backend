package andras.ilonczai.wpbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "chapters")
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "chapter_order")
    private Integer chapterOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id", nullable = false)
    private Story story;

    // --- Additional fields ---

    @Column(name = "is_published", nullable = false)
    private boolean isPublished;

    @Column(name = "publish_date")
    private LocalDateTime publishDate;

    @Column(name = "views", nullable = false)
    private Long views;

    @Column(name = "votes", nullable = false)
    private Long votes;

    @Column(name = "comments_count", nullable = false)
    private Integer commentsCount;

    @Column(name = "author_notes", columnDefinition = "TEXT")
    private String authorNotes;

    @Column(name = "read_time_minutes")
    private Integer readTimeMinutes;

    @Column(name = "word_count")
    private Integer wordCount;

    @Column(name = "media_url", length = 1024)
    private String mediaUrl;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
        this.views = 0L;
        this.votes = 0L;
        this.commentsCount = 0;
        if (this.wordCount == null && content != null) {
            this.wordCount = content.trim().split("\\s+").length;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
