package andras.ilonczai.wpbackend.entities;

import andras.ilonczai.wpbackend.entities.enums.ReadingListEnum;
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

    @Column(name = "order_index", nullable = false)
    private int orderIndex;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "readingList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReadingListItem> items = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ReadingListEnum readingListType;
}
