package andras.ilonczai.wpbackend.repositories;

import andras.ilonczai.wpbackend.entities.ReadingListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingListItemRepository extends JpaRepository<ReadingListItem, Long> {
    List<ReadingListItem> findByReadingList_Id(Long listId);
}
