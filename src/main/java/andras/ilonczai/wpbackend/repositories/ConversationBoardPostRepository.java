package andras.ilonczai.wpbackend.repositories;

import andras.ilonczai.wpbackend.entities.ConversationBoardPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationBoardPostRepository extends JpaRepository<ConversationBoardPost, Long> {
    List<ConversationBoardPost> findByOwnerIdAndParentIsNullOrderByPostedAtDesc(Long ownerId);
}
