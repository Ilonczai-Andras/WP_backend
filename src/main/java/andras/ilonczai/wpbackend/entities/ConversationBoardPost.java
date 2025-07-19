package andras.ilonczai.wpbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "conversation_board_post")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConversationBoardPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User owner;

    @ManyToOne(optional = false)
    private User poster;

    @Column(nullable = false, length = 1000)
    private String content;

    @ManyToMany
    private Set<User> mentionedUsers = new HashSet<>();

    private LocalDateTime postedAt;

    @ManyToOne
    private ConversationBoardPost parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConversationBoardPost> replies = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.postedAt = LocalDateTime.now();
    }

}
