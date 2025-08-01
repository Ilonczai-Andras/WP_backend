package andras.ilonczai.wpbackend.repositories;

import andras.ilonczai.wpbackend.entities.ReadingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingListRepository extends JpaRepository<ReadingList, Long> {
    List<ReadingList> findByOwner_Id(Long ownerId);
}
