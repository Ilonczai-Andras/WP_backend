package andras.ilonczai.wpbackend.entities;

import andras.ilonczai.wpbackend.entities.enums.*;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "stories")
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Lob
    private String description;

    private List<String> mainCharacters;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @ElementCollection
    private List<String> tags;

    @Enumerated(EnumType.STRING)
    private TargetAudienceEnum targetAudience;

    @Enumerated(EnumType.STRING)
    private LanguageEnum language;

    private String coverImageUrl;

    @Enumerated(EnumType.STRING)
    private CopyrightLicenseEnum copyright;

    private boolean mature;

    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("chapterOrder ASC")
    private List<Chapter> chapters;

    @ManyToOne
    private User author;

    @Enumerated(EnumType.STRING)
    private StoryStatusEnum status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

