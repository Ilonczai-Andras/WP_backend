package andras.ilonczai.wpbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "reading_lists")
public class ReadingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean isPrivate;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "readingList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReadingListItem> items = new ArrayList<>();
}
